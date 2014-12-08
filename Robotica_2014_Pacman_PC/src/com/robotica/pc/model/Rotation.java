package com.robotica.pc.model;

public class Rotation implements Cloneable
{
	private double rotation;

	public Rotation()
	{
		this.rotation = 0.0;
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

	public void rotate(double rotation)
	{
		this.rotation += rotation;
		validateRotation();
	}
	
	public void rotate(Rotation rotation)
	{
		this.rotate(rotation.rotation);
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
	
	
	public static Rotation locationToRotation(Location location)
	{
		return new Rotation(Math.atan2(location.getX(), location.getY())-Math.PI*0.5 );
	}
}
