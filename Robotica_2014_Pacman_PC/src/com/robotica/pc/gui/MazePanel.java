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
	}

	public MazePanel(World w, int width, int height)
	{
		this.w = w;
		this.setPreferredSize(new Dimension(width, height));
		this.revalidate();
		w.addObserver(this);
	}

	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(3));
		g2d.setColor(Color.WHITE);
		
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g2d.setColor(Color.BLACK);
		
		Maze maze = w.getMaze();
		int cellWidth, cellHeight;
		cellWidth = this.getWidth() / maze.getWidth();
		cellHeight = this.getHeight() / maze.getHeight();
		for(int i =0; i<maze.getWidth();i++)
			for(int j=0; j < maze.getHeight(); j++)
			{
				g2d.drawRect(i*cellWidth, j*cellHeight, cellWidth, cellHeight);
				if(maze.getTile(i, j)==Tile.WALL)
					g2d.fillRect(i*cellWidth, j*cellHeight, cellWidth, cellHeight);
			}
		
		for(ConnectedEntity ce:w.getConnectedEntities())
		{
			Entity e = ce.getEntity();
			g2d.setColor(e.getColor());
			g2d.fillOval((int)(e.getX()*cellWidth), (int)(e.getY()*cellHeight), cellWidth,cellHeight);
			
			g2d.setColor(Color.RED);
			
			Location loc1 = Rotation.rotationToLocationVector(new Rotation(e.getRotation()).rotate(0.25));
			Location loc2 = Rotation.rotationToLocationVector(new Rotation(e.getRotation()).rotate(-0.25));

			int x = (int)((e.getX()+0.5)*cellWidth);
			int y = (int)((e.getY()+0.5)*cellHeight);
			
			Polygon poly= new Polygon();
			poly.addPoint(x, y);
			poly.addPoint((int)(x + loc1.getX()*0.5*cellWidth),(int)( y+loc1.getY()*0.5*cellHeight));
			poly.addPoint((int)(x + loc2.getX()*0.5*cellWidth),(int)( y+loc2.getY()*0.5*cellHeight));
			
			g2d.fillPolygon(poly);
		}
				
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
		this.repaint();
	}

}
