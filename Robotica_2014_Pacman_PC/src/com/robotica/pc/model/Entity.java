package com.robotica.pc.model;

import java.awt.Point;

public class Entity implements Cloneable
{
	private int id;
	private EntityType type;
	private Point location;
	private Rotation rotation;

	public Entity(EntityType type)
	{
		this.id = 0;
		this.type = type;
		init();
	}

	public Entity(EntityType type, int id)
	{
		this.id = id;
		this.type = type;
		init();
	}

	public Entity(Entity entity)
	{
		this.id = entity.id;
		this.type = entity.type;
		this.location = (Point) entity.location.clone();
		this.rotation = entity.rotation.clone();
	}

	private void init()
	{
		location = new Point();
		rotation = new Rotation();
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
		return rotation.getRotation();
	}

	public void setRotation(double rotation)
	{
		this.rotation.setRotation(rotation);;
	}
	
	public void rotate(double rotation)
	{
		this.rotation.rotate(rotation);
	}

	public int getID()
	{
		return id;
	}

	public void setID(int id)
	{
		this.id = id;
	}
	
	public Entity clone()
	{
		return new Entity(this);
	}

	public void show()
	{
		System.out.println(this);
	}

	public String toString()
	{
		return type.name() + " " + id + " [" + location.x + "," + location.y
				+ "] " + rotation;
	}
}
