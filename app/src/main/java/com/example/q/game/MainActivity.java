package com.example.q.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int activePlayer = 0; // o: yellow, 1: red,2:emplty
    int[] gamestate = {2,2,2,2,2,2,2,2,2};
    int[][] winState = {{0,1,2},{0,3,6},{0,4,8},{1,4,7},{2,5,8},{2,4,6},{3,4,5},{6,7,8}};
    boolean gameActive = true;

    public void dropIn (View view){
        ImageView counter = (ImageView) view;
        Log.i("Tag",counter.getTag().toString());
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gamestate [tappedCounter] == 2 && gameActive ) {
            gamestate[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                counter.animate().translationYBy(1000).setDuration(200);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                counter.animate().translationYBy(1000).setDuration(200);
                activePlayer = 0;
            }

            for (int[] winPostion : winState) {
                if (gamestate[winPostion[0]] == gamestate[winPostion[1]] && gamestate[winPostion[1]] == gamestate[winPostion[2]] && gamestate[winPostion[1]] != 2) {

                    gameActive = false;
                    String winner;

                    if (activePlayer == 0) {
                        winner = "red";
                    } else {
                        winner = "yellow";
                    }

                    //won!
                    Toast.makeText(this, winner + " has won!", Toast.LENGTH_SHORT).show();
                    Button playAgain = (Button) findViewById(R.id.playAgain);
                    playAgain.setVisibility(View.VISIBLE);

                }

            }

        }

    }

    public void again(View view){
        Button playAgain = (Button) findViewById(R.id.playAgain);
        playAgain.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridlayout);
        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView child = (ImageView) gridLayout.getChildAt(i);
            child.setImageDrawable(null);

        }
        activePlayer = 0;
        gameActive = true;
        for(int i=0;i<gamestate.length; i++){
            gamestate[i] =2;

        }



        }










    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
