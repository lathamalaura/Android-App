package uk.ac.ljmu.dicegame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

public class ScoresSQLiteOpenHelper  extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME = "Roll Dice";

    public ScoresSQLiteOpenHelper(Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        db.execSQL("CREATE TABLE scores(username VARCHAR(20),high_score INT(3)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS scores");
        this.onCreate(db);
    }

    public List<Scores> getScores(){
        SQLiteDatabase db = this.getReadableDatabase();

        List<Scores> scores = new LinkedList<Scores>();

        Cursor c = db.rawQuery("SELECT *FROM scores", null);

        while(c.moveToNext()){

            scores.add(new Scores(c.getString(0), c.getString(1)));
        }
        return scores;
    }

    public void insertScores(Scores scores){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO scores (username) VALUES ('" + scores.getUsername() + "', " + scores.getHigh_score() + ")");

        db.close();
    }

    public void deleteScores(){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM scores");
        db.close();
    }

}
