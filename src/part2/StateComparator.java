package part2;

import java.util.Comparator;

public class StateComparator implements Comparator<StatePacman> {
    @Override
    public int compare(StatePacman s1, StatePacman s2) {
    	return (s1.heuristic.heuristic + s1.costSoFar) - (s2.heuristic.heuristic + s2.costSoFar);
    }
}