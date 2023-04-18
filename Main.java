import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.exit;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "/home/abdelrahman/Desktop/ShortPathTest.pl";
        File file = new File(path);

        Graph graph = Graph.Initialize(file);

        Scanner sc = new Scanner(System.in);
        System.out.print("1 for Dijkstra, 2 for Bellman Ford, 3 for Floyd Warshall\n");

        int userInput = sc.nextInt();

        System.out.println("The size of the graph is : " + graph.Size() + "\n");

        int source = 0;
        int[] costs = new int[graph.getV()];
        int[] parents = new int[graph.getV()];

        GraphProcessor gp = new GraphProcessor(graph, source, costs, parents);
        if(userInput ==  1) {
            GraphProcessor.dijkstra(source, costs, parents);
        } else if (userInput == 2) {
            boolean noCycle;
            noCycle = GraphProcessor.bellmanFord(source, costs, parents);
            if(noCycle){
                System.out.println("No negative Cycles \n");
            }else {
                System.out.println("Cycle found \n");
            }
        } else if(userInput == 3){
            //Floyd warshall
        } else{
            System.out.println("Wrong input");
            exit(1);
        }


//        //print graph
//        int v = graph.getV();
//        for(int i = 0; i < v; i++){
//            System.out.println(graph.getAdj(i));
//        }

    }
}