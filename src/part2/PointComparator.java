package part2;

import part1point1.Point;

import java.util.Comparator;

/* Used in "fancyHeuristic" to sort Points by increasing "x" value (ties are broken by increasing "y" values */
public class PointComparator implements Comparator<Point>{
	public int compare(Point p1, Point p2){
		if (p1.x == p2.x)
			return p1.y - p2.y;
		else
			return p1.x - p2.x;
	}
}
