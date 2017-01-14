package part1point2;

import java.util.ArrayList;

import part1point1.Maze;
import part1point1.Point;
import part1point1.Direction;

/* Our state representation.  Does not keep a copy of the maze */
public class StateTurns {
	public Point pacmanLocation;
	public Direction direction;		//added for Part 1.2
	public int costSoFar;
	public Heuristic heuristic;
	
	/* Private Data */
	private int moveCost = 1;		//added for Part 1.2
	private int turnCost = 2;		//added for Part 1.2
	
	/* Constructor - EXPECTS YOU TO PASS IN DEEP COPIES */
	public StateTurns(Point p, int cost, Maze maze, Direction d){
		pacmanLocation = p;
		direction = d;
		costSoFar = cost;
		heuristic = new Heuristic(pacmanLocation, maze, moveCost, turnCost);
	}
	
	public ArrayList<StateTurns> getAdjacentStates(Maze maze){
		ArrayList<StateTurns> states = new ArrayList<StateTurns>();
		Direction turnedDirection;
		
		/* State for "Turn Left" */
		Point p = new Point(pacmanLocation);
		turnedDirection = turnLeft(direction);
		states.add(new StateTurns(p, costSoFar + turnCost, maze, turnedDirection));
		
		/* State for "Turn Right" */
		p = new Point(pacmanLocation);
		turnedDirection = turnRight(direction);
		states.add(new StateTurns(p, costSoFar + turnCost, maze, turnedDirection));
		
		/* State for "Move Forward" */
		p = new Point(pacmanLocation);
		if ( ! maze.wallExists(p, direction)){
			p.moveForward(direction);
			states.add(new StateTurns(p, costSoFar + moveCost, maze, direction));
		}
		return states;
	}
	
	/* Clockwise */
	private Direction turnRight(Direction dir){
		if (dir == Direction.NORTH)
			return Direction.EAST;
		else if (dir == Direction.EAST)
			return Direction.SOUTH;
		else if (dir == Direction.SOUTH)
			return Direction.WEST;
		else if (dir == Direction.WEST)
			return Direction.NORTH;
		return null;	//should never execute
	}
	
	/* Counter-clockwise */
	private Direction turnLeft(Direction dir){
		if (dir == Direction.NORTH)
			return Direction.WEST;
		else if (dir == Direction.WEST)
			return Direction.SOUTH;
		else if (dir == Direction.SOUTH)
			return Direction.EAST;
		else if (dir == Direction.EAST)
			return Direction.NORTH;
		return null;	//should never execute
	}
	
	/* We Override .equals() and .hashCode() so we can use these in a HashSet and HashMap. */
	@Override
    public boolean equals(Object obj) {
        if (obj == this) 
            return true;				
        if (!(obj instanceof StateTurns))
            return false;	
        StateTurns otherState = (StateTurns) obj;
        return pacmanLocation.equals(otherState.pacmanLocation) &&
        	   direction == otherState.direction &&
        	   costSoFar == otherState.costSoFar &&
        	   heuristic.equals(otherState.heuristic);
    }
	
	@Override
	public int hashCode(){
		return (3 * pacmanLocation.x) + (7 * pacmanLocation.y) + (11 * costSoFar);
	}
}