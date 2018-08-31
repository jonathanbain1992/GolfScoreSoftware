import java.util.ArrayList;

public class Node {

    private int hubScore;
    private int authScore;
    private int weight;

    protected ArrayList<Node> adjacentNodes = new ArrayList<>();

    protected double getHashCode(){
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
     * Returns a Node specified if it exists in this Node's neighbourhood.
     * @param  n  node to be returned
     * @return either a) the node itself, if it exists, b) an exception if the node was not in the node's neighbourhood 
     * @see Node
     */

    public Node getNeighbour(Node n) throws Exception {
        if (this.adjacentNodes.contains(n)) {
            return adjacentNodes.get(adjacentNodes.indexOf(n));
        }
        else{
            throw new Exception("Node: "+getHashCode()+"does not contain node: "+n.getHashCode()+"in it's adjacent neighbourhood.");
        }
    }

    /**
     * Returns the removed Node object if it exists in this Node's neighbourhood.
     * @param  n  node to be removed
     * @return either a) the removed node, if it exists, b) an exception if the node was not in the node's neighbourhood and c) another
     * type of exception if any other error occurred.
     * @see Node
     */


    public Object removeNeighbour(Node n) throws Exception {
        try {
            if (this.adjacentNodes.contains(n)) {
                adjacentNodes.remove(n);
                return n;
            }
            else {
                throw new Exception("Node: " + getHashCode() + "does not contain node: " + n.getHashCode() + "in it's adjacent neighbourhood.");
            }
        }
        catch (Exception e){
            // only way to satisfy the type guard @TODO: look into ways around wrapping an exception in an exception here.
            return e;
        }
    }

    public Node(){
        this.hubScore = 1;
        this.authScore = 1;
        this.weight = 1;
    }
}
