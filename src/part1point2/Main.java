package part1point2;

import java.io.IOException;

import part1point1.Maze;

public class Main {
	public static void main (String [] args) throws IOException{
        /********************************************/
        /****************** 1.2 *********************/
        /********************************************/
		
		/* Mazes from 1.1 (in "StateTurns", set moveCost to 1 and turnCost to 0 and verify that we get the same solution costs as section 1.1 */
//		Maze mediumMaze = new Maze("mediumMaze");
//		AstarTurns mediumMazeSolution = new AstarTurns(mediumMaze);
//		mediumMazeSolution.findSolution(mediumMaze);
//		System.out.println("AstarTurns");
//		System.out.println(mediumMaze);
//		System.out.println("AstarTurns Nodes Expanded = " + mediumMazeSolution.nodesExpanded);
//		System.out.println("AstarTurns Solution Distance = " + mediumMazeSolution.solutionDistance);
//		
//		Maze bigMaze = new Maze("bigMaze");
//		AstarTurns bigMazeSolution = new AstarTurns(bigMaze);
//		bigMazeSolution.findSolution(bigMaze);
//		System.out.println("\n\nAstarTurns");
//		System.out.println(bigMaze);
//		System.out.println("AstarTurns Nodes Expanded = " + bigMazeSolution.nodesExpanded);
//		System.out.println("AstarTurns Solution Distance = " + bigMazeSolution.solutionDistance);
//		
//		Maze openMaze = new Maze("openMaze");
//		AstarTurns openMazeSolution = new AstarTurns(openMaze);
//		openMazeSolution.findSolution(openMaze);
//		System.out.println("\n\nAstarTurns");
//		System.out.println(openMaze);
//		System.out.println("AstarTurns Nodes Expanded = " + openMazeSolution.nodesExpanded);
//		System.out.println("AstarTurns Solution Distance = " + openMazeSolution.solutionDistance);
		
		/* Mazes from 1.2 */
		Maze smallTurns = new Maze("smallTurns");
		AstarTurns smallTurnsSolution = new AstarTurns(smallTurns);
		smallTurnsSolution.findSolution(smallTurns);
		System.out.println("\n\nAstarTurns");
		System.out.println(smallTurns);
		System.out.println("AstarTurns Nodes Expanded = " + smallTurnsSolution.nodesExpanded);
		System.out.println("AstarTurns Solution Distance = " + smallTurnsSolution.solutionDistance);
		System.out.println("AstarTurns Solution Cost = " + smallTurnsSolution.solutionCost);
		
		Maze bigTurns = new Maze("bigTurns");
		AstarTurns bigTurnsSolution = new AstarTurns(bigTurns);
		bigTurnsSolution.findSolution(bigTurns);
		System.out.println("\n\nAstarTurns");
		System.out.println(bigTurns);
		System.out.println("AstarTurns Nodes Expanded = " + bigTurnsSolution.nodesExpanded);
		System.out.println("AstarTurns Solution Distance = " + bigTurnsSolution.solutionDistance);
		System.out.println("AstarTurns Solution Cost = " + bigTurnsSolution.solutionCost);
	}
}