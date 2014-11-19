package main;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import com.robotica.pc.ai.GhostAI;
import com.robotica.pc.gui.ConnectedEntityPanel;
import com.robotica.pc.gui.MazePanel;
import com.robotica.pc.gui.PacmanWindow;
import com.robotica.pc.model.AINode;
import com.robotica.pc.model.ConnectedEntity;
import com.robotica.pc.model.Entity;
import com.robotica.pc.model.EntityType;
import com.robotica.pc.model.Maze;
import com.robotica.pc.model.Tile;
import com.robotica.pc.model.World;
import com.robotica.pc.remotecontrol.PCConnector;

public class MainTest
{
	public static void main(String[] args)
	{
		ArrayList<ConnectedEntity> list = new ArrayList<ConnectedEntity>();
		
		Entity pacman = new Entity(EntityType.PACMAN, 0);
		pacman.setColor(Color.YELLOW);
		pacman.setLocation(0,0);
		
		Entity ghost1 = new Entity(EntityType.GHOST, 1);
		ghost1.setColor(Color.BLUE);
		ghost1.setLocation(3,3);
		
		list.add(new ConnectedEntity(pacman, new PCConnector("pacman_device")));
		list.add(new ConnectedEntity(ghost1, new PCConnector("ghost_device")));

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
		for(ConnectedEntity ce:list)
		{
			ConnectedEntityPanel p = new ConnectedEntityPanel(ce);
			pw.add(p);
		}

		GhostAI ghostAI = new GhostAI(w, 1,0);
		AINode path = ghostAI.createPath();
		path.showChain();
		System.out.println(path.getCost());
	}

}
