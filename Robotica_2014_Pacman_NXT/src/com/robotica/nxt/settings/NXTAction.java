package com.robotica.nxt.settings;

public enum NXTAction
{
	MOVE_FORWARD, TURN_LEFT, TURN_RIGHT, MOVE_BACKWARDS, STAND_STILL;
	
	public static NXTAction getDefault()
	{
		return STAND_STILL;
	}
}

