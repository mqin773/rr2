import info.gridworld.actor.Actor;

public class Kaboom extends Actor {
	
	private int lifetime;
	private final int THRESHOLD = 3;
	
	public Kaboom() {
		setColor(null);
		lifetime = THRESHOLD;
	}
	
	public void act() {
		if (lifetime > 0) {
			lifetime--;
			if (lifetime == 0) removeSelfFromGrid();
		}
	}

}
