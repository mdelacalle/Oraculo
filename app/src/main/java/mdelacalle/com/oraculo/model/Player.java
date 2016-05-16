package mdelacalle.com.oraculo.model;

/**
 * Created by mdelacalle on 27/04/16.
 */
public class Player {
    String lastName ="";
    String firstName="";
    String preferredName="";
    String jerseyNumber="";
    String id="";
    String position="";
    int win;
    int loss;
    double era;
    Hitting hitting;
    Pitching pitching;
    Fielding fielding;
    Career career;



    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPreferredName() {
        return preferredName;
    }

    public String getJerseyNumber() {
        return jerseyNumber;
    }

    public int getWin() {
        return win;
    }

    public int getLoss() {
        return loss;
    }

    public double getEra() {
        return era;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPreferredName(String preferredName) {
        this.preferredName = preferredName;
    }

    public void setJerseyNumber(String jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public void setLoss(int loss) {
        this.loss = loss;
    }

    public void setEra(double era) {
        this.era = era;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Career getCareer() {
        return career;
    }

    public void setCareer(Career career) {
        this.career = career;
    }
}
