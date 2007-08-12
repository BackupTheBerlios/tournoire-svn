/*
 * DwzSpieler.java
 * 
 * Created on 12.08.2007, 02:07:59
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tournoire.backgrounddata;

/**
 *
 * @author rca
 */
public class DwzSpieler
{
    private String status;
    private String spielername;
    private String spielernameG;
    private String geschlecht;
    private String spielberechtigung;
    private short geburtsjahr;
    private String letzteAuswertung;
    private short dwz;
    private short dWZIndex;
    private short fIDEElo;
    private String fIDETitel;
    private String fideId;
    private String fIDELand;

    public DwzSpieler() 
    {
    }




    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getSpielername()
    {
        return spielername;
    }

    public void setSpielername(String spielername)
    {
        this.spielername = spielername;
    }

    public String getSpielernameG()
    {
        return spielernameG;
    }

    public void setSpielernameG(String spielernameG)
    {
        this.spielernameG = spielernameG;
    }

    public String getGeschlecht()
    {
        return geschlecht;
    }

    public void setGeschlecht(String geschlecht)
    {
        this.geschlecht = geschlecht;
    }

    public String getSpielberechtigung()
    {
        return spielberechtigung;
    }

    public void setSpielberechtigung(String spielberechtigung)
    {
        this.spielberechtigung = spielberechtigung;
    }

    public short getGeburtsjahr()
    {
        return geburtsjahr;
    }

    public void setGeburtsjahr(short geburtsjahr)
    {
        this.geburtsjahr = geburtsjahr;
    }

    public String getLetzteAuswertung()
    {
        return letzteAuswertung;
    }

    public void setLetzteAuswertung(String letzteAuswertung)
    {
        this.letzteAuswertung = letzteAuswertung;
    }

    public short getDwz()
    {
        return dwz;
    }

    public void setDwz(Short dwz)
    {
        this.dwz = dwz;
    }

    public short getDWZIndex()
    {
        return dWZIndex;
    }

    public void setDWZIndex(short dWZIndex)
    {
        this.dWZIndex = dWZIndex;
    }

    public short getFIDEElo()
    {
        return fIDEElo;
    }

    public void setFIDEElo(short fIDEElo)
    {
        this.fIDEElo = fIDEElo;
    }

    public String getFIDETitel()
    {
        return fIDETitel;
    }

    public void setFIDETitel(String fIDETitel)
    {
        this.fIDETitel = fIDETitel;
    }

    public String getFideId()
    {
        return fideId;
    }

    public void setFideId(String fideId)
    {
        this.fideId = fideId;
    }

    public String getFIDELand()
    {
        return fIDELand;
    }

    public void setFIDELand(String fIDELand)
    {
        this.fIDELand = fIDELand;
    }


}
