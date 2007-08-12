/*
 * Participant.java
 *
 * Created on 11.08.2007, 23:08:18
 *
 */

package tournoire;

import java.util.Date;

/**
 *
 * @author rca
 */
public class Participant
{

    public Participant()
    {
    }
    
    public int getElo()
    {
        return elo;
    }

    public void setElo(int elo)
    {
        this.elo = elo;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getNwz()
    {
        return nwz;
    }

    public void setNwz(int nwz)
    {
        this.nwz = nwz;
    }
    
    public Club getClub()
    {
        return club;
    }

    public void setClub(Club club)
    {
        this.club = club;
    }

    public Date getBirthday()
    {
        return birthday;
    }

    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }
    
    
    
    private String name;
    private int nwz;
    private int elo;
    private Club club;
    private Date birthday;

}
