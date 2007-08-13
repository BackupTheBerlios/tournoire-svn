/*
 * FoundPlayersModel.java
 * 
 * Created on 13.08.2007, 12:53:38
 * 
 */

package tournoire.backgrounddata;

import java.util.ArrayList;
import java.util.List;
import tournoire.Player;

/**
 * Represents the list of found players for a search
 *
 * @author rca
 */
public class FoundPlayersModel 
{

    public FoundPlayersModel(DetailModel detailModel) 
    {
        this.detailModel = detailModel;
        players = new ArrayList<Player>();
    }

    public List<Player> getPlayers()
    {
        return players;
    }

    public void setPlayers(List<Player> players)
    {
        this.players = players;
        if(players != null && players.size() > 0)
            onSelectionChanged(0);
        else
            onSelectionChanged(-1);
            
    }

    public int getMaxDisplayed()
    {
        return maxDisplayed;
    }

    public void setMaxDisplayed(int maxDisplayed)
    {
        this.maxDisplayed = maxDisplayed;
    }
    
    public Player getSelectedPlayer()
    {
        if(selectedIdx >= 0)
        {
            return players.get(selectedIdx);
        }
        else
        {
            return null;
        }
    }
    
    public void onSelectionChanged(int idx)
    {
        selectedIdx = idx;
        detailModel.setPlayer(getSelectedPlayer());
    }
    
    private DetailModel detailModel;
    private List<Player> players;
    private int maxDisplayed;
    protected int selectedIdx = -1;

}
