package mdelacalle.com.oraculo.model;

import java.util.ArrayList;

/**
 * Created by mdelacalle on 27/04/16.
 */
public class Team {

    String  name ="";
    String market="";
    String abbr="";
    String id = "";
    int runs =0;
    int hits=0;
    int errors=0;
    Player probablePitcher;
    Games games;
    Hitting hitting;
    Pitching pitching;
    Fielding fielding;
    ArrayList<Player> roster;

    public int getHittingRank() {
        return hittingRank;
    }

    public void setHittingRank(int hittingRank) {
        this.hittingRank = hittingRank;
    }

    int hittingRank;
    int pitchingRank;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getErrors() {
        return errors;
    }

    public void setErrors(int errors) {
        this.errors = errors;
    }

    public Player getProbablePitcher() {
        return probablePitcher;
    }

    public void setProbablePitcher(Player probablePitcher) {
        this.probablePitcher = probablePitcher;
    }

    public Games getGames() {
        return games;
    }

    public void setGames(Games games) {
        this.games = games;
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

    public void setPitching(Pitching pitching) {
        this.pitching = pitching;
    }

    public Fielding getFielding() {
        return fielding;
    }

    public void setFielding(Fielding fielding) {
        this.fielding = fielding;
    }

    public ArrayList<Player> getRoster() {
        return roster;
    }

    public void setRoster(ArrayList<Player> roster) {
        this.roster = roster;
    }

    public int getPitchingRank() {
        return pitchingRank;
    }

    public void setPitchingRank(int pitchingRank) {
        this.pitchingRank = pitchingRank;
    }
}
