/*
 * BackgroundDaoTest.java
 * JUnit 4.x based test
 *
 * Created on 12. August 2007, 14:16
 */

package tournoire.backgrounddata;

import tournoire.Player;
import tournoire.District;
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
        fixation = new BackGroundFixation();
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
        fixation.cleanUp();
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
            BackgroundDao instance = new BackgroundDao();
            String expResult = "/home/rca/Tournoire/.backgrounddata/HintergrundDatenbank";
            String result = instance.getDatabaseLocation();
            assertEquals(expResult, result);
        } 
        catch (AppException ex)
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
            System.out.println("getDatabaseUrl");
            tournoire.backgrounddata.BackgroundDao instance = new tournoire.backgrounddata.BackgroundDao();
            String expResult = "jdbc:derby:HintergrundDatenbank";
            String result = instance.getDatabaseUrl();
            assertEquals(expResult, result);
        } 
        catch (AppException ex)
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
            BackgroundDao instance = fixation.createBackgroundDao();
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
            BackgroundDao instance = fixation.createBackgroundDao();
            
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
            assertEquals("SC Vilshofen", result.get(0).getClub().getName());
            assertEquals("SF Fürth", result.get(1).getClub().getName());
            
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
    
    
    private static BackGroundFixation fixation;
    
}
