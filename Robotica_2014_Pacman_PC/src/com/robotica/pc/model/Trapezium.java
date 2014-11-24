package com.robotica.pc.model;

import java.awt.Point;

public class Trapezium
{
	Point[] points = new Point[4];// lu, ru, ld, rd
	boolean[] pointsSet = new boolean[4];

	public Trapezium()
	{
		clear();
	}

	public void clear()
	{
		for (int i = 0; i < points.length; i++)
		{
			points[i] = new Point();
			pointsSet[i] = false;
		}
	}
	
	public Point getPoint(int index)
	{
		return points[index];
	}

	public void setPoint(Point p, int index)
	{
		points[index] = p;
		pointsSet[index] = true;
	}
	

	public boolean isSet(int index)
	{
		return pointsSet[index];
	}

	public boolean allPointsSet()
	{
		for (boolean b : pointsSet)
			if (!b)
				return false;

		return true;
	}
	
	public void show()
	{
		for(boolean b : pointsSet)
			System.out.print("("+b+")");
		for(Point p : points)
			System.out.print("("+p.getX()+", "+p.getY()+")");
		System.out.println();
	}

}
