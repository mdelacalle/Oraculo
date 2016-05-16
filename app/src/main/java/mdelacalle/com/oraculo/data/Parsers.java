package mdelacalle.com.oraculo.data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mdelacalle.com.oraculo.model.Career;
import mdelacalle.com.oraculo.model.Fielding;
import mdelacalle.com.oraculo.model.Game;
import mdelacalle.com.oraculo.model.Games;
import mdelacalle.com.oraculo.model.Hitting;
import mdelacalle.com.oraculo.model.OnBase;
import mdelacalle.com.oraculo.model.Outcome;
import mdelacalle.com.oraculo.model.Pitching;
import mdelacalle.com.oraculo.model.Player;
import mdelacalle.com.oraculo.model.Season;
import mdelacalle.com.oraculo.model.SeasonHittingMLBStats;
import mdelacalle.com.oraculo.model.SeasonPitchingMLBStats;
import mdelacalle.com.oraculo.model.Team;

/**
 * Created by mdelacalle on 27/04/16.
 */
public class Parsers {

    public static ArrayList<Game> parseBoxScore(String jsonString) throws JSONException {

        ArrayList<Game> games = new ArrayList<Game>();


        JSONObject gamesJO = new JSONObject(jsonString);
        JSONObject leagueJO = gamesJO.getJSONObject("league");
        JSONArray gamesJA = leagueJO.getJSONArray("games");
        Log.d("Parser Games","Num. Games:" + gamesJA.length());

        for(int i = 0 ; i < gamesJA.length() ; i++){
            Game game = new Game();

            JSONObject gameJO = gamesJA.getJSONObject(i);
            JSONObject gameJO2 = gameJO.getJSONObject("game");
            game.setId(gameJO2.getString("id"));
            game.setStatus(gameJO2.getString("status"));

            Team home = new Team();
            JSONObject homeJO = gameJO2.getJSONObject("home");
            home = parseTeam(homeJO);
            game.setHome(home);

            Team away = new Team();
            JSONObject awayJO = gameJO2.getJSONObject("away");
            away = parseTeam(awayJO);
            game.setAway(away);
            games.add(game);
        }

       return games;
    }


    public static Team parseTeamStats(String jsonString, String probablePitcherId)throws JSONException {
        Team team = new Team();
        JSONObject teamJO = new JSONObject(jsonString);
        team.setName(teamJO.getString("name"));
        team.setMarket(teamJO.getString("market"));
        team.setAbbr(teamJO.getString("abbr"));
        team.setId(teamJO.getString("id"));



        JSONObject statisticsTeamJO = teamJO.getJSONObject("statistics");
        team.setGames(parseGames(statisticsTeamJO.getJSONObject("pitching").getJSONObject("games")));
        if(statisticsTeamJO.has("pitching")) {
            team.setPitching(parsePitching(statisticsTeamJO.getJSONObject("pitching")));
        }
        if(statisticsTeamJO.has("hitting")) {
            team.setHitting(parseHitting(statisticsTeamJO.getJSONObject("hitting")));
        }
      //  team.setFielding(parseFielding(statisticsTeamJO.getJSONObject("fielding")));

        ArrayList<Player> roster = new ArrayList<Player>();

        JSONArray rosterJO = teamJO.getJSONArray("players");

        for(int i=0; i < rosterJO.length() ; i++ ){

            if (probablePitcherId.equals(rosterJO.getJSONObject(i).getString("id"))){
                team.setProbablePitcher(parsePlayer(rosterJO.getJSONObject(i),true , false));
            }
            roster.add(parsePlayer(rosterJO.getJSONObject(i),true , false));
        }


        team.setRoster(roster);


        return team;
    }

    public static Games parseGames(JSONObject gamesJO) throws JSONException {
        Games games = new Games();
        games.setWin(gamesJO.getInt("win"));
        games.setLoss(gamesJO.getInt("loss"));

        return games;
    }

    public static Hitting parseHitting(JSONObject hittingJO) throws JSONException {
        Hitting hitting = new Hitting();
        hitting.setAb(hittingJO.getDouble("ab"));
        hitting.setBabip(hittingJO.getDouble("babip"));
        hitting.setIso(hittingJO.getDouble("iso"));
        hitting.setKtotal(hittingJO.getJSONObject("outcome").getInt("ktotal"));
        hitting.setSacFly(hittingJO.getJSONObject("outs").getInt("sacfly"));
        hitting.setObp(hittingJO.getDouble("obp"));
        hitting.setOps(hittingJO.getDouble("ops"));
        hitting.setRuns(hittingJO.getJSONObject("runs").getInt("total"));;
        hitting.setSlg(hittingJO.getDouble("slg"));
        hitting.setRbi(hittingJO.getDouble("rbi"));
        hitting.setBbk(hittingJO.getDouble("bbk"));
        hitting.setAvg(hittingJO.getDouble("avg"));
        hitting.setOnBase(parseOnBase(hittingJO.getJSONObject("onbase")));
        hitting.setOutcome(parseOutcome(hittingJO.getJSONObject("outcome")));
        return hitting;
    }

