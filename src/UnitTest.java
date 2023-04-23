import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class UnitTest {
    MainFunctions mf = new MainFunctions();

    // start single source
    ////// start dijkstra
    @Test
    // direct 1 node path
    public void dijkstra1() {
        mf.creatGraph("E:/level2/DS2/ShortPathAlgorithms/positive.txt");
        mf.chooseMethodOneSrc(1, 1);
        List<Integer> ans = new ArrayList<>();
        ans.add(0, 3);
        ans.add(0, 1);
        assertEquals(20, mf.getCostFor(1, 3));
        assertEquals(ans, mf.getPathFor(1, 3));

    }

    @Test
    // no direct path
    public void dijkstra2() {
        mf.creatGraph("E:/level2/DS2/ShortPathAlgorithms/positive.txt");
        mf.chooseMethodOneSrc(1, 2);
        List<Integer> ans = new ArrayList<>();
        ans.add(0, 1);
        ans.add(0, 0);
        ans.add(0, 3);
        ans.add(0, 2);
        assertEquals(40, mf.getCostFor(2, 1));
        assertEquals(ans, mf.getPathFor(2, 1));
    }

    @Test
    // there is a direct path but not the shortest
    public void dijkstra3() {
        mf.creatGraph("E:/level2/DS2/ShortPathAlgorithms/positive.txt");
        mf.chooseMethodOneSrc(1, 0);
        List<Integer> ans = new ArrayList<>();
        ans.add(0, 3);
        ans.add(0, 1);
        ans.add(0, 0);
        assertEquals(30, mf.getCostFor(0, 3));
        assertEquals(ans, mf.getPathFor(0, 3));
    }

    @Test
    // multi quiries
    public void dijkstra4() {
        mf.creatGraph("E:/level2/DS2/ShortPathAlgorithms/positive.txt");
        mf.chooseMethodOneSrc(1, 0);
        List<Integer> ans = new ArrayList<>();
        ans.add(0, 3);
        ans.add(0, 1);
        ans.add(0, 0);
        assertEquals(30, mf.getCostFor(0, 3));
        assertEquals(ans, mf.getPathFor(0, 3));
        ans.clear();

        ans.add(0, 2);
        ans.add(0, 0);
        assertEquals(15, mf.getCostFor(0, 2));
        assertEquals(ans, mf.getPathFor(0, 2));
        ans.clear();

        ans.add(0, 1);
        ans.add(0, 0);
        assertEquals(10, mf.getCostFor(0, 1));
        assertEquals(ans, mf.getPathFor(0, 1));
    }
    ////// end dijkstra

    ////// start bellman
    @Test
    // direct 1 node path
    public void bellman1() {
        mf.creatGraph("E:/level2/DS2/ShortPathAlgorithms/negativeW.txt");
        mf.chooseMethodOneSrc(2, 0);
        List<Integer> ans = new ArrayList<>();
        ans.add(0, 1);
        ans.add(0, 0);
        assertEquals(3, mf.getCostFor(0, 1));
        assertEquals(ans, mf.getPathFor(0, 1));
    }

    @Test
    // no direct path exists
    public void bellman2() {
        mf.creatGraph("E:/level2/DS2/ShortPathAlgorithms/negativeW.txt");
        mf.chooseMethodOneSrc(2, 0);
        List<Integer> ans = new ArrayList<>();
        ans.add(0, 3);
        ans.add(0, 4);
        ans.add(0, 0);
        assertEquals(2, mf.getCostFor(0, 3));
        assertEquals(ans, mf.getPathFor(0, 3));
    }

    @Test
    // there is a direct path but not the shortest
    public void bellman3() {
        mf.creatGraph("E:/level2/DS2/ShortPathAlgorithms/negativeW.txt");
        mf.chooseMethodOneSrc(2, 2);
        List<Integer> ans = new ArrayList<>();
        ans.add(0, 1);
        ans.add(0, 0);
        ans.add(0, 3);
        ans.add(0, 2);
        assertEquals(0, mf.getCostFor(2, 1));
        assertEquals(ans, mf.getPathFor(2, 1));
    }

    @Test
    // multi quieries all of the node
    public void bellman4() {
        mf.creatGraph("E:/level2/DS2/ShortPathAlgorithms/negativeW.txt");
        mf.chooseMethodOneSrc(2, 1);
        List<Integer> ans = new ArrayList<>();
        ans.add(0, 0);
        ans.add(0, 3);
        ans.add(0, 1);
        assertEquals(ans, mf.getPathFor(1, 0));
        assertEquals(3, mf.getCostFor(1, 0));
        ans.clear();

        ans.add(0, 2);
        ans.add(0, 0);
        ans.add(0, 3);
        ans.add(0, 1);
        assertEquals(ans, mf.getPathFor(1, 2));
        assertEquals(11, mf.getCostFor(1, 2));
        ans.clear();

        ans.add(0, 3);
        ans.add(0, 1);
        assertEquals(ans, mf.getPathFor(1, 3));
        assertEquals(1, mf.getCostFor(1, 3));
        ans.clear();

        ans.add(0, 4);
        ans.add(0, 0);
        ans.add(0, 3);
        ans.add(0, 1);
        assertEquals(ans, mf.getPathFor(1, 4));
        assertEquals(-1, mf.getCostFor(1, 4));
    }

    @Test
    // detect no negative cycle
    public void bellman5() {
        mf.creatGraph("E:/level2/DS2/ShortPathAlgorithms/negativeW.txt");
        mf.chooseMethodOneSrc(2, 1);
        assertEquals(false, mf.negativeCycle());
    }

    @Test
    // dtect negative cycle
    public void bellman6() {
        mf.creatGraph("E:/level2/DS2/ShortPathAlgorithms/negCycle.txt");
        mf.chooseMethodOneSrc(2, 1);
        assertEquals(true, mf.negativeCycle());
    }

    @Test
    // have negative cycle
    public void bellman7() {
        mf.creatGraph("E:/level2/DS2/ShortPathAlgorithms/negCycle.txt");
        mf.chooseMethodOneSrc(2, 0);
        assertEquals(-44, mf.getCostFor(0, 0));
        assertEquals(-41, mf.getCostFor(0, 2));
    }
    ////// end bellman

    ////// start floyed
    @Test
    // direct 1 node path
    public void floyed1() {
        mf.creatGraph("E:/level2/DS2/ShortPathAlgorithms/negativeW2.txt");
        mf.chooseMethodOneSrc(3, 0);
        List<Integer> ans = new ArrayList<>();
        ans.add(0, 2);
        ans.add(0, 0);
        assertEquals(7, mf.getCostFor(0, 2));
        assertEquals(ans, mf.getPathFor(0, 2));
    }

    @Test
    // no direct path
    public void floyed2() {
        mf.creatGraph("E:/level2/DS2/ShortPathAlgorithms/negativeW2.txt");
        mf.chooseMethodOneSrc(3, 0);
        List<Integer> ans = new ArrayList<>();
        ans.add(0, 3);
        ans.add(0, 6);
        ans.add(0, 4);
        ans.add(0, 2);
        ans.add(0, 0);
        assertEquals(3, mf.getCostFor(0, 3));
        assertEquals(ans, mf.getPathFor(0, 3));
    }

    @Test
    // there is a direct path but not shortest
    public void floyed3() {
        mf.creatGraph("E:/level2/DS2/ShortPathAlgorithms/negativeW2.txt");
        mf.chooseMethodOneSrc(3, 2);
        List<Integer> ans = new ArrayList<>();
        ans.add(0, 3);
        ans.add(0, 6);
        ans.add(0, 4);
        ans.add(0, 2);
        assertEquals(-4, mf.getCostFor(2, 3));
        assertEquals(ans, mf.getPathFor(2, 3));
    }

    @Test
    // multi quieries
    public void floyed4() {
        mf.creatGraph("E:/level2/DS2/ShortPathAlgorithms/negativeW2.txt");
        mf.chooseMethodOneSrc(3, 4);
        assertEquals(-6, mf.getCostFor(4, 3));
        List<Integer> ans = new ArrayList<>();
        ans.add(0, 3);
        ans.add(0, 6);
        ans.add(0, 4);
        assertEquals(ans, mf.getPathFor(4, 3));

        assertEquals(3, mf.getCostFor(4, 2));
        List<Integer> ans2 = new ArrayList<>();
        ans2.add(0, 2);
        ans2.add(0, 0);
        ans2.add(0, 4);
        assertEquals(ans2, mf.getPathFor(4, 2));
    }

    @Test
    // detect no negative cycle
    public void floyed5() {
        mf.creatGraph("E:/level2/DS2/ShortPathAlgorithms/negativeW2.txt");
        mf.chooseMethodOneSrc(2, 1);
        assertEquals(false, mf.negativeCycle());
    }

    @Test
    // dtect negative cycle
    public void floyed6() {
        mf.creatGraph("E:/level2/DS2/ShortPathAlgorithms/negCycle.txt");
        mf.chooseMethodOneSrc(2, 1);
        assertEquals(true, mf.negativeCycle());
    }

    @Test
    // have negative cycle
    public void floyed7() {
        mf.creatGraph("E:/level2/DS2/ShortPathAlgorithms/negCycle.txt");
        mf.chooseMethodOneSrc(3, 0);
        assertEquals(-41, mf.getCostFor(0, 2));
        assertEquals(-44, mf.getCostFor(0, 0));
    }
    ////// end floyed

    // end single source

    // start all pairs
    ////// start dijkstra
    @Test
    public void allDijkstra1() {
        mf.creatGraph("E:/level2/DS2/ShortPathAlgorithms/positive.txt");
        mf.chooseMethodForAll(1);
        List<Integer> ans = new ArrayList<>();
        ans.add(0, 3);
        ans.add(0, 1);
        assertEquals(20, mf.getCostFor(1, 3));
        assertEquals(ans, mf.getPathFor(1, 3));
        ans.clear();

        ans.add(0, 1);
        ans.add(0, 0);
        ans.add(0, 3);
        ans.add(0, 2);
        assertEquals(40, mf.getCostFor(2, 1));
        assertEquals(ans, mf.getPathFor(2, 1));
        ans.clear();

        ans.add(0, 3);
        ans.add(0, 1);
        ans.add(0, 0);
        assertEquals(30, mf.getCostFor(0, 3));
        assertEquals(ans, mf.getPathFor(0, 3));
        ans.clear();

        ans.add(0, 2);
        ans.add(0, 0);
        assertEquals(15, mf.getCostFor(0, 2));
        assertEquals(ans, mf.getPathFor(0, 2));
        ans.clear();

        ans.add(0, 1);
        ans.add(0, 0);
        assertEquals(10, mf.getCostFor(0, 1));
        assertEquals(ans, mf.getPathFor(0, 1));

    }
    ////// end dijkstra

    ////// start bellman

    @Test
    // multi sources no negative cycle
    public void allBellman1() {
        mf.creatGraph("E:/level2/DS2/ShortPathAlgorithms/negativeW.txt");
        mf.chooseMethodForAll(2);
        List<Integer> ans = new ArrayList<>();
        assertEquals(3, mf.getCostFor(0, 1));
        ans.add(0, 1);
        ans.add(0, 0);
        assertEquals(ans, mf.getPathFor(0, 1));
        ans.clear();

        assertEquals(2, mf.getCostFor(0, 3));
        ans.add(0, 3);
        ans.add(0, 4);
        ans.add(0, 0);
        assertEquals(ans, mf.getPathFor(0, 3));
        ans.clear();

        assertEquals(3, mf.getCostFor(1, 0));
        ans.add(0, 0);
        ans.add(0, 3);
        ans.add(0, 1);
        assertEquals(ans, mf.getPathFor(1, 0));
        ans.clear();

        assertEquals(0, mf.getCostFor(2, 1));
        ans.add(0, 1);
        ans.add(0, 0);
        ans.add(0, 3);
        ans.add(0, 2);
        assertEquals(ans, mf.getPathFor(2, 1));
        ans.clear();

        ans.add(0, 2);
        ans.add(0, 0);
        ans.add(0, 3);
        ans.add(0, 1);
        assertEquals(ans, mf.getPathFor(1, 2));
        assertEquals(11, mf.getCostFor(1, 2));
        ans.clear();

        ans.add(0, 3);
        ans.add(0, 1);
        assertEquals(ans, mf.getPathFor(1, 3));
        assertEquals(1, mf.getCostFor(1, 3));
        ans.clear();

        ans.add(0, 4);
        ans.add(0, 0);
        ans.add(0, 3);
        ans.add(0, 1);
        assertEquals(ans, mf.getPathFor(1, 4));
        assertEquals(-1, mf.getCostFor(1, 4));

        assertEquals(false, mf.negativeCycle());
    }

    @Test
    // multi sources with negative cycle
    public void allBellman2() {
        mf.creatGraph("E:/level2/DS2/ShortPathAlgorithms/negCycle.txt");
        mf.chooseMethodForAll(2);
        assertEquals(-41, mf.getCostFor(0, 2));
        assertEquals(-30, mf.getCostFor(1, 0));
        assertEquals(-29, mf.getCostFor(2, 4));
        assertEquals(-24, mf.getCostFor(3, 1));
        assertEquals(-16, mf.getCostFor(4, 3));
    }
    ////// end bellman

    ////// start floyed
    @Test
    public void allFloyed1() {
        mf.creatGraph("E:/level2/DS2/ShortPathAlgorithms/negativeW2.txt");
        mf.chooseMethodOneSrc(3, 0);
        List<Integer> ans = new ArrayList<>();
        ans.add(0, 2);
        ans.add(0, 0);
        assertEquals(7, mf.getCostFor(0, 2));
        assertEquals(ans, mf.getPathFor(0, 2));
        ans.clear();

        ans.add(0, 3);
        ans.add(0, 6);
        ans.add(0, 4);
        ans.add(0, 2);
        ans.add(0, 0);
        assertEquals(3, mf.getCostFor(0, 3));
        assertEquals(ans, mf.getPathFor(0, 3));
        ans.clear();

        ans.add(0, 3);
        ans.add(0, 6);
        ans.add(0, 4);
        ans.add(0, 2);
        assertEquals(-4, mf.getCostFor(2, 3));
        assertEquals(ans, mf.getPathFor(2, 3));
        ans.clear();

        assertEquals(-6, mf.getCostFor(4, 3));
        ans.add(0, 3);
        ans.add(0, 6);
        ans.add(0, 4);
        assertEquals(ans, mf.getPathFor(4, 3));
        ans.clear();

        assertEquals(3, mf.getCostFor(4, 2));
        ans.add(0, 2);
        ans.add(0, 0);
        ans.add(0, 4);
        assertEquals(ans, mf.getPathFor(4, 2));

        assertEquals(false, mf.negativeCycle());
    }

    @Test
    // multi sources with negative cycle
    public void allFloyed2() {
        mf.creatGraph("E:/level2/DS2/ShortPathAlgorithms/negCycle.txt");
        mf.chooseMethodForAll(3);
        assertEquals(-44, mf.getCostFor(0, 0));
        assertEquals(-37, mf.getCostFor(0, 1));
        assertEquals(-41, mf.getCostFor(0, 2));
        assertEquals(-46, mf.getCostFor(0, 3));
        assertEquals(-37, mf.getCostFor(0, 4));

        assertEquals(-30, mf.getCostFor(1, 0));
        assertEquals(-23, mf.getCostFor(1, 1));
        assertEquals(-27, mf.getCostFor(1, 2));
        assertEquals(-32, mf.getCostFor(1, 3));
        assertEquals(-23, mf.getCostFor(1, 4));

        assertEquals(-29, mf.getCostFor(2, 4));
        assertEquals(-24, mf.getCostFor(3, 1));
        assertEquals(-16, mf.getCostFor(4, 3));
    }
    ////// end floyed
    // end all pairs
}
