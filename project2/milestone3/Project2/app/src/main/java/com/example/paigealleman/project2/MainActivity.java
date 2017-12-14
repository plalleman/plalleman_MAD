package com.example.paigealleman.project2;

//import android stuff
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Calendar;

//import json schtuff
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

//import api stuff
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    //write down the api key and how to access it
    //  NOTE: API KEY is: 032a646cfb7e6d04ed5bfd097e5ea6bb
    //ID for the city of boulder is: 5586587
    static final String API_URL = "http://api.openweathermap.org/data/2.5/forecast?id=5586587&APPID=032a646cfb7e6d04ed5bfd097e5ea6bb";
    String APIresponse;
    TextView results;
    ImageView weathericon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myButton = (Button)findViewById(R.id.snowbutton);
        //create listener
        View.OnClickListener onclick = new View.OnClickListener(){
            public void onClick(View view){
                new RetrieveFeedTask().execute();
            }
        };
        myButton.setOnClickListener(onclick);
        results = (TextView)findViewById(R.id.resultsView);
        weathericon = (ImageView)findViewById(R.id.imageView2);

        //get the date from the system to display in the app
        String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        //display the date in time in the date view
        TextView date = (TextView)findViewById(R.id.dateView);
        date.setText(mydate);
    }




    class RetrieveFeedTask extends AsyncTask<Void, Void, String>{
        private Exception exception;

        protected void onPreExecute() {
            //progressBar.setVisibility(View.VISIBLE);
            //set the display text for the result to be blank
            results.setText("");
        }

        protected String doInBackground(Void... urls) {

            //connect to the api
            try {
                URL url = new URL(API_URL);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if(response == null) {
                response = "THERE WAS AN ERROR";
            }
            //hide some things to display the result
            Button myButton = (Button)findViewById(R.id.snowbutton);
            myButton.setVisibility(View.GONE);
            Log.i("INFO", response);
            //do we actually get anything back?
            APIresponse = response;
            //results.setText("The weather today is: " + APIresponse); verifies we do get schtuff

            /*try {
                JSONObject json = new JSONObject(response);
                JSONObject json_weather = json.getJSONObject("main");
                //look for main weather activity and save it
                APIresponse = json_weather.getString("temp");
                //look for any snow activity and save it as snow value
                //took this out for now because sometimes there isn't a snow value
                //String snow_value=json_LL.getString("snow");

            }

            catch (JSONException e) {
                e.printStackTrace();
            }*/

            //put stuff into the view
            results.setText("The weather today is: " + APIresponse);




        }
    }

}
