package com.robotica.nxt.settings;

public class Settings implements Cloneable
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
	
	public Settings(Settings settings)
	{
		this.motorSpeed = settings.motorSpeed;
		this.acceleration = settings.acceleration;
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
	
	public Settings clone()
	{
		return new Settings(this);
	}
}
