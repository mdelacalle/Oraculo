package mdelacalle.com.oraculo.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;
import mdelacalle.com.oraculo.model.Pitching;
import mdelacalle.com.oraculo.model.Player;
import mdelacalle.com.oraculo.utils.DecorationUtils;

import static de.codecrafters.tableview.R.color.button_material_dark;

/**
 * Created by mdelacalle on 02/05/16.
 */
public class TablePitchingSeasonAdapter extends TableDataAdapter<Player> {


    public TablePitchingSeasonAdapter(Context context, List<Player> pitchingList){
        super(context,pitchingList);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {

        Player player = getRowData(rowIndex);
        TextView renderedView = new TextView(getContext());
        parentView.setBackgroundColor(getResources().getColor(button_material_dark));


        Pitching pitchingSeason = player.getCareer().getSeasons().get(0).getPitching();
        switch (columnIndex) {
            case 0:
                renderedView.setText(player.getFirstName()+" "+player.getLastName());
                return renderedView;
            case 1:
                renderedView.setText(""+pitchingSeason.getOnbase().getH());
                return renderedView;
            case 2:
                renderedView.setText(""+pitchingSeason.getOnbase().getTb());
                return renderedView;
            case 3:
                renderedView.setText(""+ pitchingSeason.getIp2());
                return renderedView;
            case 4:
                renderedView.setText(""+ pitchingSeason.getOnbase().getHomeruns());
                return renderedView;
            case 5:
                double era = pitchingSeason.getEra();
                renderedView.setText(""+ era);
                renderedView.setBackgroundColor(getResources().getColor(DecorationUtils.colorizeCell5(2.50,4.60,era,false)));
                return renderedView;
            case 6:
                double fip = Math.round(pitchingSeason.getFip() * 100.0) / 100.0;
                renderedView.setText(""+ fip);
                renderedView.setBackgroundColor(getResources().getColor(DecorationUtils.colorizeCell5(2.90,4.70,fip,false)));
                return renderedView;
            case 7:
                double k9 = pitchingSeason.getK9();
                renderedView.setText(""+ k9);
                renderedView.setBackgroundColor(getResources().getColor(DecorationUtils.colorizeCell5(5,10,k9,true)));
                return renderedView;
            case 8:
                renderedView.setText(""+ pitchingSeason.getKbb());
                return renderedView;
        }


        return renderedView;
    }
}
