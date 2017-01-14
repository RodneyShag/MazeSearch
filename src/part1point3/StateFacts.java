package part1point3;

import part1point1.Point;

/* This is just the important data from the "StateGhost" class that we will use to determine if 2 States are equivalent */
public class StateFacts {
	/* Data */
	Point pacmanLocation;
	Ghost ghost;
	
	public StateFacts(StateGhost state){
		pacmanLocation = new Point(state.pacmanLocation);
		ghost  		   = new Ghost(state.ghost);
	}
	
	@Override
    public boolean equals(Object obj) {
        if (obj == this) 
            return true;					
        if (!(obj instanceof StateFacts))
            return false;					
        StateFacts otherState = (StateFacts) obj;
        return pacmanLocation.equals(otherState.pacmanLocation) &&
        	   ghost.equals(otherState.ghost);
    }
	
	@Override
	public int hashCode(){
		return (3 * pacmanLocation.x) + (5 * pacmanLocation.y);
	}
}
