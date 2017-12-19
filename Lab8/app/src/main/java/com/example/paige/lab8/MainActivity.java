package com.example.paige.lab8;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //pokemon description box
    private TextView nameBox;
    private GestureDetectorCompat myGestureDetector;
    private ImageView myImage;
    private Integer whichCharmander;//which iteration of charmander
    private Integer whichSquirtle;//which iteration of squirtle
    private Integer whichBulbasaur;//which iteration of bulbasaur
    private boolean charmander; //if true, charmander group
    private boolean squirtle; //if true, squirtle group
    private boolean bulbasaur;//if true, bulbasaur group
    private Integer whichPokemon;//help to cycle through
    /*to keep track of which pokemon Image I am currently displaying
    there is clearly a more elegant way to do this somewhere,
    likely involving a custom class and some switch statements
    like we did in the coffee shop app - this is currently super clunky. But it works!  */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myGestureDetector = new GestureDetectorCompat(this, new CustomGestureDetector());
        myImage = (ImageView)findViewById(R.id.imageView);
        nameBox = (TextView)findViewById(R.id.pokemonNameView);
        //default is charmander
        nameBox.setText("Charmander");

        final View.OnTouchListener onTouchListener = new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent event){
                myGestureDetector.onTouchEvent(event);
                return true;
            }
        };

        //do things to image and image will now do things
        myImage.setOnTouchListener(onTouchListener);
        //our default image is charmander, so lets set the values for that
        whichPokemon = 1; //charmander
        whichCharmander = 1; //a charmander
        charmander = true; //we are charmander, charmander is go
        //others are disabled for now, but they would start at their first evolution
        whichSquirtle = 1;
        whichBulbasaur = 1;
        squirtle = false;
        bulbasaur = false;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.myGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class CustomGestureDetector extends GestureDetector.SimpleOnGestureListener{


        @Override
        public boolean onDown(MotionEvent event){
            //must return true otherwise nothing else will work
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY){
            //so this is what happens when the user flings the image (motion based gesture)
            //toasts to show that the gestures work!
            Toast toast = Toast.makeText(getApplicationContext(), "You evolved your pokemon!", Toast.LENGTH_SHORT);
            toast.show();
            //now I need to evolve the pokemon when flung - if you reach max evolution it just goes back to the beginning
            if(charmander){
                if(whichCharmander == 1){
                    myImage.setImageResource(R.drawable.charmeleon);
                    nameBox.setText("Charmeleon");
                }
                if(whichCharmander == 2){
                    myImage.setImageResource(R.drawable.charizard);
                    nameBox.setText("Charizard");
                }
                if(whichCharmander == 3){
                    myImage.setImageResource(R.drawable.charmander);
                    nameBox.setText("Charmander");
                    whichCharmander = 0;
                }
                whichCharmander++;
            }
            if(squirtle){
                if(whichSquirtle == 1){
                    myImage.setImageResource(R.drawable.wartortle);
                    nameBox.setText("Wartortle");
                }
                if(whichSquirtle == 2){
                    myImage.setImageResource(R.drawable.blastoise);
                    nameBox.setText("Blastoise");
                }
                if(whichSquirtle == 3){
                    myImage.setImageResource(R.drawable.squirtle);
                    nameBox.setText("Squirtle");
                    whichSquirtle = 0;
                }
                whichSquirtle++;
            }
            if(bulbasaur){
                if(whichBulbasaur == 1){
                    myImage.setImageResource(R.drawable.ivysaur);
                    nameBox.setText("Ivysaur");
                }
                if(whichBulbasaur == 2){
                    myImage.setImageResource(R.drawable.venosaur);
                    nameBox.setText("Venosaur");
                }
                if(whichBulbasaur == 3){
                    myImage.setImageResource(R.drawable.bulbasaur);
                    nameBox.setText("Bulbasaur");
                    whichBulbasaur = 0;
                }
                whichBulbasaur++;
            }


            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent event){
            //so that we can do something when the user double taps the image (second gesture)
            //toasts show that the gestures work!
            Toast toast2 = Toast.makeText(getApplicationContext(), "You switched pokemon!", Toast.LENGTH_SHORT);
            toast2.show();
            //now I need to switch pokemon when double tapped
            if(whichPokemon == 1){
                //we are on charmander, switch to squirtle
                myImage.setImageResource(R.drawable.squirtle);
                nameBox.setText("Squirtle");
                charmander = false;
                squirtle = true;
            }
            if(whichPokemon == 2){
                //we are on squirtle, switch to bulbasaur
                myImage.setImageResource(R.drawable.bulbasaur);
                nameBox.setText("Bulbasaur");
                squirtle = false;
                bulbasaur = true;
            }
            if(whichPokemon == 3){
                //same deal
                myImage.setImageResource(R.drawable.charmander);
                nameBox.setText("Charmander");
                bulbasaur = false;
                charmander = true;
                whichPokemon = 0; //reset to go back to the beginning
            }
            whichPokemon++;

            return true;
        }
    }

}
