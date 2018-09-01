public class Edge {

    Node from;
    Node to;
    int weight;
    double fromHashCode = from.hashCode();
    double toHashCode = to.hashCode();
    //@todo: change definitions of weight to distance later so it's easier to see what's going on.
    public boolean setDistance(int distance){
        this.weight = distance;
        return true;
    }

    public int getDistance(){
        return this.weight;
    }

    public Edge(Node from, Node to){
        this.from = from;
        this.to = to;
        this.weight = 1; //default
    }

    public Edge(Node from, Node to, int weight){
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}
