package mdelacalle.com.oraculo.model;

import android.util.Log;

import mdelacalle.com.oraculo.utils.Constants;

/**
 * Created by mdelacalle on 28/04/16.
 */
public class Pitching {
    public double whip;
    public double era;
    public double fip;
    public double k9;
    public double kbb;
    public int runs;
    public double ip1;
    public double ip2;
    public double obpAllowed;
    public double slgAllowed;
    public OnBase onbase;
    public Outcome outcome;

    public double getWhip() {
        return whip;
    }

    public void setWhip(double whip) {
        this.whip = whip;
    }

    public double getEra() {
        return era;
    }

    public void setEra(double era) {
        this.era = era;
    }

    public double getFip() {

        double   k =(k9*ip2)/9;
        double numerator = (13*onbase.getHomeruns()) + (3 * (onbase.getBb()+onbase.getHbp())) - (2*k) ;
        fip =(numerator/ip2)+ Constants.FIP_CONSTANT;
        return fip;
    }

    public void setFip(double fip) {
        this.fip = fip;
    }

    public double getK9() {
        return k9;
    }

    public void setK9(double k9) {
        this.k9 = k9;
    }

    public double getKbb() {
        return kbb;
    }

    public void setKbb(double kbb) {
        this.kbb = kbb;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public OnBase getOnbase() {
        return onbase;
    }

    public void setOnbase(OnBase onbase) {
        this.onbase = onbase;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    public double getIp1() {
        return ip1;
    }

    public void setIp1(double ip1) {
        this.ip1 = ip1;
    }

    public double getIp2() {
        return ip2;
    }

    public void setIp2(double ip2) {
        this.ip2 = ip2;
    }

    public double getObpAllowed() {
        return obpAllowed;
    }

    public void setObpAllowed(double obpAllowed) {
        this.obpAllowed = obpAllowed;
    }

    public double getSlgAllowed() {
        return slgAllowed;
    }

    public void setSlgAllowed(double slgAllowed) {
        this.slgAllowed = slgAllowed;
    }
}
