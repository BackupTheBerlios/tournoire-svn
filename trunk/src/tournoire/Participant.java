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
    
    public int getFideElo()
    {
        return elo;
    }

    public void setFideElo(short elo)
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

    public int getNRating()
    {
        return nRating;
    }

    public void setNRating(short nwz)
    {
        this.nRating = nwz;
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
    private short nRating;
    private short elo;
    private Club club;
    private Date birthday;

}
