package part2;

import java.util.*;

import animation.Animation;
import part1point1.Maze;
import part1point1.Point;

public class AstarPacman {
    /* Data */
    public PriorityQueue<StatePacman> frontier;
	public HashSet<StateFacts> visited;
    public HashMap<StatePacman, StatePacman> predecessor;
    public int solutionDistance;
    public int nodesExpanded;
    
    public AstarPacman(Maze maze){        
        frontier         = new PriorityQueue<StatePacman>(1000, new StateComparator());
        visited          = new HashSet<StateFacts>();
		predecessor      = new HashMap<StatePacman, StatePacman>();
		nodesExpanded    = 0;
		solutionDistance = 0;
    }
    
    public void findSolution(Maze maze) throws InterruptedException{
    	Animation.drawMaze(maze);
		/* Load Start State onto Frontier. Update stuff */
		StatePacman initial = new StatePacman(new Point(maze.startPoint), 0, maze.dots);	// new Point(maze.startPoint)
		frontier.add(initial);
		visited.add(new StateFacts(initial));
		
		/* Actual Algorithm */
		while ( ! frontier.isEmpty()){
			StatePacman currentState = frontier.remove();
			if (!visited.contains(new StateFacts(currentState))) //Not sure if correct way of counting nodes
				nodesExpanded++;
			visited.add(new StateFacts(currentState));
            
			/* Loop through adjacent states and update stuff */
			for (StatePacman state : currentState.getAdjacentNonwallStates(maze)){
				if (!visited.contains(new StateFacts(state))){
						frontier.add(state);
						predecessor.put(state, currentState);
						if (state.dots.size() == 0){
							Animation.drawSolution(maze, predecessor, state);
							solutionDistance = maze.calculateSolutionDistance(predecessor, state);
							return;
					}
				}
			}
		}
    }
}
