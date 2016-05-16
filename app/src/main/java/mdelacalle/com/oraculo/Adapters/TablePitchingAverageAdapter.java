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

import static de.codecrafters.tableview.R.color.material_blue_grey_800;

/**
 * Created by mdelacalle on 02/05/16.
 */
public class TablePitchingAverageAdapter extends TableDataAdapter<Player> {


    public TablePitchingAverageAdapter(Context context, List<Player> pitchingList){
        super(context,pitchingList);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {

        Player player = getRowData(rowIndex);
        TextView renderedView = new TextView(getContext());
        parentView.setBackgroundColor(getResources().getColor(material_blue_grey_800));


        Pitching avgPitching = player.getCareer().getAvgPitching();
        switch (columnIndex) {
            case 0:
               renderedView.setText(player.getFirstName()+" "+player.getLastName());
                return renderedView;
            case 1:
                renderedView.setText(""+avgPitching.getOnbase().getH());
                return renderedView;
            case 2:
                renderedView.setText(""+avgPitching.getOnbase().getTb());
                return renderedView;
            case 3:
                double ip2 =  Math.round(avgPitching.getIp2());
                renderedView.setText(""+ ip2);
                return renderedView;
            case 4:
                renderedView.setText(""+avgPitching.getOnbase().getHomeruns());
                return renderedView;
            case 5:
                double era = Math.round(avgPitching.getEra()* 100.0) / 100.0;
                renderedView.setText(""+ era);
                renderedView.setBackgroundColor(getResources().getColor(DecorationUtils.colorizeCell5(2.50,4.60,era,false)));
                return renderedView;
            case 6:
                double fip = Math.round(avgPitching.getFip() * 100.0) / 100.0;
                renderedView.setText(""+ fip);
                renderedView.setBackgroundColor(getResources().getColor(DecorationUtils.colorizeCell5(2.90,4.70,fip,false)));
                return renderedView;
            case 7:
                double k9 = Math.round(avgPitching.getK9()* 100.0) / 100.0;
                renderedView.setText(""+ k9);
                renderedView.setBackgroundColor(getResources().getColor(DecorationUtils.colorizeCell5(5,10,k9,true)));
                return renderedView;
            case 8:
                renderedView.setText(""+Math.round(avgPitching.getKbb()* 100.0) / 100.0);
                return renderedView;
        }


        return renderedView;
    }
}
