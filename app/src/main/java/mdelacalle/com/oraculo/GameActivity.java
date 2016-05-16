package mdelacalle.com.oraculo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;

import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.TableView;
import mdelacalle.com.oraculo.Adapters.TableHittingAdapter;
import mdelacalle.com.oraculo.Adapters.TableHittingHeaderAdapter;
import mdelacalle.com.oraculo.Adapters.TableHittingPlayerAdapter;
import mdelacalle.com.oraculo.Adapters.TableHittingPlayerHeaderAdapter;
import mdelacalle.com.oraculo.Adapters.TablePitchingAverageAdapter;
import mdelacalle.com.oraculo.Adapters.TablePitchingHeaderAdapter;
import mdelacalle.com.oraculo.Adapters.TablePitchingHeaderTeamAdapter;
import mdelacalle.com.oraculo.Adapters.TablePitchingRosterBullpenAdapter;
import mdelacalle.com.oraculo.Adapters.TablePitchingRosterBullpenHeaderAdapter;
import mdelacalle.com.oraculo.Adapters.TablePitchingSeasonAdapter;
import mdelacalle.com.oraculo.Adapters.TablePitchingTeamAdapter;
import mdelacalle.com.oraculo.data.DataFetchingListener;
import mdelacalle.com.oraculo.data.DataUtils;
import mdelacalle.com.oraculo.model.Game;
import mdelacalle.com.oraculo.model.Player;
import mdelacalle.com.oraculo.model.Team;
import mdelacalle.com.oraculo.utils.Constants;


public class GameActivity extends Activity implements DataFetchingListener{

    Calendar calendar= Calendar.getInstance();
    private ImageView _arrieta;
    private Game _game;
    private String _homeID;
    private String _pitcherHomeID;
    private String _pitcherAwayID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


       _arrieta = (ImageView)findViewById(R.id.arrieta);
        _arrieta.bringToFront();

        Intent intent = getIntent();
        String gameID = intent.getStringExtra(Constants.GAME_ID);
        _homeID =  intent.getStringExtra(Constants.TEAM_HOME_ID);
        String awayID = intent.getStringExtra(Constants.TEAM_AWAY_ID);
        _pitcherHomeID = intent.getStringExtra(Constants.PITCHER_HOME_ID);
        _pitcherAwayID = intent.getStringExtra(Constants.PITCHER_AWAY_ID);

