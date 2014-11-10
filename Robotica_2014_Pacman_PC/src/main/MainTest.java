package main;

import java.awt.Color;

import com.robotica.pc.ai.GhostAI;
import com.robotica.pc.gui.MazePanel;
import com.robotica.pc.gui.PacmanWindow;
import com.robotica.pc.model.AINode;
import com.robotica.pc.model.Entity;
import com.robotica.pc.model.EntityList;
import com.robotica.pc.model.EntityType;
import com.robotica.pc.model.Maze;
import com.robotica.pc.model.Tile;
import com.robotica.pc.model.World;

public class MainTest
{
	public static void main(String[] args)
	{
		Entity[] entities = new Entity[2];
		entities[0] = new Entity(EntityType.PACMAN, 0);
		entities[0].setColor(Color.YELLOW);
		entities[0].setLocation(0,0);
		entities[1] = new Entity(EntityType.GHOST, 1);
		entities[1].setColor(Color.BLUE);
		entities[1].setLocation(3,3);
		EntityList list = new EntityList(entities);

		Maze m = new Maze(4,4);
		m.setTile(2, 2, Tile.WALL);
		m.setTile(0, 3, Tile.WALL);
		m.setTile(3, 0, Tile.WALL);
		m.show();

		World w = new World(m, list);
		System.out.println(w);
		
		PacmanWindow pw = new PacmanWindow();
		MazePanel mazePanel = new MazePanel(w,500,400);
		pw.add(mazePanel);

		GhostAI ghostAI = new GhostAI(w, 1,0);
		AINode path = ghostAI.createPath();
		path.showChain();
		System.out.println(path.getCost());
		
		
	}

}
