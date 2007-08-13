/*
 * ErrorNotifyer.java
 * 
 * Created on 13.08.2007, 17:31:35
 * 
 */

package tournoire;

/**
 * Interface for notifying the user with a message
 * 
 * @author rca
 */
public interface Notifyer 
{
    /**
     * Ask the user whether to proceed with the current action
     * 
     * @param message Message informing the user about teh options
     * @param description Further descriptions
     * @return true: proceed, false: do not proceed
     */
    boolean askProceed(String message, String description);
}
