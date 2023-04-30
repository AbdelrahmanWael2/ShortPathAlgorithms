import java.util.*;

public class GraphProcessor {
    static Graph graph;

    public GraphProcessor(Graph graph) {
        this.graph = graph;
    }

    public void dijkstra(int source, int[] costs, int[] parents) {
        Arrays.fill(costs, Integer.MAX_VALUE); // set all costs to infinity
        Arrays.fill(parents, -1); // set all parents to undefined
        costs[source] = 0; // set cost of source to 0
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(source, 0));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int u = node.vertex;
            if (node.cost > costs[u])
                continue; // skip if not improving
            for (Graph.Edge e : graph.getAdj(u)) {
                int v = e.getV();
                int newCost = costs[u] + e.getWeight();
                if (newCost < costs[v]) { // found shorter path to v
                    costs[v] = newCost;
                    parents[v] = u;
                    pq.offer(new Node(v, newCost));
                }
            }
        }
    }

    public boolean bellmanFord(int source, int[] costs, int[] parents) {
        Arrays.fill(costs, Integer.MAX_VALUE); // set all costs to infinity
        Arrays.fill(parents, -1); // set all parents to undefined
        costs[source] = 0; // set cost of source to 0

        int V = graph.getV();
        int E = graph.getEdges().size();

        boolean updated = true;
        for (int i = 1; i < V && updated; i++) {
            updated = false;
            for (int j = 0; j < E; j++) {
                int u = graph.getEdges().get(j).getU();
                int v = graph.getEdges().get(j).getV();
                int weight = graph.getEdges().get(j).getWeight();
                if (costs[u] != Integer.MAX_VALUE && costs[u] + weight < costs[v]) {
                    costs[v] = costs[u] + weight;
                    parents[v] = u;
                    updated = true;
                }
            }
        }

        // Check for negative-weight cycles
        for (int j = 0; j < E; j++) {
            int u = graph.getEdges().get(j).getU();
            int v = graph.getEdges().get(j).getV();
            int weight = graph.getEdges().get(j).getWeight();
            if (costs[u] != Integer.MAX_VALUE && costs[u] + weight < costs[v]) {
                System.out.println("Found negative cycle");
                return false; // negative-weight cycle found
            }
        }

        return true;
    }

    public boolean floyedWarsell(int[][] costs, int[][] parents) {
        int n = graph.getV();
        boolean NoCycle = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                costs[i][j] = Integer.MAX_VALUE;
                parents[i][j] = -1;
            }
        }
        for (int i = 0; i < n; i++)
            costs[i][i] = 0;
        for (int i = 0; i < n; i++) {
            for (Graph.Edge e : graph.getEdges()) {
                costs[e.getU()][e.getV()] = e.getWeight();
                parents[e.getU()][e.getV()] = e.getU();
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (j != i && k != i && costs[j][i] != Integer.MAX_VALUE && costs[i][k] != Integer.MAX_VALUE) {
                        if (costs[j][i] + costs[i][k] < costs[j][k]) {
                            costs[j][k] = costs[j][i] + costs[i][k];
                            parents[j][k] = parents[i][k];
                        }
                    }

                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (costs[i][i] < 0) {
                NoCycle = false;
                break;
            }
        }
        return NoCycle;
    }
}