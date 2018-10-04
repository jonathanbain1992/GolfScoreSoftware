package sample;

import javax.persistence.*;
import java.time.LocalDateTime;


public class Match {

    private static LocalDateTime matchTime;

    public LocalDateTime getMatchTime() {
        return matchTime;
    }

    private Player player1;
    private Player player2;
    private String location;

    public Player getPlayer1(){
        return this.player1;
    }

    public Player getPlayer2(){
        return this.player2;
    }

    public Match(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
        matchTime = LocalDateTime.now();
        location = "palacerigg golf course";
    }

    public Match(Player player1, Player player2, LocalDateTime matchDateTime){
        this(player1,player2);
        matchTime = matchDateTime;
    }

    public Match(Player player1, Player player2, LocalDateTime matchDateTime, String location){
        this(player1,player2);
        matchTime = matchDateTime;
        this.location = location;
    }

    public Match(Player player1, Player player2, String location){
        this(player1,player2);
        this.location = location;


    }

    public Match(){

    }


}
