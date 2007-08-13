/*
 * BackgroundDaoTest.java
 * JUnit 4.x based test
 *
 * Created on 12. August 2007, 14:16
 */

package tournoire.backgrounddata;

import tournoire.Player;
import tournoire.District;
import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tournoire.AppException;

/**
 *
 * @author rca
 */
public class BackgroundDaoTest
{
    
    public BackgroundDaoTest()
    {
    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
        File dummyTemp = File.createTempFile("dummy", "");
        tempDir = dummyTemp.getParent();
        dummyTemp.delete();
        tmpSpielerCsv = new File(tempDir, "spieler.csv");
        tmpVerbaendeCsv = new File(tempDir, "verband.csv");
        tmpVereineCsv = new File(tempDir, "vereine.csv");
        
        PrintWriter writer = null;
        writer = new PrintWriter(tmpSpielerCsv);
        writer.println("\"ZPS\",\"Mgl-Nr\",\"Status\",\"Spielername\",\"Geschlecht\",\"Spielberechtigung\",\"Geburtsjahr\",\"Letzte-Auswertung\",\"DWZ\",\"Index\",\"FIDE-Elo\",\"FIDE-Titel\",\"FIDE-ID\",\"FIDE-Land\"");
        writer.println("\"24421\",\"201\",\"\",\"Volokitin,Andrej\",\"M\",\"A\",\"1986\",\"200715\",\"2698\",\"23\",\"2681\",\"GM\",\"14107090\",\"UKR\"");
        writer.println("\"24421\",\"135\",\"\",\"Bromberger,Stefan\",\"M\",\"D\",\"1982\",\"200730\",\"2461\",\"100\",\"2500\",\"IM\",\"4635248\",\"GER\"");
        writer.println("\"24420\",\"302\",\"P\",\"Bromberger,Stefan\",\"M\",\"D\",\"1982\",\"200730\",\"2461\",\"100\",\"2500\",\"IM\",\"4635248\",\"GER\"");
        writer.println("\"23017\",\"106\",\"\",\"Zwack,Hans\",\"M\",\"D\",\"1990\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"");        
        writer.println("\"23017\",\"107\",\"\",\"Zwick,Herbert\",\"M\",\"D\",\"1980\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"");
        writer.println("\"21206\",\"252\",\"\",\"Zwack,Hansemann\",\"M\",\"D\",\"1958\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"");
       
        writer.close();
        
        writer = new PrintWriter(tmpVerbaendeCsv);
        writer.println("\"Verband\",\"LV\",\"Uebergeordnet\",\"Verbandname\"");
        writer.println("\"200\",\"2\",\"000\",\"Bayerischer Schachbund\"");
        writer.println("\"210\",\"2\",\"200\",\"Bezirk Mittelfranken\"");
        writer.println("\"211\",\"2\",\"210\",\"Kreis Mittelfranken-Mitte\"");
        writer.println("\"212\",\"2\",\"210\",\"Kreis Mittelfranken-Nord\"");
        writer.println("\"220\",\"2\",\"200\",\"München\"");
        writer.close();
        
        writer = new PrintWriter(tmpVereineCsv);
        //writer.println("");
        writer.close();
        
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
        tmpSpielerCsv.delete();
        tmpVerbaendeCsv.delete();
        tmpVereineCsv.delete();
    }

    @Before
    public void setUp() throws Exception
    {
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void getDatabaseLocation()
    {
        try
        {
            java.lang.System.out.println("getDatabaseLocation");
            tournoire.backgrounddata.BackgroundDao instance = new tournoire.backgrounddata.BackgroundDao();
            java.lang.String expResult = "/home/rca/Tournoire/.backgrounddata/HintergrundDatenbank";
            java.lang.String result = instance.getDatabaseLocation();
            assertEquals(expResult, result);
        } catch (AppException ex)
        {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            fail("Exception occurred");
        }
    } /* Test of getDatabaseLocation method, of class BackgroundDao. */

    @Test
    public void getDatabaseUrl()
    {
        try
        {
            java.lang.System.out.println("getDatabaseUrl");
            tournoire.backgrounddata.BackgroundDao instance = new tournoire.backgrounddata.BackgroundDao();
            java.lang.String expResult = "jdbc:derby:HintergrundDatenbank";
            java.lang.String result = instance.getDatabaseUrl();
            assertEquals(expResult, result);
        } catch (AppException ex)
        {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            fail("Exception occurred");
        }
    } /* Test of getDatabaseUrl method, of class BackgroundDao. */


    @Test
    public void getDistricts()
    {
        try
        {
            java.lang.System.out.println("getDisricts");
            tournoire.backgrounddata.BackgroundDao instance = new tournoire.backgrounddata.BackgroundDao();
            instance.clear();
            instance.loadDsbCsvFiles(tempDir);
            List<District> result = instance.getDistricts();
            assertEquals(5, result.size());
            boolean found = false;
            for(District verband : result)
            {
                if(verband.getId().equals("220"))
                {
                    found = true;
                    assertEquals("München", verband.getName());
                    assertEquals("200", verband.getParentId());
                    assertEquals('2', verband.getLv());
                }
            }
            assertTrue(found);
        } 
        catch (AppException ex)
        {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            fail("Exception occurred");
        }
    } /* Test of getDistricts method, of class BackgroundDao. */
        
    
    @Test
    public void getPlayers()
    {
        try
        {
            java.lang.System.out.println("getPlayers");
            tournoire.backgrounddata.BackgroundDao instance = new tournoire.backgrounddata.BackgroundDao();
            instance.clear();
            instance.loadDsbCsvFiles(tempDir);
            
            //no player found
            List<Player> result = instance.getPlayers("xxx", 0);
            assertEquals(0, result.size());
            
            //all players
            result = instance.getPlayers("", 0);
            assertEquals(6, result.size());
            //subset
            result = instance.getPlayers("", 3);
            assertEquals(3, result.size());            
            
            //pattern search
            result = instance.getPlayers("Zwack", 0);
            assertEquals(2, result.size());
            result = instance.getPlayers("zwack", 0);
            assertEquals(2, result.size());
            
            //player of a district
            result = instance.getPlayeresOfDistrict("", "244", 0);
            assertEquals(3, result.size());
            result = instance.getPlayeresOfDistrict("b", "244", 0);
            assertEquals(2, result.size());
            
            //player of a club
            result = instance.getPlayersOfClub("", "23017", 0);
            assertEquals(2, result.size());
            result = instance.getPlayersOfClub("zwi", "23017", 0);
            assertEquals(1, result.size());
            assertEquals("Zwick,Herbert", result.get(0).getName());
        } 
        catch (AppException ex)
        {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            fail("Exception occurred");
        }
    } /* Test of getPlayers* methods, of class BackgroundDao. */
    
    
    private static String tempDir;
    private static File tmpSpielerCsv;
    private static File tmpVerbaendeCsv;
    private static File tmpVereineCsv;
}
