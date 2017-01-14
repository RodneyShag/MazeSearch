package part1point1;

import java.util.*;

/* Similar to BFS and DFS, except that the frontier is now a PriorityQueue with a custom GreedyComparator that helps sort Points in increasing Heuristic value */
public class Greedy {
	/* Data */
	public Queue<Point> frontier;
	public HashSet<Point> visited;
	public HashMap<Point, Point> predecessor;
	public int nodesExpanded;
	public int solutionDistance;
	
	/* Constructor */
	public Greedy(Maze maze){
        frontier         = new PriorityQueue<Point>(maze.rows * maze.columns, new GreedyComparator());
		visited          = new HashSet<Point>();
		predecessor      = new HashMap<Point, Point>();
		nodesExpanded    = 0;
		solutionDistance = 0;
		maze.setHeuristics();
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
					visited.add(point);
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
