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
    private boolean cycle;
    private int method = 0;
    private boolean forAll = false;

    public void creatGraph(String path) {
        File file = new File(path);
        try {
            graph = Graph.Initialize(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        gp = new GraphProcessor(graph);
        costOneD = new int[graph.Size()];
        parentsOneD = new int[graph.Size()];
        costTwoD = new int[graph.Size()][graph.Size()];
        parentsTwoD = new int[graph.Size()][graph.Size()];
    }

    public void chooseMethodOneSrc(int method, int source) {
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
    }

    public void chooseMethodForAll(int method) {
        this.method = method;
        forAll = true;
        switch (method) {
            case 1:
            case 2:
                for (int i = 0; i < graph.Size(); i++) {
                    chooseMethodOneSrc(method, i);
                    costTwoD[i] = costOneD;
                    parentsTwoD[i] = parentsOneD;
                }
            case 3:
                chooseMethodOneSrc(method, 0);
                break;
        }
    }

    public int getCostFor(int src, int dest) {
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
        return ans;
    }

    public boolean negativeCycle() {
        return !cycle;
    }

}
