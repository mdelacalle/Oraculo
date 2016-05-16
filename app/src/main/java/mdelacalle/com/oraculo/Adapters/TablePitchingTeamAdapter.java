package mdelacalle.com.oraculo.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;
import mdelacalle.com.oraculo.OraculoSesion;
import mdelacalle.com.oraculo.model.SeasonHittingMLBStats;
import mdelacalle.com.oraculo.model.SeasonPitchingMLBStats;
import mdelacalle.com.oraculo.model.Team;
import mdelacalle.com.oraculo.utils.DecorationUtils;

import static de.codecrafters.tableview.R.color.button_material_dark;

/**
 * Created by mdelacalle on 02/05/16.
 */
public class TablePitchingTeamAdapter extends TableDataAdapter<Team> {


    public TablePitchingTeamAdapter(Context context, List<Team> hittingList){
        super(context,hittingList);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {

        Team team = getRowData(rowIndex);
        TextView renderedView = new TextView(getContext());
        parentView.setBackgroundColor(getResources().getColor(button_material_dark));
        OraculoSesion os = OraculoSesion.getInstance();
        SeasonPitchingMLBStats pitchingMLBStats = os.getSeasonPitchingMlbStats();

        for(Team teamPitchingRanks:pitchingMLBStats.getTeams()){
            if(team.getAbbr().equals(teamPitchingRanks.getAbbr())){
                team.setPitchingRank(teamPitchingRanks.getPitchingRank());
                break;
            }

        }

        switch (columnIndex) {
            case 0:
                renderedView.setText(team.getName() + "  #  " + team.getHittingRank());
                return renderedView;
            case 1:
                int hits = team.getPitching().getOnbase().getH();
                renderedView.setText(""+ hits);
                renderedView.setBackgroundColor(getResources().getColor(DecorationUtils.colorizeCell5(pitchingMLBStats.getMinHits(),pitchingMLBStats.getMaxHits(),hits,true)));
                return renderedView;
            case 2:
                double ip = team.getPitching().getIp1();
                renderedView.setText(""+ ip);
                renderedView.setBackgroundColor(getResources().getColor(DecorationUtils.colorizeCell5(pitchingMLBStats.getMinIP(),pitchingMLBStats.getMaxIP(),ip,true)));
                return renderedView;
            case 3:
                int runs = team.getPitching().getRuns();
                renderedView.setText(""+ runs);
                renderedView.setBackgroundColor(getResources().getColor(DecorationUtils.colorizeCell5(pitchingMLBStats.getMinRuns(),pitchingMLBStats.getMaxRuns(),runs,true)));
                return renderedView;
            case 4:
                int homeRuns = team.getPitching().getOnbase().getHomeruns();
                renderedView.setText(""+ homeRuns);
                renderedView.setBackgroundColor(getResources().getColor(DecorationUtils.colorizeCell5(pitchingMLBStats.getMinHomeRuns(),pitchingMLBStats.getMaxHomeRuns(),homeRuns,true)));
                return renderedView;
            case 5:
                double era = team.getPitching().getEra();
                renderedView.setText(""+ era);
                renderedView.setBackgroundColor(getResources().getColor(DecorationUtils.colorizeCell5(pitchingMLBStats.getMinERA(),pitchingMLBStats.getMaxERA(),era,false)));
                return renderedView;
            case 6:
                double whip = team.getPitching().getWhip();
                renderedView.setText(""+ whip);
                renderedView.setBackgroundColor(getResources().getColor(DecorationUtils.colorizeCell5(pitchingMLBStats.getMinWhip(),pitchingMLBStats.getMaxWhip(),whip,false)));
                return renderedView;
            case 7:
                double obpAllowed = team.getPitching().getObpAllowed();
                renderedView.setText(""+ obpAllowed);
                renderedView.setBackgroundColor(getResources().getColor(DecorationUtils.colorizeCell5(pitchingMLBStats.getMinOBPAllowed(),pitchingMLBStats.getMaxOBPAllowed(),obpAllowed,false)));
                return renderedView;
            case 8:
                double slgAllowed = team.getPitching().getSlgAllowed();
                renderedView.setText(""+ slgAllowed);
                renderedView.setBackgroundColor(getResources().getColor(DecorationUtils.colorizeCell5(pitchingMLBStats.getMinSLGAllowed(),pitchingMLBStats.getMaxSLGAllowed(),slgAllowed,false)));
                return renderedView;

        }


        return renderedView;
    }
}
