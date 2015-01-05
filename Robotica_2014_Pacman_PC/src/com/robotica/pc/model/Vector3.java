package com.robotica.pc.model;

public class Vector3 {
	private int x, y, z;
	
	public Vector3(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3()
	{
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	// returns the angle of this vector and the other vector in radians
	public double getAngle(Vector3 other)
	{
		return Math.acos(((double) dotProduct(other) / (this.getLength() * other.getLength())));
	}
	
	public double getLength()
	{
		return Math.sqrt(x * x + y * y + z * z);
	}
	
	public int dotProduct(Vector3 other)
	{
		return this.x * other.getX() + this.y * other.getY() + this.z * other.getZ();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}
	
	
}
