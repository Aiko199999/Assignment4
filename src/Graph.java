import java.util.*;

public class Graph {
    private final Map<Character, List<Edge>> adjList = new TreeMap<>();

    public void addVertex(char v) {
        adjList.putIfAbsent(v, new ArrayList<>());
    }

    public void addEdge(char v, char w, int weight) {
        addVertex(v);
        addVertex(w);
        adjList.get(v).add(new Edge(w, weight));
        adjList.get(w).add(new Edge(v, weight));
    }

    public void printGraph() {
        System.out.println("task 1: Adjacency list representation");
        for (char vertex : adjList.keySet()) {
            System.out.println("Vertex " + vertex + " -> " + adjList.get(vertex));
        }
    }

    public void bfs(char startNode) {
        System.out.print("BFS Traversal Order: ");
        Set<Character> visited = new HashSet<>();
        Queue<Character> queue = new LinkedList<>();

        visited.add(startNode);
        queue.add(startNode);

        while (!queue.isEmpty()) {
            char current = queue.poll();
            System.out.print(current + " ");

            List<Edge> neighbors = adjList.getOrDefault(current, new ArrayList<>());
            neighbors.stream()
                    .map(e -> e.destination)
                    .sorted()
                    .forEach(neighbor -> {
                        if (!visited.contains(neighbor)) {
                            visited.add(neighbor);
                            queue.add(neighbor);
                        }
                    });
        }
        System.out.println();
    }

    public void dfs(char startNode) {
        System.out.print("DFS Traversal Order: ");
        Set<Character> visited = new HashSet<>();
        dfsHelper(startNode, visited);
        System.out.println();
    }

    private void dfsHelper(char current, Set<Character> visited) {
        visited.add(current);
        System.out.print(current + " ");

        List<Edge> neighbors = adjList.getOrDefault(current, new ArrayList<>());
        List<Character> sortedNeighbors = new ArrayList<>();
        for (Edge e : neighbors) sortedNeighbors.add(e.destination);
        Collections.sort(sortedNeighbors);

        for (char neighbor : sortedNeighbors) {
            if (!visited.contains(neighbor)) {
                dfsHelper(neighbor, visited);
            }
        }
    }

    public void dijkstra(char startNode) {
        System.out.println("\ntask 3: Dijkstra's shortest paths from source" + startNode);

        Map<Character, Integer> distances = new HashMap<>();
        Map<Character, Character> parentNodes = new HashMap<>();
        PriorityQueue<NodeDistance> minHeap = new PriorityQueue<>();

        for (char vertex : adjList.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }

        distances.put(startNode, 0);
        minHeap.add(new NodeDistance(startNode, 0));

        while (!minHeap.isEmpty()) {
            NodeDistance curr = minHeap.poll();
            char u = curr.vertex;

            if (curr.distance > distances.get(u)) continue;

            for (Edge edge : adjList.getOrDefault(u, new ArrayList<>())) {
                char v = edge.destination;
                int weight = edge.weight;

                int newDist = distances.get(u) + weight;
                if (newDist < distances.get(v)) {
                    distances.put(v, newDist);
                    parentNodes.put(v, u);
                    minHeap.add(new NodeDistance(v, newDist));
                }
            }
        }

        for (char target : adjList.keySet()) {
            System.out.print("To " + target + " -> Distance: " + distances.get(target) + " | Path: ");
            printPathSequence(target, parentNodes, startNode);
            System.out.println();
        }
    }

    private void printPathSequence(char target, Map<Character, Character> parentNodes, char startNode) {
        if (target == startNode) {
            System.out.print(startNode);
            return;
        }
        if (!parentNodes.containsKey(target)) {
            System.out.print("No Path");
            return;
        }
        printPathSequence(parentNodes.get(target), parentNodes, startNode);
        System.out.print(" -> " + target);
    }
}