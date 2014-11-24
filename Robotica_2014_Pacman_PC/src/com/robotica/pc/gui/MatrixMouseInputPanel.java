package com.robotica.pc.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;

import com.robotica.pc.model.Trapezium;
import com.robotica.pc.model.World;

import controllers.MouseMatrixController;

public class MatrixMouseInputPanel extends MatrixPanel
{
	private static final long serialVersionUID = -5385724417985521499L;

	public MatrixMouseInputPanel(String key, World world)
	{
		super(key, world);
		MouseMatrixController mouseController = new MouseMatrixController(world);
		this.addMouseListener(mouseController);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.RED);
		g2d.setStroke(new BasicStroke(5));
		Trapezium shape = world.getMazeShape();
		if (shape.allPointsSet())
		{
			Polygon poly = new Polygon();
			for (int i = 0; i < 4; i++)
			{
				Point p = shape.getPoint(i);
				poly.addPoint(p.x, p.y);
			}
			g2d.drawPolygon(poly);
		}

	}

}
