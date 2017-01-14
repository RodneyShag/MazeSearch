package part1point1;

import java.util.ArrayList;
import java.util.HashMap;

import part1point2.StateTurns;
import part1point3.StateGhost;
import part1point3.Ghost;
import part2.StatePacman;

import java.io.*;

public class Maze {
	/* Data */
	public int rows;
	public int columns;
	public Point[][] grid;
	public Point startPoint;
	public Point endPoint;
	
	/* Data for Part 1.3 */
	public Ghost ghost = new Ghost();

	
	/* Data for Part 2 */
	public ArrayList<Point> dots = new ArrayList<>();

	/* Constructor */
	public Maze(String name) throws FileNotFoundException, IOException{
		BufferedReader br1 = new BufferedReader(new FileReader("mazes/" + name + ".txt"));	//can throw FileNotFoundException

		/* Pass 1: Count number of rows and columns. Create grid */
        String line = br1.readLine();			//can throw IOException
        columns = line.length();
        while (line != null) {
	       	rows++;
	        line = br1.readLine();
	    }
	    br1.close();
	    grid = new Point[rows][columns];
	    
	    /* Pass 2: Create the Points in the grid. Find the startPoint and endPoint. Create the Ghost.
	     *         Also save all the Dots in an ArrayList<Point> for Part 2. */
	    BufferedReader br2 = new BufferedReader(new FileReader("mazes/" + name + ".txt"));	//can throw FileNotFoundException
	   
        PointType type = null;
        int row = 0;
        line = br2.readLine();
        while (line != null) {
        	for (int col = 0; col < columns; col++){
        		if (line.charAt(col) == ' '){
        			type = PointType.EMPTY;
        		}
        		else if (line.charAt(col) == '%'){
        			type = PointType.WALL;
        		}
        		else if (line.charAt(col) == '.'){
        			type = PointType.DOT;
        			dots.add(new Point(col, row));
        		}
        		else if (line.charAt(col) == 'P'){
        			type = PointType.START;
        		}
        		else if (line.charAt(col) == 'G' || line.charAt(col) == 'g'){
        			type = PointType.EMPTY;
        			ghost.minX = Math.min(ghost.minX, col);
        			ghost.maxX = Math.max(ghost.maxX, col);
        		}
        		
        		grid[row][col] = new Point(col, row, type);
        		
        		/* Update startPoint and endPoint */
        		if (line.charAt(col) == '.'){
        			endPoint = grid[row][col];	//if multiple dots exist in maze, this will be last dot.
        		}
        		else if (line.charAt(col) == 'P'){
        			startPoint = grid[row][col];
        		}
        		else if (line.charAt(col) == 'G'){
        			ghost.location = new Point(grid[row][col]);
        		}
        	}
        	line = br2.readLine();
            row++;
        }
        br2.close();
	}
	
	/* Valid if within bounds of maze */
	public boolean validPosition(Point point){
		return (point.x >= 0 && point.x < columns && point.y >= 0 && point.y < rows);
	}
	
	/* Prints solution onto maze. Returns solution distance */
	public int putSolutionDotsOnMaze(HashMap<Point, Point> solutionMap, Point point){
		int solutionDistance = 0;
		while(solutionMap.containsKey(point)) {
			solutionDistance++;
			point = solutionMap.get(point);
			if( ! point.equals(startPoint))		//This is so we don't have a DOT cover up the "P"
				point.pointType = PointType.DOT;
		}
		return solutionDistance;
	}
	
	public int putSolutionDotsOnMaze(HashMap<StateAstar, StateAstar> solutionMap, StateAstar state){
		int solutionDistance = 0;
		while(solutionMap.containsKey(state)) {
			solutionDistance++;
			state = solutionMap.get(state);
			if( ! state.pacmanLocation.equals(startPoint))		//This is so we don't have a DOT cover up the "P"
				grid[state.pacmanLocation.y][state.pacmanLocation.x].pointType = PointType.DOT;
		}
		return solutionDistance;
	}
	
