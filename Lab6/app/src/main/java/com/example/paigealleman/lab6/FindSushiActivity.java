package com.example.paigealleman.lab6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;
import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.content.Intent;

public class FindSushiActivity extends AppCompatActivity {

    private SushiShop mySushiShop = new SushiShop();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_sushi);
        //get the button
        final Button button = (Button)findViewById(R.id.button);
        //create the listener
        View.OnClickListener onclick = new View.OnClickListener(){
            public void onClick(View view){
                findSushi(view);
            }
        };
        button.setOnClickListener(onclick);
    }

    public void findSushi(View view){
        //get spinner
        Spinner priceSpinner = (Spinner)findViewById(R.id.spinner);

        //get spinner position
        Integer price = priceSpinner.getSelectedItemPosition();

        //set the sushi shop
        mySushiShop.setSushiShop(price);

        //get suggested sushi shop
        String suggestedSushiShop = mySushiShop.getSushiShop();

        //get URL
        String suggestedSushiShopURL = mySushiShop.getSushiShopURL();
        Log.i("Shop", suggestedSushiShop);
        Log.i("url", suggestedSushiShopURL);

        //create explicit intent
        Intent intent = new Intent(this, ReceiveSushiActivity.class);

        //give it the information it needs
        intent.putExtra("sushiShopName", suggestedSushiShop);
        intent.putExtra("sushiShopURL", suggestedSushiShopURL);

        //start intent
        startActivity(intent);
    }

}

