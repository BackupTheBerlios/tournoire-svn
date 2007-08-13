/*
 * DwzVereine.java
 * 
 * Created on 12.08.2007, 02:07:57
 * 
 */

package tournoire;

import java.util.HashMap;

/**
 *
 * @author rca
 */
public class Club
{
    public static Club createClub(String id, String lv, String district, String name)
    {
        Club club = new Club(id, lv, district, name);
        
        clubs.put(id, club);
        return club;
    }
    
    public static Club getClub(String id)
    {
        return clubs.get(id);
    }

    private static HashMap<String, Club> clubs = new HashMap<String, Club>();
    

    private Club(String id, String lv, String district, String name)
    {
        this.id = id;
        this.lv = lv;
        this.district = district;
        this.name = name;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String zps)
    {
        this.id = zps;
    }

    public String getLv()
    {
        return lv;
    }

    public void setLv(String lv)
    {
        this.lv = lv;
    }

    public String getDistrict()
    {
        return district;
    }

    public void setDistrict(String district)
    {
        this.district = district;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String vereinname)
    {
        this.name = vereinname;
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
if (!(object instanceof Club))
        {
            return false;
        }
        Club other = (Club) object;
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
    
    
    private String id;
    private String lv;
    private String district;
    private String name;

}
