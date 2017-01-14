package part1point1;

import java.util.ArrayList;

/* Our state representation does not keep a copy of the maze */
public class StateAstar {
	public Point pacmanLocation;
	public int costSoFar;
	public Heuristic heuristic;
	
	public StateAstar(Point p, int cost, Maze maze){
		pacmanLocation = p;
		costSoFar = cost;
		heuristic = new Heuristic(pacmanLocation, maze);
	}
	
	public ArrayList<StateAstar> getAdjacentStates(Maze maze){
		ArrayList<StateAstar> states = new ArrayList<StateAstar>();
		for (Point p : pacmanLocation.getAdjacentPoints(maze)){
			if (p.pointType != PointType.WALL){
				Point deepCopyPoint = new Point(p);
				states.add(new StateAstar(deepCopyPoint, costSoFar + 1, maze));
			}
		}
		return states;
	}
	
	/* We Override .equals() and .hashCode() so we can use these in a HashSet and HashMap */
	@Override
    public boolean equals(Object obj) {
        if (obj == this) 
            return true;				
        if (!(obj instanceof StateAstar))
            return false;	
        StateAstar otherState = (StateAstar) obj;
        return pacmanLocation.equals(otherState.pacmanLocation) &&
        	   costSoFar == otherState.costSoFar &&
        	   heuristic.equals(otherState.heuristic);
    }
	
	@Override
	public int hashCode(){
		return (3 * pacmanLocation.x) + (7 * pacmanLocation.y) + (11 * costSoFar) + (13 * heuristic.distanceHeuristic);
	}
}