	/* Used in Part 1.2 */
	public boolean wallExists(Point p, Direction dir){
		if (dir == Direction.EAST && validPosition(new Point(p.x + 1, p.y)))
			return grid[p.y][p.x + 1].pointType == PointType.WALL;
		else if (dir == Direction.WEST && validPosition(new Point(p.x - 1, p.y)))
			return grid[p.y][p.x - 1].pointType == PointType.WALL;
		else if (dir == Direction.NORTH && validPosition(new Point(p.x, p.y + 1)))
			return grid[p.y + 1][p.x].pointType == PointType.WALL;
		else if (dir == Direction.SOUTH && validPosition(new Point(p.x, p.y - 1)))
			return grid[p.y - 1][p.x].pointType == PointType.WALL;
		return true; //should never execute
	}
	
	public boolean noWallInRow(int xStart, int xEnd, int y){
		for (int i = xStart; i <= xEnd; i++){
			if (grid[y][i].pointType == PointType.WALL)
				return false;
		}
		return true;
	}
	
	public boolean noWallInColumn(int yStart, int yEnd, int x){
		for (int i = yStart; i <= yEnd; i++){
			if (grid[i][x].pointType == PointType.WALL)
				return false;
		}
		return true;
	}
	
	/* For "Part 1.2" */
	public int calculateSolutionDistance(HashMap<StateTurns, StateTurns> solutionMap, StateTurns state){
		int solutionDistance = 0;
		ArrayList<Point> uniquePoints = new ArrayList<Point>();
		while(solutionMap.containsKey(state)) {
			
			/* Update solutionDistance. DO NOT COUNT TURNS as part of distance. Do not count endPoint either */
			if( ! uniquePoints.contains(state.pacmanLocation) && ! state.pacmanLocation.equals(endPoint))
				solutionDistance++;
			uniquePoints.add(state.pacmanLocation);

			/* Print Dot on Maze */
			if( ! state.pacmanLocation.equals(startPoint))		//This is so we don't have a DOT cover up the "P"
				grid[state.pacmanLocation.y][state.pacmanLocation.x].pointType = PointType.DOT;
			
			/* Get next state */
			state = solutionMap.get(state);
		}
		return solutionDistance;
	}
	
	/* For Part 1.3 */
	public int calculateSolutionDistance(HashMap<StateGhost, StateGhost> solutionMap, StateGhost state){
		int solutionDistance = 0;
		while(solutionMap.containsKey(state)) {
			solutionDistance++;
			/* Print Dot on Maze */
			if( ! state.pacmanLocation.equals(startPoint))		//This is so we don't have a DOT cover up the "P"
				grid[state.pacmanLocation.y][state.pacmanLocation.x].pointType = PointType.DOT;
			/* Get next state */
			state = solutionMap.get(state);
		}
		return solutionDistance;
	}
	
	/* For "Part 2". Doesn't put dots on maze though */
	public int calculateSolutionDistance(HashMap<StatePacman, StatePacman> solutionMap, StatePacman state){
		int solutionDistance = 0;
		while(solutionMap.containsKey(state)) {
			solutionDistance++;
			state = solutionMap.get(state);
		}
		return solutionDistance;
	}
	
	public void setHeuristics() {
        for (int row = 0; row < rows; row++){
			for (int col = 0; col < columns; col++){
            	Point point = grid[row][col];
            	point.heuristic = new Heuristic(point, this);	//set heuristics for all Points. (could do it just for PointType.EMPTY, PointType.DOT)
            }
        }
    }
	
	/* Enables us to print mazes with System.out.println() */
	@Override
	public String toString(){
    	StringBuilder sb = new StringBuilder();
		for (int row = 0; row < rows; row++){
			for (int col = 0; col < columns; col++){
				sb.append(grid[row][col]);
			}
			sb.append(System.lineSeparator());	//a newline that works with Unix and Windows
		}
    	return sb.toString();
    }
}
