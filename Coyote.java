import info.gridworld.grid.Location;
import java.util.ArrayList;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;

public class Coyote extends Critter {
	
	private boolean stop, remove, wall;
	private int steps, stopCount;
	public Coyote() {
		setColor(null);
		setDirection( pickRandomDirection() );
		stop = false;
		remove=  false;
		wall=false;
		steps = 0;
		stopCount=0;
	}
	
	/* meet other actor, bump into wall, or taken 5 steps */
	
	/**
	 * get actor in spot directly in front
	 * @return	list of actors 
	 * */
	public ArrayList<Actor> getActors() {
		if (stop) return null; //return null if sleeping
		
		
		int dir = getDirection();
		Location loc = getLocation();
		ArrayList<Actor> actors = new ArrayList<>();
		if (getGrid().isValid(loc.getAdjacentLocation(dir))
			&& getGrid().get( loc.getAdjacentLocation(dir) ) != null) 
			actors.add(getGrid().get( loc.getAdjacentLocation(dir) ) );
		return actors; 
	}
	
	/**
	 * if runs into a boulder its removed from the grid in makeMove()
	 * @param actors	list of actors from getActors()
	 * */
	public void processActors(ArrayList<Actor> actors) {
		if (actors == null) return; 
		
		if (actors.size() > 0) {
			for (Actor a:actors) {
				if (a instanceof Boulder) remove=true;
			}
			if (!remove) {
				stop=true;
				steps=0;
			}
		}
	
	}
	
	/**
	 * gets the next move location
	 * @return	list of move locations
	 * */
	public ArrayList<Location> getMoveLocations() {
		//if next location is a wall
		if (isEdge(getLocation())) {
			stop=true;
			wall=true;
			steps=0;
			return null;
		}
		
		Location loc = getLocation().getAdjacentLocation(getDirection());
		ArrayList<Location> move = new ArrayList<>();
		if (getGrid().isValid(loc)) move.add(loc);
		else {
			stop=true;
			wall=true;
			steps=0;
			return null;
		}
		return move;
	}
	
	/**
	 * return either the next location or null if getMoveLocations() returned
	 * null (if at a wall)
	 * @param	list of locations from getMoveLocations()
	 * @return	location to move to
	 * */
	public Location selectMoveLocation(ArrayList<Location> locs) {
		if (locs == null || locs.size()==0) return null;
		
		return locs.get(0);
	} 
	
	/**
	 * removes self from grid if necessary
	 * checks if sleeping, if not make move to new location and
	 * track # steps to start sleeping if necessary
	 * @param	location to move to
	 * */
	public void makeMove(Location loc) {
		if (remove) {
			Kaboom kb = new Kaboom();
			Location currLoc = getLocation();
			Grid<Actor> gr = getGrid();
				
			removeSelfFromGrid();
			kb.putSelfInGrid(gr, currLoc);
			return;
		}
		if (stop) {
			stopCount++;
			if (stopCount==5) {
				stop=false;
				stopCount=0;
				if (!wall) {
					ArrayList<Location> empty = getGrid().getEmptyAdjacentLocations(getLocation());
					Stone st = new Stone();
					st.putSelfInGrid ( getGrid(), empty.get ( (int)Math.random()*empty.size()));
				} else wall=false;
				
				setDirection (pickRandomDirection(getDirection()));
			}
		} else {
			steps++;
			moveTo(loc);
			if (steps==5) {
				steps=0;
				stop=true;
			}
		}
		
	}
	
	/** picks a random direction 0-315 in 45s
	 * @return	the direction
	 * */
	private int pickRandomDirection() {
		return (int)(Math.random()*7)*Location.HALF_RIGHT;
	}
	
	/** pick random direction thats not previous direction
	 * @param	previous direction
	 * @return	new direction 
	 * */
	private int pickRandomDirection(int prev) {
		int ret = 0;
		do {
			ret = (int)(Math.random()*7)*Location.HALF_RIGHT;
		} while (ret == prev);
		return ret;
	}
	
	/** check if next location is wall
	 * @param	current location
	 * @return	true if next location is wall, false if not
	 * */
	private boolean isEdge(Location loc) {
		Location next = loc.getAdjacentLocation(getDirection());
		Grid<Actor> gr = getGrid();
		return next.getRow() >= gr.getNumRows() || next.getCol() >= gr.getNumCols();
	}
	
	
	



}
