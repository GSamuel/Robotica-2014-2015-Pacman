package com.robotica.pc.model;

public class Rotation
{
	private double rotation;

	public Rotation()
	{
		this.rotation = 0.0;
	}

	public Rotation(double rotation)
	{
		this.rotation = rotation;
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

	public Rotation clone()
	{
		return new Rotation(rotation);
	}

	private void validateRotation()
	{
		rotation += Math.PI;
		rotation = rotation % (Math.PI * 2);
		rotation -= Math.PI;
	}
	
	public String toString()
	{
		return ""+rotation;
	}
}
