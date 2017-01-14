package part2;

import java.io.IOException;

import part1point1.Maze;

public class Main {
	public static void main (String [] args) throws IOException, InterruptedException {
	    /********************************************/
	    /****************** 2.1 *********************/
	    /********************************************/

		/*************/
		/* Old Mazes */
	    /*************/
		Maze maze1 = new Maze("zzz_smallSearch");
	    AstarPacman solution1 = new AstarPacman(maze1);
	    solution1.findSolution(maze1);
	    
	    System.out.print(maze1);
	    System.out.println("Nodes Expanded = " + solution1.nodesExpanded);
	    System.out.println("Solution Distance = " + solution1.solutionDistance);
	    System.out.println();
	    

	    Maze maze2 = new Maze("zzz_trickySearch");
	    AstarPacman solution2 = new AstarPacman(maze2);
	    solution2.findSolution(maze2);
	    
	    System.out.print(maze2);
	    System.out.println("Nodes Expanded = " + solution2.nodesExpanded);
	    System.out.println("Solution Distance = " + solution2.solutionDistance);
	    System.out.println();
	    
		/* tinySearch.txt */
//	    Maze tinySearch = new Maze("tinySearch");
//	    AstarPacman tinySearchSolution = new AstarPacman(tinySearch);
//	    tinySearchSolution.findSolution(tinySearch);
//	    
//	    System.out.print(tinySearch);
//	    System.out.println("Nodes Expanded = " + tinySearchSolution.nodesExpanded);
//	    System.out.println("Solution Distance = " + tinySearchSolution.solutionDistance);
//	    System.out.println();
	    
	    /* smallSearch.txt */
//	    Maze smallSearch = new Maze("smallSearch");
//	    AstarPacman smallSearchSolution = new AstarPacman(smallSearch);
//	    smallSearchSolution.findSolution(smallSearch);
//	    
//	    System.out.print(smallSearch);
//	    System.out.println("Nodes Expanded = " + smallSearchSolution.nodesExpanded);
//	    System.out.println("Solution Distance = " + smallSearchSolution.solutionDistance);
//	    System.out.println();
	    
	    /* mediumSearch.txt */
//	    Maze mediumSearch = new Maze("mediumSearch");
//	    AstarPacman mediumSearchSolution = new AstarPacman(mediumSearch);
//	    mediumSearchSolution.findSolution(mediumSearch);
//	    
//	    System.out.print(mediumSearch);
//	    System.out.println("Nodes Expanded = " + mediumSearchSolution.nodesExpanded);
//	    System.out.println("Solution Distance = " + mediumSearchSolution.solutionDistance);
//	    System.out.println();
	}
}
