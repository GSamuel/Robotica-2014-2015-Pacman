package com.robotica.pc.model;

import java.awt.Point;

public enum Direction
{
	NORTH, EAST, SOUTH, WEST;

	public Direction turnRight()
	{
		int num = (this.ordinal() + 1) % Direction.values().length;
		return Direction.values()[num];
	}

	public Direction turnLeft()
	{
		int num = (this.ordinal() + Direction.values().length - 1)
				% Direction.values().length;
		return Direction.values()[num];
	}

	public Point getDirectionVector()
	{
		switch (this)
		{
		case NORTH:
			return new Point(0, -1);
		case EAST:
			return new Point(1, 0);
		case SOUTH:
			return new Point(0, 1);
		case WEST:
			return new Point(-1, 0);
		}
		return null;
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
