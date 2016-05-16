package mdelacalle.com.oraculo.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;
import mdelacalle.com.oraculo.R;
import mdelacalle.com.oraculo.model.Games;
import mdelacalle.com.oraculo.model.Pitching;
import mdelacalle.com.oraculo.model.Player;
import mdelacalle.com.oraculo.utils.DecorationUtils;

import static android.R.color.holo_green_light;
import static de.codecrafters.tableview.R.color.button_material_dark;

/**
 * Created by mdelacalle on 02/05/16.
 */
public class TablePitchingAdapter extends TableDataAdapter<Player> {


    public TablePitchingAdapter(Context context, List<Player> pitchingList){
        super(context,pitchingList);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {

        Player player = getRowData(rowIndex);
        TextView renderedView = new TextView(getContext());
        parentView.setBackgroundColor(getResources().getColor(button_material_dark));


        switch (columnIndex) {
            case 0:
                renderedView.setText(player.getFirstName()+" "+player.getLastName());
                return renderedView;
            case 1:
                renderedView.setText(""+player.getWin());
                return renderedView;
            case 2:
                renderedView.setText(""+player.getLoss());
                return renderedView;
            case 3:
                renderedView.setText(""+player.getPitching().getIp2());
                return renderedView;
            case 4:
                renderedView.setText(""+player.getPitching().getOnbase().getHomeruns());
                return renderedView;
            case 5:
                renderedView.setText(""+player.getPitching().getEra());
                renderedView.setBackgroundColor(getResources().getColor(DecorationUtils.colorizeCell5(2.50,4.60,player.getPitching().getEra(),false)));
                return renderedView;
            case 6:
                renderedView.setText(""+Math.round(player.getPitching().getFip()*100.0)/100.0);
                renderedView.setBackgroundColor(getResources().getColor(DecorationUtils.colorizeCell5(2.90,4.70,player.getPitching().getFip(),false)));
                return renderedView;
            case 7:
                renderedView.setText(""+player.getPitching().getK9());
                renderedView.setBackgroundColor(getResources().getColor(DecorationUtils.colorizeCell5(5,10,player.getPitching().getK9(),true)));
                return renderedView;
            case 8:
                renderedView.setText(""+player.getPitching().getKbb());
                return renderedView;
        }


        return renderedView;
    }
}
