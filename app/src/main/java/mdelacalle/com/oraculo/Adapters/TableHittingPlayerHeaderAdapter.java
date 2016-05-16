package mdelacalle.com.oraculo.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.codecrafters.tableview.TableHeaderAdapter;

/**
 * Created by mdelacalle on 02/05/16.
 */
public class TableHittingPlayerHeaderAdapter extends TableHeaderAdapter{

    public TableHittingPlayerHeaderAdapter(Context context) {
        super(context);
    }

    @Override
    public View getHeaderView(int columnIndex, ViewGroup parentView) {

        TextView renderedView = new TextView(getContext());

        switch (columnIndex) {
            case 0:
                renderedView.setText("Name");
                return renderedView;
            case 1:
                renderedView.setText("obp");
                return renderedView;
            case 2:
                renderedView.setText("babip");
                return renderedView;
            case 3:
                renderedView.setText("slg");
                return renderedView;
            case 4:
                renderedView.setText("avg");
                return renderedView;
            case 5:
                renderedView.setText("rbi");
                return renderedView;
        }


        return renderedView;
    }
}
