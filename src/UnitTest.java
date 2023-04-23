import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.VoiceStatus;

import org.junit.Test;

public class UnitTest {
    MainFunctions mf = new MainFunctions();

    // start single source
    ////// start dijkstra
    ///////////////////////////////
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
    ////////////////////////////////////// need negative cycle
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

    ////// end floyed

    // end single source

    // start all pairs
    ////// start dijkstra
    ////////////////////////
    ////// end dijkstra

    ////// start bellman

    @Test
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

    ////// end bellman

    ////// start floyed

    ////// end floyed
    // end all pairs
}
