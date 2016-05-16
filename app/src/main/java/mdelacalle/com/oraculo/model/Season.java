package mdelacalle.com.oraculo.model;

/**
 * Created by mdelacalle on 04/05/16.
 */
public class Season {
    public int season;
    public Hitting hitting;
    public Pitching pitching;
    public Fielding fielding;
    public String team;

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public Hitting getHitting() {
        return hitting;
    }

    public void setHitting(Hitting hitting) {
        this.hitting = hitting;
    }

    public Pitching getPitching() {
        return pitching;
    }

    public void setPitching(Pitching avgPitching) {
        this.pitching = avgPitching;
    }

    public Fielding getFielding() {
        return fielding;
    }

    public void setFielding(Fielding fielding) {
        this.fielding = fielding;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
