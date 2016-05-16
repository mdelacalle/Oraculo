package mdelacalle.com.oraculo.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.codecrafters.tableview.TableHeaderAdapter;

/**
 * Created by mdelacalle on 02/05/16.
 */
public class TablePitchingHeaderTeamAdapter extends TableHeaderAdapter{

    public TablePitchingHeaderTeamAdapter(Context context) {
        super(context);
    }

    @Override
    public View getHeaderView(int columnIndex, ViewGroup parentView) {

        TextView renderedView = new TextView(getContext());

        switch (columnIndex) {
            case 0:
                renderedView.setText("Team");
                return renderedView;
            case 1:
                renderedView.setText("Hits");
                return renderedView;
            case 2:
                renderedView.setText("IP");
                return renderedView;
            case 3:
                renderedView.setText("Runs");
                return renderedView;
            case 4:
                renderedView.setText("HR");
                return renderedView;
            case 5:
                renderedView.setText("ERA");
                return renderedView;
            case 6:
                renderedView.setText("WHIP");
                return renderedView;
            case 7:
                renderedView.setText("OBP");
                return renderedView;
            case 8:
                renderedView.setText("SLG");
                return renderedView;
        }


        return renderedView;
    }
}
