package part1point2;

import part1point1.Direction;
import part1point1.Point;

/* Objects of this class will be added to HashSet<StateFacts>.
 * This is just the important data from the "StateTurns" class that we will use to determine if 2 States are equivalent */
public class StateFacts {
	/* Data */
	Point pacmanLocation;
	Direction direction;
	
	public StateFacts(StateTurns state){
		pacmanLocation	= new Point(state.pacmanLocation);
		direction		= state.direction;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (obj == this) 
            return true;					
        if (!(obj instanceof StateFacts))
            return false;					
        StateFacts otherState = (StateFacts) obj;
        return pacmanLocation.equals(otherState.pacmanLocation) &&
        	   direction == otherState.direction;
    }
	
	@Override
	public int hashCode(){
		return (3 * pacmanLocation.x) + (5 * pacmanLocation.y);
	}
}
