package com.example.paigealleman.allemanfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.Switch;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //make burrito shop from class
    private burritoshop myburritoshop = new burritoshop();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //CALL THE BUTTON! so that the function actually runs, woo
        final Button button = (Button)findViewById(R.id.button);
        //create a listener
        View.OnClickListener onclick = new View.OnClickListener(){
            public void onClick(View view){
                buildBurrito(view);
            }
        };
        final Button button2 = (Button)findViewById(R.id.button2);
        View.OnClickListener onclick2 = new View.OnClickListener(){
            public void onClick(View view){
                callNewActivity(view);
            }
        };
        button.setOnClickListener(onclick);
        button2.setOnClickListener(onclick2);

    }

    public void buildBurrito(View view){
        //make some variables so I can throw it all in the text view at the end
        String burritoChoice;
        String topping1 = "";
        String topping2 = "";
        String topping3 = "";
        String topping4 = "";
        String meatChoice = "";
        String gluten = "";

        //don't forget the image change
        ImageView image = (ImageView)findViewById(R.id.imageView);

        //get burrito name
        EditText name = (EditText) findViewById(R.id.editText);
        String burritoName = name.getText().toString();

        //get burrito or taco option
        RadioGroup choice = (RadioGroup)findViewById(R.id.burritochoice);
        int group_id = choice.getCheckedRadioButtonId();

        //get meat toggle
        ToggleButton toggle = (ToggleButton)findViewById(R.id.toggleButton);
        boolean veggie = toggle.isChecked();

        //check boxes for toppings
        //check box 1 is salsa
        CheckBox salsaCheckBox = (CheckBox)findViewById(R.id.checkBox);
        Boolean salsa = salsaCheckBox.isChecked();

        //check box 2 is cheese
        CheckBox cheeseCheckBox = (CheckBox)findViewById(R.id.checkBox2);
        Boolean cheese = cheeseCheckBox.isChecked();

        //check box 3 is sour cream
        CheckBox sourCreamCheckBox = (CheckBox)findViewById(R.id.checkBox3);
        Boolean sourCream = sourCreamCheckBox.isChecked();

        //check box 4 is guacamole
        CheckBox guacamoleCheckBox = (CheckBox)findViewById(R.id.checkBox4);
        Boolean guacamole = guacamoleCheckBox.isChecked();

        //gluten free switch
        Switch glutenFreeSwitch = (Switch)findViewById(R.id.switch1);
        Boolean glutenFree = glutenFreeSwitch.isChecked();

        //if statements to make us do stuff

        //radio buttons first
        //default is a burrito so this makes this easy
        if(group_id == R.id.radioButton2){
            //user chose taco
            burritoChoice = "taco ";
            //change image to taco
            image.setImageResource(R.drawable.taco);

        } else{
            //default is burrito
            burritoChoice = "burrito ";
            //set image to burrito
            image.setImageResource(R.drawable.burrito);

        }
        //now the checkboxes
        if(salsa){
            topping1 = "salsa ";
        }
        if(sourCream){
            topping2 = "sour cream ";

        }
        if(cheese){
            topping3 = "cheese ";
        }
        if(guacamole){
            topping4 = "guacamole ";
        }
        //now meat toggle
        if(veggie){
            meatChoice = "veggie ";
        } else{
            meatChoice = "meat ";
        }
        //now the spinner
        Spinner locationSpinner = (Spinner)findViewById(R.id.locationspinner);
        Integer location = locationSpinner.getSelectedItemPosition();

        //easy processing for text view
        String locationName = "";
        if(location == 0){
            locationName = "The Hill";
        }
        if(location == 1){
            locationName = "29th Street";
        }
        if(location == 2){
            locationName = "Pearl Street";
        }

        //gluten free
        if(glutenFree){
            //set gluten free string
            gluten = "on a corn tortilla ";
        }

        //now put it all together
        TextView Burritoresult = (TextView)findViewById(R.id.creationresult);
        Burritoresult.setText(burritoName+ " is a " + meatChoice + burritoChoice + gluten + "with " + topping1 + topping2 + topping3 + topping4 + ". You would like to eat on " + locationName);

    }

    public void callNewActivity(View view){
        //now the spinner
        Spinner locationSpinner = (Spinner)findViewById(R.id.locationspinner);
        Integer location = locationSpinner.getSelectedItemPosition();
        //send this info out

        myburritoshop.setBurritoShop(location);
        String suggestedBurritoShop = myburritoshop.getBurritoShop();
        String suggestedBurritoShopURL = myburritoshop.getBurritoShopURL();

        //create intent
        Intent intent = new Intent(this, recieveBurrito.class);

        intent.putExtra("burritoShopName", suggestedBurritoShop);
        intent.putExtra("burritoShopURL", suggestedBurritoShopURL);

        startActivity(intent);
    }
}
