/*
 * NotifyerMock.java
 * 
 * Created on 13.08.2007, 18:00:31
 * 
 */

package tournoire;

/**
 *
 * @author rca
 */
public class NotifyerMock implements Notifyer
{

    public NotifyerMock() 
    {
    }
    
    public boolean answerProceed;
    public boolean wasAsked = false;
    

    public boolean askProceed(String message, String description)
    {
        wasAsked = true;
        return answerProceed;
    }
    

}
