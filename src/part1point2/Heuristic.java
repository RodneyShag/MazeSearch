package part1point2;

import part1point1.Maze;
import part1point1.Point;
import part1point1.Direction;

public class Heuristic {
	
    public int minimumTurns;
    
	public int heuristic;
    public int distanceHeuristic; // Manhattan Distance from current Point to maze's endPoint

    
    public Heuristic(Point point, Maze maze, int moveCost, int turnCost) {
        setDistanceHeuristic(point, maze);
        findMinimumTurns(point, maze);
        heuristic = (moveCost * distanceHeuristic) + (turnCost * minimumTurns);
        //heuristic = distanceHeuristic; //Uncomment this to have manhattan distance as our heuristic
    }
    
    public void setDistanceHeuristic(Point point, Maze maze){
    	distanceHeuristic = Math.abs(point.x - maze.endPoint.x) + Math.abs(point.y - maze.endPoint.y);
    }
    
    /* For Dead Ends (just walked into a spot with walls on 3 sides) */
    public void findMinimumTurns(Point point, Maze maze){
    	minimumTurns = 0;
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
			minimumTurns = 2;
    }
}
