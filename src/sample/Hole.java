package sample;

public class Hole {
    public static int HOLE_NUMBER = 1;


    public int holeNumber;
    public int strokeIndex;
    public int par;


    public Hole(int strokeIndex, int par) {
        //for standard 18 hole golf courses
        if (HOLE_NUMBER > 18) {
            HOLE_NUMBER = 1;
            this.holeNumber = HOLE_NUMBER;
        } else {
            this.holeNumber = HOLE_NUMBER;
            HOLE_NUMBER += 1;
        }
        this.strokeIndex = strokeIndex;
        this.par = par;

    }
    //for manual hole id entry
    public Hole(int holeNumber, int strokeIndex, int par){

        this.strokeIndex = strokeIndex;
        this.holeNumber = holeNumber;
        this.par = par;
    }

}
