/*
 * FoundPlayersModelTest.java
 * JUnit 4.x based test
 *
 * Created on 13. August 2007, 17:06
 */

package tournoire.backgrounddata;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tournoire.Player;

/**
 *
 * @author rca
 */
public class FoundPlayersModelTest
{
    
    public FoundPlayersModelTest()
    {
    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
    }

    @Before
    public void setUp() throws Exception
    {
        detailModel = new DetailModel(null);
        instance = new FoundPlayersModel(detailModel);        
    }

    @After
    public void tearDown() throws Exception
    {
    }


    @Test
    public void setPlayersNull()
    {
        System.out.println("setPlayersNull");
        List<Player> players = null;
        instance.setPlayers(players);
        assertNull(detailModel.getPlayer());
    } /* Test of setPlayers method, of class FoundPlayersModel. */
    
    @Test
    public void setPlayers2()
    {
        System.out.println("setPlayers2");
        List<Player> players = new ArrayList<Player>();
        Player p = new Player();
        p.setName("P1");
        players.add(p);
        p = new Player();
        p.setName("P2");
        players.add(p);
        instance.setPlayers(players);
        assertEquals("P1", detailModel.getPlayer().getName());
    } /* Test of setPlayers method, of class FoundPlayersModel. */
    
    @Test
    public void setPlayers2_0()
    {
        System.out.println("setPlayers2_0");
        List<Player> players = new ArrayList<Player>();
        Player p = new Player();
        p.setName("P1");
        players.add(p);
        p = new Player();
        p.setName("P2");
        players.add(p);
        instance.setPlayers(players);
        assertEquals("P1", detailModel.getPlayer().getName());
        
        players = new ArrayList<Player>();
        instance.setPlayers(players);
        assertNull(detailModel.getPlayer());
        
    } /* Test of setPlayers method, of class FoundPlayersModel. */


 
    FoundPlayersModel instance;
    DetailModel detailModel;
}
