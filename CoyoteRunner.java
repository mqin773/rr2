import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

public class CoyoteRunner {
	public static void main(String[] args) {
		BoundedGrid<Actor> mygrid = new BoundedGrid<Actor>(20,20);
        ActorWorld world = new ActorWorld(mygrid);
        
        Coyote bob  =new Coyote();
        Coyote sam = new Coyote();
        
        world.add(new Location(2, 3), bob);
        world.add(new Location(6, 9), sam);
        world.show();
	}
}
