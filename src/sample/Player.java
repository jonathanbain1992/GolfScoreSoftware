package sample;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;




public class Player {



    private String firstName;
    private String lastName;
    private int age;
    private int handicap;
    private String address1;
    private String address2;
    private String address3;
    private String postCode;
    private int isActive;

    //private Match[] matchesPlayed;

    private static LocalDateTime memberCreationDateTime = LocalDateTime.now();

    public Player() {

    }

    public static LocalDateTime getMemberCreationDateTime() {
        return memberCreationDateTime;
    }

   /*public ArrayList<Match> getMatchesPlayed(){

        return this.matchesPlayed;
    }
    */

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public int getAge(){
        return this.age;
    }

    public int getHandicap(){
        return this.handicap;
    }

    public ArrayList<String> getAddress(){
        return new ArrayList<>(Arrays.asList(address1,address2,address3,postCode));
    }

    public int getIsActive(){
        return this.isActive;
    }

    public Player(String firstName,String lastName, String address1,String address2,String address3, String postCode, int age,int handicap,int isActive){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.postCode = postCode;
        this.isActive = isActive;
        this.handicap = handicap;
    }

    @Override
    public String toString()
    {
        return String.format(
                // id, forename, surname, address lines, age, handicap, isActive
                "(%d, '%s', '%s', '%s', '%s', '%s', %d, %d, %d)",
                hashCode(), firstName, lastName, address1,address2,address3,
                age, handicap, isActive
        );
    }

}
