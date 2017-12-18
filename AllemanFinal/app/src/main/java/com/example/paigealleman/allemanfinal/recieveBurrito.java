package com.example.paigealleman.allemanfinal;

import android.support.v7.app.AppCompatActivity;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
import android.view.View;
import android.widget.ImageButton;

public class recieveBurrito extends AppCompatActivity {

    private String burritoShop;
    private String burritoShopURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve_burrito);

        //get intent
        Intent intent = getIntent();
        burritoShop = intent.getStringExtra("burritoShopName");
        burritoShopURL = intent.getStringExtra("burritoShopURL");

        //update the text view
        TextView messageView = (TextView)findViewById(R.id.burritoTextView);
        messageView.setText(burritoShop+ "!");

        final ImageButton imagebutton = (ImageButton)findViewById(R.id.imageButton);
        View.OnClickListener onclick = new View.OnClickListener(){
            public void onClick(View view){
                loadWebSite(view);
            }
        };

    }
    //add link to outside website
    public void loadWebSite(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(burritoShopURL));
        startActivity(intent);
    }
}
