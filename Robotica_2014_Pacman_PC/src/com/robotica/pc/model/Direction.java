package com.robotica.pc.model;

public enum Direction
{
	NORTH, EAST, SOUTH, WEST;

	public Direction turnRight()
	{
		int num = (this.ordinal()+1) % Direction.values().length;
		return Direction.values()[num];
	}
	
	public Direction turnLeft()
	{
		int num = (this.ordinal()+Direction.values().length-1) % Direction.values().length;
		return Direction.values()[num];
	}
	
	public static Direction radianToDirection(double rad)
	{
		if (rad < -Math.PI * 0.75)
			return WEST;
		else if (rad < -Math.PI * 0.25)
			return SOUTH;
		else if (rad < Math.PI * 0.25)
			return EAST;
		else if (rad < Math.PI * 0.75)
			return NORTH;
		return WEST;
	}
}
