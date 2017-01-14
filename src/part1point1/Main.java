package part1point1;

import java.io.IOException;

public class Main {
	public static void main (String [] args) throws IOException{
        /********************************************/
        /****************** 1.1 *********************/
        /********************************************/
		
		/****************/
		/** mediumMaze **/
		/****************/
		
		/* Create Mazes */
		Maze mediumMazeBFS = new Maze("mediumMaze");
		Maze mediumMazeDFS = new Maze("mediumMaze");
		Maze mediumMazeGreedy = new Maze("mediumMaze");
        Maze mediumMazeAstar = new Maze("mediumMaze");

        /* Create Solvers */
        BFS mediumMazeSolution_BFS = new BFS();
        DFS mediumMazeSolution_DFS = new DFS();
        Greedy mediumMazeSolution_Greedy = new Greedy(mediumMazeGreedy);
        Astar mediumMazeSolution_Astar = new Astar(mediumMazeAstar);
    	
        /* Solve Mazes */
        mediumMazeSolution_BFS.findSolution(mediumMazeBFS);
        mediumMazeSolution_DFS.findSolution(mediumMazeDFS);
        mediumMazeSolution_Greedy.findSolution(mediumMazeGreedy);
        mediumMazeSolution_Astar.findSolution(mediumMazeAstar);
        
		/* Print Mazes */
        System.out.println("Breadth First Search");
		System.out.println(mediumMazeBFS);
        System.out.println("Depth First Search");
        System.out.println(mediumMazeDFS);
        System.out.println("Greedy Best First Search");
		System.out.println(mediumMazeGreedy);
        System.out.println("A* Search");
        System.out.println(mediumMazeAstar);
        
        /* Print Nodes Expanded and Solution Distances */
		System.out.println("BFS Nodes Expanded = " + mediumMazeSolution_BFS.nodesExpanded);
        System.out.println("DFS Nodes Expanded = " + mediumMazeSolution_DFS.nodesExpanded);
        System.out.println("Greedy Best-First Nodes Expanded = " + mediumMazeSolution_Greedy.nodesExpanded);
        System.out.println("A* Nodes Expanded = " + mediumMazeSolution_Astar.nodesExpanded);
        System.out.println();
        System.out.println("BFS Solution Distance = " + mediumMazeSolution_BFS.solutionDistance);
        System.out.println("DFS Solution Distance = " + mediumMazeSolution_DFS.solutionDistance);
        System.out.println("Greedy Best-First Solution Distance = " + mediumMazeSolution_Greedy.solutionDistance);
        System.out.println("A* Solution Distance = " + mediumMazeSolution_Astar.solutionDistance);
        System.out.println("\n");

        
        /*************/
		/** bigMaze **/
		/*************/
        
        /* Create Mazes */
		Maze bigMazeBFS = new Maze("bigMaze");
		Maze bigMazeDFS = new Maze("bigMaze");
		Maze bigMazeGreedy = new Maze("bigMaze");
		Maze bigMazeAstar = new Maze("bigMaze");
		
		/* Create Solvers */
        BFS bigMazeSolution_BFS = new BFS();
        DFS bigMazeSolution_DFS = new DFS();
        Greedy bigMazeSolution_Greedy = new Greedy(bigMazeGreedy);
        Astar bigMazeSolution_Astar = new Astar(bigMazeAstar);
    	
        /* Solve Mazes */
        bigMazeSolution_BFS.findSolution(bigMazeBFS);
        bigMazeSolution_DFS.findSolution(bigMazeDFS);
        bigMazeSolution_Greedy.findSolution(bigMazeGreedy);
        bigMazeSolution_Astar.findSolution(bigMazeAstar);
        
		/* Print Mazes */
        System.out.println("Breadth First Search");
		System.out.println(bigMazeBFS);
        System.out.println("Depth First Search");
        System.out.println(bigMazeDFS);
        System.out.println("Greedy Best First Search");
		System.out.println(bigMazeGreedy);
		System.out.println("A* Search");
        System.out.println(bigMazeAstar);
        
        /* Print Nodes Expanded and Solution Distances */
		System.out.println("BFS Nodes Expanded = " + bigMazeSolution_BFS.nodesExpanded);
        System.out.println("DFS Nodes Expanded = " + bigMazeSolution_DFS.nodesExpanded);
        System.out.println("Greedy Best-First Nodes Expanded = " + bigMazeSolution_Greedy.nodesExpanded);
        System.out.println("A* Nodes Expanded = " + bigMazeSolution_Astar.nodesExpanded);
        System.out.println();
        System.out.println("BFS Solution Distance = " + bigMazeSolution_BFS.solutionDistance);
        System.out.println("DFS Solution Distance = " + bigMazeSolution_DFS.solutionDistance);
        System.out.println("Greedy Best-First Solution Distance = " + bigMazeSolution_Greedy.solutionDistance);
        System.out.println("A* Solution Distance = " + bigMazeSolution_Astar.solutionDistance);
        System.out.println("\n");
        
        
        /**************/
		/** openMaze **/
		/**************/
        
        /* Create Mazes */
		Maze openMazeBFS = new Maze("openMaze");
		Maze openMazeDFS = new Maze("openMaze");
		Maze openMazeGreedy = new Maze("openMaze");
		Maze openMazeAstar = new Maze("openMaze");
        
		/* Create Solvers */
        BFS openMazeSolution_BFS = new BFS();
        DFS openMazeSolution_DFS = new DFS();
        Greedy openMazeSolution_Greedy = new Greedy(openMazeGreedy);
        Astar openMazeSolution_Astar = new Astar(openMazeAstar);
    	
        /* Solve Mazes */
        openMazeSolution_BFS.findSolution(openMazeBFS);
        openMazeSolution_DFS.findSolution(openMazeDFS);
        openMazeSolution_Greedy.findSolution(openMazeGreedy);
        openMazeSolution_Astar.findSolution(openMazeAstar);
        
		/* Print Mazes */
        System.out.println("Breadth First Search");
		System.out.println(openMazeBFS);
        System.out.println("Depth First Search");
        System.out.println(openMazeDFS);
        System.out.println("Greedy Best First Search");
		System.out.println(openMazeGreedy);
		System.out.println("A* Search");
        System.out.println(openMazeAstar);
        
        /* Print Nodes Expanded and Solution Distances */
		System.out.println("BFS Nodes Expanded = " + openMazeSolution_BFS.nodesExpanded);
        System.out.println("DFS Nodes Expanded = " + openMazeSolution_DFS.nodesExpanded);
        System.out.println("Greedy Best-First Nodes Expanded = " + openMazeSolution_Greedy.nodesExpanded);
        System.out.println("A* Nodes Expanded = " + openMazeSolution_Astar.nodesExpanded);
        System.out.println();
        System.out.println("BFS Solution Distance = " + openMazeSolution_BFS.solutionDistance);
        System.out.println("DFS Solution Distance = " + openMazeSolution_DFS.solutionDistance);
        System.out.println("Greedy Best-First Solution Distance = " + openMazeSolution_Greedy.solutionDistance);
        System.out.println("A* Solution Distance = " + openMazeSolution_Astar.solutionDistance);
        System.out.println("\n");
        
        
        /****** Tests for 1.3 (maps when ghosts removed) ******/
//		Maze smallGhostNone = new Maze("smallGhostNone");
//		Astar smallGhostNoneSolution = new Astar(smallGhostNone);
//		smallGhostNoneSolution.findSolution(smallGhostNone);
//		System.out.println("\nAstar");
//		System.out.println(smallGhostNone);
//		System.out.println("Astar Nodes Expanded = " + smallGhostNoneSolution.nodesExpanded);
//		System.out.println("Astar Solution Distance = " + smallGhostNoneSolution.solutionDistance);
//		
//		Maze mediumGhostNone = new Maze("mediumGhostNone");
//		Astar mediumGhostNoneSolution = new Astar(mediumGhostNone);
//		mediumGhostNoneSolution.findSolution(mediumGhostNone);
//		System.out.println("\nAstar");
//		System.out.println(mediumGhostNone);
//		System.out.println("Astar Nodes Expanded = " + mediumGhostNoneSolution.nodesExpanded);
//		System.out.println("Astar Solution Distance = " + mediumGhostNoneSolution.solutionDistance);
//		
//		Maze bigGhostNone = new Maze("bigGhostNone");
//		Astar bigGhostNoneSolution = new Astar(bigGhostNone);
//		bigGhostNoneSolution.findSolution(bigGhostNone);
//		System.out.println("\nAstar");
//		System.out.println(bigGhostNone);
//		System.out.println("Astar Nodes Expanded = " + bigGhostNoneSolution.nodesExpanded);
//		System.out.println("Astar Solution Distance = " + bigGhostNoneSolution.solutionDistance);
	}
}
