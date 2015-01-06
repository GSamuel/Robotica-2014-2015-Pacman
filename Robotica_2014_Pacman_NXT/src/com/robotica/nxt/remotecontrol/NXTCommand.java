package com.robotica.nxt.remotecontrol;

public enum NXTCommand
{
	SET_STATE, SET_MOTORSPEED, SET_TURNSPEED, FLUSH, EXIT;

	public static NXTCommand getDefault()
	{
		return EXIT;
	}
}
