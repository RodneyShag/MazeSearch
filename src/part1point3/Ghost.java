package part1point3;

import part1point1.Point;
import part1point1.Direction;

public class Ghost{
	public Point location;
	public int minX;
	public int maxX;
	Direction direction;
	
	/* Constructors */
	public Ghost(){
		location  = null;
		minX 	  = Integer.MAX_VALUE;
		maxX 	  = 0;
		direction = Direction.EAST;
	}
	
	public Ghost(Point p, int min, int max){
		location  = new Point(p);
		minX 	  = min;
		maxX 	  = max;
		direction = Direction.EAST;
	}
	
	/* Copy Constructor */
	public Ghost(Ghost otherGhost){
		location  = new Point(otherGhost.location);
		minX 	  = otherGhost.minX;
		maxX 	  = otherGhost.maxX;
		direction = otherGhost.direction;
	}
	
	/* Tricky coding since movedGhost is different than this ghost. Don't mix up the variables! */
	public Ghost getNewMovedGhost(){
		Ghost movedGhost = new Ghost(this);
		if (direction == Direction.EAST){
			if (location.x == maxX){
				movedGhost.direction = Direction.WEST;
				movedGhost.location.x--;
			}
			else
				movedGhost.location.x++;
		}
		else{ //direction is WEST
			if (location.x == minX){
				movedGhost.direction = Direction.EAST;
				movedGhost.location.x++;
			}
			else
				movedGhost.location.x--;
		}
		return movedGhost;
	}
	
	/* Overrides */
	@Override
    public boolean equals(Object obj) {
        if (obj == this) 
            return true;				
        if (!(obj instanceof Ghost))
            return false;	
        Ghost otherState = (Ghost) obj;
        return location.equals(otherState.location) &&
        	   minX == otherState.minX &&
        	   maxX == otherState.maxX &&
        	   direction == otherState.direction;
    }
	
	@Override
	public int hashCode(){
		return (3* location.x) + (5 * location.y) + (7 * minX) + (11 * maxX);
	}
}
