package com.robotica.pc.model;

public class Rotation implements Cloneable
{
	private double rotation;

	public Rotation()
	{
		this.rotation = 0.0;
	}
	
	public Rotation(Rotation rotation)
	{
		this.rotation = rotation.rotation;
		validateRotation();
	}

	public Rotation(double rotation)
	{
		this.rotation = rotation;
		validateRotation();
	}

	public double getRotation()
	{
		return rotation;
	}

	public void setRotation(double rotation)
	{
		this.rotation = rotation;
		validateRotation();
	}

	public Rotation rotate(double rotation)
	{
		this.rotation += rotation;
		validateRotation();
		return this;
	}
	
	public Rotation rotate(Rotation rotation)
	{
		this.rotate(rotation.rotation);
		return this;
	}

	public Rotation clone()
	{
		return new Rotation(rotation);
	}

	private void validateRotation()
	{
		rotation += Math.PI;
		while(rotation <0)
			rotation += Math.PI*2;
		rotation = rotation % (Math.PI * 2);
		rotation -= Math.PI;
	}
	
	public String toString()
	{
		return ""+rotation;
	}
	
	public Rotation difference(Rotation b)
	{
		return new Rotation(b.rotation - this.rotation);
	}
	
	public static Location rotationToLocationVector(Rotation rotation)
	{
		return new Location( Math.cos(rotation.getRotation()), -Math.sin(rotation.getRotation()) );
	}
	
	
	public static Rotation locationToRotation(Location location)
	{
		return new Rotation(Math.atan2(location.getX(), location.getY())-Math.PI*0.5 );
	}
}
