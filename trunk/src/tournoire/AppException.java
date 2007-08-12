/*
 * AppException.java
 * 
 * Created on 12.08.2007, 12:55:20
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tournoire;

/**
 *
 * @author rca
 */
public class AppException extends Exception
{

    public AppException() 
    {
    }
    
    public AppException(String messageKey) 
    {
        msgKey = messageKey;
    }
    
    public AppException(String messageKey, Throwable cause) 
    {
        super(cause);
        msgKey = messageKey;
    }
    
    //@TODO: retrieve localized message
    @Override
    public String getMessage()
    {
        return msgKey;
    }

    
    private String msgKey;

}
