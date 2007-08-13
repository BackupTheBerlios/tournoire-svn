/*
 * SearchFieldModel.java
 * 
 * Created on 13.08.2007, 12:50:57
 * 
 */

package tournoire.backgrounddata;

import java.util.logging.Level;
import java.util.logging.Logger;
import tournoire.AppException;

/**
 * Represents the search attributes in the search dialog
 *
 * @author rca
 */
public class SearchFieldModel 
{

    public SearchFieldModel(BackgroundDao backgrounDao, FoundPlayersModel foundPlayers) 
    {
        this.backGroundDao = backgrounDao;
        this.foundPlayers = foundPlayers;
    }    

    /**
     * Get the value of the text property
     * 
     * @return 
     */
    public String getText()
    {
        return text;
    }

    /**
     * Sets the text-property of this object.
     * If the length of the string is at least 3, the search method will
     * be called automatically
     * 
     * @param text 
     */
    public void setText(String text)
    {
        try
        {
            this.text = text;
            if (text != null && text.length() >= minLength)
            {
                search();
            }
        } 
        catch (AppException ex)
        {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Search for the player by name with content of text.
     * The setPlayers of the FoundPlayersModel associated with this object will
     * be called with the result of this search 
     * 
     * @throws tournoire.AppException 
     */
    public void search()
    throws AppException 
    {        
        foundPlayers.setPlayers(
                backGroundDao.getPlayers(text,foundPlayers.getMaxDisplayed())
              );
    }
    
    private String text;
    private FoundPlayersModel foundPlayers;
    private final short minLength = 3;
    private BackgroundDao backGroundDao;

}
