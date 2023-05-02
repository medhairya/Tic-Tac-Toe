package com.dhairya.ttt;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    boolean gameActive=true;
    //Player Representation
    //0 -> X
    //1 -> 0
    int activePlayer =0;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    //State Meaning
    //0 -> X
    //1 -> 0
    //2 -> Blank

    int[][] winPositions ={{0,1,2},{3,4,5},{6,7,8},
                            {0,3,6},{1,4,7},{2,5,8},
                            {0,4,8},{2,4,6}};
    @SuppressLint("SetTextI18n")
    public void playerTap(View view){

        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset(view);
        }
        if(gameState[tappedImage]==2 ) {
            gameState[tappedImage]=activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.mad);
                status.setText("O's Turn - Tap to play");
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.mad);
                status.setText("X's Turn - Tap to play");
            }
                img.animate().translationYBy(1000f).setDuration(300);
        }
        String winnerStr;
        int count=0;
        for(int j=0; j<9; j++){
            if(gameState[j]!=2){
                count=count+1;
            }
            if(count==9){
                winnerStr="Draw";
                Toast.makeText(this, winnerStr, Toast.LENGTH_SHORT).show();
                TextView status = findViewById(R.id.mad);
                status.setText(winnerStr);
                gameReset(view);
            }
        }
            //check if any player has won
            for(int[] winPositions: winPositions ){
                if(gameState[winPositions[0]] == gameState[winPositions[1]] &&
                        gameState[winPositions[1]] == gameState[winPositions[2]] &&
                        gameState[winPositions[0]]!=2){
                    //someone have won find out who?

                    gameActive=false;
                    if(gameState[winPositions[0]]==0){
                        winnerStr = "X has won";
                    }
                    else{
                        winnerStr = "O has won";
                    }
                    //update status bar
                    Toast.makeText(this, winnerStr, Toast.LENGTH_SHORT).show();
                    TextView status = findViewById(R.id.mad);
                    status.setText(winnerStr);
                }
            }

        }
        @SuppressLint("SetTextI18n")
        public void gameReset(View view) {
            gameActive = true;
            activePlayer = 0;
            for(int i=0; i<gameState.length; i++){
                gameState[i] = 2;
            }
            ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);


            TextView status = findViewById(R.id.mad);
            status.setText("X's Turn - Tap to play");

        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
    }
}