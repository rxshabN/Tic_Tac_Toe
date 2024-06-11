package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    // PLayer representation
    // 0 - X
    // 1 - O
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    // State meanings
    //    1 - O
    //    0 - X
    //    2- Null
    int[][] winPositions = {{0,1,2},{3,4,5},{6,7,8},
                            {0,3,6},{1,4,7},{2,5,8},
                            {0,4,8},{2,4,6}};
    public void playerTap(View view){
        ImageView img = (ImageView)view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset(view);
        }
        if(gameState[tappedImage] == 2) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.o);
                activePlayer = 1;
                TextView textView2 = findViewById(R.id.textView2);
                textView2.setText("O's Turn");
            } else {
                img.setImageResource(R.drawable.x);
                activePlayer = 0;
                TextView textView2 = findViewById(R.id.textView2);
                textView2.setText("X's Turn");
            }

            img.animate().translationYBy(1000f).setDuration(300);
        }
        //Check if any player has won
        for(int[] winPosition: winPositions){
            if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]]!=2) {
                String winnerStr;
                gameActive = false;
                if(gameState[winPosition[0]] == 0){
                    winnerStr = "X has won! - Tap anywhere to restart";
                }
                else{
                    winnerStr = "O has won! - Tap anywhere to restart";
                }
                TextView textView2 = findViewById(R.id.textView2);
                textView2.setText(winnerStr);
            }
        }
        boolean emptySquare = false;
        for (int squareState : gameState) {
            if (squareState == 2) {
                emptySquare = true;
                break;
            }
        }
        if (!emptySquare && gameActive) {
            // Game is a draw
            gameActive = false;
            String winnerStr;
            winnerStr = "Draw!";
            TextView status = findViewById(R.id.textView2);
            status.setText(winnerStr);
        }
    }
    public void gameReset(View view){
        gameActive = true;
        activePlayer = 0;
        for(int i=0; i<gameState.length; i++) {
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView10)).setImageResource(0);
        TextView textView2 = findViewById(R.id.textView2);
        textView2.setText("X's Turn - Tap to play!");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}