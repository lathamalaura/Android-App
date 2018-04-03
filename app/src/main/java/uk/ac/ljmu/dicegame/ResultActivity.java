package uk.ac.ljmu.dicegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {



    TextView textScores = null;
    private static String Scores;

    private ScoresSQLiteOpenHelper scrs;
    private List<Scores> scoresList = new ArrayList<Scores>();
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        scrs = new ScoresSQLiteOpenHelper(this);


        if (scrs.getScores().size()==0){

          //  scrs.insertScores(new Scores("Bob", 0));
          //  scrs.insertScores(new Scores("Pete"));
          //  scrs.insertScores(new Scores("Liam"));
        }

    }

    protected void displayScores(){
        for(Scores scores:scrs.getScores()){
            System.out.println(scores);
        }
    }

    public void Return (View view){

        startActivity(new Intent(ResultActivity.this, RollDice.class));

    }

}
