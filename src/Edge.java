public class Edge {
    public char destination;
    public int weight;

    public Edge(char destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "(" + destination + ", wt: " + weight + ")";
    }
}