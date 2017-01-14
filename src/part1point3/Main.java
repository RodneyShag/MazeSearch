package part1point3;

import java.io.IOException;

import part1point1.Maze;

public class Main {
	public static void main(String[] args) throws IOException, InterruptedException{
        /********************************************/
        /****************** 1.3 *********************/
        /********************************************/
		
		/* smallGhost.txt */
//		Maze smallGhost = new Maze("smallGhost");
//		AstarGhost smallGhostSolution = new AstarGhost(smallGhost);
//		smallGhostSolution.findSolution(smallGhost);
//		System.out.println("\nAstarGhost");
//		System.out.println(smallGhost);
//		System.out.println("AstarGhost Nodes Expanded = " + smallGhostSolution.nodesExpanded);
//		System.out.println("AstarGhost Solution Distance = " + smallGhostSolution.solutionDistance);

		/* mediumGhost.txt */
//		Maze mediumGhost = new Maze("mediumGhost");
//		AstarGhost mediumGhostSolution = new AstarGhost(mediumGhost);
//		mediumGhostSolution.findSolution(mediumGhost);
//		System.out.println("\nAstarGhost");
//		System.out.println(mediumGhost);
//		System.out.println("AstarGhost Nodes Expanded = " + mediumGhostSolution.nodesExpanded);
//		System.out.println("AstarGhost Solution Distance = " + mediumGhostSolution.solutionDistance);

		/* bigGhost.txt */
		Maze bigGhost = new Maze("bigGhost");
		AstarGhost bigGhostSolution = new AstarGhost(bigGhost);
		bigGhostSolution.findSolution(bigGhost);
		System.out.println("\nAstarGhost");
		System.out.println(bigGhost);
		System.out.println("AstarGhost Nodes Expanded = " + bigGhostSolution.nodesExpanded);
		System.out.println("AstarGhost Solution Distance = " + bigGhostSolution.solutionDistance);
	}
}