    public static Pitching parsePitching(JSONObject pitchingJO) throws JSONException {
        Pitching pitching = new Pitching();
        pitching.setRuns(pitchingJO.getJSONObject("runs").getInt("total"));
        pitching.setEra(pitchingJO.getDouble("era"));
        //pitching.setFip();
        pitching.setK9(pitchingJO.getDouble("k9"));
        pitching.setKbb(pitchingJO.getDouble("kbb"));
        pitching.setWhip(pitchingJO.getDouble("whip"));
        pitching.setIp1(pitchingJO.getDouble("ip_1"));
        pitching.setIp2(pitchingJO.getDouble("ip_2"));
        pitching.setOnbase(parseOnBase(pitchingJO.getJSONObject("onbase")));
        pitching.setOutcome(parseOutcome(pitchingJO.getJSONObject("outcome")));

        return pitching;
    }

    public static Outcome parseOutcome(JSONObject outcomeJO) throws JSONException {
        Outcome outcome = new Outcome();
        outcome.setKtotal(outcomeJO.getInt("ktotal"));
        outcome.setBall(outcomeJO.getInt("ball"));
        outcome.setIntentionalBall(outcomeJO.getInt("iball"));
        return outcome;
    }

    public static OnBase parseOnBase(JSONObject onBaseJO) throws JSONException {
        OnBase onBase = new OnBase();
        onBase.setSingles(onBaseJO.getInt("s"));
        onBase.setDoubles(onBaseJO.getInt("d"));
        onBase.setTriples(onBaseJO.getInt("t"));
        onBase.setHomeruns(onBaseJO.getInt("hr"));
        onBase.setBb(onBaseJO.getInt("bb"));
        onBase.setHbp(onBaseJO.getInt("hbp"));
        onBase.setH(onBaseJO.getInt("h"));
        onBase.setTb(onBaseJO.getInt("tb"));
        return onBase;
    }


    public static Fielding parseFielding(JSONObject fieldingJO) throws JSONException {
        Fielding fielding = new Fielding();
        fielding.setA(fieldingJO.getDouble("a"));
        fielding.setPo(fieldingJO.getDouble("po"));
        return fielding;
    }


   public static Team parseTeam(JSONObject teamJO) throws JSONException {

       Team team = new Team();
       team.setName(teamJO.getString("name"));
       team.setMarket(teamJO.getString("market"));
       team.setAbbr(teamJO.getString("abbr"));
       team.setId(teamJO.getString("id"));
       team.setProbablePitcher(parsePlayer(teamJO.getJSONObject("probable_pitcher"),false,false));
      // team.setHitting(parseHitting(teamJO.getJSONObject("hitting)));"
       return team;
   }

    public static Player parsePlayer(JSONObject playerJO, boolean hasStats,boolean hasCareer ) throws JSONException {
        Player player = new Player();

        player.setLastName(playerJO.getString("last_name"));
        player.setFirstName(playerJO.getString("first_name"));
        player.setPreferredName(playerJO.getString("preferred_name"));
        player.setJerseyNumber(playerJO.getString("jersey_number"));
        player.setId(playerJO.getString("id"));


        if(!hasCareer&&!hasStats){
            player.setWin(playerJO.getInt("win"));
            player.setLoss(playerJO.getInt("loss"));
            player.setEra(playerJO.getDouble("era"));
        }
        if(hasCareer) {
            player.setPosition(playerJO.getString("position"));
            JSONArray seasonsJsonArray = playerJO.getJSONArray("seasons");

            ArrayList<Season> seasons = new ArrayList<>();
            for (int i = 0; i < seasonsJsonArray.length(); i++) {
                JSONObject seasonJO = seasonsJsonArray.getJSONObject(i);
                JSONArray teamSeasonalStatsJsonArray = seasonJO.getJSONArray("teams");
                for (int a = 0; a < teamSeasonalStatsJsonArray.length(); a++) {
                    JSONObject teamSeasonalStatsJO = teamSeasonalStatsJsonArray.getJSONObject(a);
                    Season season = new Season();
                    season.setSeason(seasonJO.getInt("year"));
                    season.setTeam(teamSeasonalStatsJO.getString("name"));

                    JSONObject statsJO = teamSeasonalStatsJO.getJSONObject("statistics");
                    if (statsJO.has("hitting")) {
                        season.setHitting(parseHitting(statsJO.getJSONObject("hitting")));
                    }
                    if (statsJO.has("pitching")) {
                        season.setPitching(parsePitching(statsJO.getJSONObject("pitching")));
                    }
                    if (statsJO.has("fielding")) {
                        season.setFielding(parseFielding(statsJO.getJSONObject("fielding")));
                    }
                    seasons.add(season);
                }
                Career career = new Career();
                career.setSeasons(seasons);
                player.setCareer(career);
            }
        }
            if (hasStats && !hasCareer) {

                player.setPosition(playerJO.getString("position"));
                JSONObject statisticsPLayerJO = playerJO.getJSONObject("statistics");

                if (player.getPosition().equals("P")) {

                    if (statisticsPLayerJO.has("pitching")) {
                        player.setWin(parseGames(statisticsPLayerJO.getJSONObject("pitching").getJSONObject("games")).getWin());
                        player.setLoss(parseGames(statisticsPLayerJO.getJSONObject("pitching").getJSONObject("games")).getLoss());
                        player.setEra(parsePitching(statisticsPLayerJO.getJSONObject("pitching")).getEra());
                        player.setPitching(parsePitching(statisticsPLayerJO.getJSONObject("pitching")));
                    }
                }
                if (statisticsPLayerJO.has("hitting")) {
                    player.setHitting(parseHitting(statisticsPLayerJO.getJSONObject("hitting")));
                }
                // player.setFielding(parseFielding(statisticsPLayerJO.getJSONObject("fielding")));
            }

        return player;
    }


