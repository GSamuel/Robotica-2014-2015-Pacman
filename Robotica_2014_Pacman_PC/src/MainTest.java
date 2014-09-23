import com.robotica.pc.model.Entity;
import com.robotica.pc.model.EntityType;
import com.robotica.pc.model.Maze;
import com.robotica.pc.model.Tile;
import com.robotica.pc.model.World;

public class MainTest
{

	public static void main(String[] args) throws InterruptedException
	{
		Entity e = new Entity(EntityType.PACMAN, 4);
		e.setLocation(5, 37);
		
		World w = new World();
		w.addEntity(e);
		w.addEntity(e);
		w.addEntity(e);
		w.show();
		
		Maze m = new Maze(10,10);
		m.setTile(2, 2, Tile.WALL);
		m.setTile(3, 2, Tile.WALL);
		m.show();
	}

}
