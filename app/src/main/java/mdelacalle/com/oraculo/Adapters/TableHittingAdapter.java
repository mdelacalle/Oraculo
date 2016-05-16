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
import mdelacalle.com.oraculo.model.Team;
import mdelacalle.com.oraculo.utils.DecorationUtils;

import static de.codecrafters.tableview.R.color.button_material_dark;

/**
 * Created by mdelacalle on 02/05/16.
 */
public class TableHittingAdapter extends TableDataAdapter<Team> {


    public TableHittingAdapter(Context context, List<Team> hittingList){
        super(context,hittingList);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {

        Team team = getRowData(rowIndex);
        TextView renderedView = new TextView(getContext());
        parentView.setBackgroundColor(getResources().getColor(button_material_dark));
        OraculoSesion os = OraculoSesion.getInstance();

        SeasonHittingMLBStats hittingStats = os.getSeasonHittingMlbStats();

        for(Team teamHittingRanks:hittingStats.getTeams()){
            if(team.getAbbr().equals(teamHittingRanks.getAbbr())){
                team.setHittingRank(teamHittingRanks.getHittingRank());
                break;
            }

        }

        switch (columnIndex) {
            case 0:
                renderedView.setText(team.getName() + "  #  " + team.getHittingRank());
                return renderedView;
            case 1:
                renderedView.setText(""+team.getGames().getWin());
                return renderedView;
            case 2:
                renderedView.setText(""+team.getGames().getLoss());
                return renderedView;
            case 3:
                double obp = team.getHitting().getObp();
                renderedView.setText(""+ obp);
                renderedView.setBackgroundColor(getResources().getColor(DecorationUtils.colorizeCell5(0.290,0.390,obp,true)));
                return renderedView;
            case 4:
                double slg = team.getHitting().getSlg();
                renderedView.setText(""+ slg);
                renderedView.setBackgroundColor(getResources().getColor(DecorationUtils.colorizeCell5(hittingStats.getMinHittingSlg(),hittingStats.getMaxHittingSlg(),slg,true)));
                return renderedView;
            case 5:
                renderedView.setText(""+team.getHitting().getBabip());
                return renderedView;
            case 6:
                double ops = team.getHitting().getOps();
                renderedView.setText(""+ ops);
                renderedView.setBackgroundColor(getResources().getColor(DecorationUtils.colorizeCell5(hittingStats.getMinHittingOps(),hittingStats.getMaxHittingOps(),ops,true)));
                return renderedView;
            case 7:
                int runs = team.getHitting().getRuns();
                renderedView.setText(""+ runs);
                renderedView.setBackgroundColor(getResources().getColor(DecorationUtils.colorizeCell5(hittingStats.getMinRuns(),hittingStats.getMaxRuns(),runs,true)));
                return renderedView;
            case 8:
                double rbi = team.getHitting().getRbi();
                renderedView.setText(""+ rbi);
                renderedView.setBackgroundColor(getResources().getColor(DecorationUtils.colorizeCell5(hittingStats.getMinHittingRbi(),hittingStats.getMaxHittingRbi(),rbi,true)));
                return renderedView;
            case 9:
                double bbk = team.getHitting().getBbk();
                renderedView.setText(""+ bbk);
             //   renderedView.setBackgroundColor(getResources().getColor(DecorationUtils.colorizeCell5(hittingStats.getMinHittingBbk(),hittingStats.getMaxHittingBbk(),bbk,true)));
                return renderedView;
            case 10:
                double avg = team.getHitting().getAvg();
                renderedView.setText(""+ avg);
                renderedView.setBackgroundColor(getResources().getColor(DecorationUtils.colorizeCell5(hittingStats.getMinHittingAvg(),hittingStats.getMaxHittingAvg(),avg,true)));
                return renderedView;
            case 11:
                double wOBA = team.getHitting().getwOBA();
                renderedView.setText(""+Math.round(wOBA*1000.0)/1000.0);
                renderedView.setBackgroundColor(getResources().getColor(DecorationUtils.colorizeCell5(0.290,0.400,wOBA,true)));
                return renderedView;
        }


        return renderedView;
    }
}
