/*
 * BackgroundDao.java
 *
 * Created on 12.08.2007, 04:31:53
 *
 */

package tournoire.backgrounddata;

import tournoire.Player;
import tournoire.District;
import com.Ostermiller.util.CSVParser;
import com.Ostermiller.util.CSVPrinter;
import com.Ostermiller.util.LabeledCSVParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import tournoire.AppException;
import tournoire.Club;

/**
 *Data Access Object for accessing the background data.
 * The underlying databae corresponds to that from the DSB
 * 
 * @author rca
 */
public class BackgroundDao
{

    //Public Methods
    
    //Constructors
    
    /**
     *
     * @throws tournoire.AppException
     */
    public BackgroundDao() throws AppException
    {
        this("HintergrundDatenbank");
    }

    /**
     *
     * @param backgroundDataName
     * @throws tournoire.AppException
     */
    public BackgroundDao(String backgroundDataName) throws AppException
    {
        try
        {
            isConnected = false;
            dbName = backgroundDataName;
            setDBSystemDir();
            dbProperties = loadDBProperties();
            String driverName = dbProperties.getProperty("derby.driver");
            loadDatabaseDriver(driverName);
            if (!dbExists())
            {
                createDatabase();
            }
        } catch (Exception ex)
        {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            throw new AppException("BackgroundDao.CouldNotCreate");
        }
    }

    ////////////////////////////////////////////////////////////////////////////////

    /**
     * Gets the path to the underlying database
     *
     * @return
     */
    public String getDatabaseLocation()
    {
        String dbLocation = System.getProperty("derby.system.home") + "/" + dbName;
        return dbLocation;
    }

    /**
     * Gets the URL for the underlying database
     *
     * @return
     */
    public String getDatabaseUrl()
    {
        String dbUrl = dbProperties.getProperty("derby.url") + dbName;
        return dbUrl;
    }
    
    //database handling
    
    /**
     * Connect to the database
     *  
     * @throws tournoire.AppException 
     */
    public void connect()
    throws AppException
    {
        if(isConnected)
            return;
       
        String dbUrl = getDatabaseUrl();
        try 
        {
            dbConnection = DriverManager.getConnection(dbUrl, dbProperties);
            /*
            DatabaseMetaData metaData = dbConnection.getMetaData();
            String[] types = {"TABLE"};
            ResultSet tableNames = metaData.getTables(null, "%", "%", types);
            while(tableNames.next())
            {
                System.out.println(tableNames.getString("TABLE_NAME"));
            }
             * */
            isConnected = dbConnection != null;
            if(isConnected)
            {
                psSelectVerbaende = dbConnection.prepareStatement(stmtSelectVerbaende);
                psSelectSpieler = dbConnection.prepareStatement(stmtSelectSpieler);
                psSelectSpielerVerband = dbConnection.prepareStatement(stmtSelectSpielerVerband);
                psSelectSpielerVerein = dbConnection.prepareStatement(stmtSelectSpielerVerein);
                psSelectClub = dbConnection.prepareStatement(stmtSelectClub);
                psSelectClubsOfDistricts = dbConnection.prepareStatement(stmtSelectClubsOfDistrict);
            }
        }
        catch (SQLException ex) 
        {
            isConnected = false;
            throw new AppException("BackgroundDao.CouldNotConnect", ex);
        }
        if(!isConnected)
        {    
            throw new AppException("BackgroundDao.CouldNotConnect");
        }
        return; 
    }
    
    /**
     * Disconnect from the database
     */
    public void disconnect() 
    {
        if(isConnected) 
        {
            String dbUrl = getDatabaseUrl();
            dbProperties.put("shutdown", "true");
            try 
            {
                DriverManager.getConnection(dbUrl, dbProperties);
            } 
            catch (SQLException ex) 
            {
            }
            isConnected = false;
        }
    }
    
