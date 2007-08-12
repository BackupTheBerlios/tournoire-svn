/*
 * DwzVerbaende.java
 * 
 * Created on 12.08.2007, 02:08:04
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tournoire.backgrounddata;


/**
 *
 * @author rca
 */
public class DwzVerband
{
    private String verband;
    private char lv;
    private String uebergeordnet;
    private String verbandname;

    public DwzVerband() 
    {
    }

    public DwzVerband(String verband)
    {
        this.verband = verband;
    }

    public DwzVerband(String verband, char lv, String uebergeordnet, String verbandname)
    {
        this.verband = verband;
        this.lv = lv;
        this.uebergeordnet = uebergeordnet;
        this.verbandname = verbandname;
    }

    public String getVerband()
    {
        return verband;
    }

    public void setVerband(String verband)
    {
        this.verband = verband;
    }

    public char getLv()
    {
        return lv;
    }

    public void setLv(char lv)
    {
        this.lv = lv;
    }

    public String getUebergeordnet()
    {
        return uebergeordnet;
    }

    public void setUebergeordnet(String uebergeordnet)
    {
        this.uebergeordnet = uebergeordnet;
    }

    public String getVerbandname()
    {
        return verbandname;
    }

    public void setVerbandname(String verbandname)
    {
        this.verbandname = verbandname;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (verband != null ? verband.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {

        if (!(object instanceof DwzVerband))
        {
            return false;
        }
        DwzVerband other = (DwzVerband) object;
        if (this.verband != other.verband && (this.verband == null || !this.verband.equals(other.verband)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "tournoire.backgrounddata.DwzVerbaende[verband=" + verband + "]";
    }

}
