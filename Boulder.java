import info.gridworld.actor.Actor;
import java.awt.Color;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class Boulder extends Actor {
	
	private int lifetime;
	private final int THRESHOLD = 3;
	
	private boolean put;
	
	public Boulder() {
		setColor(null);
		lifetime = (int)(Math.random()*200)+1;
		put=false;
		
	}
	
	public Boulder (int lt) {
		setColor(null);
		lifetime = lt;
		put=false;
	}
	
	public void act() {
		if (!put) {
			if (lifetime != 0) {
				if (lifetime < THRESHOLD) {
					setColor(Color.RED);
				}
				
				
				lifetime--;
			} else {
				Kaboom k = new Kaboom();
				Grid<Actor> gr = getGrid();
				Location loc = getLocation();
				removeSelfFromGrid();
				k.putSelfInGrid(gr, loc);
				put=true;
			}
		}
		
		
		
	}



}
