/*
 * DwzSpieler.java
 * 
 * Created on 12.08.2007, 02:07:59
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tournoire;

/**
 *
 * @author rca
 */
public class Player extends Participant
{
    private String status;
    private String sex;
    private String activity;
    private short yearOfBirth;
    private short nRatingIndex;
    private String fideTitle;
    private String fideId;
    private String fideCountry;

    public Player() 
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



    public String getSex()
    {
        return sex;
    }

    public void setSex(String geschlecht)
    {
        this.sex = geschlecht;
    }

    public String getActivity()
    {
        return activity;
    }

    public void setActivity(String spielberechtigung)
    {
        this.activity = spielberechtigung;
    }

    public short getYearOfBirth()
    {
        return yearOfBirth;
    }

    public void setYearOfBirth(short geburtsjahr)
    {
        this.yearOfBirth = geburtsjahr;
    }



    public short getNRatingIndex()
    {
        return nRatingIndex;
    }

    public void setNRatingIndex(short dWZIndex)
    {
        this.nRatingIndex = dWZIndex;
    }


    public String getFideTitle()
    {
        return fideTitle;
    }

    public void setFideTitle(String fIDETitel)
    {
        this.fideTitle = fIDETitel;
    }

    public String getFideId()
    {
        return fideId;
    }

    public void setFideId(String fideId)
    {
        this.fideId = fideId;
    }

    public String getFideCountry()
    {
        return fideCountry;
    }

    public void setFideCountry(String fIDELand)
    {
        this.fideCountry = fIDELand;
    }


}
