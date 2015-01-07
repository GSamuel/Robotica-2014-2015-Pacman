package com.robotica.pc.model;

import java.awt.Color;
import java.awt.Point;
import java.util.Observable;

public class Entity extends Observable implements Cloneable
{
	private int id;
	private EntityType type;
	private Location location;
	private Location lastCheckpoint;
	private Rotation rotation;
	private Color guiColor;
	private Vector3 camColor;

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
		this.guiColor = entity.guiColor;
		this.camColor = entity.camColor;
	}

	private void init()
	{
		location = new Location();
		lastCheckpoint = new Location();
		rotation = new Rotation();
		guiColor = Color.BLACK;
		camColor = new Vector3(0,0,0);
		this.setChanged();
		this.notifyObservers();
	}

	public Location getLocation()
	{
		return location;
	}

	public Location getLastCheckpoint()
	{
		return lastCheckpoint;
	}

	private void updateCheckPoint()
	{
		double x = location.x;
		double y = location.y;
		boolean negX = x < 0;
		boolean negY = y < 0;
		if (negX)
			x *= -1;
		if (negY)
			y *= -1;

		x %= 1;
		y %= 1;

		if (x > 0.3 && x < 0.7 && y > 0.3 && y < 0.7)
			lastCheckpoint.setLocation(Math.floor(location.x) + 0.5,
					Math.floor(location.y) + 0.5);

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
		lastCheckpoint.setLocation(location);
		this.setChanged();
		this.notifyObservers();
	}

	public void translateLocation(double dx, double dy)
	{
		location.translate(dx, dy);
		updateCheckPoint();
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
		this.updateCheckPoint();
		this.setChanged();
		this.notifyObservers();
	}

	public void backward()
	{
		Point p = Direction.radianToDirection(rotation.getRotation())
				.getDirectionVector();
		location.translate(-p.x, -p.y);
		this.updateCheckPoint();
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
	
	public Vector3 getCamColor()
	{
		return camColor;
	}
	
	public void setCamColor(Vector3 vec)
	{
		camColor = vec;
	}

	public Color getGuiColor()
	{
		return guiColor;
	}

	public void setGuiColor(Color color)
	{
		this.guiColor = color;
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
