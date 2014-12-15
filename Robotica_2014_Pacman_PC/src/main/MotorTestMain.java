package main;

import java.awt.Color;
import java.util.ArrayList;

import org.opencv.core.Core;
import org.opencv.highgui.VideoCapture;

import com.robotica.nxt.remotecontrol.Command;
import com.robotica.nxt.remotecontrol.CommandCommunicator;
import com.robotica.nxt.remotecontrol.NXTCommand;
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
import com.robotica.pc.remotecontrol.PCConnector;

public class MotorTestMain
{

	public static void main(String[] args)
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		ArrayList<ConnectedEntity> list = new ArrayList<ConnectedEntity>();

		Entity pacman = new Entity(EntityType.PACMAN, 0);
		pacman.setColor(Color.YELLOW);
		pacman.setLocation(0, 0);
		pacman.setRotation(0);

		Entity ghost1 = new Entity(EntityType.GHOST, 1);
		ghost1.setColor(Color.BLUE);
		ghost1.setLocation(2, 2);
		ghost1.setRotation(Math.PI*0.5);

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
		
		CommandCommunicator comm = null;

		for (ConnectedEntity ce : list)
		{
			ConnectedEntityPanel p = new ConnectedEntityPanel(ce);
			pw.add(p);
			if(ce.getEntity().getID() == 1)
			{
				//ce.connectWithBluetooth();
				//comm = new CommandCommunicator(ce.getConnector());
			}
			
		}
		
		
		
		//comm.sendCommand(new Command(NXTCommand.SET_STATE, 1));

		 
		
		pw.revalidate();

		GhostAI ghostAI = new GhostAI(w, 1, 0);
		AINode path = ghostAI.createPath();
		path.showChain();
		System.out.println(path.getCost());
		
		PathCalculator.calculate(path, w, 1);
		
		while(true)
		{
			
		}
	}

}
