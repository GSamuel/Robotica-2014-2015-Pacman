package com.robotica.pc.model;

import java.awt.Color;
import java.awt.Point;

public class Entity implements Cloneable
{
	private int id;
	private EntityType type;
	private Location location;
	private Rotation rotation;
	private Color color;

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
		this.location = entity.location.clone();
		this.rotation = entity.rotation.clone();
	}

	private void init()
	{
		location = new Location();
		rotation = new Rotation();
		color = Color.BLACK;
	}

	public double getX()
	{
		return location.x;
	}

	public double getY()
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

	public Direction getDirection()
	{
		return Direction.radianToDirection(rotation.getRotation());
	}

	public void turnLeft()
	{
		rotation.rotate(Math.PI * 0.5);
	}

	public void turnRight()
	{
		rotation.rotate(-Math.PI * 0.5);
	}

	public void forward()
	{
		Point p = Direction.radianToDirection(rotation.getRotation())
				.getDirectionVector();
		location.translate(p.x, p.y);
	}

	public void backward()
	{
		Point p = Direction.radianToDirection(rotation.getRotation())
				.getDirectionVector();
		location.translate(-p.x, -p.y);
	}

	public double getRotation()
	{
		return rotation.getRotation();
	}

	public void setRotation(double rotation)
	{
		this.rotation.setRotation(rotation);
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

	public EntityType getType()
	{
		return type;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public void setColor(Color color)
	{
		this.color = color;
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
