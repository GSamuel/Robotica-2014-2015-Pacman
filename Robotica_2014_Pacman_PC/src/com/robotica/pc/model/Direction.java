package com.robotica.pc.model;

public enum Direction
{
	NORTH, EAST, SOUTH, WEST;

	public Direction radianToDirection(double rad)
	{
		// TODO this function doesnt return the correct values yet.
		if (rad < Math.PI * 0.5)
			return NORTH;
		else if (rad < Math.PI)
			return EAST;
		else if (rad < Math.PI * 1.5)
			return SOUTH;
		return WEST;
	}
}
