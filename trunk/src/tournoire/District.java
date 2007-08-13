/*
 * DwzVerbaende.java
 * 
 * Created on 12.08.2007, 02:08:04
 * 
 */

package tournoire;


/**
 *
 * @author rca
 */
public class District
{
    private String id;
    private char lv;
    private String parent;
    private String name;

    public District() 
    {
    }

    public District(String id)
    {
        this.id = id;
    }

    public District(String id, char lv, String parent, String name)
    {
        this.id = id;
        this.lv = lv;
        this.parent = parent;
        this.name = name;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public char getLv()
    {
        return lv;
    }

    public void setLv(char lv)
    {
        this.lv = lv;
    }

    public String getParentId()
    {
        return parent;
    }

    public void setParentId(String uebergeordnet)
    {
        this.parent = uebergeordnet;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String verbandname)
    {
        this.name = verbandname;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {

        if (!(object instanceof District))
        {
            return false;
        }
        District other = (District) object;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return getName();
    }

}
