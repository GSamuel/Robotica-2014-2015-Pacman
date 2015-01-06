package com.robotica.nxt.brick;

import lejos.nxt.Motor;

public class AdvancedBrick extends Brick
{
	@Override
	protected void move_forwards()
	{
		Motor.A.setSpeed(settings.getMotorSpeed());
		Motor.A.forward();
		Motor.C.setSpeed(settings.getMotorSpeed());
		Motor.C.forward();
	}

	@Override
	protected void turn_left()
	{
		Motor.A.setSpeed(settings.getTurnSpeed());
		Motor.A.backward();
		Motor.C.setSpeed(settings.getTurnSpeed());
		Motor.C.forward();
	}

	@Override
	protected void turn_right()
	{
		Motor.A.setSpeed(settings.getTurnSpeed());
		Motor.A.forward();
		Motor.C.setSpeed(settings.getTurnSpeed());
		Motor.C.backward();
		
	}

	@Override
	protected void move_backwards()
	{
		Motor.A.setSpeed(settings.getMotorSpeed());
		Motor.A.backward();
		Motor.C.setSpeed(settings.getMotorSpeed());
		Motor.C.backward();
	}

	@Override
	protected void stand_still()
	{
		Motor.A.suspendRegulation();
		Motor.C.suspendRegulation();
	}

}