    public static SeasonHittingMLBStats parseMLBSHittingStats(String teamHittingSeasonLeader) throws JSONException {

        SeasonHittingMLBStats sMLBStats = new SeasonHittingMLBStats();
        JSONObject tHSLJO = new JSONObject(teamHittingSeasonLeader);
        JSONObject tHSLM = tHSLJO.getJSONObject("team_hitting_season_leader_master");
        JSONObject queryResultsJO = tHSLM.getJSONObject("queryResults");
        JSONArray hittingStatsArrayJO = queryResultsJO.getJSONArray("row");

        ArrayList<Team> teams = new ArrayList<>();
        for (int i = 0 ; i < hittingStatsArrayJO.length(); i++){
            Team team = new Team();
            JSONObject row = hittingStatsArrayJO.getJSONObject(i);
            team.setAbbr(row.getString("team_abbrev"));

            Hitting hitting =  new Hitting();
            hitting.setSlg(Double.parseDouble("0"+row.getString("slg")));
            hitting.setOps(Double.parseDouble("0"+row.getString("ops")));
            hitting.setRuns(Integer.parseInt(row.getString("r")));
            hitting.setRbi(Double.parseDouble(row.getString("rbi")));
            hitting.setAvg(Double.parseDouble("0"+row.getString("avg")));
          //  hitting.setBbk(Double.parseDouble("0"+row.getString("bbk")));
            team.setHittingRank(Integer.parseInt(row.getString("rank")));
            team.setHitting(hitting);

            teams.add(team);
        }
        sMLBStats.setTeams(teams);
        return sMLBStats;
    }

    public static SeasonPitchingMLBStats parseMLBPitchingStats(String teamPitchingSeasonLeader) throws JSONException {
        SeasonPitchingMLBStats sMLBStats = new SeasonPitchingMLBStats();
        JSONObject tPSLJO = new JSONObject(teamPitchingSeasonLeader);
        JSONObject tPSLM = tPSLJO.getJSONObject("team_pitching_season_leader_master");
        JSONObject queryResultsJO = tPSLM.getJSONObject("queryResults");
        JSONArray pitchingStatsArrayJO = queryResultsJO.getJSONArray("row");

        ArrayList<Team> teams = new ArrayList<>();
        for (int i = 0 ; i < pitchingStatsArrayJO.length(); i++) {
            Team team = new Team();
            JSONObject row = pitchingStatsArrayJO.getJSONObject(i);
            team.setAbbr(row.getString("team_abbrev"));
            Pitching pitching =  new Pitching();
            OnBase onBase = new OnBase();

            onBase.setH(Integer.parseInt(row.getString("h")));
            onBase.setHomeruns(Integer.parseInt(row.getString("hr")));
            pitching.setRuns(Integer.parseInt(row.getString("r")));
            pitching.setIp1(Double.parseDouble("0"+row.getString("ip")));
            pitching.setEra(Double.parseDouble("0"+row.getString("era")));
            pitching.setWhip(Double.parseDouble("0"+row.getString("whip")));
            pitching.setObpAllowed(Double.parseDouble("0"+row.getString("obp")));
            pitching.setSlgAllowed(Double.parseDouble("0"+row.getString("slg")));
            pitching.setOnbase(onBase);
            team.setPitching(pitching);
            teams.add(team);
        }


        sMLBStats.setTeams(teams);
        return sMLBStats;

    }
}
