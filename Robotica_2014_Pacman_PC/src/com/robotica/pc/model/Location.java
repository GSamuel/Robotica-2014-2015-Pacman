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
		return (int) Math.floor(x);
	}

	public int getGridY()
	{
		return (int) Math.floor(y);
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

	public void translate(double dx, double dy)
	{
		this.x += dx;
		this.y += dy;
	}
	
	public boolean isTheSameGrid(Location location)
	{
		return (this.getGridX() == location.getGridX() && this.getGridY() == location.getGridY());
	}
	
	public Location difference(Location b)
	{
		return new Location(this.x - b.x, this.y - b.y);
	}
	
	public double distance(Location b)
	{
		Location diff = difference(b);
		return Math.sqrt(diff.x*diff.x+diff.y*diff.y);
	}
	
	public double length()
	{
		return Math.sqrt(x*x+y*y);
	}

	@Override
	public void setLocation(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
}
