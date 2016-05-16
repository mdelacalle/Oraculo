package mdelacalle.com.oraculo.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.codecrafters.tableview.TableHeaderAdapter;

/**
 * Created by mdelacalle on 02/05/16.
 */
public class TableHittingHeaderAdapter extends TableHeaderAdapter{

    public TableHittingHeaderAdapter(Context context) {
        super(context);
    }

    @Override
    public View getHeaderView(int columnIndex, ViewGroup parentView) {

        TextView renderedView = new TextView(getContext());

        switch (columnIndex) {
            case 0:
                renderedView.setText(" ");
                return renderedView;
            case 1:
                renderedView.setText("Win");
                return renderedView;
            case 2:
                renderedView.setText("Loss");
                return renderedView;
            case 3:
                renderedView.setText("obp");
                return renderedView;
            case 4:
                renderedView.setText("slg");
                return renderedView;
            case 5:
                renderedView.setText("babip");
                return renderedView;
            case 6:
                renderedView.setText("ops");
                return renderedView;
            case 7:
                renderedView.setText("runs");
                return renderedView;
            case 8:
                renderedView.setText("rbi");
                return renderedView;
            case 9:
                renderedView.setText("bbk");
                return renderedView;
            case 10:
                renderedView.setText("avg");
                return renderedView;
            case 11:
                renderedView.setText("wOBA");
                return renderedView;
        }


        return renderedView;
    }
}
