package sample;

import jnr.ffi.annotations.In;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class Match {

    private static LocalDateTime matchTime;

    public LocalDateTime getMatchTime() {
        return matchTime;
    }

    private Player[] players;
    protected ArrayList<Hole> course = new ArrayList<>();
    public ArrayList<Player> d = new ArrayList(Arrays.asList(players));
    public int[][] playerScores = new int[players.length][course.size()];

    private String location;



    public ArrayList<Integer> player1Scores;
    public ArrayList<Integer> player2Scores;

  /*  private int[] getAwardablePoints(Player player){
        int[] pointsAwarded = new int[course.size()];
        int pointsToAward = player.getHandicap();
        while(pointsToAward>0){
            for(int i=0; i<pointsAwarded.length; i++){
                if(player.getHandicap()>course.get(i).strokeIndex) {
                    pointsAwarded[i] += 1;
                    pointsToAward--;
                }
            }
        }
        return pointsAwarded;
    }
    */

    private int[] getAwardablePoints(Player player) {
        int handicap = player.getHandicap();
        int[] pointsAwarded = new int[course.size()];
        while (handicap > 0) {
            for (int i = 0; i < pointsAwarded.length; i++) {
                if (handicap != 0) {
                    pointsAwarded[i]++;
                    handicap--;
                }
            }
        }
        return pointsAwarded;
    }

    private int[] getAdjustedScore(Player player, int[] score){
        int[] awardablePoints = getAwardablePoints(player);
        for(int i=0; i<score.length;i++){
            score[i] += awardablePoints[i];  //TODO: 26/10/2018  :might be -= depending on rules.
        }
        return score;
    }






    public Match(Player... players){
        this.players = players;
        this.players = null; //might return null pointer, if does, delete.
        matchTime = LocalDateTime.now();
        location = "palacerigg golf course";
    }

    public Match(LocalDateTime matchDateTime,Player... players){
        this(players);
        matchTime = matchDateTime;
    }

    public Match(LocalDateTime matchDateTime, String location,Player...players){
        this(players);
        matchTime = matchDateTime;
        this.location = location;
    }

    public Match( String location, Player... players){
        this(players);
        this.location = location;


    }

}
