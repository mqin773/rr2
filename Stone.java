import info.gridworld.actor.Actor;
import java.awt.Color;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class Stone extends Rock {
	private int lifetime;
	private final int THRESHOLD = 3;
	
	private boolean put;
	
	public Stone() {
		setColor(null);
		lifetime = (int)(Math.random()*200)+1;
		put=false;
	}
	
	public Stone(int lt) {
		lifetime=lt;
		setColor(null);
		put=false;
	}
	
	public void act() {
		if (!put) {
			if (lifetime != 0) {
				if (lifetime < THRESHOLD) {
					setColor(Color.GREEN);
				}
				lifetime--;
			} else {
				Boulder b = new Boulder();
				Grid<Actor> gr = getGrid();
				Location loc = getLocation();
				removeSelfFromGrid();
				b.putSelfInGrid(gr, loc);
				put=true;
			}
		}
		
	}
}
