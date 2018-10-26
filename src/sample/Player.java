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
                "('%s', '%s', '%s', '%s', '%s', %d, %d, %d, '%s')",
                firstName, lastName, address1, address2, address3,
                (int)age, (int)handicap, (int)isActive, ""
        );
    }


    public String[] asStringArray()
    {
        return new String[]{
                firstName, lastName, address1, address2, address3, String.valueOf(age), String.valueOf(handicap),
                String.valueOf(isActive), ""
        };
    }

    /**
     * Get a player object from a string array of its expected values in the expected order.
     * @param values String[] of {firstName, lastName, address1, address2, address3, age, handicap, isActive}
     * @return A Player object with the given attributes
     */
    public Player(String... values)
    {
        firstName = values[0];
        lastName = values[1];
        address1 = values[2];
        address2 = values[3];
        address3 = values[4];
        age = Integer.parseInt(values[5]);
        handicap= Integer.parseInt(values[6]);
        isActive = Integer.parseInt(values[7]);
    }


    @Override
    public int hashCode()
    {
        int result = 17;
        for (String attr: this.asStringArray())
            result *= 31 + attr.hashCode();     // Cut down on code required: ints in String form still have different hashCodes
        return result;
    }



    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Player))
            return false;
        return Arrays.equals(((Player) o).asStringArray(), this.asStringArray());
    }


    public String attributes()
    {
        return String.format(
                "firstName = '%s', secondName = '%s', age = %d, addressLine1 = '%s', addressLine2 = '%s', addressLine3 = '%s'," +
                        "isActive = %d, handicap = '%s', postcode=''",
                firstName, lastName, age, address1, address2, address3, isActive, handicap
        );
    }

}
