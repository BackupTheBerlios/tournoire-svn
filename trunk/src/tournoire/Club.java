/*
 * Club.java
 * 
 * Created on 11.08.2007, 23:13:23
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tournoire;

/**
 *
 * @author rca
 */
public class Club 
{

    public Club() 
    {
        
    }
    

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    
    private String name;
    private String id;

}
