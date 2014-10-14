package com.robotica.pc.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import com.robotica.pc.model.Entity;
import com.robotica.pc.model.Maze;
import com.robotica.pc.model.Tile;
import com.robotica.pc.model.World;

public class MazePanel extends JPanel implements Observer
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7299900260184427014L;
	private World w;

	public MazePanel(World w)
	{
		this.w = w;
		this.setPreferredSize(new Dimension(100, 100));
		this.revalidate();
	}

	public MazePanel(World w, int width, int height)
	{
		this.w = w;
		this.setPreferredSize(new Dimension(width, height));
		this.revalidate();
	}

	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(3));
		
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
		
		for(Entity e:w.getEntities())
		{
			g2d.setColor(e.getColor());
			g2d.fillOval((int)e.getX()*cellWidth, (int)e.getY()*cellHeight, cellWidth,cellHeight);
		}
				
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
	}

}
