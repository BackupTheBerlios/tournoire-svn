/*
 * SearchFieldModelTest.java
 * JUnit 4.x based test
 *
 * Created on 13. August 2007, 15:52
 */

package tournoire.backgrounddata;

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
public class SearchFieldModelTest
{
    //Mock class, just ignoring detailModel
    class FoundPlayersModelMock extends FoundPlayersModel
    {
        
        public FoundPlayersModelMock(DetailModel detailModel) 
        {
            super(null);
        }
        
        @Override
        public void onSelectionChanged(int idx)
        {
            selectedIdx = idx;
        }
    }
    
    public SearchFieldModelTest()
    {
    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
        fixation = new BackGroundFixation();
        backgroundDao = fixation.createBackgroundDao();
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
        fixation.cleanUp();
    }

    @Before
    public void setUp() throws Exception
    {
        foundPlayerModelMock = new FoundPlayersModelMock(null);
        foundPlayerModelMock.setMaxDisplayed(100);
        instance = new SearchFieldModel(backgroundDao,
                foundPlayerModelMock); 
    }

    @After
    public void tearDown() throws Exception
    {
    }


    @Test
    public void setTextEmpty()
    {
        System.out.println("setTextEmpty");
        String text = "";
        instance.setText(text);
        assertNotNull(foundPlayerModelMock.getPlayers());
        assertNull(foundPlayerModelMock.getSelectedPlayer());
        assertEquals(0, foundPlayerModelMock.getPlayers().size());
    } /* Test of setText method, of class SearchFieldModel. */
    
    @Test
    public void setText_result2()
    {
        System.out.println("setText_result2");
        String text = "zwa";
        instance.setText(text);
        assertNotNull(foundPlayerModelMock.getPlayers());
        assertNotNull(foundPlayerModelMock.getSelectedPlayer());
        assertEquals(2, foundPlayerModelMock.getPlayers().size());
        assertEquals("Zwack,Hans", 
                foundPlayerModelMock.getSelectedPlayer().getName());
    } /* Test of setText method, of class SearchFieldModel. */
    
    @Test
    public void setText_result1()
    {
        System.out.println("setText_result1");
        String text = "Vol";
        instance.setText(text);
        assertNotNull(foundPlayerModelMock.getPlayers());
        assertNotNull(foundPlayerModelMock.getSelectedPlayer());
        assertEquals(1, foundPlayerModelMock.getPlayers().size());
        assertEquals("Volokitin,Andrej", 
                foundPlayerModelMock.getSelectedPlayer().getName());
    } /* Test of setText method, of class SearchFieldModel. */
    
    @Test
    public void setText_result2_1_0()
    {
        System.out.println("setText_result2_1");
        String text1 = "zwack,hans";
        String text2 = "zwack,hanse";
        String text3 = "zwack,hansel";
        instance.setText(text1);
        assertNotNull(foundPlayerModelMock.getPlayers());
        assertNotNull(foundPlayerModelMock.getSelectedPlayer());
        assertEquals(2, foundPlayerModelMock.getPlayers().size());
        assertEquals("Zwack,Hans", 
                foundPlayerModelMock.getSelectedPlayer().getName());
        
        instance.setText(text2);
        assertEquals(1, foundPlayerModelMock.getPlayers().size());
        assertEquals("Zwack,Hansemann", 
                foundPlayerModelMock.getSelectedPlayer().getName());
        
        instance.setText(text3);
        assertEquals(0, foundPlayerModelMock.getPlayers().size());
        assertNull(foundPlayerModelMock.getSelectedPlayer());
        
    } /* Test of setText method, of class SearchFieldModel. */

    
    private static BackGroundFixation fixation;
    private FoundPlayersModelMock foundPlayerModelMock;
    private SearchFieldModel instance;
    private static BackgroundDao backgroundDao;
}
