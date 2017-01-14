package part2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

import part1point1.Point;

public class Heuristic {
	
	public int heuristic;
	
	/* Consistent Heuristics */
	public int dotsLeft; 			// 15715, 12506			//"Consistent"       (Those 2 numbers are for smallSearch, trickySearch from last semester)
	public int closestDot;			//fails on its own		//"Consistent"
	public int farthestDot;			//5758, 9437			//"Consistent"
	
	/* Combined Consistent Heuristics */
	public int closestPlusRest;		//10540, 10446          //"Consistent"
	
	/* Admissible Heuristics */
	public int twoDotDistance;		//1017, 11642			//Not "Consistent"
	public int xyRange;				//2656, 11743			//Not "Consistent"
	public int fancyHeuristic;
	public int fancyHeuristic2;
	
	/* Constructor */
	public Heuristic(StatePacman state){
		/* Admissible Heuristics */
//		setDotsLeftHeuristic(state);
//		heuristic = dotsLeft;
		
		/* All Heuristics */
		setDotsLeftHeuristic(state);
		setClosestDotHeuristic(state);
		setFarthestDotHeuristic(state);
		setTwoDotDistanceHeuristic(state.dots);
		setXYRangeHeuristic(state);
		setFancyHeuristic(state);
		setFancyHeuristic2(state);
		
		closestPlusRest = closestDot + (dotsLeft - 1);
		heuristic = Math.max(closestPlusRest, farthestDot);	//6006, 9412
		heuristic = Math.max(heuristic, twoDotDistance);	//1204, 8744
		heuristic = Math.max(heuristic, xyRange);			//1191, 8699
		heuristic = Math.max(heuristic, fancyHeuristic);
		heuristic = Math.max(heuristic, fancyHeuristic2);
		
		//heuristic = Math.max(100 * dotsLeft, 0);
		
		
		/* Uncomment below to use branchHeuristic (which doesn't generate great results */
		//branchHeuristic(state);
		//heuristic = branchHeuristic;
		//heuristic = Math.max(heuristic, branchHeuristic);//branchHeuristic;
		
		/* Heuristic for Part 2.2 */
		//heuristic = Math.max(25 * dotsLeft - farthestDot, 0);
	}
	
	/* 
	 * Branch and Bound technique - learned from wikipedia's "Traveling Salesman Problem" Page
	 * Did not work as a good heuristic since it still expanded too many nodes
	 */
//	public int branchHeuristic;
//	int lowestCost = Integer.MAX_VALUE;											//global variable
//	static HashMap<ArrayList<SimplePoint>, Integer> cache = new HashMap<>();	//global variable
//	public void branchHeuristic(StatePacman state){
//		/* Convert from Points to SimplePoints */
//		ArrayList<SimplePoint> dots = new ArrayList<>();
//		for (Point p : state.dots){
//			dots.add(new SimplePoint(p));
//		}
//		if (cache.containsKey(dots)){
//			lowestCost = cache.get(dots);
//			return;
//		}
//		ArrayList<SimplePoint> currentPath = new ArrayList<>();
//		//currentPath.add(new SimplePoint(state.pacmanLocation.x, state.pacmanLocation.y));
//		branchHeuristicHelper(dots, currentPath);
//		cache.put(dots, lowestCost);
//		branchHeuristic = lowestCost;
//	}
//	
//	public void branchHeuristicHelper(ArrayList<SimplePoint> dots, ArrayList<SimplePoint> currentPath){
//		for (SimplePoint dot : dots){
//			if (! currentPath.contains(dot)){
//				currentPath.add(dot);
//				if (costOfPath(currentPath) + (dots.size() - currentPath.size()) < lowestCost){
//					branchHeuristicHelper(dots, currentPath);
//					if (currentPath.size() == dots.size()){
//						int cost = costOfPath(currentPath);
//						lowestCost = Math.min(lowestCost, cost);
//					}
//				}
//				currentPath.remove(dot);
//			}
//		}
//	}
//	
//	private int costOfPath(ArrayList<SimplePoint> path){
//		if (path.size() <= 1)
//			return 0; //will work for now
//		int cost = 0;
//		for (int i = 1; i < path.size(); i++){
//			SimplePoint previousDot = path.get(i-1);
//			SimplePoint currentDot  = path.get(i);
//			cost += manhattanDistance(previousDot, currentDot);
//		}
//		return cost;
//	}

	/*
	 * Fancy Heuristic - By Column. 
	 * Inspired from the "Held–Karp algorithm" where "Every subpath of a path of minimum distance is itself of minimum distance."
	 */
	public void setFancyHeuristic(StatePacman state){
		fancyHeuristic = 0;
		if (state.dots.size() == 0)
			return;
		
		LinkedList<Point> dots = new LinkedList<Point>(state.dots);	//copy the state's dots. Using LinkedList since will be removing from front
		ArrayList<Point> processedDots = new ArrayList<Point>();
		ArrayList<Point> columnOfDots = new ArrayList<Point>();
		
		Collections.sort(dots, new PointComparator());
		
		/* Process 1st Dot */
		Point firstDot = dots.remove();
		processedDots.add(firstDot);
		int currColumn = firstDot.x;
		/* Process 1st Column (if any other dots are there) */
		while (!dots.isEmpty() && dots.peek().x == currColumn){
			Point currentDot = dots.remove();
			processedDots.add(currentDot);
			int distance = manhattanDistance(firstDot, currentDot);
			fancyHeuristic = Math.max(fancyHeuristic, distance);
		}
		while (!dots.isEmpty()){
			/* Find next column that has 1+ dots */
			while(currColumn != dots.peek().x){
				currColumn++;
			}
			/* Save all the dots in the column */
			while(!dots.isEmpty() && dots.peek().x == currColumn){
				Point currentDot = dots.remove();
				columnOfDots.add(currentDot);
			}
			fancyHeuristic += farthestDotDistance(processedDots, columnOfDots);
			processedDots.addAll(columnOfDots);
			columnOfDots.clear();
		}
	}
	
