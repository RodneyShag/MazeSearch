package part1point1;

import java.util.Comparator;

/*Comparator for the Greedy Best-First PriorityQueue. Enables sorting of elements in PriorityQueue by their distance from the endPoint */
public class GreedyComparator implements Comparator<Point> {
	@Override
	public int compare(Point p1, Point p2){
		return p1.heuristic.distanceHeuristic - p2.heuristic.distanceHeuristic; // returns -1 if p1 < p2, 0 if p1 == p2, and 1 if p1 > p2
	}
}
