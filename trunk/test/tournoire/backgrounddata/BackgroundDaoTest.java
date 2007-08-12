/*
 * BackgroundDaoTest.java
 * JUnit 4.x based test
 *
 * Created on 12. August 2007, 14:16
 */

package tournoire.backgrounddata;

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
        
        System.out.println(tmpSpielerCsv.getAbsolutePath() + " erzeugt");
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
        //tmpSpielerCsv.delete();
        tmpVerbaendeCsv.delete();
        tmpVereineCsv.delete();
        System.out.println(tmpSpielerCsv.getAbsolutePath() + " grelöscht");
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
    public void getVerbaende()
    {
        try
        {
            java.lang.System.out.println("getVerbaende");
            tournoire.backgrounddata.BackgroundDao instance = new tournoire.backgrounddata.BackgroundDao();
            instance.clear();
            instance.loadDsbCsvFiles(tempDir);
            List<DwzVerband> result = instance.getVerbaende();
            assertEquals(5, result.size());
            boolean found = false;
            for(DwzVerband verband : result)
            {
                if(verband.getVerband().equals("220"))
                {
                    found = true;
                    assertEquals("München", verband.getVerbandname());
                    assertEquals("200", verband.getUebergeordnet());
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
    } /* Test of getVerbaende method, of class BackgroundDao. */
        
    
    @Test
    public void getSpieler()
    {
        try
        {
            java.lang.System.out.println("getSpieler");
            tournoire.backgrounddata.BackgroundDao instance = new tournoire.backgrounddata.BackgroundDao();
            instance.clear();
            instance.loadDsbCsvFiles(tempDir);
            
            //no player found
            List<DwzSpieler> result = instance.getSpieler("xxx", 0);
            assertEquals(0, result.size());
            
            //all players
            result = instance.getSpieler("", 0);
            assertEquals(5, result.size());
            //subset
            result = instance.getSpieler("", 3);
            assertEquals(3, result.size());
            
            
            //pattern search
            result = instance.getSpieler("Zwack", 0);
            assertEquals(2, result.size());
            result = instance.getSpieler("zwack", 0);
            assertEquals(2, result.size());
        } 
        catch (AppException ex)
        {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            fail("Exception occurred");
        }
    } /* Test of getSpieler method, of class BackgroundDao. */
    
    
    private static String tempDir;
    private static File tmpSpielerCsv;
    private static File tmpVerbaendeCsv;
    private static File tmpVereineCsv;
}