	/*
	 * Fancy Heuristic - By Row. 
	 */
	public void setFancyHeuristic2(StatePacman state){
		fancyHeuristic2 = 0;
		if (state.dots.size() == 0)
			return;
		
		LinkedList<Point> dots = new LinkedList<Point>(state.dots);	//copy the state's dots. Using LinkedList since will be removing from front
		ArrayList<Point> processedDots = new ArrayList<Point>();
		ArrayList<Point> rowOfDots = new ArrayList<Point>();
		
		Collections.sort(dots, new PointComparator2());
		
		/* Process 1st Dot */
		Point firstDot = dots.remove();
		processedDots.add(firstDot);
		int currRow = firstDot.y;
		/* Process 1st Row (if any other dots are there) */
		while (!dots.isEmpty() && dots.peek().y == currRow){
			Point currentDot = dots.remove();
			processedDots.add(currentDot);
			int distance = manhattanDistance(firstDot, currentDot);
			fancyHeuristic2 = Math.max(fancyHeuristic2, distance);
		}
		while (!dots.isEmpty()){
			/* Find next row that has 1+ dots */
			while(currRow != dots.peek().y){
				currRow++;
			}
			/* Save all the dots in the column */
			while(!dots.isEmpty() && dots.peek().y == currRow){
				Point currentDot = dots.remove();
				rowOfDots.add(currentDot);
			}
			fancyHeuristic2 += farthestDotDistance(processedDots, rowOfDots);
			processedDots.addAll(rowOfDots);
			rowOfDots.clear();
		}
	}
	
	/* Used by setFancyHeuristic */
	private int farthestDotDistance(ArrayList<Point> processedDots, ArrayList<Point> columnOfDots){
		int maxDistance = 0;
		for (Point dot : columnOfDots){
			int currDistance = closestDotDistance(processedDots, dot);
			maxDistance = Math.max(maxDistance, currDistance);
		}
		return maxDistance;
	}
	
	/* Used by setFancyHeuristic (since farthestDotDistance calls it) */
	private int closestDotDistance(ArrayList<Point> processedDots, Point currentDot){
		int minDistance = Integer.MAX_VALUE;
		for (Point p : processedDots){
			int currDistance = manhattanDistance(p, currentDot);
			minDistance = Math.min(minDistance, currDistance);
		}
		return minDistance;
	}
	
	public void setDotsLeftHeuristic(StatePacman state){
		dotsLeft = state.dots.size();
	}
	
	public void setClosestDotHeuristic(StatePacman state){
		closestDot = Integer.MAX_VALUE;
        for (Point point : state.dots) {
        	int distanceToDot = manhattanDistance(point, state.pacmanLocation);
        	closestDot = Math.min(closestDot, distanceToDot);
        }
        if (state.dots.isEmpty())
        	closestDot = 0;
	}
	
	public void setFarthestDotHeuristic(StatePacman state){
		farthestDot = 0;
        for (Point point: state.dots) {
        	int distanceToDot = manhattanDistance(state.pacmanLocation, point);
        	farthestDot = Math.max(farthestDot, distanceToDot);
        }
	}
	
	public void setTwoDotDistanceHeuristic(ArrayList<Point> dots){
		twoDotDistance = 0;
		int numDots = dots.size();
		for (int i = 0; i < numDots - 1; i++){
			Point p1 = dots.get(i);
			for (int j = 0; j < numDots; j++){
				Point p2 = dots.get(j);
				int currDistance = manhattanDistance(p1, p2);
				twoDotDistance = Math.max(twoDotDistance, currDistance);
			}
		}
	}
	
	public void setXYRangeHeuristic(StatePacman state){
		if (state.dots.size() == 0){
			xyRange = 0;
			return;
		}
		if (state.dots.size() == 1){
			xyRange = manhattanDistance(state.pacmanLocation, state.dots.get(0));
			return;
		}
		int xMin = Integer.MAX_VALUE;
		int yMin = Integer.MAX_VALUE;
		int xMax = 0;
		int yMax = 0;

		for (Point p : state.dots){
			xMin = Math.min(xMin, p.x);
			yMin = Math.min(yMin, p.y);
			xMax = Math.max(xMax, p.x);
			yMax = Math.max(yMax, p.y);
		}
		xyRange = (xMax - xMin) + (yMax - yMin);
	}
	
	private int manhattanDistance(Point p1, Point p2){
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }
	
	private int manhattanDistance(SimplePoint p1, SimplePoint p2){
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }
}
