package part1point3;

import java.util.Comparator;

/* Enables sorting of elements in PriorityQueue by the evaluation function f(n) = g(n) + h(n) */
public class StateComparator implements Comparator<StateGhost>{
	@Override
	public int compare(StateGhost s1, StateGhost s2){
		return (s1.heuristic.heuristic + s1.costSoFar) - (s2.heuristic.heuristic + s2.costSoFar);
	}
}
