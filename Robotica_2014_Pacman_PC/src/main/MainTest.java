package main;

import java.awt.Color;
import java.util.ArrayList;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.VideoCapture;

import com.robotica.pc.ai.GhostAI;
import com.robotica.pc.gui.ConnectedEntityPanel;
import com.robotica.pc.gui.MatrixMouseInputPanel;
import com.robotica.pc.gui.MatrixPanel;
import com.robotica.pc.gui.MazePanel;
import com.robotica.pc.gui.PacmanWindow;
import com.robotica.pc.imageprocessing.Filter;
import com.robotica.pc.imageprocessing.Utils;
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
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		ArrayList<ConnectedEntity> list = new ArrayList<ConnectedEntity>();
		
		Entity pacman = new Entity(EntityType.PACMAN, 0);
		pacman.setGuiColor(Color.YELLOW);
		pacman.setLocation(0,0);
		
		Entity ghost1 = new Entity(EntityType.GHOST, 1);
		ghost1.setGuiColor(Color.BLUE);
		ghost1.setLocation(3,3);
		
		list.add(new ConnectedEntity(pacman, new PCConnector("pacman_device")));
		list.add(new ConnectedEntity(ghost1, new PCConnector("Parasect")));

		Maze m = new Maze(4,4);
		m.setTile(2, 2, Tile.WALL);
		m.setTile(0, 3, Tile.WALL);
		m.setTile(3, 0, Tile.WALL);
		m.show();

		World w = new World(m, list);
		w.setCamera(new VideoCapture(0));
		System.out.println(w);
		
		PacmanWindow pw = new PacmanWindow();
		MazePanel mazePanel = new MazePanel(w,500,400);
		pw.add(mazePanel);
		
		
		MatrixMouseInputPanel mousePanel = new MatrixMouseInputPanel("cam", w);
		pw.add(mousePanel);
		MatrixPanel mPanel = new MatrixPanel("warped", w);
		pw.add(mPanel);
		MatrixPanel mPanel2 = new MatrixPanel("filtered", w);
		pw.add(mPanel2);

		
		for(ConnectedEntity ce:list)
		{
			ConnectedEntityPanel p = new ConnectedEntityPanel(ce);
			pw.add(p);
		}

		GhostAI ghostAI = new GhostAI(w, 1,0);
		AINode path = ghostAI.createPath();
		path.showChain();
		System.out.println(path.getCost());
		
		
		
		Mat cam = new Mat();
		while(true)
		{
			w.camera.read(cam);
			w.getMatrixContainer().addMatrix("cam", cam);
			w.getMatrixContainer().addMatrix("warped", Filter.createWarpedImage(cam, new Size(mousePanel.getWidth(),  mousePanel.getHeight()), w.getMazeShape()));
			w.setMaze(Utils.createMazePattern(w.getMatrixContainer().getMatrix("warped"), 5,4));
			w.container.addMatrix("gray", Filter.createGrayImage(w.container.getMatrix("warped")));
			w.container.addMatrix("filtered",Filter.treshholdGrayImage(w.container.getMatrix("gray"), 20));
		}
	}

}
