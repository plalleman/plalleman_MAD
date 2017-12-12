package com.example.paigealleman.lab6;

import android.support.v7.app.AppCompatActivity;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
import android.view.View;
import android.widget.ImageButton;

public class ReceiveSushiActivity extends AppCompatActivity {

    private String sushiShop;
    private String sushiShopURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_sushi);

        //get intent
        Intent intent = getIntent();
        sushiShop = intent.getStringExtra("sushiShopName");
        sushiShopURL = intent.getStringExtra("sushiShopURL");

        //update the text view to show what we got from the intent

        TextView messageView = (TextView)findViewById(R.id.sushiTextView);
        messageView.setText(sushiShop + "!");

        //add event listener for clicking the image
        final ImageButton imageButton = (ImageButton)findViewById(R.id.imageButton);
        View.OnClickListener onclick = new View.OnClickListener(){
            public void onClick(View view){
                loadWebSite(view);
            }
        };
    }

    //add in implicit intent
    public void loadWebSite(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(sushiShopURL));
        startActivity(intent);
    }
}
