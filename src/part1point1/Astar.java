package part1point1;

import java.util.*;

/* - For A* to be optimal, we must NOT do repeated state detection. (example: Our PriorityQueue frontier can have 2 states with the same point information)
 * - We use a "consistent" heuristic. Manhattan Distance is CONSISTENT, so (from Wiki on A*) "no node needs to be processed more than once".
 *   However, I still search each duplicate since my heuristics in Part 1.2, 1.3, 2.1, 2.2 are admissible, but not necessarily "consistent".
 * - I only count nodesExpanded for 1 of the duplicates, which is also a weird way to do it.
 * - For our Comparator, instead of comparing Points (like we did in Greedy Algo), we compare States (which I created).
 */

/* New Note: Instead of permitting duplicates on the frontier, I could have used the "remove(Object o)" function that PriorityQueue's have */
public class Astar {
	/* Data */
	public Queue<StateAstar> frontier;
	public HashSet<Point> visited;	//Notice we use Point instead of AstarState here.
	public HashMap<StateAstar, StateAstar> predecessor;
	public int nodesExpanded;
	public int solutionDistance;
	
	/* Constructor */
	public Astar(Maze maze){
        frontier         = new PriorityQueue<StateAstar>(500, new StateComparator());
        visited          = new HashSet<Point>();
		predecessor      = new HashMap<StateAstar, StateAstar>();
		nodesExpanded    = 0;
		solutionDistance = 0;
	}

	public void findSolution(Maze maze){
		/* Load Start State onto Frontier. Update stuff */
		StateAstar initial = new StateAstar(new Point(maze.startPoint), 0, maze);
		frontier.add(initial);
		visited.add(initial.pacmanLocation);
		
		/* Actual Algorithm */
		while ( ! frontier.isEmpty()){
			StateAstar currentState = frontier.remove();
			if(!visited.contains(currentState.pacmanLocation))	//JUST ADDED THIS CODE
				nodesExpanded++;
			visited.add(currentState.pacmanLocation);
            
            
			/* Loop through adjacent states and update stuff */
			for (StateAstar state : currentState.getAdjacentStates(maze)){
				if (!visited.contains(state.pacmanLocation)){
					frontier.add(state);
					predecessor.put(state, currentState);
					if (state.pacmanLocation.equals(maze.endPoint)){	//Notice we use the .equals() class I wrote for Points.
						solutionDistance = maze.putSolutionDotsOnMaze(predecessor, state);
						return;
					}
				}
			}
		}
	}
}

