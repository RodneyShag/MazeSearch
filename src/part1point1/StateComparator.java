package part1point1;

import java.util.Comparator;

/*Comparator for the Astar PriorityQueue. Enables sorting of elements in PriorityQueue by the evaluation function f(n) = g(n) + h(n) */
public class StateComparator implements Comparator<StateAstar> {
	@Override
	public int compare(StateAstar s1, StateAstar s2){
		return (s1.heuristic.distanceHeuristic + s1.costSoFar) - (s2.heuristic.distanceHeuristic + s2.costSoFar);
	}
}
