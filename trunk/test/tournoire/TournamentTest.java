/*
 * TournamentTest.java
 * JUnit 4.x based test
 *
 * Created on 13. August 2007, 17:59
 */

package tournoire;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rca
 */
public class TournamentTest
{
    
    public TournamentTest()
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
        notifyer = new NotifyerMock();
        instance = new Tournament();
        instance.setErrorNotifyer(notifyer);
        players = new ArrayList<Player>();
        Player p;
        p = new Player();
        p.setName("P1");
        players.add(p);
        p = new Player();
        p.setName("P2");
        players.add(p);
    }

    @After
    public void tearDown() throws Exception
    {
    }


    @Test
    public void addParticipant2()
    {
        System.out.println("addParticipant2");
        Participant participant = players.get(0);
        instance.addParticipant(participant);
        assertEquals(1, instance.getParticipants().size());
        instance.addParticipant(players.get(1));
        assertEquals(2, instance.getParticipants().size());
    } /* Test of addParticipant method, of class Tournament. */
    
    @Test
    public void addParticipantNoDouble()
    {
        System.out.println("addParticipantNoDouble");
        Participant participant = players.get(0);
        instance.addParticipant(participant);
        assertEquals(1, instance.getParticipants().size());
        
        notifyer.answerProceed = false;
        instance.addParticipant(participant);
        assertEquals(1, instance.getParticipants().size());
        assertTrue(notifyer.wasAsked);
        
    } /* Test of addParticipant method, of class Tournament. */
    
    @Test
    public void addParticipantDouble()
    {
        System.out.println("addParticipantDouble");
        Participant participant = players.get(0);
        instance.addParticipant(participant);
        assertEquals(1, instance.getParticipants().size());
        
        notifyer.answerProceed = true;
        instance.addParticipant(participant);
        assertEquals(2, instance.getParticipants().size());
        assertTrue(notifyer.wasAsked);
        
    } /* Test of addParticipant method, of class Tournament. */
    
    private Tournament instance;
    private NotifyerMock notifyer;
    private ArrayList<Player> players;
    
}
