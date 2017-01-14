package part1point1;

import java.util.*;

/* The only difference between the BFS code and DFS code is that we use a Queue for BFS and a Stack for DFS */
public class BFS {
	/* Data */
	public Queue<Point> frontier;
	public HashSet<Point> visited;
	public HashMap<Point, Point> predecessor;
	public int nodesExpanded;
	public int solutionDistance;
	
	/* Constructor */
	public BFS(){
		frontier         = new LinkedList<Point>();
		visited          = new HashSet<Point>();
		predecessor      = new HashMap<Point, Point>();
		nodesExpanded    = 0;
		solutionDistance = 0;
	}
	
	public void findSolution(Maze maze){
		/* Load Start Point onto Frontier. Update stuff */
		frontier.add(maze.startPoint);
		visited.add(maze.startPoint);

		/* Actual Algorithm */
		while ( ! frontier.isEmpty()){
			Point currentPoint = frontier.remove();
            nodesExpanded++;

			/* Loop through adjacent points and update stuff */
            for (Point point : currentPoint.getAdjacentPoints(maze)){
				if ((point.pointType == PointType.EMPTY || point.pointType == PointType.DOT) && !visited.contains(point)){
					frontier.add(point);
					visited.add(point);	//must mark as visited when we add to frontier, not when we remove from frontier. This prevents duplicate nodes in frontier.
					predecessor.put(point, currentPoint);
					if (point == maze.endPoint){
						solutionDistance = maze.putSolutionDotsOnMaze(predecessor, point);
						return;
					}
				}
			}
		}
	}
}
