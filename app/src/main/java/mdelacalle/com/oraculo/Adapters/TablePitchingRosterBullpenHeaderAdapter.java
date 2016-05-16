package mdelacalle.com.oraculo.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.codecrafters.tableview.TableHeaderAdapter;

/**
 * Created by mdelacalle on 02/05/16.
 */
public class TablePitchingRosterBullpenHeaderAdapter extends TableHeaderAdapter{

    public TablePitchingRosterBullpenHeaderAdapter(Context context) {
        super(context);
    }

    @Override
    public View getHeaderView(int columnIndex, ViewGroup parentView) {

        TextView renderedView = new TextView(getContext());

        switch (columnIndex) {
            case 0:
                renderedView.setText("Player");
                return renderedView;
            case 1:
                renderedView.setText("IP");
                return renderedView;
            case 2:
                renderedView.setText("ERA");
                return renderedView;
            case 3:
                renderedView.setText("FIP");
                return renderedView;
            case 4:
                renderedView.setText("K9");
                return renderedView;
            case 5:
                renderedView.setText("KBB");
                return renderedView;
        }


        return renderedView;
    }
}
