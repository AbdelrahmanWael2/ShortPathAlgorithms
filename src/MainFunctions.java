import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MainFunctions {
    private Graph graph = null;
    private GraphProcessor gp;
    private int[] costOneD;
    private int[] parentsOneD;
    private int[][] costTwoD;
    private int[][] parentsTwoD;
    private boolean cycle = true;
    private int method = 0;
    private boolean forAll = false;

    public void creatGraph(String path) {
        File file = new File(path);
        try {
            graph = Graph.initialize(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found, or invalid file format");
            // e.printStackTrace();
        }
        gp = new GraphProcessor(graph);
        costOneD = new int[graph.Size()];
        parentsOneD = new int[graph.Size()];
        costTwoD = new int[graph.Size()][graph.Size()];
        parentsTwoD = new int[graph.Size()][graph.Size()];
    }

    public void chooseMethodOneSrc(int method, int source) {
        long begin = System.currentTimeMillis();
        this.method = method;
        switch (method) {
            case 1:
                gp.dijkstra(source, costOneD, parentsOneD);
                break;
            case 2:
                cycle = gp.bellmanFord(source, costOneD, parentsOneD);
                break;
            case 3:
                cycle = gp.floyedWarsell(costTwoD, parentsTwoD);
                break;
        }
        long end = System.currentTimeMillis();
        long time = end - begin;
        System.out.println("Time for one source: " + time);

    }

    public void chooseMethodForAll(int method) {
        long begin = System.currentTimeMillis();
        this.method = method;
        forAll = true;
        switch (method) {
            case 1:
            case 2:
                for (int i = 0; i < graph.Size(); i++) {
                    chooseMethodOneSrc(method, i);
                    if (!cycle)
                        return;
                    for (int j = 0; j < graph.Size(); j++) { /// here
                        costTwoD[i][j] = costOneD[j];
                        parentsTwoD[i][j] = parentsOneD[j];
                    }
                }
                break;
            case 3:
                System.out.println("Working in Floyd .. ");
                chooseMethodOneSrc(method, 0);
                break;
        }
        long end = System.currentTimeMillis();
        long time = end - begin;
        System.out.println("Time for all pairs: " + time);
    }

    public int getCostFor(int src, int dest) {
        // long begin = System.currentTimeMillis();
        switch (method) {
            case 1:
            case 2:
                if (forAll)
                    return costTwoD[src][dest];
                return costOneD[dest];
            case 3:
                return costTwoD[src][dest];
        }

        return -Integer.MAX_VALUE;
    }

    public List<Integer> getPathFor(int src, int dest) {
        // long begin = System.currentTimeMillis();
        List<Integer> ans = new ArrayList<>();
        switch (method) {
            case 1:
            case 2:
                if (forAll) {
                    if (parentsTwoD[src][dest] != -1) {
                        int cur = dest;
                        ans.add(0, cur);
                        while (cur != src && cur != -1) {
                            cur = parentsTwoD[src][cur];
                            ans.add(0, cur);
                        }
                    }
                } else {
                    if (parentsOneD[dest] != -1) {
                        int cur = dest;
                        ans.add(0, cur);
                        while (cur != src && cur != -1) {
                            cur = parentsOneD[cur];
                            ans.add(0, cur);
                        }
                    }
                }

                break;
            case 3:
                if (parentsTwoD[src][dest] != -1) {
                    int cur = dest;
                    ans.add(0, cur);
                    while (cur != src) {
                        cur = parentsTwoD[src][cur];
                        ans.add(0, cur);
                    }
                }
                break;
        }
        // long end = System.currentTimeMillis();
        // long time = end-begin;
        // System.out.println("Time for getting path: " + time);
        return ans;
    }

    public boolean negativeCycle() {
        return !cycle;
    }

}
