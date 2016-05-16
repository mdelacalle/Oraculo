package mdelacalle.com.oraculo.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by mdelacalle on 11/05/16.
 */
public class SeasonHittingMLBStats {
    ArrayList<Team> teams = new ArrayList<>();



   public ArrayList<Team> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    private ArrayList<Integer> getWins(){
        ArrayList<Integer> wins = new ArrayList<>();
         for (Team team:teams){
            wins.add(team.getGames().getWin()) ;
        }
        return wins;
    }

    private ArrayList<Integer> getLoss(){
        ArrayList<Integer> loss = new ArrayList<>();
        for (Team team:teams){
            loss.add(team.getGames().getLoss()) ;
        }
        return loss;
    }


    public int getMaxWins() {
        return Collections.max(getWins());
    }

    public int getMinWins() {
        return Collections.min(getWins());
    }

    public int getMaxLoss() {
        return Collections.max(getLoss());
    }
    public int getMinLoss() {
        return Collections.min(getLoss());
    }

    private ArrayList<Double> getHittingSlg(){
        ArrayList<Double> hittingSlg = new ArrayList<>();
        for (Team team:teams){
            hittingSlg.add(team.getHitting().getSlg()) ;
        }
        return hittingSlg;
    }
    public double getMaxHittingSlg() {
        return Collections.max(getHittingSlg());
    }
    public double getMinHittingSlg() {
        return Collections.min(getHittingSlg());
    }

    private ArrayList<Double> getHittingOps(){
        ArrayList<Double> hittingOps = new ArrayList<>();
        for (Team team:teams){
            hittingOps.add(team.getHitting().getOps()) ;
        }
        return hittingOps;
    }
    public double getMaxHittingOps() {
        return Collections.max(getHittingOps());
    }
    public double getMinHittingOps() {
        return Collections.min(getHittingOps());
    }

    private ArrayList<Integer> getRuns(){
        ArrayList<Integer> runs = new ArrayList<>();
        for (Team team:teams){
            runs.add(team.getHitting().getRuns()) ;
        }
        return runs;
    }

    public int getMinRuns() {
        return Collections.min(getRuns());
    }
    public int getMaxRuns() {
        return Collections.max(getRuns());
    }

    private ArrayList<Double> getHittingRbi(){
        ArrayList<Double> hittingRbi = new ArrayList<>();
        for (Team team:teams){
            hittingRbi.add(team.getHitting().getRbi()) ;
        }
        return hittingRbi;
    }

    public double getMinHittingRbi() {
        return Collections.min(getHittingRbi());
    }
    public double getMaxHittingRbi() {
        return Collections.max(getHittingRbi());
    }

    private ArrayList<Double> getHittingBbk(){
        ArrayList<Double> hittingbbk = new ArrayList<>();
        for (Team team:teams){
            hittingbbk.add(team.getHitting().getBbk());
        }
        return hittingbbk;
    }

    public double getMinHittingBbk() {
        return Collections.min(getHittingBbk());
    }

    public double getMaxHittingBbk() {
        return  Collections.max(getHittingBbk());
    }

    private ArrayList<Double> getHittingAvg(){
        ArrayList<Double> hittingAvg = new ArrayList<>();
        for (Team team:teams){
            hittingAvg.add(team.getHitting().getAvg()) ;
        }
        return hittingAvg;
    }

    public double getMinHittingAvg() {
         return Collections.min(getHittingAvg());
    }
    public double getMaxHittingAvg() {
        return Collections.max(getHittingAvg());
    }


}
