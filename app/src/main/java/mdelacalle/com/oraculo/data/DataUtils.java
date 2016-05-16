package mdelacalle.com.oraculo.data;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

import mdelacalle.com.oraculo.OraculoSesion;
import mdelacalle.com.oraculo.model.Player;
import mdelacalle.com.oraculo.utils.Constants;

/**
 * Created by mdelacalle on 27/04/16.
 */
public  class DataUtils {

    private static  DataFetchingListener _listener;
    static String extensionJson = ".json";
    private static OraculoSesion _os;


    public static void getPlayerStats(final String playerID , final String day, final String month, final String year , final DataFetchingListener listener , final boolean home , final String apiKey ){
        _listener = listener;

        try{

            if(internalFileExists(playerID+"@"+year+month+day+ extensionJson)){
                Log.wtf("WTF","This player data has been downloaded previously. Fetching from internal storage");
                FileInputStream in = ((Activity)_listener).openFileInput(playerID+"@"+year+month+day+ extensionJson);
                InputStreamReader inputStreamReader = new InputStreamReader(in);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }

                if(sb.length()<5){
                    Log.wtf("Players Stats","Trash  internal memory");
                    removeFile(playerID+"@"+year+month+day+ extensionJson);
                }

                JSONObject playerJO = new JSONObject(sb.toString());
                Player player = Parsers.parsePlayer(playerJO.getJSONObject("player"),false,true);
                listener.onPlayerReady(player,home);
            }else{
                Log.wtf("WTF","Downloading player data and saving on internal storage");
                Log.wtf("WTF","http://api.sportradar.us/mlb-t5/players/"+playerID+"/profile.json?api_key="+apiKey);


                final Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Log.wtf("WTF","Exists");
                        final String player = getJsonResponse("http://api.sportradar.us/mlb-t5/players/"+playerID+"/profile.json?api_key="+apiKey);

                        writeToFile(player,day,month,playerID+"@"+year);
                        try {
                            JSONObject playerJO = new JSONObject(player);
                            Player playerParsed = Parsers.parsePlayer( playerJO.getJSONObject("player"),false,true );
                            listener.onPlayerReady(playerParsed,home);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();

            }

        }catch (Exception e){
            Log.wtf("DataUtils reading players stats:","Error reading internal file:" + e.getMessage() );

        }

    }