        _game = new Game();
        DataUtils.getTeamStats(String.valueOf(calendar.get(GregorianCalendar.DAY_OF_MONTH)) ,String.valueOf(calendar.get(GregorianCalendar.MONTH)+1), String.valueOf(calendar.get(GregorianCalendar.YEAR)),awayID,GameActivity.this, _pitcherAwayID,false,Constants.APIKey);

    }

    @Override
    public void onGamesReady(ArrayList<Game> games) {

    }

    public ArrayList<Player> getPitchers(ArrayList<Player> roster){

        ArrayList<Player> pitchers = new ArrayList<Player>();

        for(Player player:roster) {
            if (player.getPosition().equals("P")) {
                pitchers.add(player);
            }
        }
        return pitchers;

    }

    @Override
    public void onTeamReady(final Team team,final boolean home) {


        if(home){
            _game.setHome(team);
        }else{
            _game.setAway(team);
        }




        if(_game.getHome()!=null&&_game.getAway()!=null) {

            if (home) {
                _game.setHome(team);
            } else {
                _game.setAway(team);
            }

            if (_game.getHome() != null && _game.getAway() != null) {

                Log.wtf("GameActivity", "Team Home:" + _game.getHome().getName() + " Team Away:" + _game.getAway().getName());

                //Retrieving pitchers career's info

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                      TextView awayName = (TextView)findViewById(R.id.away);
                        awayName.setText(_game.getAway().getName());
                        TextView awayRecordName = (TextView)findViewById(R.id.recordAway);
                        awayRecordName.setText(_game.getAway().getGames().getWin() + "-" + _game.getAway().getGames().getLoss());
                        TextView homeName = (TextView)findViewById(R.id.home);
                        homeName.setText(_game.getHome().getName());
                        TextView homeRecordName = (TextView)findViewById(R.id.recordHome);
                        homeRecordName.setText(_game.getHome().getGames().getWin() + "-" + _game.getHome().getGames().getLoss());


                        TableView tableHitting = (TableView) findViewById(R.id.tableTeamHitting);
                        tableHitting.setColumnWeight(0,3);
                        ArrayList<Team> teams = new ArrayList<Team>();
                        teams.add(_game.getAway());
                        teams.add(_game.getHome());

                        TableHittingAdapter adapterTableTeamHitting = new TableHittingAdapter(getBaseContext(), teams);
                        TableHittingHeaderAdapter adapterHeaderTableTeamHitting = new TableHittingHeaderAdapter(getBaseContext());
                        tableHitting.setHeaderAdapter(adapterHeaderTableTeamHitting);
                        tableHitting.setDataAdapter(adapterTableTeamHitting);

                        TableView tablePitchingSeasonMLB = (TableView) findViewById(R.id.tableTeamSeasonMLBPitching);
                        tablePitchingSeasonMLB.setColumnWeight(0,3);
                        TablePitchingHeaderTeamAdapter adapterHeaderTableTeamPitching = new TablePitchingHeaderTeamAdapter(getBaseContext());
                        tablePitchingSeasonMLB.setHeaderAdapter(adapterHeaderTableTeamPitching);
                        TablePitchingTeamAdapter adapterTableTeamPitching = new TablePitchingTeamAdapter(getBaseContext(), teams);
                        tablePitchingSeasonMLB.setDataAdapter(adapterTableTeamPitching);

                        TableHittingPlayerHeaderAdapter adapterHeaderTablePlayerHitting = new TableHittingPlayerHeaderAdapter(getBaseContext());

                        SortableTableView tableHittingPlayerAway = (SortableTableView) findViewById(R.id.tableRosterHittingAway);
                        tableHittingPlayerAway.setColumnWeight(0, 3);
                        ArrayList<Player> rosterAway = _game.getAway().getRoster();

                        TableHittingPlayerAdapter adapterTablePlayerAwayHitting = new TableHittingPlayerAdapter(getBaseContext(), rosterAway);
                        tableHittingPlayerAway.setHeaderAdapter(adapterHeaderTablePlayerHitting);
                        tableHittingPlayerAway.setColumnComparator(1,new HittingObpComparator());
                        tableHittingPlayerAway.setDataAdapter(adapterTablePlayerAwayHitting);

                        SortableTableView tableHittingPlayerHome = (SortableTableView) findViewById(R.id.tableRosterHittingHome);
                        tableHittingPlayerAway.setColumnWeight(0, 3);
                        ArrayList<Player> rosterHome = _game.getHome().getRoster();

                        TableHittingPlayerAdapter adapterTablePlayerHomeHitting = new TableHittingPlayerAdapter(getBaseContext(), rosterHome);
                        tableHittingPlayerHome.setHeaderAdapter(adapterHeaderTablePlayerHitting);
                        tableHittingPlayerHome.setColumnComparator(1,new HittingObpComparator());
                        tableHittingPlayerHome.setDataAdapter(adapterTablePlayerHomeHitting);


                        TablePitchingRosterBullpenHeaderAdapter adapterHeaderBullpen = new TablePitchingRosterBullpenHeaderAdapter(getBaseContext());

                        SortableTableView tableBullpenAway = (SortableTableView) findViewById(R.id.tableBullpenAway);
                        tableBullpenAway.setColumnWeight(0, 3);

                        TablePitchingRosterBullpenAdapter adapterTablePlayerAwayPitchingBullpen = new TablePitchingRosterBullpenAdapter(getBaseContext(), getPitchers(rosterAway));
                        tableBullpenAway.setHeaderAdapter(adapterHeaderBullpen);
                        tableBullpenAway.setColumnComparator(2,new PitchingERAComparator());
                        tableBullpenAway.setDataAdapter(adapterTablePlayerAwayPitchingBullpen);

                        SortableTableView tableBullpenHome = (SortableTableView) findViewById(R.id.tableBullpenHome);
                        tableBullpenHome.setColumnWeight(0, 3);

                        TablePitchingRosterBullpenAdapter adapterTablePlayerHomePitchingBullpen = new TablePitchingRosterBullpenAdapter(getBaseContext(), getPitchers(rosterHome));
                        tableBullpenHome.setHeaderAdapter(adapterHeaderBullpen);
                        tableBullpenHome.setColumnComparator(2,new PitchingERAComparator());
                        tableBullpenHome.setDataAdapter(adapterTablePlayerHomePitchingBullpen);


                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                DataUtils.getPlayerStats(_pitcherAwayID,String.valueOf(calendar.get(GregorianCalendar.DAY_OF_MONTH)) ,String.valueOf(calendar.get(GregorianCalendar.MONTH)+1), String.valueOf(calendar.get(GregorianCalendar.YEAR)),GameActivity.this,false, Constants.APIKey2);
                            }
                        }, 1000);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                DataUtils.getPlayerStats(_pitcherHomeID,String.valueOf(calendar.get(GregorianCalendar.DAY_OF_MONTH)) ,String.valueOf(calendar.get(GregorianCalendar.MONTH)+1), String.valueOf(calendar.get(GregorianCalendar.YEAR)),GameActivity.this,true,Constants.APIKey);
                            }
                        }, 2000);


                  }
                });
            }

            } else {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Log.wtf("GameActivity", "Team parsed:" + team.getName() + " is Home:" + home);
                            DataUtils.getTeamStats(String.valueOf(calendar.get(GregorianCalendar.DAY_OF_MONTH)) ,String.valueOf(calendar.get(GregorianCalendar.MONTH)+1), String.valueOf(calendar.get(GregorianCalendar.YEAR)),_homeID,GameActivity.this,_pitcherHomeID,true,Constants.APIKey2);
                        }
                    }, 1000);
                }
            });
            }

        }

    @Override
    public void onPlayerReady(Player player,boolean home) {

        Log.wtf("GameActivity", "Player parsed:" + player.getFirstName());


        if(home){
            _game.getHome().setProbablePitcher(player);
        }else{
            _game.getAway().setProbablePitcher(player);
        }

        if (_game.getHome().getProbablePitcher().getCareer() != null && _game.getAway().getProbablePitcher().getCareer() != null) {


            runOnUiThread(new Runnable() {
                @Override
                public void run() {


            TableView tableSeasonPitching = (TableView) findViewById(R.id.tableTeamSeasonPitching);
            tableSeasonPitching.setColumnWeight(0, 2);
            TableView tableAveragePitching = (TableView) findViewById(R.id.tableTeamAveragePitching);
            tableAveragePitching.setColumnWeight(0, 2);
            TablePitchingHeaderAdapter adapterHeaderTablePitching = new TablePitchingHeaderAdapter(getBaseContext());
            tableSeasonPitching.setHeaderAdapter(adapterHeaderTablePitching);
            tableAveragePitching.setHeaderAdapter(adapterHeaderTablePitching);

            ArrayList<Player> pitchers = new ArrayList<Player>();

            pitchers.add(_game.getAway().getProbablePitcher());
            pitchers.add(_game.getHome().getProbablePitcher());

            TablePitchingSeasonAdapter tablePitchingSeasonAdapter = new TablePitchingSeasonAdapter(getBaseContext(), pitchers);
            tableSeasonPitching.setDataAdapter(tablePitchingSeasonAdapter);

            TablePitchingAverageAdapter tablePitchingAverageAdapter = new TablePitchingAverageAdapter(getBaseContext(), pitchers);
            tableAveragePitching.setDataAdapter(tablePitchingAverageAdapter);







                    _arrieta.setVisibility(View.GONE);
                }
            });


        }


    }

    @Override
    public void onDataError() {

    }

    private static class HittingObpComparator implements Comparator<Player>

    {
        @Override
        public int compare(Player lhs, Player rhs) {
            if(lhs.getHitting()==null) { return 0;}
            if (lhs.getHitting().getObp() > rhs.getHitting().getObp()){
                return -1;
            }
            return +1;
        }
    }

    private static class PitchingERAComparator implements Comparator<Player>

    {
        @Override
        public int compare(Player lhs, Player rhs) {
            if(lhs.getPitching()==null) { return 0;}
            if (lhs.getPitching().getEra() > rhs.getPitching().getEra()){
                return -1;
            }
            return +1;
        }
    }


}
