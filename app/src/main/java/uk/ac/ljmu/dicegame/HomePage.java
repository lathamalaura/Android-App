package uk.ac.ljmu.dicegame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class HomePage extends AppCompatActivity {

 //   private static final String FILENAME = "SaveName.txt";

    Button buttonStartNewGame;
    Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        buttonStartNewGame = (Button) findViewById(R.id.buttonStartNewGame);
        exitButton = (Button) findViewById(R.id.exitButton);


    }

    public void StartNewGame (View view){

        startActivity(new Intent(HomePage.this, RollDice.class));

    }

    public void exitOnClick (View view){

        finish();
        System.exit(0);
    }
}
