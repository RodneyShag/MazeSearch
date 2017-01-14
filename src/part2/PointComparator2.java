package part2;

import part1point1.Point;

import java.util.Comparator;

/* Used in "fancyHeuristic" to sort Points by increasing "y" value (ties are broken by increasing "x" values */
public class PointComparator2 implements Comparator<Point>{
	public int compare(Point p1, Point p2){
		if (p1.y == p2.y)
			return p1.x - p2.x;
		else
			return p1.y - p2.y;
	}
}