    public static void getTeamStats(final String day, final String month, final String year, final String idTeam, final DataFetchingListener listener, final String probablePitcherId, final boolean home, final String apiKey){

        _listener = listener;
        try{
            if(internalFileExists(idTeam+"@"+year+month+day+ extensionJson)){

                Log.wtf("WTF","Exists");
                FileInputStream in = ((Activity)_listener).openFileInput(idTeam+"@"+year+month+day+ extensionJson);
                InputStreamReader inputStreamReader = new InputStreamReader(in);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                if(sb.length()<5){
                    Log.wtf("Team Stats","Trash on internal memory");
                    removeFile(idTeam+"@"+year+month+day+ extensionJson);
                }
                listener.onTeamReady(Parsers.parseTeamStats(sb.toString(),probablePitcherId),home);
            }else{

                Log.wtf("WTF","http://api.sportradar.us/mlb-t5/seasontd/"+year+"/REG/teams/"+idTeam+"/statistics.json?api_key="+ apiKey);

                final Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                     //   final String boxScore = getJsonResponse("http://api.sportradar.us/mlb-t5/games/"+year+"/"+month+"/"+day+"/boxscore.json?api_key="+ Constants.APIKey );

                        Log.wtf("WTF","Non Exists, dowloading it");
                        final String team = getJsonResponse("http://api.sportradar.us/mlb-t5/seasontd/"+year+"/REG/teams/"+idTeam+"/statistics.json?api_key="+apiKey );
                          writeToFile(team,day,month,idTeam+"@"+year);
                        try {
                            listener.onTeamReady(Parsers.parseTeamStats(team,probablePitcherId),home);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();

            }
    }catch (Exception e){
        Log.wtf("DataUtils Team Stats", idTeam+"@"+year+month+day+ extensionJson+"    Error reading internal file:" + e.getMessage() );
    }

    }


    public static void getDailyBoxScore(final String day, final String month, final String year, final DataFetchingListener listener,final String apiKey){

        _listener = listener;
        try{
       if(internalFileExists(year+month+day+ extensionJson)){
           Log.wtf("WTF","Exists");
           FileInputStream in = ((Activity)_listener).openFileInput(year+month+day+ extensionJson);
               InputStreamReader inputStreamReader = new InputStreamReader(in);
               BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
               StringBuilder sb = new StringBuilder();
               String line;
               while ((line = bufferedReader.readLine()) != null) {
                   sb.append(line);
               }
               listener.onGamesReady(Parsers.parseBoxScore(sb.toString()));
               if(sb.length()<5){
                   Log.wtf("Daily Box Score Stats","Trash on internal memory");
                   removeFile(year+month+day+ extensionJson);
               }


       }else{
           Log.wtf("WTF","Non Exists");

           final Thread thread = new Thread(new Runnable() {
               @Override
               public void run() {
                  final String boxScore = getJsonResponse("http://api.sportradar.us/mlb-t5/games/"+year+"/"+month+"/"+day+"/boxscore.json?api_key="+ apiKey );
                   //String boxScore = (getJsonResponse("file:///box-score.json"));
                   writeToFile(boxScore,day,month,year);
                   try {
                       listener.onGamesReady(Parsers.parseBoxScore(boxScore));
                   } catch (JSONException e) {
                       e.printStackTrace();
                   }
               }
           });
            thread.start();

       }
        }catch (Exception e){
            Log.wtf("DataUtils Daily Box Score","Error reading internal file:" + e.getMessage() );
        }
    }

    public static boolean internalFileExists(String fname){
        File file = ((Activity)_listener).getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }

    public static boolean removeFile(String fname){
        File file = ((Activity)_listener).getBaseContext().getFileStreamPath(fname);
        return file.delete();
    }


    public static String getJsonResponse(final String url) {

        final StringBuilder builder = new StringBuilder();

        if(url.startsWith("file:///")){

            try {
                InputStream inputStream =  ((Activity)_listener).getResources().getAssets().open(url.substring(8));
                final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }else {


            final HttpGet httpGet = new HttpGet(url);
            final HttpParams httpParameters = new BasicHttpParams();
            final int timeoutConnection = 60000;
            HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
            final HttpClient client = new DefaultHttpClient(httpParameters);
            try {
                final HttpResponse response = client.execute(httpGet);

                final StatusLine statusLine = response.getStatusLine();
                final int statusCode = statusLine.getStatusCode();
                if (statusCode == 200) {
                    final HttpEntity entity = response.getEntity();
                    final InputStream content = entity.getContent();
                    final BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                } else {
                    Log.e("Data Utils:", "Failed to download file");
                }
            } catch (final ClientProtocolException e) {
                Log.e("DataUtilsJSONResponse:", "Failed to download file:" + e.getMessage());
            } catch (final IOException e) {
                Log.e("DataUtilsJSONResponse", "Failed to download file:" + e.getMessage());
            }
        }
        return builder.toString();
    }


    private static void writeToFile(String data,String day,String month,String year) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(((Activity)_listener).openFileOutput(year+month+day+extensionJson, Context.MODE_WORLD_READABLE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


    public static void getOraculoSesion(final String year) {
        _os = OraculoSesion.getInstance();

        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                final String teamHittingSeasonLeader = getJsonResponse("http://mlb.mlb.com/lookup/json/named.team_hitting_season_leader_master.bam?season="
                        + year + "&sort_order='desc'&sort_column='avg'&game_type='R'&sport_code='mlb'&recSP=1&recPP=50");

                final String teamPitchingSeasonLeader = getJsonResponse("http://mlb.mlb.com/lookup/json/named.team_pitching_season_leader_master.bam?season="
                        + year + "&sort_order='desc'&sort_column='avg'&game_type='R'&sport_code='mlb'&recSP=1&recPP=50");


                try {

                    _os.setSeasonHittingMlbStats(Parsers.parseMLBSHittingStats(teamHittingSeasonLeader));
                    _os.setSeasonPitchingMlbStats(Parsers.parseMLBPitchingStats(teamPitchingSeasonLeader));

                } catch (JSONException e) {
                    Log.e("Exception", "Failed parsing the MLB Stats query:"+ e.toString());
                }
            }
        });
        thread.start();
    }
}
