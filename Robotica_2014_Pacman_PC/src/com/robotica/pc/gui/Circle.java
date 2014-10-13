package com.robotica.pc.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;

public class Circle extends Point2D
{
	private int x, y, radius;

	public Circle(int x, int y, int radius)
	{
		this.x = x;
		this.y = y;
		this.radius = radius;
	}

	public Circle()
	{
		x = 0;
		y = 0;
		radius = 0;
	}

	public void draw(Graphics2D g2d, Color color)
	{
		g2d.setColor(color);
		g2d.drawOval(x, y, 2 * radius, 2 * radius);
	}

	@Override
	public double getX()
	{
		return x;
	}

	@Override
	public double getY()
	{
		return y;
	}

	@Override
	public void setLocation(double x, double y)
	{
		this.x = (int) Math.round(x);
		this.y = (int) Math.round(y);
	}

	public void setRadius(int radius)
	{
		this.radius = radius;
	}

	public void setRadius(double radius)
	{
		this.radius = (int) Math.round(radius);
	}

	public Point getCenter()
	{
		return new Point(x + radius, y + radius);
	}
	
	public int getRadius()
	{
		return radius;
	}
}
