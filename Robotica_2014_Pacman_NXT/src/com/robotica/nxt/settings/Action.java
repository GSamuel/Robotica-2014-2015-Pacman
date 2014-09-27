package com.robotica.nxt.settings;

public enum Action
{
	MOVE_FORWARDS, TURN_LEFT, TURN_RIGHT, MOVE_BACKWARDS, STAND_STILL;
	
	public static Action getDefault()
	{
		return STAND_STILL;
	}
}

