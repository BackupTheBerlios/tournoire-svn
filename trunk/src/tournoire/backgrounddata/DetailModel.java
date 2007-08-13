/*
 * DetailModel.java
 * 
 * Created on 13.08.2007, 13:22:15
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tournoire.backgrounddata;

import tournoire.Player;
import tournoire.Tournament;

/**
 * Represents the detail pane displaying all information about
 * the currently selected player from the list of found players
 *
 * @author rca
 */
public class DetailModel 
{

    public DetailModel(Tournament tournament) 
    {
        this.tournament = tournament;
    }

    public Player getPlayer()
    {
        return player;
    }

    public void setPlayer(Player player)
    {
        this.player = player;
    }
    
    public void onAccept()
    {
        if(player != null)
            tournament.getParticipants().add(player);
    }
    
    
    private Player player;
    private Tournament tournament;

}
