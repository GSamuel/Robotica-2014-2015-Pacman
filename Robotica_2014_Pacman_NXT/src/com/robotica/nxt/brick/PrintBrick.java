package com.robotica.nxt.brick;


public class PrintBrick extends Brick
{

	public PrintBrick()
	{
				
	}

	@Override
	protected void move_forwards()
	{
		System.out.println("moving forward");
	}

	@Override
	protected void turn_left()
	{
		System.out.println("turning left");
	}

	@Override
	protected void turn_right()
	{
		
		System.out.println("turning right");
	}

	@Override
	protected void move_backwards()
	{
		System.out.println("moving backwards");
		
	}

	@Override
	protected void stand_still()
	{
		System.out.println("standing still");
	}

}
