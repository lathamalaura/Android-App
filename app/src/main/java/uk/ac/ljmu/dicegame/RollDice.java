package uk.ac.ljmu.dicegame;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class RollDice extends AppCompatActivity {

    private static int player = 1;
    int dice1, dice2;
    int score1 =0, score2 =0;
    ImageView diceImage1, diceImage2;
    TextView playerStatus = null;
    TextView textEnterPlayer1Name, textEnterPlayer2Name;
    private EditText editText1, editText2;
    String player1, player2;
    Button buttonRoll, buttonQuit, buttonGo;

    private ScoresSQLiteOpenHelper scrs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll_dice);

        //calls created SQLite database
        scrs = new ScoresSQLiteOpenHelper(this);

        diceImage1 = (ImageView) findViewById(R.id.imageView1);
        diceImage2 = (ImageView) findViewById(R.id.imageView2);

        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);

        buttonRoll = (Button) findViewById(R.id.buttonRoll);
        buttonQuit = (Button) findViewById(R.id.buttonQuit);

        textEnterPlayer1Name = (TextView) findViewById(R.id.textEnterPlayer1Name);
        textEnterPlayer2Name = (TextView) findViewById(R.id.textEnterPlayer2Name);

        //Allows 'Quit' and 'Roll' button's to appear invisible
        buttonQuit.setVisibility(View.INVISIBLE);
        buttonRoll.setVisibility(View.INVISIBLE);

        playerStatus = (TextView) findViewById(R.id.textViewInfo);

    }

    public void SaveNames (View view){
        player1 = editText1.getText().toString();
        player2 = editText2.getText().toString();

        //Initially sets player status to player1 as they will roll first
        playerStatus.setText(player1 + ": Your turn to Roll!");

        buttonGo = (Button) findViewById(R.id.buttonGo);

        //Sets player name entry fields, text and 'go' button to appear invisible as they are no longer required
        buttonGo.setVisibility(View.INVISIBLE);
        textEnterPlayer1Name.setVisibility(View.INVISIBLE);
        textEnterPlayer2Name.setVisibility(View.INVISIBLE);
        editText1.setVisibility(View.INVISIBLE);
        editText2.setVisibility(View.INVISIBLE);

        //Makes 'Quit' and 'Roll' button's appear again
        buttonRoll.setVisibility(View.VISIBLE);
        buttonQuit.setVisibility(View.VISIBLE);
    }

    public void rollDice(View view) {

        Random random = new Random();

        //get random numbers
        dice1 = random.nextInt(6) + 1;
        dice2 = random.nextInt(6) + 1;

        //assign correct dice picture
        switch(dice1) {
            case 1:
                diceImage1.setImageResource(R.drawable.s1);
                break;
            case 2:
                diceImage1.setImageResource(R.drawable.s2);
                break;
            case 3:
                diceImage1.setImageResource(R.drawable.s3);
                break;
            case 4:
                diceImage1.setImageResource(R.drawable.s4);
                break;
            case 5:
                diceImage1.setImageResource(R.drawable.s5);
                break;
            case 6:
                diceImage1.setImageResource(R.drawable.s6);
                break;
        }

        switch(dice2) {
            case 1:
                diceImage2.setImageResource(R.drawable.s1);
                break;
            case 2:
                diceImage2.setImageResource(R.drawable.s2);
                break;
            case 3:
                diceImage2.setImageResource(R.drawable.s3);
                break;
            case 4:
                diceImage2.setImageResource(R.drawable.s4);
                break;
            case 5:
                diceImage2.setImageResource(R.drawable.s5);
                break;
            case 6:
                diceImage2.setImageResource(R.drawable.s6);
                break;
        }

        //Animation Sets for dice images to rotate fully
        AnimatorSet set1 = new AnimatorSet();
        set1.play(ObjectAnimator.ofFloat(diceImage1, "rotation", 0, 360));

        set1.setDuration(1000);
        set1.start();

        AnimatorSet set2 = new AnimatorSet();
        set2.play(ObjectAnimator.ofFloat(diceImage2, "rotation", 0, 360));

        set2.setDuration(1000);
        set2.start();


        //Continuously adds to players scores when player rolls two matching dice
        if(dice2 == dice1) {
            if(player == 1) {
                score1++;
            } else if (player == 2) {
                score2++;
            }
            Toast.makeText(this, player1 + " " + score1 + " vs " + player2 + " " + score2, Toast.LENGTH_LONG).show();

        }

        //Indicates what user is expected to roll next based on previous player that rolled
        if(player == 1) {

            player = 2;
            playerStatus.setText(player2 + ": Your turn to Roll!");

        } else {
            player = 1;
            playerStatus.setText(player1 + ": Your turn to Roll!");
        }

        Log.i("Button", "Pressed!");
    }


    public void onResume (){
        super.onResume();
        //Show previous scores of paused game as long as players name strings are not null
        if (player1 != null && player2 != null) {
            Toast.makeText(this, player1 + " " + score1 + " vs " + player2 + " " + score2, Toast.LENGTH_LONG).show();
        }
    }

    //Finishes and removes data from previous game and returns to the homepage
    public void exitGameOnClick (View view){

        startActivity(new Intent(RollDice.this, HomePage.class));
        finish();

    }

}

