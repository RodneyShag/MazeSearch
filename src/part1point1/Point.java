package part1point1;

import java.util.ArrayList;

import part1point1.Direction;

public class Point {
	/* Data */
	public int x;
	public int y;
	public PointType pointType;
	public Heuristic heuristic;
    
	/* Constructors */
	public Point(int xCoordinate, int yCoordinate){
		x = xCoordinate;
		y = yCoordinate;
		pointType = null;
		heuristic = null;
	}
	
	public Point(int xCoordinate, int yCoordinate, PointType type){
		x = xCoordinate;
		y = yCoordinate;
		pointType = type;
		heuristic = null;
	}
	
	/* Copy Constructor - Copies everything except Heuristic */
	public Point(Point otherPoint){
		x = otherPoint.x;
		y = otherPoint.y;
		pointType = otherPoint.pointType;
		//Heuristic is not copied
	}
	
	/* Returns: Any space that's on the map */
	public ArrayList<Point> getAdjacentPoints(Maze maze){
		ArrayList<Point> points = new ArrayList<Point>();
		
		/* Find up to 4 valid (not off the map) Points */
		if(maze.validPosition(new Point(x+1, y)))	//right
			points.add(maze.grid[y][x+1]);
		if(maze.validPosition(new Point(x-1, y)))	//left
			points.add(maze.grid[y][x-1]);
		if(maze.validPosition(new Point(x, y+1)))	//up
			points.add(maze.grid[y+1][x]);
		if(maze.validPosition(new Point(x, y-1)))	//down
			points.add(maze.grid[y-1][x]);

		return points;
	}
	
	/* Used in Part 1.2 - Assumes No Wall is in the way */
	public void moveForward(Direction dir){
		if (dir == Direction.EAST)
			x++;
		else if (dir == Direction.WEST)
			x--;
		else if (dir == Direction.NORTH)
			y++;
		else if (dir == Direction.SOUTH)
			y--;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (obj == this) 
            return true;
        if (!(obj instanceof Point))
            return false;
        Point pos = (Point) obj;
        return (pos.x == x && pos.y == y);
    }
	
	@Override
	public int hashCode(){
		return 3*x + 7*y;
	}
	
	@Override
	public String toString(){
    	if (pointType == PointType.WALL)
    		return "%";
    	else if (pointType == PointType.EMPTY)
    		return " ";
    	else if (pointType == PointType.DOT)
    		return ".";
    	else if (pointType == PointType.START)
    		return "P";
    	return "";
    }
}
