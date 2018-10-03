package sample;

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
    private boolean isActive;

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

    public boolean getIsActive(){
        return this.isActive;
    }
}
