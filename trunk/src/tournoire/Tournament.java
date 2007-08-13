/*
 * Tournament.java
 * 
 * Created on 13.08.2007, 12:46:23
 * 
 */

package tournoire;

import java.util.ArrayList;
import java.util.List;

/**
 * The central class representing the tournament on which the program works
 * 
 * @author rca
 */
public class Tournament 
{

    public Tournament() 
    {
    }

    public Notifyer getErrorNotifyer()
    {
        return notifyer;
    }

    public void setErrorNotifyer(Notifyer errorNotifyer)
    {
        this.notifyer = errorNotifyer;
    }
    
    
    
    /**
     * The list of participants in this tournament
     * 
     * @return 
     */
    public List<Participant> getParticipants()
    {
        return participants;
    }
    
    /**
     * Method to add a pariticipant to the tournament.
     * Has to be called instead of getParticipants().add()
     * 
     * @param participant 
     */
    public void addParticipant(Participant participant)
    {
        if(notifyer != null && checkDublicateName(participant) 
                && !notifyer.askProceed("", null))
        {
            return;
        }
        participants.add(participant);
    }
    
    private boolean checkDublicateName(Participant participant)
    {
        String name = participant.getName();
        
        for(Participant p : participants)
        {
            if(p.getName().equals(name))
            {
                return true;
            }
        }
        return false;
    }
    
    private List<Participant> participants = new ArrayList<Participant>();
    private Notifyer notifyer;


}
