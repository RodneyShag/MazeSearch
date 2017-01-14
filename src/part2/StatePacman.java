package part2;

import java.util.*;

import part1point1.Maze;
import part1point1.Point;
import part1point1.PointType;

public class StatePacman {
	/* Data */
	public Point pacmanLocation;
	public ArrayList<Point> dots;
	public int costSoFar;
	public Heuristic heuristic;
	
	/* Constructor - EXPECTS YOU TO PASS IN DEEP COPIES */
	public StatePacman(Point pacmanLocation, int costSoFar, ArrayList<Point> dots){
		this.pacmanLocation = pacmanLocation;	
		this.dots = dots;
		this.costSoFar = costSoFar;
		heuristic = new Heuristic(this);
	}
	
	public ArrayList<StatePacman> getAdjacentNonwallStates(Maze maze){
		ArrayList<StatePacman> states = new ArrayList<StatePacman>();
		for (Point p : pacmanLocation.getAdjacentPoints(maze)){
			if (p.pointType != PointType.WALL){
				Point deepCopyPoint = new Point(p);
				ArrayList<Point> deepCopyDots = new ArrayList<Point>(dots);
				if (deepCopyDots.contains(deepCopyPoint))
					deepCopyDots.remove(deepCopyPoint);		// Eat a dot
				StatePacman tempState = new StatePacman(deepCopyPoint, costSoFar + 1, deepCopyDots);
				states.add(tempState);
			}
		}
		return states;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (obj == this) 
            return true;					
        if (!(obj instanceof StatePacman))
            return false;					
        StatePacman otherState = (StatePacman) obj;
        return pacmanLocation.equals(otherState.pacmanLocation) &&
        	   dots.containsAll(otherState.dots) && otherState.dots.containsAll(dots) &&
        	   costSoFar == otherState.costSoFar &&
        	   heuristic.equals(otherState.heuristic);
    }
	
	@Override
	public int hashCode(){
		return (3 * pacmanLocation.x) + (7 * pacmanLocation.y) + (11 * costSoFar);
	}
}
