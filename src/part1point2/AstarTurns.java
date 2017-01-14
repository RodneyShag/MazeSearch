package part1point2;

import java.util.*;

import part1point1.Maze;
import part1point1.Point;
import part1point1.Direction;

public class AstarTurns {
	/* Data */
	public PriorityQueue<StateTurns> frontier;
	public HashSet<StateFacts> visited;
	public HashMap<StateTurns, StateTurns> predecessor;
	public int nodesExpanded;
	public int solutionDistance;
	public int solutionCost;
	
	/* Constructor */
	public AstarTurns(Maze maze){
        frontier         = new PriorityQueue<StateTurns>(1000, new StateComparator());
        visited          = new HashSet<StateFacts>();
		predecessor      = new HashMap<StateTurns, StateTurns>();
		nodesExpanded    = 0;
		solutionDistance = 0;
		solutionCost     = 0;
	}

	public void findSolution(Maze maze){
		/* Load Start State onto Frontier. Update stuff */
		StateTurns initial = new StateTurns(new Point(maze.startPoint), 0, maze, Direction.EAST);
		frontier.add(initial);
		visited.add(new StateFacts(initial));
		
		/* Actual Algorithm */
		while ( ! frontier.isEmpty()){
			StateTurns currentState = frontier.remove();
			if(!visited.contains(new StateFacts(currentState)))	//JUST ADDED THIS CODE
				nodesExpanded++;
			visited.add(new StateFacts(currentState));
			
			/* Loop through adjacent states and update stuff */
			for (StateTurns state : currentState.getAdjacentStates(maze)){
				if (!visited.contains(new StateFacts(state))){
					frontier.add(state);
					predecessor.put(state, currentState);
					if (state.pacmanLocation.equals(maze.endPoint)){	//Notice we use the .equals() class I wrote for Points.
						solutionDistance = maze.calculateSolutionDistance(predecessor, state);
						solutionCost = state.costSoFar;
						return;
					}
				}
			}
		}
	}
}
