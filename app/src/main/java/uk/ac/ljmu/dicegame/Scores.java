package uk.ac.ljmu.dicegame;

public class Scores {

    private String username , high_score;

    /*
    public Scores(){
    }
    */

    public Scores(String username, String high_score) {
        this.username = username;
        this.high_score = high_score;
    }

    @Override
    public String toString() {
        return "User: " + username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }


    public void setHigh_score(String high_score) {
        this.high_score = high_score;
    }

    public String getHigh_score() {
        return high_score;
    }
}




