package com.robotica.pc.model;

import java.awt.Color;
import java.awt.Point;
import java.util.Observable;

public class Entity extends Observable implements Cloneable
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
		this.setChanged();
		this.notifyObservers();
	}
	public Location getLocation()
	{
		return location;
	}

	public double getX()
	{
		return location.x;
	}

	public double getY()
	{
		return location.y;
	}

	public void setLocation(double x, double y)
	{
		location.setLocation(x, y);
		this.setChanged();
		this.notifyObservers();
	}

	public void translateLocation(double dx, double dy)
	{
		location.translate(dx, dy);
		this.setChanged();
		this.notifyObservers();
	}

	public Direction getDirection()
	{
		return Direction.radianToDirection(rotation.getRotation());
	}

	public void turnLeft()
	{
		rotation.rotate(Math.PI * 0.5);
		this.setChanged();
		this.notifyObservers();
	}

	public void turnRight()
	{
		rotation.rotate(-Math.PI * 0.5);
		this.setChanged();
		this.notifyObservers();
	}

	public void forward()
	{
		Point p = Direction.radianToDirection(rotation.getRotation())
				.getDirectionVector();
		location.translate(p.x, p.y);
		this.setChanged();
		this.notifyObservers();
	}

	public void backward()
	{
		Point p = Direction.radianToDirection(rotation.getRotation())
				.getDirectionVector();
		location.translate(-p.x, -p.y);
		this.setChanged();
		this.notifyObservers();
	}

	public Rotation getRotation()
	{
		return rotation;
	}

	public void setRotation(double rotation)
	{
		this.rotation.setRotation(rotation);
		this.setChanged();
		this.notifyObservers();
	}

	public void rotate(double rotation)
	{
		this.rotation.rotate(rotation);
		this.setChanged();
		this.notifyObservers();
	}

	public int getID()
	{
		return id;
	}

	public void setID(int id)
	{
		this.id = id;
		this.setChanged();
		this.notifyObservers();
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
		this.setChanged();
		this.notifyObservers();
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
