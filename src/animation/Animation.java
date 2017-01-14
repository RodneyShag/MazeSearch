package animation;

import java.util.HashMap;
import java.util.Stack;

import part1point1.Maze;
import part1point1.Point;
import part1point1.PointType;
import part1point3.StateGhost;
import part2.StatePacman;

/* 
 * Downloading and using StdDraw to draw was so intuitive:
 * http://introcs.cs.princeton.edu/java/stdlib/StdDraw.java.html
 */
public class Animation {
	static int squareWidth = 10;
	public static void drawMaze(Maze maze){
		int xWidth = maze.columns * squareWidth;
		int yHeight = maze.rows * squareWidth;
		StdDraw.setCanvasSize(xWidth*2, yHeight*2);
		StdDraw.setXscale(0, xWidth);
		StdDraw.setYscale(0, yHeight);
		
		for (int i = 0; i < maze.rows; i++){
			for (int j = 0; j < maze.columns; j++){
				int xPos = (j * squareWidth) + (squareWidth) / 2;
				int yPos = (i * squareWidth) + (squareWidth) / 2;
				yPos = yHeight - yPos;
				Point currentPoint = maze.grid[i][j];
				if (currentPoint.pointType == PointType.WALL){
					StdDraw.setPenColor(java.awt.Color.BLACK);
					StdDraw.filledSquare(xPos, yPos, squareWidth/2);
				}
				else if (currentPoint.pointType == PointType.DOT){
					StdDraw.setPenColor(java.awt.Color.BLUE);
					StdDraw.filledCircle(xPos, yPos, squareWidth/3);
				}
				else if (currentPoint.pointType == PointType.START){
					StdDraw.picture(xPos, yPos, "pacman.png", 10, 10);
				}
			}
		}
		StdDraw.show(5000);
	}
	public static void drawSolution(Maze maze, HashMap<StateGhost, StateGhost> solutionMap, StateGhost state) throws InterruptedException{
		/* Initial setup */
		StdDraw.setPenColor(java.awt.Color.WHITE);
		int yHeight = maze.rows * squareWidth;		
		Stack<StateGhost> stack = new Stack<>();
		int counter = 0;
		
		/* Save initial picture */
		StdDraw.save("picture" + counter + ".jpg");
		counter++;
		
		/* Erase the initial Pacman */
		int xStartPoint = (maze.startPoint.x * squareWidth) + (squareWidth) / 2;
		int yStartPoint = (maze.startPoint.y * squareWidth) + (squareWidth) / 2;
		yStartPoint = yHeight - yStartPoint;
		StdDraw.filledSquare(xStartPoint, yStartPoint, squareWidth/2);
		
		/* Move solution from Stack into a HashMap */
		while(solutionMap.containsKey(state)) {
			stack.push(state);
			state = solutionMap.get(state);
		}
		while (!stack.isEmpty()){
			state = stack.pop();
			
			/* Draw Pacman */
			int xPosPacman = (state.pacmanLocation.x * squareWidth) + (squareWidth) / 2;
			int yPosPacman = (state.pacmanLocation.y * squareWidth) + (squareWidth) / 2;
			yPosPacman = yHeight - yPosPacman;
			StdDraw.filledSquare(xPosPacman, yPosPacman, squareWidth/2);
			StdDraw.picture(xPosPacman, yPosPacman, "pacman.png", 10, 10);
			/* Draw Ghost */
			int xPosGhost = (state.ghost.location.x * squareWidth) + (squareWidth) / 2;
			int yPosGhost = (state.ghost.location.y * squareWidth) + (squareWidth) / 2;
			yPosGhost = yHeight - yPosGhost;
			StdDraw.picture(xPosGhost, yPosGhost, "ghost.png", 10, 10);
			
			Thread.sleep(200);
			StdDraw.show();
			StdDraw.save("picture" + counter + ".jpg");
			counter++;
			
			/* Erase the Pacman and Ghost previous positions */
			if (stack.size() != 0){
				StdDraw.filledSquare(xPosPacman, yPosPacman, squareWidth/2);
				StdDraw.filledSquare(xPosGhost, yPosGhost, squareWidth/2);
			}
			
		}
	}
	
	public static void drawSolution(Maze maze, HashMap<StatePacman, StatePacman> solutionMap, StatePacman state) throws InterruptedException{
		/* Initial setup */
		StdDraw.setPenColor(java.awt.Color.WHITE);
		int yHeight = maze.rows * squareWidth;		
		Stack<StatePacman> stack = new Stack<>();
		int counter = 0;
		
		/* Save initial picture */
		StdDraw.save("picture" + counter + ".jpg");
		counter++;
		
		/* Erase the initial Pacman */
		int xStartPoint = (maze.startPoint.x * squareWidth) + (squareWidth) / 2;
		int yStartPoint = (maze.startPoint.y * squareWidth) + (squareWidth) / 2;
		yStartPoint = yHeight - yStartPoint;
		StdDraw.filledSquare(xStartPoint, yStartPoint, squareWidth/2);
		
		/* Move solution from Stack into a HashMap */
		while(solutionMap.containsKey(state)) {
			stack.push(state);
			state = solutionMap.get(state);
		}
		while (!stack.isEmpty()){
			state = stack.pop();
			
			/* Draw Pacman */
			int xPosPacman = (state.pacmanLocation.x * squareWidth) + (squareWidth) / 2;
			int yPosPacman = (state.pacmanLocation.y * squareWidth) + (squareWidth) / 2;
			yPosPacman = yHeight - yPosPacman;
			StdDraw.filledSquare(xPosPacman, yPosPacman, squareWidth/2);
			StdDraw.picture(xPosPacman, yPosPacman, "pacman.png", 10, 10);
			
			Thread.sleep(30);
			StdDraw.show();
			StdDraw.save("picture" + counter + ".jpg");
			counter++;
			
			/* Erase the Pacman from previous positions */
			if (stack.size() != 0){
				StdDraw.filledSquare(xPosPacman, yPosPacman, squareWidth/2);
			}
			
		}
	}
}
