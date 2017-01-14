package part1point3;

import java.util.ArrayList;

import part1point1.Maze;
import part1point1.Point;
import part1point1.PointType;

//import part1point2.Heuristic;
public class StateGhost {
	public Point pacmanLocation;
	public Ghost ghost;
	public int costSoFar;
	public Heuristic heuristic;
	
	/* Constructor - EXPECTS YOU TO PASS IN DEEP COPIES */
	public StateGhost(Point pacman, Ghost g, int cost, Maze maze){
		pacmanLocation = pacman;
		ghost = g;
		costSoFar = cost;
		heuristic = new Heuristic(pacmanLocation, maze);
		//heuristic = new Heuristic(pacmanLocation, maze, 1, 1); //for the extra credit part
	}
	
	public ArrayList<StateGhost> getAdjacentStates(Maze maze){
		ArrayList<StateGhost> states = new ArrayList<StateGhost>();
		for (Point p : pacmanLocation.getAdjacentPoints(maze)){
			Ghost ghostMoved = ghost.getNewMovedGhost();
			if (p.pointType != PointType.WALL && !p.equals(ghost.location) && !p.equals(ghostMoved.location)){
				Point deepCopyPoint = new Point(p);
				states.add(new StateGhost(deepCopyPoint, ghostMoved, costSoFar + 1, maze));
			}
		}
		return states;
	}
	
	/* We Override .equals() and .hashCode() so we can use these in a HashSet and HashMap. */
	@Override
    public boolean equals(Object obj) {
        if (obj == this) 
            return true;				
        if (!(obj instanceof StateGhost))
            return false;	
        StateGhost otherState = (StateGhost) obj;
        return pacmanLocation.equals(otherState.pacmanLocation) &&
        	   ghost.equals(otherState.ghost) &&
        	   costSoFar == otherState.costSoFar &&
        	   heuristic.equals(otherState.heuristic);
    }
	
	@Override
	public int hashCode(){
		return (3 * pacmanLocation.x) + (5 * pacmanLocation.y) + (7 * costSoFar);
	}
}
