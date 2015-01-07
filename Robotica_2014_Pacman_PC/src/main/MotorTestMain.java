package main;

import java.awt.Color;
import java.util.ArrayList;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.VideoCapture;

import com.robotica.pc.ai.GhostAI;
import com.robotica.pc.ai.PacmanAI;
import com.robotica.pc.gui.ConnectedEntityPanel;
import com.robotica.pc.gui.MatrixCirclePanel;
import com.robotica.pc.gui.MatrixMouseInputPanel;
import com.robotica.pc.gui.MatrixPanel;
import com.robotica.pc.gui.MazePanel;
import com.robotica.pc.gui.PacmanWindow;
import com.robotica.pc.imageprocessing.Filter;
import com.robotica.pc.imageprocessing.Utils;
import com.robotica.pc.model.AINode;
import com.robotica.pc.model.Circle;
import com.robotica.pc.model.ConnectedEntity;
import com.robotica.pc.model.Entity;
import com.robotica.pc.model.EntityType;
import com.robotica.pc.model.Location;
import com.robotica.pc.model.Maze;
import com.robotica.pc.model.Tile;
import com.robotica.pc.model.Vector3;
import com.robotica.pc.model.World;
import com.robotica.pc.pathCalculator.PathCalculator;
import com.robotica.pc.pathCalculator.PathExecutor;
import com.robotica.pc.remotecontrol.PCConnector;

public class MotorTestMain
{
	private static int pacmanID = 0;
	private static int ghost1ID = 1;
	private static int ghost2ID = 2;

	// these should be approximations of the colours of the actual circles
	// should probably still be finetuned!!!
	private static Vector3 pacmanColor = new Vector3(220, 205, 90); // yellow
	private static Vector3 ghost1Color = new Vector3(220, 130, 22); // orange
	private static Vector3 ghost2Color = new Vector3(150, 185, 178); // cyan

	public static void main(String[] args)

	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		ArrayList<ConnectedEntity> list = new ArrayList<ConnectedEntity>();

		Entity pacman = new Entity(EntityType.PACMAN, pacmanID);
		pacman.setGuiColor(Color.YELLOW);
		pacman.setCamColor(pacmanColor);
		pacman.setLocation(0.5, 0.5);
		pacman.setRotation(0);

		Entity ghost1 = new Entity(EntityType.GHOST, ghost1ID);
		ghost1.setGuiColor(Color.ORANGE);
		ghost1.setCamColor(ghost1Color);
		ghost1.setLocation(1.5, 0.5);
		ghost1.setRotation(0);

		Entity ghost2 = new Entity(EntityType.GHOST, ghost2ID);
		ghost2.setGuiColor(Color.CYAN);
		ghost2.setCamColor(ghost2Color);
		ghost2.setLocation(2.5, 0.5);
		ghost2.setRotation(0);

		list.add(new ConnectedEntity(pacman, new PCConnector("NXT4")));
		list.add(new ConnectedEntity(ghost1, new PCConnector("Parasect")));
		list.add(new ConnectedEntity(ghost2, new PCConnector("NXT_9_1")));

		Maze m = new Maze(5, 5);
		m.setTile(0, 3, Tile.WALL);
		m.setTile(0, 4, Tile.WALL);
		m.setTile(1, 1, Tile.WALL);
		m.setTile(1, 4, Tile.WALL);
		m.setTile(2, 2, Tile.WALL);
		m.setTile(3, 0, Tile.WALL);
		m.setTile(3, 3, Tile.WALL);
		m.setTile(4, 0, Tile.WALL);
		m.setTile(4, 1, Tile.WALL);
		m.show();

		World w = new World(m, list);
		w.setCamera(new VideoCapture(0));
		System.out.println(w);

		PacmanWindow pw = new PacmanWindow();
		MazePanel mazePanel = new MazePanel(w, 500, 400);
		pw.add(mazePanel);
		MatrixMouseInputPanel cameraPanel = new MatrixMouseInputPanel("cam", w);
		pw.add(cameraPanel);
		MatrixCirclePanel mcP = new MatrixCirclePanel("warped", "greyWarped", w);
		pw.add(mcP);
		MatrixPanel mcP2 = new MatrixPanel("greyDifference", w);
		pw.add(mcP2);

		for (ConnectedEntity ce : list)
		{
			ConnectedEntityPanel p = new ConnectedEntityPanel(ce);
			pw.add(p);
		}

		pw.revalidate();

		GhostAI ghostAI = new GhostAI(w, ghost1ID, pacmanID);
		GhostAI ghostAI2 = new GhostAI(w, ghost2ID, pacmanID);
		PacmanAI pacmanAI = new PacmanAI(w, pacmanID);

		boolean mazeDone = false;

		while (true)
		{
			try
			{
				Thread.sleep(0);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			// read image.
			// update world based on the image
			Mat mat = new Mat();
			w.camera.read(mat);
			w.container.addMatrix("cam", mat);

			w.container.addMatrix(
					"warped",
					Filter.createWarpedImage(mat, new Size(400,400),
							w.getMazeShape()));

			Mat m1 = w.container.getMatrix("greyWarped"), m2;

			w.container.addMatrix("greyWarped", Filter.createBlurred(Filter
					.createGrayImage(w.container.getMatrix("warped"))));

			m2 = w.container.getMatrix("greyWarped");

			w.container.addMatrix("greyDifference",
					Filter.differenceGrey(m1, m2));

			if (!mazeDone)
			{
				w.setMaze(Utils.createMazePattern(w.getMatrixContainer()
						.getMatrix("warped"), 8, 8));
			}
			if (w.getMazeShape().allPointsSet())
				mazeDone = true;

			// berekenen circles van image procressing
			ArrayList<Circle> circles = Utils.getCirclesFromMat(
					Filter.getCircles(w.getMatrixContainer().getMatrix(
							"greyWarped")),
					w.getMatrixContainer().getMatrix("warped"));

			for (Circle c : circles)
			{
				Color col = c.getColor();
				Vector3 vec = new Vector3(col.getRed(), col.getGreen(),
						col.getBlue());

				for (ConnectedEntity ce : w.getConnectedEntities())
				{
					if (vec.getAngle(ce.getEntity().getCamColor()) < 0.15)
					{
						Location loc = c.getLocation(w.getMaze().getSize());
						ce.getEntity().setLocation(loc.getX(), loc.getY());

						// ce.getEntity().setRotation(c.getRotation().getRotation());
					}
				}
			}

			// 500,500

			// einde

			for (ConnectedEntity ce : w.getConnectedEntities())
			{
				if (ce.isConnected())
				{
					if (ce.getEntity().getID() == ghost2ID)
					{
						AINode path = ghostAI2.createPath(); // calculate Path
						PathExecutor.execute(
								PathCalculator.calculate(path, w, ghost2ID), w,
								ghost2ID); // take first step off the path
					}
					if (ce.getEntity().getID() == ghost1ID)
					{
						AINode path = ghostAI.createPath(); // calculate Path
						PathExecutor.execute(
								PathCalculator.calculate(path, w, ghost1ID), w,
								ghost1ID); // take first step off the path
					}
					if (ce.getEntity().getID() == pacmanID)
					{
						AINode path = pacmanAI.createPath(); // calculate Path
						PathExecutor.execute(
								PathCalculator.calculate(path, w, pacmanID), w,
								pacmanID); // take first step off the path
					}
				}
			}

			pw.repaint();
			mcP.repaint();
		}
	}

}
