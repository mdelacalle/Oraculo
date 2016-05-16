package mdelacalle.com.oraculo.model;

/**
 * Created by mdelacalle on 27/04/16.
 */
public class Game {

    String id = "";
    Team home;
    Team away;
    String status="";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Team getHome() {
        return home;
    }

    public void setHome(Team home) {
        this.home = home;
    }

    public Team getAway() {
        return away;
    }

    public void setAway(Team away) {
        this.away = away;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}



