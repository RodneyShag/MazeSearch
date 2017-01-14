package part1point3;

import java.util.*;

import animation.Animation;
import part1point1.Maze;
import part1point1.Point;

public class AstarGhost {
	/* Data */
	public PriorityQueue<StateGhost> frontier;
	public HashSet<StateFacts> visited;
	public HashMap<StateGhost, StateGhost> predecessor;
	public int nodesExpanded;
	public int solutionDistance;
	
	/* Constructor */
	public AstarGhost(Maze maze){
        frontier         = new PriorityQueue<StateGhost>(1000, new StateComparator());
        visited          = new HashSet<StateFacts>();
		predecessor      = new HashMap<StateGhost, StateGhost>();
		nodesExpanded    = 0;
		solutionDistance = 0;
	}

	public void findSolution(Maze maze) throws InterruptedException{
		Animation.drawMaze(maze);
		/* Load Start State onto Frontier. Update stuff */
		StateGhost initial = new StateGhost(new Point(maze.startPoint), new Ghost(maze.ghost), 0, maze);
		frontier.add(initial);
		visited.add(new StateFacts(initial));
		
		/* Actual Algorithm */
		while ( ! frontier.isEmpty()){
			StateGhost currentState = frontier.remove();
			if(!visited.contains(new StateFacts(currentState)))	//JUST ADDED THIS CODE
				nodesExpanded++;
			visited.add(new StateFacts(currentState));
			
			/* Loop through adjacent states and update stuff */
			for (StateGhost state : currentState.getAdjacentStates(maze)){
				if (!visited.contains(new StateFacts(state))){
					frontier.add(state);
					predecessor.put(state, currentState);
					if (state.pacmanLocation.equals(maze.endPoint)){	//Notice we use the .equals() class I wrote for Points.
						Animation.drawSolution(maze, predecessor, state);
						solutionDistance = maze.calculateSolutionDistance(predecessor, state);
						return;
					}
				}
			}
		}
	}
}
