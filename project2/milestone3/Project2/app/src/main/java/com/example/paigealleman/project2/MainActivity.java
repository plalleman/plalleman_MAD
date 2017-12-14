package com.example.paigealleman.project2;

/* Sources used for this project:
NOTE: Image sources are found in a text file in the images folder. They are open source.
I used:
The Open Weather API (and documentation): http://openweathermap.org/
This tutorial for implementing an API call into the app: https://www.androidauthority.com/use-remote-web-api-within-android-app-617869/
Stackoverflow for helping to get interwebs working on the emulator (command line help) and general JSON parsing reference
 */

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
    static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?&id=5586587&units=imperial&APPID=032a646cfb7e6d04ed5bfd097e5ea6bb";
    String APIresponse;
    String temp;
    TextView results;
    ImageView weathericon;
    String main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myButton = (Button)findViewById(R.id.snowbutton);
        //create listener for button
        View.OnClickListener onclick = new View.OnClickListener(){
            public void onClick(View view){
                //action is to make a new api call ->thanks so tutorial for demonstrating this
                new getWeather().execute();
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




    class getWeather extends AsyncTask<Void, Void, String>{
        private Exception exception;

        protected void onPreExecute() {
            //progressBar.setVisibility(View.VISIBLE);
            //set the display text for the result to be blank
            results.setText("");
        }

        protected String doInBackground(Void... urls) {

            //connect to the api -> Thanks to web tutorial for this part!
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
                //error if I get nothing from the API call
                response = "Error";
            }
            //hide the button (i don't want people overloading my free api - plus more roomy. And how often do people check the weather anyway?)
            Button myButton = (Button)findViewById(R.id.snowbutton);
            myButton.setVisibility(View.GONE);
            //do we actually get anything back?
            //APIresponse = response;
            //results.setText("The weather today is: " + APIresponse); //verifies we do get schtuff


            try {
                //make a json object
                JSONObject json = (JSONObject) new JSONObject(response);
                //get temperature
                temp = json.getJSONObject("main").getString("temp");

                //weather is an array and so requires special treatment
                JSONArray arrayweather = (JSONArray) json.get("weather");
                JSONObject jsonweather = arrayweather.getJSONObject(0);
                APIresponse = jsonweather.getString("description");
                main = jsonweather.getString("main");

            }

            catch (JSONException e) {
                //error if the JSON parsing goes awry
                e.printStackTrace();
                APIresponse = "error";
            }

            //put stuff into the view
            results.setText("The current weather is: " + APIresponse + "." + "\n" + "The temperature is: " + temp + " degrees.");
            //change the image based on the description
            if(main.equals("Clouds")){
                weathericon.setImageResource(R.drawable.cloud);
            }
            else if(main.equals("Rain")){
                weathericon.setImageResource(R.drawable.drop);
            }
            else if(main.equals("Snow")){
                weathericon.setImageResource(R.drawable.snowflake);
            }


        }
    }

}
