package mdelacalle.com.oraculo.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;
import mdelacalle.com.oraculo.model.Player;
import mdelacalle.com.oraculo.model.Team;
import mdelacalle.com.oraculo.utils.DecorationUtils;

import static de.codecrafters.tableview.R.color.button_material_dark;

/**
 * Created by mdelacalle on 02/05/16.
 */
public class TableHittingPlayerAdapter extends TableDataAdapter<Player> {


    public TableHittingPlayerAdapter(Context context, List<Player> hittingList){
        super(context,hittingList);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {

        Player player = getRowData(rowIndex);
        TextView renderedView = new TextView(getContext());
        parentView.setBackgroundColor(getResources().getColor(button_material_dark));

        if (player.getHitting()!=null) {

            switch (columnIndex) {
                case 0:
                    renderedView.setText(player.getLastName());
                    return renderedView;
                case 1:
                    double obp = player.getHitting().getObp();
                    renderedView.setText("" + obp);
                    renderedView.setBackgroundColor(getResources().getColor(DecorationUtils.colorizeCell5(0.290, 0.390, obp, true)));
                case 2:
                    renderedView.setText("" + player.getHitting().getBabip());
                    return renderedView;
                case 3:
                    renderedView.setText("" + player.getHitting().getSlg());
                    return renderedView;
                case 4:
                    renderedView.setText("" + player.getHitting().getAvg());
                    return renderedView;
                case 5:
                    renderedView.setText("" + player.getHitting().getRbi());
            }
        }

        return renderedView;
    }
}
