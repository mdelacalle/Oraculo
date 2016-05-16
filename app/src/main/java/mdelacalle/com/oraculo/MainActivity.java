package mdelacalle.com.oraculo;

import android.app.Activity;
import android.graphics.LinearGradient;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import mdelacalle.com.oraculo.Adapters.ListGameAdapter;
import mdelacalle.com.oraculo.data.DataFetchingListener;
import mdelacalle.com.oraculo.data.DataUtils;
import mdelacalle.com.oraculo.model.Game;
import mdelacalle.com.oraculo.model.Player;
import mdelacalle.com.oraculo.model.Team;
import mdelacalle.com.oraculo.utils.Constants;

public class MainActivity extends Activity implements DataFetchingListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OraculoSesion os = OraculoSesion.getInstance();


        DatePicker datePicker = (DatePicker)findViewById(R.id.datePicker);

        final  Calendar calendar= Calendar.getInstance();


        datePicker.init(calendar.get(GregorianCalendar.YEAR), calendar.get(GregorianCalendar.MONTH), calendar.get(GregorianCalendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                DataUtils.getDailyBoxScore(String.valueOf(dayOfMonth),String.valueOf(monthOfYear+1),String.valueOf(year), MainActivity.this, Constants.APIKey2);
            }
        });
        DataUtils.getOraculoSesion(String.valueOf(calendar.get(GregorianCalendar.YEAR)));
        DataUtils.getDailyBoxScore(String.valueOf(calendar.get(GregorianCalendar.DAY_OF_MONTH)),String.valueOf(calendar.get(GregorianCalendar.MONTH)+1),String.valueOf(calendar.get(GregorianCalendar.YEAR)), MainActivity.this, Constants.APIKey2);


    }
    @Override
    public void onGamesReady(ArrayList<Game> games) {

        Log.wtf("MainActivity","Num games parsed:"+games.size());
        final GridView gridViewDailyGames = (GridView) findViewById(R.id.gridViewDailyGames);
        final ListGameAdapter adapter = new ListGameAdapter(games,MainActivity.this);



        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                gridViewDailyGames.setAdapter(adapter);
            }
        });


    }

    @Override
    public void onTeamReady(Team team, boolean home) {

    }

    @Override
    public void onPlayerReady(Player player,boolean home) {

    }

    @Override
    public void onDataError() {

    }
}
