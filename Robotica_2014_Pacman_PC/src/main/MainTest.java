package main;
import com.robotica.pc.model.Entity;
import com.robotica.pc.model.EntityList;
import com.robotica.pc.model.EntityType;
import com.robotica.pc.model.Maze;
import com.robotica.pc.model.Tile;
import com.robotica.pc.model.World;

public class MainTest
{

	public static void main(String[] args) throws InterruptedException
	{		
		Entity[] entities = new Entity[3];
		entities[0] = new Entity(EntityType.PACMAN, 0);
		entities[1] = new Entity(EntityType.GHOST, 1);
		entities[2] = new Entity(EntityType.GHOST, 2);
		EntityList list = new EntityList(entities);

		Maze m = new Maze(10, 10);
		m.setTile(2, 2, Tile.WALL);
		m.setTile(3, 2, Tile.WALL);
		m.show();

		World w = new World(m, list);
		System.out.println(w);
	}

}
