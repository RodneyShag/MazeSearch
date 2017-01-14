package part2;

import java.util.ArrayList;

import part1point1.Point;

/* Just the important data from the "State" class that we will use to determine if 2 States are equivalent */
public class StateFacts {
	/* Data */
	Point pacmanLocation;
	ArrayList<Point> dots;
	
	public StateFacts(StatePacman state){
		pacmanLocation = new Point(state.pacmanLocation);
		dots           = new ArrayList<Point>(state.dots);
	}
	
	@Override
    public boolean equals(Object obj) {
        if (obj == this) 
            return true;					
        if (!(obj instanceof StateFacts))
            return false;					
        StateFacts otherState = (StateFacts) obj;
        return pacmanLocation.equals(otherState.pacmanLocation) &&
        	   dots.containsAll(otherState.dots) && otherState.dots.containsAll(dots);
    }
	
	@Override
	public int hashCode(){
		return (3 * pacmanLocation.x) + (5 * pacmanLocation.y);
	}
}
