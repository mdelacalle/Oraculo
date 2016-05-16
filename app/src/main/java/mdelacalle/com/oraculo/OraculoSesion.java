package mdelacalle.com.oraculo;

import mdelacalle.com.oraculo.model.SeasonHittingMLBStats;
import mdelacalle.com.oraculo.model.SeasonPitchingMLBStats;

/**
 * Created by mdelacalle on 11/05/16.
 */
public class OraculoSesion {
    private static OraculoSesion ourInstance = new OraculoSesion();
    private static SeasonHittingMLBStats _seasonHittingMlbStats;
    private static SeasonPitchingMLBStats _seasonPitchingMlbStats;

    public static OraculoSesion getInstance() {
        if(ourInstance == null) {
            ourInstance =new OraculoSesion();
        }
        return ourInstance;
    }

    private OraculoSesion() {
    }

    public static SeasonHittingMLBStats get_seasonHittingMlbStats() {
        return _seasonHittingMlbStats;
    }

    public static SeasonPitchingMLBStats get_seasonPitchingMlbStats() {
        return _seasonPitchingMlbStats;
    }

    public static void setSeasonPitchingMlbStats(SeasonPitchingMLBStats _seasonPitchingMlbStats) {
        OraculoSesion._seasonPitchingMlbStats = _seasonPitchingMlbStats;
    }

    public  void setSeasonHittingMlbStats(SeasonHittingMLBStats _seasonHittingMlbStats) {
        OraculoSesion._seasonHittingMlbStats = _seasonHittingMlbStats;
    }

    public SeasonHittingMLBStats getSeasonHittingMlbStats() {
       return OraculoSesion._seasonHittingMlbStats;
    }

    public SeasonPitchingMLBStats getSeasonPitchingMlbStats() {
        return OraculoSesion._seasonPitchingMlbStats;
    }
}
