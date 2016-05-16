package mdelacalle.com.oraculo.model;

import android.util.Log;

/**
 * Created by mdelacalle on 28/04/16.
 */
public class Hitting {
    public double obp;
    public double slg;
    public double babip;
    public double rbi;
    public double bbk;
    public double avg;
    public double ops;
    public double iso;
    public double ab;
    public int sacFly;
    public int runs;
    public int ktotal;
    public int plateAppearance;
    public double wOBA;
    public Outcome outcome;
    public OnBase onBase;

    public double getObp() {
        return obp;
    }

    public void setObp(double obp) {
        this.obp = obp;
    }

    public double getSlg() {
        return slg;
    }

    public void setSlg(double slg) {
        this.slg = slg;
    }

    public double getBabip() {
        return babip;
    }

    public void setBabip(double babip) {
        this.babip = babip;
    }

    public double getOps() {
        return ops;
    }

    public void setOps(double ops) {
        this.ops = ops;
    }

    public double getIso() {
        return iso;
    }

    public void setIso(double iso) {
        this.iso = iso;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getKtotal() {
        return ktotal;
    }

    public void setKtotal(int ktotal) {
        this.ktotal = ktotal;
    }

    public double getRbi() {
        return rbi;
    }

    public void setRbi(double rbi) {
        this.rbi = rbi;
    }

    public double getBbk() {
        return bbk;
    }

    public void setBbk(double bbk) {
        this.bbk = bbk;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public int getPlateAppearance() {
        return plateAppearance;
    }

    public void setPlateAppearance(int plateAppearance) {
        this.plateAppearance = plateAppearance;
    }

    public double getwOBA() {
        double wOBANumerator = (0.690*onBase.getBb())+(0.72*onBase.getHbp())+(0.89*onBase.getSingles())+(1.271*onBase.getDoubles())+(1.616*onBase.getTriples())+(2.101*onBase.getHomeruns());
        double wOBADenominator = ab + onBase.getBb() -  onBase.getIbb() + sacFly+ onBase.getHbp();
        wOBA = wOBANumerator/wOBADenominator;
        return wOBA;
    }

    public void setwOBA(double wOBA) {
        this.wOBA = wOBA;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    public OnBase getOnBase() {
        return onBase;
    }

    public void setOnBase(OnBase onBase) {
        this.onBase = onBase;
    }

    public double getAb() {
        return ab;
    }

    public void setAb(double ab) {
        this.ab = ab;
    }

    public int getSacFly() {
        return sacFly;
    }

    public void setSacFly(int sacFly) {
        this.sacFly = sacFly;
    }
}
