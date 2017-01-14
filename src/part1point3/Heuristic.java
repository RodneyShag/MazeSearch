package part1point3;

import part1point1.Direction;
import part1point1.Maze;
import part1point1.Point;
import part1point1.PointType;

public class Heuristic {
	public int heuristic;
	public int distanceHeuristic; // Manhattan Distance from current Point to maze's endPoint
    
	private boolean deadEnd = false;	//Used for Part 1.3.2 (where we create additional heuristics)
	
    public Heuristic(Point point, Maze maze) {
        setDistanceHeuristic(point, maze);
        heuristic = distanceHeuristic;
        //setDeadEnd(point, maze);	//Uncomment these 3 lines to include this deadEnd (nonadmissible) heuristic.
        //if (deadEnd)				
        //	heuristic += 100;		
    }
    
    public void setDistanceHeuristic(Point point, Maze maze){
    	distanceHeuristic = Math.abs(point.x - maze.endPoint.x) + Math.abs(point.y - maze.endPoint.y);
    }
    
    public void setDeadEnd(Point point, Maze maze){
    	if (point.pointType == PointType.DOT)
    		return;
		int numWalls = 0;
		if (maze.wallExists(point, Direction.NORTH))
			numWalls++;
		if (maze.wallExists(point, Direction.SOUTH))
			numWalls++;
		if (maze.wallExists(point, Direction.EAST))
			numWalls++;
		if (maze.wallExists(point, Direction.WEST))
			numWalls++;
		if (numWalls == 3)
			deadEnd = true;
    }
}
