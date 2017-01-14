package part1point1;

public class Heuristic {
    public int distanceHeuristic; // Manhattan Distance from current Point to maze's endPoint
    
    public Heuristic(Point point, Maze maze) {
        distanceHeuristic = Math.abs(point.x - maze.endPoint.x) + Math.abs(point.y - maze.endPoint.y);
    }
}
