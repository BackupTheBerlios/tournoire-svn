/*
 * DwzVereine.java
 * 
 * Created on 12.08.2007, 02:07:57
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tournoire.backgrounddata;


/**
 *
 * @author rca
 */
public class DwzVerein
{
    
    private String zps;
    private char lv;
    private String verband;
    private String vereinname;

    public DwzVerein() 
    {
    }

    public DwzVerein(String zps)
    {
        this.zps = zps;
    }

    public DwzVerein(String zps, char lv, String verband, String vereinname)
    {
        this.zps = zps;
        this.lv = lv;
        this.verband = verband;
        this.vereinname = vereinname;
    }

    public String getZps()
    {
        return zps;
    }

    public void setZps(String zps)
    {
        this.zps = zps;
    }

    public char getLv()
    {
        return lv;
    }

    public void setLv(char lv)
    {
        this.lv = lv;
    }

    public String getVerband()
    {
        return verband;
    }

    public void setVerband(String verband)
    {
        this.verband = verband;
    }

    public String getVereinname()
    {
        return vereinname;
    }

    public void setVereinname(String vereinname)
    {
        this.vereinname = vereinname;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (zps != null ? zps.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
if (!(object instanceof DwzVerein))
        {
            return false;
        }
        DwzVerein other = (DwzVerein) object;
        if (this.zps != other.zps && (this.zps == null || !this.zps.equals(other.zps)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "tournoire.backgrounddata.DwzVereine[zps=" + zps + "]";
    }

}
