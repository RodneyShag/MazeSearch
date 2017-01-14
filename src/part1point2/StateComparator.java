package part1point2;

import java.util.Comparator;

/* Enables sorting of elements in PriorityQueue by the evaluation function f(n) = g(n) + h(n) */
public class StateComparator implements Comparator<StateTurns> {
	@Override
	public int compare(StateTurns s1, StateTurns s2){
		return (s1.heuristic.heuristic + s1.costSoFar) - (s2.heuristic.heuristic + s2.costSoFar);
	}
}