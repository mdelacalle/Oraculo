package mdelacalle.com.oraculo.data;



import java.util.ArrayList;

import mdelacalle.com.oraculo.model.Game;
import mdelacalle.com.oraculo.model.Player;
import mdelacalle.com.oraculo.model.Team;

/**
 * Created by mdelacalle on 27/04/16.
 *
 */
public interface DataFetchingListener {

    void onGamesReady(ArrayList<Game> games);
    void onTeamReady(Team team,boolean home);
    void onPlayerReady(Player player,boolean home);
    void onDataError();

}
