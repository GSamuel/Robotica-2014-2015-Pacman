package main;

import java.awt.Color;
import java.util.ArrayList;

import org.opencv.core.Core;
import org.opencv.highgui.VideoCapture;

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
import com.robotica.pc.pathCalculator.PathCalculator;
import com.robotica.pc.pathCalculator.PathExecutor;
import com.robotica.pc.remotecontrol.PCConnector;

public class MotorTestMain
{
	private static int pacmanID = 0;
	private static int ghost1ID = 1;
	private static int ghost2ID = 2;

	private static int pacmanColor = 0xFF000000;
	private static int ghost1Color = 0x00FF0000;
	private static int ghost2Color = 0x0000FF00;
	
	
	public static void main(String[] args)
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		ArrayList<ConnectedEntity> list = new ArrayList<ConnectedEntity>();

		Entity pacman = new Entity(EntityType.PACMAN, pacmanID);
		pacman.setColor(Color.YELLOW);
		pacman.setLocation(0.5, 0.5);
		pacman.setRotation(0);

		Entity ghost1 = new Entity(EntityType.GHOST, ghost1ID);
		ghost1.setColor(Color.BLUE);
		ghost1.setLocation(2.5, 2.8);
		ghost1.setRotation(Math.PI*0.5+0.2);

		list.add(new ConnectedEntity(pacman, new PCConnector("pacman_device")));
		list.add(new ConnectedEntity(ghost1, new PCConnector("NXT_9_1")));

		Maze m = new Maze(3,3);
		m.setTile(0, 1, Tile.WALL);
		m.setTile(0, 2, Tile.WALL);
		m.setTile(1, 1, Tile.WALL);
		m.setTile(1, 2, Tile.WALL);
		m.show();

		World w = new World(m, list);
		w.setCamera(new VideoCapture(3));
		System.out.println(w);
		
		
		

		PacmanWindow pw = new PacmanWindow();
		MazePanel mazePanel = new MazePanel(w, 500, 400);
		pw.add(mazePanel);

		for (ConnectedEntity ce : list)
		{
			ConnectedEntityPanel p = new ConnectedEntityPanel(ce);
			pw.add(p);
			
		}
		
		pw.revalidate();

		
		
		while(true)
		{
			try
			{
				Thread.sleep(200);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			//read image.
			//update world based on the image
			GhostAI ghostAI = new GhostAI(w, ghost1ID, pacmanID);//calculate AI
			AINode path = ghostAI.createPath();	// path created
			PathExecutor.execute(PathCalculator.calculate(path, w, ghost1ID), w, ghost1ID); // take first step off the path
			pw.repaint();
			
		}
	}

}
