package com.robotica.nxt.settings;

public class Settings
{
	private int motorSpeed;
	private int acceleration;
	
	public Settings()
	{
		this.motorSpeed = 0;
		this.acceleration = 0;
	}
	
	public Settings (int motorSpeed, int acceleration)
	{
		this.motorSpeed = motorSpeed;
		this.acceleration = acceleration;
	}
	
	public int getMotorSpeed()
	{
		return motorSpeed;
	}
	
	public int getAcceleration()
	{
		return acceleration;
	}
	
	public void setMotorSpeed(int motorSpeed)
	{
		this.motorSpeed = motorSpeed;
	}
	
	public void setAcceleration(int acceleration)
	{
		this.acceleration = acceleration;
	}
}