    /**
     * Clear all data in the database
     * 
     * @throws tournoire.AppException 
     */
    public void clear()
    throws AppException 
    {
        connect();
        try
        {
            java.sql.Statement delStatement = dbConnection.createStatement();
            delStatement.execute("DELETE FROM \"APP.DWZ_SPIELER\"");
            delStatement.execute("DELETE FROM \"APP.DWZ_VERBAENDE\"");
            delStatement.execute("DELETE FROM \"APP.DWZ_VEREINE\"");
        } 
        catch (SQLException ex)
        {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            throw new AppException("BackgroundDao.CouldNotClearDatabase");
        }
                
    }


    //Load Data
    /**
     * Load .csv Files from DSB into the DB<br>
     * There have to be three files:<br>
     * <ul>
     * <li>verband.csv</li>
     * <li>vereine.csv</li>
     * <li>spieler.csv</li>
     * </ul>
     *
     * @param directory Directory where the files are located
     * @throws tournoire.AppException 
     */
    public void loadDsbCsvFiles(String directory)
    throws AppException 
    {
        connect();
        loadDsbCsvVerband(directory + "/verband.csv");
        loadDsbCsvVereine(directory + "/vereine.csv");
        loadDsbCsvSpieler(directory + "/spieler.csv");
    }


    //Access Data
    /**
     * Get a list of all districts in the database
     *
     * @return
     * @throws tournoire.AppException 
     */
    public List<District> getDistricts()
    throws AppException 
    {
        List<District> result = new java.util.ArrayList<District>();
        ResultSet results = null;
        try
        {
            results = psSelectVerbaende.executeQuery();
            while(results.next())
            {
                District verband = new District();
                verband.setId(results.getString(1));
                verband.setLv(results.getString(2).charAt(0));
                verband.setParentId(results.getString(3));
                verband.setName(results.getString(4));
                
                result.add(verband);
            }
        } 
        catch (SQLException ex)
        {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            throw new AppException("BackgroundDao.CouldNotGetVerbaende");
        }
        return result;
    }
    
    /**
     * Get the players based on the beginning of their name
     * 
     * @param nameBegin The string the name has to start with
     * @param count Maximum number of entries to be returned
     * @return 
     * @throws tournoire.AppException 
     */
    public List<Player> getPlayers(String nameBegin, int count)
    throws AppException 
    {   
        String searchString = getWordBeginPattern(nameBegin);
        try
        {
            psSelectSpieler.setString(1, searchString);
        }
        catch (SQLException ex)
        {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            throw new AppException("BackgroundDao.CouldNotGetSpieler");
        }
        return getPlayers(psSelectSpieler, count);
    }
    
    /**
     * Get the players of a specific district
     * 
     * @param nameBegin The string the name has to start with
     * @param verband Id of the district
     * @param count Maximum number of entries to be returned
     * @return 
     * @throws tournoire.AppException 
     */
    public List<Player> getPlayeresOfDistrict(String nameBegin, String verband, int count)
    throws AppException 
    {   
        String sVerband = null;
        if(!isNullOrEmpty(verband))
        {
            sVerband = getWordBeginPattern(verband);
        }
        return getPlayers(nameBegin, sVerband, psSelectSpielerVerband, count);
    }
    
    /**
     * Get the players of a specific club
     * 
     * @param nameBegin The string the name has to start with 
     * @param zps Id of the club 
     * @param count Maximum number of entries to be returned 
     * @return 
     * @throws tournoire.AppException 
     */
    public List<Player> getPlayersOfClub(String nameBegin, String zps, int count)
    throws AppException 
    {   
        return getPlayers(nameBegin, zps, psSelectSpielerVerein, count);
    }

    
    
    

    ////////////////////////////////////////////////////////////////////////////////

    //Private Methods
    //Configuration
    private void setDBSystemDir()
    {
        // decide on the db system directory
        String userHomeDir = System.getProperty("user.home", ".");
        String systemDir = userHomeDir + "/Tournoire/.backgrounddata";
        System.setProperty("derby.system.home", systemDir);

        // create the db system directory
        File fileSystemDir = new File(systemDir);
        fileSystemDir.mkdir();
    }

