package mdelacalle.com.oraculo.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import mdelacalle.com.oraculo.GameActivity;
import mdelacalle.com.oraculo.R;
import mdelacalle.com.oraculo.model.Game;
import mdelacalle.com.oraculo.utils.Constants;

/**
 * Created by mdelacalle on 16/04/16.
 */
public class ListGameAdapter extends BaseAdapter {

    private final Activity _activity;
    private ArrayList<Game> _games;

    public ListGameAdapter(ArrayList<Game> games, Activity parentActivity) {
        _activity = parentActivity;
        _games = games;
    }

    public String toImageCoventionName(String name){
        String imageName = "";
        imageName = name.replace(' ','_');
        return imageName.toLowerCase();
    }

    @Override
    public int getCount() {
        return _games.size();
    }

    @Override
    public Game getItem(int position) {
        return _games.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) _activity
                    .getSystemService(_activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.game_grid_item, parent, false);
        }

        TextView home = (TextView) view.findViewById(R.id.home);
        TextView  namePitcherHome = (TextView) view.findViewById(R.id.namePitcherHome);
        TextView  winHome = (TextView) view.findViewById(R.id.winHome);
        TextView lostHome = (TextView) view.findViewById(R.id.lostHome);
        TextView  eraHome = (TextView) view.findViewById(R.id.eraHome);
        TextView   away = (TextView) view.findViewById(R.id.away);
        TextView   namePitcherAway = (TextView) view.findViewById(R.id.namePitcherAway);
        TextView   winAway = (TextView) view.findViewById(R.id.winAway);
        TextView  lostAway = (TextView) view.findViewById(R.id.lostAway);
        TextView   eraAway = (TextView) view.findViewById(R.id.eraAway);
        RelativeLayout   layoutGame = (RelativeLayout) view.findViewById(R.id.gamesLayout);
        ImageView awayImage = (ImageView)view.findViewById(R.id.awayImageView);
        ImageView homeImage = (ImageView)view.findViewById(R.id.homeImageView);



        final Game game = getItem(position);
        home.setText(game.getHome().getName());


        int homeImageResource = _activity.getResources().getIdentifier(toImageCoventionName(game.getHome().getName()), "mipmap", _activity.getPackageName());
        Log.wtf("names",toImageCoventionName(game.getHome().getName()));
        Drawable image = _activity.getResources().getDrawable(homeImageResource);
        homeImage.setImageDrawable(image);


        namePitcherHome.setText(game.getHome().getProbablePitcher().getFirstName()+ " " + game.getHome().getProbablePitcher().getLastName());
        winHome.setText(""+game.getHome().getProbablePitcher().getWin());
        lostHome.setText(""+game.getHome().getProbablePitcher().getLoss());
        eraHome.setText(""+ game.getHome().getProbablePitcher().getEra());
        away.setText(game.getAway().getName());

        int awayImageResource = _activity.getResources().getIdentifier(toImageCoventionName(game.getAway().getName()), "mipmap", _activity.getPackageName());
        Drawable image2 = _activity.getResources().getDrawable(awayImageResource);
        awayImage.setImageDrawable(image2);

        namePitcherAway.setText(game.getAway().getProbablePitcher().getFirstName()+ " " + game.getAway().getProbablePitcher().getLastName());
        winAway.setText(""+game.getAway().getProbablePitcher().getWin());
        lostAway.setText(""+game.getAway().getProbablePitcher().getLoss());
        eraAway.setText(""+ game.getAway().getProbablePitcher().getEra());

        layoutGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //starting the right demo
                Log.d(ListGameAdapter.this.toString(), game.getId());
                Intent intentGame = new Intent(_activity, GameActivity.class);
                intentGame.putExtra(Constants.GAME_ID, game.getId());
                intentGame.putExtra(Constants.TEAM_HOME_ID, game.getHome().getId());
                intentGame.putExtra(Constants.PITCHER_HOME_ID, game.getHome().getProbablePitcher().getId());
                intentGame.putExtra(Constants.TEAM_AWAY_ID, game.getAway().getId());
                intentGame.putExtra(Constants.PITCHER_AWAY_ID, game.getAway().getProbablePitcher().getId());
                _activity.startActivity(intentGame);
            }
        });

        return view;
    }
}

