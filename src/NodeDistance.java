public class NodeDistance implements Comparable<NodeDistance> {
    public char vertex;
    public int distance;

    public NodeDistance(char vertex, int distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    @Override
    public int compareTo(NodeDistance other) {
        return Integer.compare(this.distance, other.distance);
    }
}