    private Properties loadDBProperties() throws IOException
    {
        InputStream dbPropInputStream = null;
        dbPropInputStream = BackgroundDao.class.getResourceAsStream("BackgroundDao.properties");
        dbProperties = new Properties();
        try
        {
            dbProperties.load(dbPropInputStream);
        } catch (IOException ex)
        {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            throw ex;
        }
        return dbProperties;
    }


    private String loadStmt(String fileName) throws IOException
    {
        String result = null;
        InputStream stmInputStream = null;
        BufferedReader stmReader = null;
        try
        {
            stmInputStream = BackgroundDao.class.getResourceAsStream(fileName);
            stmReader = new BufferedReader(new InputStreamReader(stmInputStream));
            StringWriter sw = new StringWriter();
            PrintWriter stmWriter = new PrintWriter(sw);
            String l;
            while ((l = stmReader.readLine()) != null)
            {
                stmWriter.println(l);
            }
            result = sw.toString();
        } catch (IOException ex)
        {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            throw ex;
        }

        finally
        {
            if (stmReader != null)
            {
                try
                {
                    stmReader.close();
                } catch (IOException ex)
                {
                    Logger.getLogger("global").log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }

    ////////////////////////////////////////////////////////////////////////////////

    //Initialization of the DB
    private void loadDatabaseDriver(String driverName) throws ClassNotFoundException
    {
        // load Derby driver
        Class.forName(driverName);
    }

    private boolean dbExists()
    {
        boolean bExists = false;
        String dbLocation = getDatabaseLocation();
        File dbFileDir = new File(dbLocation);
        if (dbFileDir.exists())
        {
            bExists = true;
        }
        return bExists;
    }



    private boolean createTables(Connection dbConnection) throws SQLException, IOException
    {
        boolean bCreatedTables = false;
        Statement statement = null;
        try
        {
            statement = dbConnection.createStatement();
            statement.execute(loadStmt("CreateVerbaende.sql"));
            statement.execute(loadStmt("CreateVereine.sql"));
            statement.execute(loadStmt("CreateSpieler.sql"));
            bCreatedTables = true;
        } catch (SQLException ex)
        {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            throw ex;
        }

        return bCreatedTables;
    }

    private boolean createDatabase() throws SQLException, IOException
    {
        boolean bCreated = false;
        dbConnection = null;

        String dbUrl = getDatabaseUrl();
        dbProperties.put("create", "true");
        //dbProperties.put("create", "false");

        try
        {
            dbConnection = DriverManager.getConnection(dbUrl, dbProperties);
            bCreated = createTables(dbConnection);
        } catch (SQLException ex)
        {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            throw ex;
        }
        dbProperties.remove("create");
        return bCreated;
    }

    ////////////////////////////////////////////////////////////////////////////////
    //Loading Data
    
    private void loadDsbCsv(String fileName, String tableName)
    throws AppException
    {
     
        //Data from DSB are not nice for bulk import, crate a temp. file
        //with useful columns and read this bulk import this file
        File tempFile = null;
        PrintWriter writer = null;
        try
        {
            tempFile = File.createTempFile(tableName, "", null);
            writer = new PrintWriter(tempFile);
        }
        catch (IOException ex)
        {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            throw new AppException("BackgroundDao.CouldNotReadFile");
        }
        
        if(tableName.equals("APP.DWZ_SPIELER"))
        {
            BufferedReader reader = null;
            CSVPrinter csvp = null;
            try
            {
                csvp = new CSVPrinter(writer);
                reader = new BufferedReader(new FileReader(fileName));
                LabeledCSVParser lcsvp = new LabeledCSVParser(
                        new CSVParser(reader));
                String[] values;
                while((values = lcsvp.getLine()) != null)
                {
                    String[] newVals = new String[values.length+1];
                    for(int i=0; i<values.length; ++i)
                    {
                        newVals[i] = values[i];
                    }
                    newVals[values.length] = values[3].toLowerCase();
                    
                    if(isNullOrEmpty(newVals[8]))
                        newVals[8] = "0";
                    if(isNullOrEmpty(newVals[9]))
                        newVals[9] = "0";
                    if(isNullOrEmpty(newVals[10]))
                        newVals[10] = "0";
                    
                    csvp.writeln(newVals);
                }
            } 
            catch (IOException ex)
            {
                Logger.getLogger("global").log(Level.SEVERE, null, ex);
                throw new AppException("BackgroundDao.CouldNotReadFile");
            }
            finally
            {
                try
                {
                    if(reader != null)
                    {
                        reader.close();
                        reader = null;
                    }
                    if(csvp != null)
                    {
                        csvp.close();
                        csvp = null;
                    }
                     
                }
                catch(Exception ex)
                {                
                    Logger.getLogger("global").log(Level.WARNING, null, ex);
                }
            }
        }
        else
        {
        
            //copy file minus first line to temporary file
            BufferedReader reader = null;
            try
            {
                reader = new BufferedReader(new FileReader(fileName));
                String l = reader.readLine();
                if(l==null)
                    return;
                while((l=reader.readLine()) != null)
                {
                    writer.println(l);
                }
                reader.close();
                reader = null;
                writer.close();
                writer = null;
            } 
            catch (IOException ex)
            {
                Logger.getLogger("global").log(Level.SEVERE, null, ex);
                throw new AppException("BackgroundDao.CouldNotReadFile");
            }
            finally
            {
                try
                {
                    if(reader != null)
                    {
                        reader.close();
                    }
                    if(writer != null)
                    {
                        writer.close();
                    }
                }
                catch(Exception ex)
                {                
                    Logger.getLogger("global").log(Level.WARNING, null, ex);
                }
            } 
        }        
            
        //read content of temporary file into DB
        PreparedStatement ps = null;
        try
        {
            ps = 
               dbConnection.prepareStatement("CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE (?,?,?,?,?,?,?)");
            ps.setString(1, null);
            ps.setString(2, tableName);
            ps.setString(3, tempFile.getAbsolutePath());
            ps.setString(4, null);
            ps.setString(5, null);
            ps.setString(6, null);
            ps.setInt(7, 0);
            ps.execute();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            throw new AppException("BackgroundDao.CouldNotReadData");
        }
        finally
        {
            tempFile.delete();
        }
    }
    
    private void loadDsbCsvSpieler(String fileName)
    throws AppException
    {
        loadDsbCsv(fileName, "APP.DWZ_SPIELER");
    }

    private void loadDsbCsvVerband(String fileName)
    throws AppException 
    {
        loadDsbCsv(fileName, "APP.DWZ_VERBAENDE");
    }

    private void loadDsbCsvVereine(String fileName)
    throws AppException 
    {
        loadDsbCsv(fileName, "APP.DWZ_VEREINE");
    }
   
    
    ////////////////////////////////////////////////////////////////////////////////
    
    //retrieving Data
    
    
    private Club getClub(String id)
    {
        Club res = Club.getClub(id);
        if(res == null)
        {
            try
            {
                psSelectClub.clearParameters();
                psSelectClub.setString(1, id);
                java.sql.ResultSet resultSet = psSelectClub.executeQuery();
                if(resultSet.next())
                {
                    res = Club.createClub(id,
                            resultSet.getString("LV"),
                            resultSet.getString("Verband"),
                            resultSet.getString("Vereinname"));
                }
                
            }
            catch (SQLException ex)
            {
                Logger.getLogger("global").log(Level.SEVERE, null, ex);
            }
        }
        
        return res;
    }
    
    private List<Player> getPlayers(PreparedStatement ps, int count)
    throws AppException 
    {
        List<Player> result = new ArrayList<Player>();
        ResultSet resultSet = null;
        try
        {
            ps.setMaxRows(count);
            resultSet = ps.executeQuery();
            while(resultSet.next())
            {
                Player player = new Player();
                player.setNRatingIndex(resultSet.getShort("DWZ_Index") );
                player.setNRating(resultSet.getShort("DWZ"));
                player.setFideElo(resultSet.getShort("FIDE_Elo"));
                player.setFideCountry(resultSet.getString("FIDE_Land"));
                player.setFideTitle(resultSet.getString("FIDE_Titel"));
                player.setFideId(resultSet.getString("FIDE_ID"));
                player.setYearOfBirth(resultSet.getShort("Geburtsjahr"));
                player.setSex(resultSet.getString("Geschlecht"));
                player.setActivity(resultSet.getString("Spielberechtigung"));
                player.setName(resultSet.getString("Spielername"));               
                player.setStatus(resultSet.getString("Status"));
                player.setClub(getClub(resultSet.getString("ZPS")));
                
                result.add(player);
            }
        } 
        catch (SQLException ex)
        {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            throw new AppException("BackgroundDao.CouldNotGetSpieler");
        }
        
        return result;        
    }
       
    private List<Player> getPlayers(String nameBegin, String zps, PreparedStatement ps, int count)
    throws AppException 
    {   
        if(isNullOrEmpty(zps))
        {
            return getPlayers(nameBegin, count);
        }
        
        String searchString = getWordBeginPattern(nameBegin);
        try
        {
            ps.setString(1, searchString);
            ps.setString(2, zps);
        }
        catch (SQLException ex)
        {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            throw new AppException("BackgroundDao.CouldNotGetSpieler");
        }
        return getPlayers(ps, count);
    }
    
    private String getWordBeginPattern(String nameBegin)
    {
        java.lang.String searchString;
        if (isNullOrEmpty(nameBegin))
        {
            searchString = "%";
        } else
        {
            searchString = nameBegin.toLowerCase() + "%";
        }
        return searchString;
    }
    
    ////////////////////////////////////////////////////////////////////////////////  
    
    private boolean isNullOrEmpty(String s)
    {
        return s==null || s.length() == 0;
    }
    ////////////////////////////////////////////////////////////////////////////////

    //Private Members
    
    //DB parameters
    private String dbName;
    private Connection dbConnection;
    private Properties dbProperties;
    private boolean isConnected;
    
    //prepared statements
    private PreparedStatement psSelectVerbaende;
    private PreparedStatement psSelectSpieler;
    private PreparedStatement psSelectSpielerVerband;
    private PreparedStatement psSelectSpielerVerein;
    private PreparedStatement psSelectClub;
    private PreparedStatement psSelectClubsOfDistricts;
    
    //SQL-expressions
    private static final String stmtSelectVerbaende = 
            "SELECT * FROM \"APP.DWZ_VERBAENDE\"";
    private static final String stmtSelectClub = 
            "SELECT * FROM \"APP.DWZ_VEREINE\" \n" +
            "WHERE \"ZPS\" = ?";
    private static final String stmtSelectClubsOfDistrict = 
            "SELECT * FROM \"APP.DWZ_VEREINE\" \n" +
            "WHERE \"Verband\" = ?";
    private static final String stmtSelectSpieler = 
            "SELECT * FROM \"APP.DWZ_SPIELER\" WHERE \"SpielernameL\" LIKE ?";
    private static final String stmtSelectSpielerVerband = 
            "SELECT * FROM \"APP.DWZ_SPIELER\" \n" + "" +
            "WHERE \"SpielernameL\" LIKE ? AND \"ZPS\" LIKE ?";
    private static final String stmtSelectSpielerVerein = 
            "SELECT * FROM \"APP.DWZ_SPIELER\" \n" + 
            "WHERE \"SpielernameL\" LIKE ? AND \"ZPS\" = ?";
    
    ////////////////////////////////////////////////////////////////////////////////
}
