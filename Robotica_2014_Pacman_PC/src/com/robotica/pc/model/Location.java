package com.robotica.pc.model;

import java.awt.geom.Point2D;

public class Location extends Point2D implements Cloneable
{
	public double x, y;

	public Location()
	{
		x = 0.0;
		y = 0.0;
	}

	public Location(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public double getX()
	{
		return x;
	}

	public int getGridX()
	{
		return Math.round(Math.round(x));
	}

	public int getGridY()
	{
		return Math.round(Math.round(y));
	}

	public double getY()
	{
		return y;
	}

	public Location clone()
	{
		return new Location(x, y);
	}

	public void setLocation(Location loc)
	{
		this.x = loc.x;
		this.y = loc.y;
	}

	public void translate(int dx, int dy)
	{
		this.x += dx;
		this.y += dy;
	}

	@Override
	public void setLocation(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
}
