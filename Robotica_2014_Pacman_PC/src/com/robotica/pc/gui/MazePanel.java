package com.robotica.pc.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import com.robotica.pc.model.ConnectedEntity;
import com.robotica.pc.model.Entity;
import com.robotica.pc.model.Location;
import com.robotica.pc.model.Maze;
import com.robotica.pc.model.Rotation;
import com.robotica.pc.model.Tile;
import com.robotica.pc.model.World;

import controllers.MousePanelController;

public class MazePanel extends JPanel implements Observer
{
	private static final long serialVersionUID = 7299900260184427014L;
	private World w;

	public MazePanel(World w)
	{
		this.w = w;
		this.setPreferredSize(new Dimension(100, 100));
		this.revalidate();
		w.addObserver(this);
		this.addMouseListener(new MousePanelController(w, this));
	}

	public MazePanel(World w, int width, int height)
	{
		this.w = w;
		this.setPreferredSize(new Dimension(width, height));
		this.revalidate();
		w.addObserver(this);
		this.addMouseListener(new MousePanelController(w,this));
	}

	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(3));
		g2d.setColor(Color.WHITE);

		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

		g2d.setColor(Color.BLACK);

		Maze maze = w.getMaze();
		int cellWidth = 0, cellHeight = 0;
		if (maze.getWidth() != 0)
			cellWidth = this.getWidth() / maze.getWidth();
		if (maze.getHeight() != 0)
			cellHeight = this.getHeight() / maze.getHeight();
		for (int i = 0; i < maze.getWidth(); i++)
			for (int j = 0; j < maze.getHeight(); j++)
			{
				g2d.drawRect(i * cellWidth, j * cellHeight, cellWidth,
						cellHeight);
				if (maze.getTile(i, j) == Tile.WALL)
					g2d.fillRect(i * cellWidth, j * cellHeight, cellWidth,
							cellHeight);
			}

		for (ConnectedEntity ce : w.getConnectedEntities())
		{
			Entity e = ce.getEntity();
			g2d.setColor(e.getColor());
			g2d.fillOval((int) (e.getX() * cellWidth - cellWidth * 0.5),
					(int) (e.getY() * cellHeight - cellHeight * 0.5),
					cellWidth, cellHeight);
			
			//now draw the last checkpoint
			Location cp = e.getLastCheckpoint();
			g2d.fillOval((int) (cp.getX() * cellWidth - cellWidth * 0.5/3),
					(int) (cp.getY() * cellHeight - cellHeight * 0.5/3),
					cellWidth/3, cellHeight/3);
			g2d.setColor(Color.BLACK);
			g2d.drawOval((int) (cp.getX() * cellWidth - cellWidth * 0.5/3),
					(int) (cp.getY() * cellHeight - cellHeight * 0.5/3),
					cellWidth/3, cellHeight/3);
			

			g2d.setColor(Color.RED);

			Location loc1 = Rotation.rotationToLocationVector(new Rotation(e
					.getRotation()).rotate(0.25));
			Location loc2 = Rotation.rotationToLocationVector(new Rotation(e
					.getRotation()).rotate(-0.25));

			int x = (int) ((e.getX() + 0.5) * cellWidth);
			int y = (int) ((e.getY() + 0.5) * cellHeight);

			Polygon poly = new Polygon();
			poly.addPoint(x - (int) (cellWidth * 0.5), y
					- (int) (cellHeight * 0.5));
			poly.addPoint(
					(int) (x + loc1.getX() * 0.5 * cellWidth - (cellWidth * 0.5)),
					(int) (y + loc1.getY() * 0.5 * cellHeight - (cellHeight * 0.5)));
			poly.addPoint(
					(int) (x + loc2.getX() * 0.5 * cellWidth - (cellWidth * 0.5)),
					(int) (y + loc2.getY() * 0.5 * cellHeight - (cellHeight * 0.5)));

			g2d.fillPolygon(poly);
		}
		
		g2d.setColor(Color.ORANGE);
		g2d.drawRect((int)(w.getPacmanGoal().getX()*cellWidth), (int)(w.getPacmanGoal().getY()*cellHeight), cellWidth, cellHeight);

	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
		this.repaint();
	}

}
