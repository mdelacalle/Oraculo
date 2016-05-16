package mdelacalle.com.oraculo.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by mdelacalle on 11/05/16.
 */
public class SeasonPitchingMLBStats {
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

    private ArrayList<Double> getPitchingSlg(){
        ArrayList<Double> pitchingSlg = new ArrayList<>();
        for (Team team:teams){
            pitchingSlg.add(team.getPitching().getSlgAllowed()) ;
        }
        return pitchingSlg;
    }
    public double getMaxPitchingSlg() {
        return Collections.max(getPitchingSlg());
    }
    public double getMinPitchingSlg() {
        return Collections.min(getPitchingSlg());
    }

    private ArrayList<Integer> getHomeRuns(){
        ArrayList<Integer> hr = new ArrayList<>();
        for (Team team:teams){
            hr.add(team.getPitching().getOnbase().getHomeruns()) ;
        }
        return hr;
    }

    public int getMinHomeRuns() {
        return Collections.min(getHomeRuns());
    }
    public int getMaxHomeRuns() {
        return Collections.max(getHomeRuns());
    }

    private ArrayList<Integer> getRuns(){
        ArrayList<Integer> runs = new ArrayList<>();
        for (Team team:teams){
            runs.add(team.getPitching().getRuns()) ;
        }
        return runs;
    }

    public int getMinRuns() {
        return Collections.min(getRuns());
    }
    public int getMaxRuns() {
        return Collections.max(getRuns());
    }

    private ArrayList<Integer> getHits(){
        ArrayList<Integer> hits = new ArrayList<>();
        for (Team team:teams){
            hits.add(team.getPitching().getOnbase().getH()) ;
        }
        return hits;
    }

    public int getMinHits() {
        return Collections.min(getHits());
    }
    public int getMaxHits() {
        return Collections.max(getHits());
    }


    private ArrayList<Double> getIP(){
        ArrayList<Double> ip = new ArrayList<>();
        for (Team team:teams){
            ip.add(team.getPitching().getIp1()) ;
        }
        return ip;
    }
    public double getMaxIP() {
        return Collections.max(getIP());
    }
    public double getMinIP() {
        return Collections.min(getIP());
    }

    private ArrayList<Double> getERA(){
        ArrayList<Double> era = new ArrayList<>();
        for (Team team:teams){
            era.add(team.getPitching().getEra()) ;
        }
        return era;
    }
    public double getMaxERA() {
        return Collections.max(getERA());
    }
    public double getMinERA() {
        return Collections.min(getERA());
    }

    private ArrayList<Double> getWhip(){
        ArrayList<Double> whip = new ArrayList<>();
        for (Team team:teams){
            whip.add(team.getPitching().getWhip()) ;
        }
        return whip;
    }
    public double getMaxWhip() {
        return Collections.max(getWhip());
    }
    public double getMinWhip() {
        return Collections.min(getWhip());
    }

    private ArrayList<Double> getOBPAllowed(){
        ArrayList<Double> obpAllowed = new ArrayList<>();
        for (Team team:teams){
            obpAllowed.add(team.getPitching().getObpAllowed()) ;
        }
        return obpAllowed;
    }
    public double getMaxOBPAllowed() {
        return Collections.max(getOBPAllowed());
    }
    public double getMinOBPAllowed() {
        return Collections.min(getOBPAllowed());
    }

    private ArrayList<Double> getSLGAllowed(){
        ArrayList<Double> slgAllowed = new ArrayList<>();
        for (Team team:teams){
            slgAllowed.add(team.getPitching().getSlgAllowed()) ;
        }
        return slgAllowed;
    }
    public double getMaxSLGAllowed() {
        return Collections.max(getSLGAllowed());
    }
    public double getMinSLGAllowed() {
        return Collections.min(getSLGAllowed());
    }


}
