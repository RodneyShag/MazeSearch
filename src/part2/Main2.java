package part2;

import java.io.IOException;

import part1point1.Maze;

public class Main2 {
	public static void main (String [] args) throws IOException, InterruptedException {   
		/********************************************/
	    /****************** 2.2 *********************/
		/********************************************/
	    
	    /* mediumDots.txt */
//	    Maze mediumDots = new Maze("mediumDots");
//	    AstarPacman mediumDotsSolution = new AstarPacman(mediumDots);
//	    mediumDotsSolution.findSolution(mediumDots);
//	    
//	    System.out.print(mediumDots);
//	    System.out.println("Nodes Expanded = " + mediumDotsSolution.nodesExpanded);
//	    System.out.println("Solution Distance = " + mediumDotsSolution.solutionDistance);
//	    System.out.println();
	    
	    /* bigDots.txt */
	    Maze bigDots = new Maze("zzz_hugeDots");
	    AstarPacman bigDotsSolution = new AstarPacman(bigDots);
	    bigDotsSolution.findSolution(bigDots);
	    
	    System.out.print(bigDots);
	    System.out.println("Nodes Expanded = " + bigDotsSolution.nodesExpanded);
	    System.out.println("Solution Distance = " + bigDotsSolution.solutionDistance);
	    System.out.println();
	}
}