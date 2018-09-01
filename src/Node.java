
import java.util.ArrayList;

public class Node {

    private int hubScore;
    private int authScore;
    private int weight;

    //unbelievable that I had to make my own Tuple data structure. @TODO: see if any alternatives exist, despite stack overflow saying otherwise.
    private ArrayList<Tuple> adjacentNodes = new ArrayList<>();

    private double getHashCode(){
        return this.hashCode();
    }

    public int getHubScore(){
        return this.hubScore;
    }

    public int getAuthScore(){
        return this.authScore;
    }

    public int getWeight(){
        return this.weight;
    }

    protected Boolean updateHubScore(int value){
        try {
            this.hubScore += value;
            return true;
        }
        //some problem happened:
        catch (Exception e){
            return false;
        }
    }

    protected Boolean updateAuthScore(int value){
        try {
            this.authScore += value;
            return true;
        }
        //some problem happened:
        catch (Exception e){
            return false;
        }
    }

    protected Boolean updateWeight(int value){
        try{
            this.weight += value;
            return true;
        }
        //some problem happened:
        catch(Exception e){
            return false;
        }
    }

    /**
     * Returns a Node specified if it exists in this Node's neighbourhood, can also get it's hashCode.
     * @param  t  node to be returned
     * @return either a) the node itself, if it exists, b) an exception if the node was not in the node's neighbourhood
     * @see Node
     */

    public Node getNeighbour(Tuple t) throws Exception {
        if (this.adjacentNodes.contains(t)) {
            return adjacentNodes.get(adjacentNodes.indexOf(t)).getLast();
        }
        else{
            throw new Exception("Node: "+getHashCode()+"does not contain node: "+t.getLast().getHashCode()+"in it's adjacent neighbourhood.");
        }
    }

    /**
     * Returns a Node specified by it's hashCode, if it exists in this Node's neighbourhood.
     * @param  hashCode  node to be returned
     * @return either a) the node itself, if it exists, b) an exception if the node was not in the node's neighbourhood
     * @see Node
     */

    private Object getNeighbour(Double hashCode) throws Exception {
        for(Tuple t : adjacentNodes) {
            if (t.getFirst()==hashCode) {
                return adjacentNodes.get(adjacentNodes.indexOf(t)).getLast();
            }
            else {
                throw new Exception("Node: " + getHashCode() + "does not contain node: " + hashCode + "in it's adjacent neighbourhood.");
            }
        }
        return null;
    }


    /**
     * Returns the removed Node object if it exists in this Node's neighbourhood.
     * @param  t tuple containing hashcode of node, and actual pointer to node to be removed
     * @return either a) the removed node, if it exists, b) an exception if the node was not in the node's neighbourhood and c) another
     * type of exception if any other error occurred, returning it's cause.
     * @see Node
     */


    public Object removeNeighbour(Tuple t) throws Exception {
        try {
            if (this.adjacentNodes.contains(t)) {
                adjacentNodes.remove(t);
                return t;
            }
            else {
                throw new Exception("Node: " + getHashCode() + "does not contain node: " + t.getFirst() + " in it's adjacent neighbourhood.");
            }
        }

        catch (Exception e){
            throw new Exception(e);
        }
    }

    /**
     * Returns hashCode of removed Node object if it exists in this Node's neighbourhood.
     * @param  hashCode hasCode of node to be removed
     * @return either a) the hashCode of the removed Node, if it exists, b) an exception if the node was not in the node's neighbourhood and c) another
     * type of exception if any other error occurred, returning it's cause.
     * @see Node
     */

    public Object removeNeighbour(Double hashCode) throws Exception {
        try {
            for(Tuple t: adjacentNodes) {
                if (t.getFirst()==hashCode) {
                    adjacentNodes.remove(getNeighbour(hashCode));
                    return hashCode;
                }
                else {
                    throw new Exception("Node: " + getHashCode() + "does not contain node: " + hashCode + "in it's adjacent neighbourhood.");
                }
            }

        }

        catch (Exception e){
            throw new Exception(e);

        }
        return null;
    }

    public Object addNeighbour(Tuple t) throws Exception {
        try {
            if (!(this.adjacentNodes.contains(t))) {
                adjacentNodes.add(t);
                return t;
            }
            else {
                throw new Exception("Node: " + getHashCode() + "already contains node: " + t.getFirst() + " in it's adjacent neighbourhood.");
            }
        }

        catch (Exception e){
            throw new Exception(e);
        }
    }

    public Node(){
        this.hubScore = 1;
        this.authScore = 1;
        this.weight = 1;
    }
}

//@TODO: see about using an int as a colour, and grouping them by this in the graph to represent type?
