package mdelacalle.com.oraculo.model;

import java.util.ArrayList;

/**
 * Created by mdelacalle on 04/05/16.
 */
public class Career {

    public ArrayList<Season> seasons;
    public Hitting avgHitting;
    public Pitching avgPitching;
    public Fielding avgFielding;

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }



    public OnBase getOnBaseAverage(){
        OnBase onBaseAvg = new OnBase();

        for (Season season:seasons){
            OnBase onBaseSeason = season.getPitching().getOnbase();
            onBaseAvg.setBb(onBaseAvg.getBb()+onBaseSeason.getBb());
            onBaseAvg.setDoubles(onBaseAvg.getDoubles()+onBaseSeason.getDoubles());
            onBaseAvg.setHbp(onBaseAvg.getHbp()+onBaseSeason.getHbp());
            onBaseAvg.setHomeruns(onBaseAvg.getHomeruns()+onBaseSeason.getHomeruns());
            onBaseAvg.setSingles(onBaseAvg.getSingles()+onBaseSeason.getSingles());
            onBaseAvg.setTriples(onBaseAvg.getTriples()+onBaseSeason.getTriples());
            onBaseAvg.setIbb(onBaseAvg.getIbb()+onBaseSeason.getIbb());
            onBaseAvg.setH(onBaseAvg.getH()+onBaseSeason.getH());
            onBaseAvg.setTb(onBaseAvg.getTb()+onBaseSeason.getTb());
        }
        onBaseAvg.setBb(onBaseAvg.getBb()/seasons.size());
        onBaseAvg.setDoubles(onBaseAvg.getDoubles()/seasons.size());
        onBaseAvg.setHbp(onBaseAvg.getHbp()/seasons.size());
        onBaseAvg.setHomeruns(onBaseAvg.getHomeruns()/seasons.size());
        onBaseAvg.setSingles(onBaseAvg.getSingles()/seasons.size());
        onBaseAvg.setTriples(onBaseAvg.getTriples()/seasons.size());
        onBaseAvg.setIbb(onBaseAvg.getIbb()/seasons.size());
        onBaseAvg.setH(onBaseAvg.getH()/seasons.size());
        onBaseAvg.setTb(onBaseAvg.getTb()/seasons.size());
        return onBaseAvg;
    }

    public Hitting getAvgHitting() {
        avgHitting = new Hitting();
        for (Season season:seasons){
            Hitting hitting = season.getHitting();
            if (hitting == null){
                return null;
            }
            //avgHitting.setRuns(avgHitting.getRuns()+hitting.getRuns());
            avgHitting.setSlg(avgHitting.getSlg()+hitting.getSlg());
            avgHitting.setKtotal(avgHitting.getKtotal()+hitting.getKtotal());
            avgHitting.setIso(avgHitting.getIso()+hitting.getIso());
            avgHitting.setBabip(avgHitting.getBabip()+hitting.getBabip());
            avgHitting.setOps(avgHitting.getOps()+hitting.getOps());
            avgHitting.setObp(avgHitting.getObp()+hitting.getObp());
        }

      //  avgHitting.setRuns(avgHitting.getRuns()/seasons.size());
        avgHitting.setSlg(avgHitting.getSlg()/seasons.size());
        avgHitting.setKtotal(avgHitting.getKtotal()/seasons.size());
        avgHitting.setIso(avgHitting.getIso()/seasons.size());
        avgHitting.setBabip(avgHitting.getBabip()/seasons.size());
        avgHitting.setOps(avgHitting.getOps()/seasons.size());
        avgHitting.setObp(avgHitting.getObp()/seasons.size());
        return avgHitting;
    }

    public void setAvgHitting(Hitting avgHitting) {
        this.avgHitting = avgHitting;
    }

    public Pitching getAvgPitching() {
        avgPitching = new Pitching();

        for (Season season:seasons){
            Pitching pitching = season.getPitching();

            if (pitching == null){
                return null;
            }

            avgPitching.setRuns(avgPitching.getRuns()+pitching.getRuns());
            avgPitching.setIp1(avgPitching.getIp1()+pitching.getIp1());
            avgPitching.setKbb(avgPitching.getKbb()+pitching.getKbb());
            avgPitching.setK9(avgPitching.getK9()+pitching.getK9());
            avgPitching.setWhip(avgPitching.getWhip()+pitching.getWhip());
            avgPitching.setEra(avgPitching.getEra()+pitching.getEra());
            avgPitching.setIp2(avgPitching.getIp2()+pitching.getIp2());

        }
        avgPitching.setRuns(avgPitching.getRuns()/seasons.size());
        avgPitching.setIp1(avgPitching.getIp1()/seasons.size());
        avgPitching.setKbb(avgPitching.getKbb()/seasons.size());
        avgPitching.setK9(avgPitching.getK9()/seasons.size());
        avgPitching.setWhip(avgPitching.getWhip()/seasons.size());
        avgPitching.setEra(avgPitching.getEra()/seasons.size());
        avgPitching.setIp2(avgPitching.getIp2()/seasons.size());
        avgPitching.setOnbase(getOnBaseAverage());

        return avgPitching;
    }

    public void setAvgPitching(Pitching avgPitching) {
        this.avgPitching = avgPitching;
    }

    public Fielding getAvgFielding() {
        return avgFielding;
    }

    public void setAvgFielding(Fielding avgFielding) {
        this.avgFielding = avgFielding;
    }



}
