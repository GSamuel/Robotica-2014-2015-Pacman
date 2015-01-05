package com.robotica.nxt.brick;

import lejos.nxt.Motor;

public class MotorTestBrick extends Brick
{

	@Override
	protected void move_forwards()
	{
		Motor.A.setSpeed(200);
		Motor.A.forward();
		Motor.C.setSpeed(200);
		Motor.C.forward();
	}

	@Override
	protected void turn_left()
	{
		Motor.A.setSpeed(200);
		Motor.A.backward();
		Motor.C.setSpeed(200);
		Motor.C.forward();
	}

	@Override
	protected void turn_right()
	{
		Motor.A.setSpeed(200);
		Motor.A.forward();
		Motor.C.setSpeed(200);
		Motor.C.backward();
		
	}

	@Override
	protected void move_backwards()
	{
		Motor.A.setSpeed(200);
		Motor.A.backward();
		Motor.C.setSpeed(200);
		Motor.C.backward();
	}

	@Override
	protected void stand_still()
	{
		Motor.A.suspendRegulation();
		Motor.C.suspendRegulation();
	}

}
