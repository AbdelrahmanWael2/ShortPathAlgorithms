import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {

    /* ------------------------Utils------------------------ */
    public static final String ANSI_RESET = "\u001B[0m", BLACK = "\u001B[30m", RED = "\u001B[31m", GREEN = "\u001B[32m",
            YELLOW = "\u001B[33m", BLUE = "\u001B[34m", PURPLE = "\u001B[35m", CYAN = "\u001B[36m";

    public static void prompt(){
        System.out.println("Press any key to go back");
        try
        {
            System.in.read();
        }
        catch(Exception e)
        {}
    }

    public static boolean assertStr(char[] str){
        if(str.length == 0) return false;
        for(int i = 0; i < str.length; ++i){
            if(str[i] < '0' || str[i] > '9') return false;
        }
        return true;
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /* ------------------------End Utils------------------------ */

    public static void main(String[] args) throws FileNotFoundException {
        // MainFunctions m = new MainFunctions();
        // m.creatGraph("E:/level2/DS2/test.txt");
        // m.chooseMethodOneSrc(2, 3);
        // System.out.println(m.getCostFor(3, 2));
        // System.out.println(m.getPathFor(3, 1));
        // String path = "E:/level2/DS2/test.txt";
        // File file = new File(path);

        // Graph graph = Graph.Initialize(file);

        // Scanner sc = new Scanner(System.in);
        // System.out.print("1 for Dijkstra, 2 for Bellman Ford, 3 for Floyd
        // Warshall\n");

        // int userInput = sc.nextInt();

        // System.out.println("The size of the graph is : " + graph.Size() + "\n");

        // int source = 0;
        // int[] costs = new int[graph.getV()];
        // int[] parents = new int[graph.getV()];

        // GraphProcessor gp = new GraphProcessor(graph, source, costs, parents);
        // if (userInput == 1) {
        // GraphProcessor.dijkstra(source, costs, parents);
        // } else if (userInput == 2) {
        // boolean noCycle;
        // noCycle = GraphProcessor.bellmanFord(source, costs, parents);
        // if (noCycle) {
        // System.out.println("No negative Cycles \n");
        // } else {
        // System.out.println("Cycle found \n");
        // }
        // } else if (userInput == 3) {
        // boolean noCycle;
        // int[][] p = new int[graph.getV()][graph.getV()];
        // int[][] c = new int[graph.getV()][graph.getV()];
        // noCycle = GraphProcessor.floyedWarsell(c, p);
        // if (noCycle) {
        // System.out.println("No negative Cycles \n");
        // } else {
        // System.out.println("Cycle found \n");
        // }
        // for (int i = 0; i < graph.getV(); i++) {
        // for (int j = 0; j < graph.getV(); j++) {
        // System.out.print(p[i][j] + " ");
        // }
        // System.out.println();

        // }
        // } else {
        // System.out.println("Wrong input");
        // exit(1);
        // }

        // //print graph
        // int v = graph.getV();
        // for(int i = 0; i < v; i++){
        // System.out.println(graph.getAdj(i));
        // }

        // Start CLI

        MainFunctions init = new MainFunctions();
        Scanner sc = new Scanner(System.in);

        // Initialize the graph
        boolean initialized = false;
        while(!initialized){
            clearScreen();
            System.out.print(GREEN + "Enter the Graph's Path: " + ANSI_RESET);
            initialized = true;
            try {
                String graphPath = sc.nextLine();
                init.creatGraph(graphPath);
            } catch (Exception e) {
                System.out.println("Press any key to try again.");
                sc.nextLine();
                initialized = false;
            }
        }

        while(true){
            // Main menu
            String selection = "0";
            while(!selection.equals("1") && !selection.equals("2") && !selection.equals("3") && !selection.equals("4")){
                clearScreen();
                System.out.println(BLACK + "--> " + PURPLE + "1. " + CYAN + "Single Source Shortest Paths." + ANSI_RESET);
                System.out.println(BLACK + "--> " + PURPLE + "2. " + CYAN + "All-Pairs Shortest Paths." + ANSI_RESET);
                System.out.println(BLACK + "--> " + PURPLE + "3. " + CYAN + "Check for Negative Cycles." + ANSI_RESET);
                System.out.println(BLACK + "--> " + PURPLE + "4. " + CYAN + "Exit." + ANSI_RESET);
                selection = sc.nextLine();
            }
            if(selection.equals("4")) break;

            clearScreen();

            // Sub-menus
            boolean endSubMenusLoop = false;
            while(!endSubMenusLoop){
                boolean modifySource = false;
                clearScreen();
                if(selection.equals("1")){  // One source
                    // Ask for source
                    int source = -1;
                    while(source == -1){
                        System.out.print(CYAN + "Enter the source node: " + ANSI_RESET);
                        String str = sc.nextLine();
                        char[] sourceStr = str.toCharArray();
                        if(assertStr(sourceStr)){
                            source = Integer.parseInt(String.copyValueOf(sourceStr));
                        }
                        clearScreen();
                    }

                    // Ask for method
                    boolean endMethodSelectionLoop = false;
                    while(!endMethodSelectionLoop){
                        System.out.println(RED + "Select Method\t\t\t" + GREEN + "Source: " + (source!=-1?String.valueOf(source):"NAN") + ANSI_RESET);
                        System.out.println(PURPLE + "1." + CYAN + " Dijkestra" + ANSI_RESET);
                        System.out.println(PURPLE + "2." + CYAN + " Bellman-Ford" + ANSI_RESET);
                        System.out.println(PURPLE + "3." + CYAN + " Floyd-Warshall" + ANSI_RESET);
                        selection = sc.nextLine();
                        clearScreen();
                        if(!selection.equals("1") && !selection.equals("2") && !selection.equals("3")) continue;
                        else {
                            try {
                                init.chooseMethodOneSrc(Integer.parseInt(selection), source);
                            } catch (Exception e) {
                                System.out.println("Error: Source Node doesn't exist.");
                                modifySource = true;
                                prompt();
                            }
                            endMethodSelectionLoop = true;
                        }
                    }
                    boolean endOneSourceQueryLoop = false;
                    while(!endOneSourceQueryLoop && !modifySource){
                        clearScreen();
                        System.out.println(PURPLE + "1." + CYAN + " Path Cost." + ANSI_RESET);
                        System.out.println(PURPLE + "2." + CYAN + " Path Nodes." + ANSI_RESET);
                        System.out.println(PURPLE + "3." + CYAN + " Back to Main Menu." + ANSI_RESET);
                        selection = sc.nextLine();
                        clearScreen();
                        int dest = -1;
                        switch(selection){
                            case "1":
                                // Ask for destination
                                while(dest == -1){
                                    System.out.print(CYAN + "Enter the destination node: " + ANSI_RESET);
                                    String str = sc.nextLine();
                                    char[] destStr = str.toCharArray();
                                    if(assertStr(destStr)){
                                        dest = Integer.parseInt(String.copyValueOf(destStr));
                                    }
                                    try {
                                        int res = init.getCostFor(source, dest);
                                        if(res == Integer.MAX_VALUE){
                                            System.out.println("No path exists between the two specified nodes.");
                                        } else {
                                            System.out.println("Minimum Cost from " + source + " to " + dest + ": " + init.getCostFor(source, dest));
                                        }
                                    } catch (Exception e) {
                                        dest = -1;
                                        clearScreen();
                                    }
                                }
                                prompt();
                                break;
                            case "2":
                                // Ask for destination
                                while(dest == -1){
                                    System.out.print(CYAN + "Enter the destination node: " + ANSI_RESET);
                                    String str = sc.nextLine();
                                    char[] destStr = str.toCharArray();
                                    if(assertStr(destStr)){
                                        dest = Integer.parseInt(String.copyValueOf(destStr));
                                    }
                                    try {
                                        List<Integer> res = init.getPathFor(source, dest);
                                        if(res.isEmpty()){
                                            System.out.println("No path exists between the two specified nodes.");
                                        } else {
                                            System.out.println("Shortest Path from " + source + " to " + dest + ": " + init.getPathFor(source, dest));
                                        }
                                    } catch (Exception e) {
                                        dest = -1;
                                        clearScreen();
                                    }
                                }
                                prompt();
                                break;
                            case "3":
                                endOneSourceQueryLoop = true;
                                endSubMenusLoop = true;
                                break;
                            default:
                                break;
                        }
                    }
                }else if(selection.equals("2")){  // All-pairs
                    // Ask for Method
                    boolean endMethodSelectionLoop = false;
                    while(!endMethodSelectionLoop){
                        System.out.println(RED + "Select Method\t\t\t" + GREEN + "All-Pairs" + ANSI_RESET);
                        System.out.println(PURPLE + "1." + CYAN + " Dijkestra" + ANSI_RESET);
                        System.out.println(PURPLE + "2." + CYAN + " Bellman-Ford" + ANSI_RESET);
                        System.out.println(PURPLE + "3." + CYAN + " Floyd-Warshall" + ANSI_RESET);
                        selection = sc.nextLine();
                        clearScreen();
                        if(!selection.equals("1") && !selection.equals("2") && !selection.equals("3")) continue;
                        else {
                            init.chooseMethodForAll(Integer.parseInt(selection));
                            endMethodSelectionLoop = true;
                        }
                    }
                    boolean endAllPairsQueryLoop = false;
                    while(!endAllPairsQueryLoop){
                        clearScreen();
                        System.out.println(PURPLE + "1." + CYAN + " Path Cost." + ANSI_RESET);
                        System.out.println(PURPLE + "2." + CYAN + " Path Nodes." + ANSI_RESET);
                        System.out.println(PURPLE + "3." + CYAN + " Back to Main Menu." + ANSI_RESET);
                        selection = sc.nextLine();
                        clearScreen();
                        int source = -1, dest = -1;
                        switch(selection){
                            case "1":
                                // Ask for source
                                while(source == -1){
                                    System.out.print(CYAN + "Enter the source node: " + ANSI_RESET);
                                    String str = sc.nextLine();
                                    char[] sourceStr = str.toCharArray();
                                    if(assertStr(sourceStr)){
                                        source = Integer.parseInt(String.copyValueOf(sourceStr));
                                    }
                                }
                                // Ask for destination
                                while(dest == -1){
                                    System.out.print(CYAN + "Enter the destination node: " + ANSI_RESET);
                                    String str = sc.nextLine();
                                    char[] destStr = str.toCharArray();
                                    if(assertStr(destStr)){
                                        dest = Integer.parseInt(String.copyValueOf(destStr));
                                    }
                                }
                                try {
                                    int res = init.getCostFor(source, dest);
                                    if(res == Integer.MAX_VALUE){
                                        System.out.println("No path exists between the two specified nodes.");
                                    } else {
                                        System.out.println("Minimum Cost from " + source + " to " + dest + ": " + init.getCostFor(source, dest));
                                    }
                                } catch (Exception e) {
                                    System.out.println("An error has occured, please try again.");
                                }
                                prompt();
                                break;
                            case "2":
                                // Ask for source
                                while(source == -1){
                                    System.out.print(CYAN + "Enter the source node: " + ANSI_RESET);
                                    String str = sc.nextLine();
                                    char[] sourceStr = str.toCharArray();
                                    if(assertStr(sourceStr)){
                                        source = Integer.parseInt(String.copyValueOf(sourceStr));
                                    }
                                }
                                // Ask for destination
                                while(dest == -1){
                                    System.out.print(CYAN + "Enter the destination node: " + ANSI_RESET);
                                    String str = sc.nextLine();
                                    char[] destStr = str.toCharArray();
                                    if(assertStr(destStr)){
                                        dest = Integer.parseInt(String.copyValueOf(destStr));
                                    }
                                }
                                try {
                                    List<Integer> res = init.getPathFor(source, dest);
                                    if(res.isEmpty()){
                                        System.out.println("No path exists between the two specified nodes.");
                                    } else {
                                        System.out.println("Shortest Path from " + source + " to " + dest + ": " + init.getPathFor(source, dest));
                                    }
                                } catch (Exception e) {
                                    System.out.println("An error has occured, please try again.");
                                }
                                prompt();
                                break;
                            case "3":
                                endAllPairsQueryLoop = true;
                                endSubMenusLoop = true;
                                break;
                            default:
                                break;
                        }
                    }
                }else{  // Negative Cycles Detector
                    // Ask for Method
                    boolean endMethodSelectionLoop = false;
                    while(!endMethodSelectionLoop){
                        System.out.println(RED + "Select Method" + ANSI_RESET);
                        System.out.println(PURPLE + "1." + CYAN + " Bellman-Ford" + ANSI_RESET);
                        System.out.println(PURPLE + "2." + CYAN + " Floyd-Warshall" + ANSI_RESET);
                        System.out.println(PURPLE + "3." + CYAN + " Back to Main Menu" + ANSI_RESET);
                        selection = sc.nextLine();
                        clearScreen();
                        if(selection.equals("3")){
                            endMethodSelectionLoop = true;
                            endSubMenusLoop = true;
                        }
                        if(!selection.equals("1") && !selection.equals("2")) continue;
                        else {
                            int source = 0;
                            init.chooseMethodOneSrc(Integer.parseInt(selection) + 1, source);
                            System.out.println("Negative Cycle " + (init.negativeCycle()?"":"not ") + "detected.");
                            prompt();
                            endSubMenusLoop = true;
                            endMethodSelectionLoop = true;
                        }
                    }
                }
            }
        }
        // End CLI
    }
}