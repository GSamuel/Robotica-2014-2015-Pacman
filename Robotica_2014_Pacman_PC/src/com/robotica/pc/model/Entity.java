package com.robotica.pc.model;

import java.awt.Point;

public class Entity
{
	private int id;
	private EntityType type;
	private Point location;
	private double rotation;
	
	public Entity(EntityType type)
	{
		this.id = 0;
		this.type  =type;
		init();
	}

	public Entity(EntityType type, int id)
	{
		this.id = id;
		this.type = type;
		init();
	}
	
	private void init()
	{
		location = new Point();
		rotation =0.0;
	}

	public int getX()
	{
		return location.x;
	}

	public int getY()
	{
		return location.y;
	}

	public void setLocation(int x, int y)
	{
		location.setLocation(x, y);
	}

	public void translateLocation(int dx, int dy)
	{
		location.translate(dx, dy);
	}
	
	public double getRotation()
	{
		return rotation;
	}
	
	public void setRotation(double rotation)
	{
		this.rotation = rotation;
	}

	public int getID()
	{
		return id;
	}
	
	public void setID(int id)
	{
		this.id = id;
	}

	public void show()
	{
		System.out.println(this);
	}

	public String toString()
	{
		return type.name() + " " + id + " [" + location.x + "," + location.y
				+ "] "+rotation;
	}
}
