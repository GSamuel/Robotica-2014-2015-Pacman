package com.robotica.nxt.settings;

public class Settings implements Cloneable
{
	private int motorSpeed;
	private int turnSpeed;
	
	public Settings()
	{
		this.motorSpeed = 200;
		this.turnSpeed = 200;
	}
	
	public Settings (int motorSpeed, int turnSpeed)
	{
		this.motorSpeed = motorSpeed;
		this.turnSpeed = turnSpeed;
	}
	
	public Settings(Settings settings)
	{
		this.motorSpeed = settings.motorSpeed;
		this.turnSpeed = settings.turnSpeed;
	}

	public int getMotorSpeed()
	{
		return motorSpeed;
	}
	
	public int getTurnSpeed()
	{
		return turnSpeed;
	}
	
	public void setMotorSpeed(int motorSpeed)
	{
		this.motorSpeed = motorSpeed;
	}
	
	public void setAcceleration(int turnSpeed)
	{
		this.turnSpeed = turnSpeed;
	}
	
	public Settings clone()
	{
		return new Settings(this);
	}
}
