public static void main(String[] args) {
    Graph graph = new Graph();

    graph.addEdge('B', 'A', 4);
    graph.addEdge('C', 'B', 7);
    graph.addEdge('D', 'A', 10);
    graph.addEdge('E', 'B', 3);
    graph.addEdge('E', 'A', 13);
    graph.addEdge('C', 'E', 3);
    graph.printGraph();


    System.out.println("\ntask 2: Search & Traversal (Starting from 'B')");
    graph.dfs('B');
    graph.bfs('B');


    graph.dijkstra('B');
}