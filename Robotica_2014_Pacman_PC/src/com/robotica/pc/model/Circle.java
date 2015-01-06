package com.robotica.pc.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Size;

import com.robotica.pc.imageprocessing.Utils;

public class Circle
{
	private int x, y, radius;
	private BufferedImage source;

	public Circle(int x, int y, int radius)
	{
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
	public Circle(int x, int y, int radius, BufferedImage source)
	{
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.source = source;
	}

	public Circle()
	{
		x = 0;
		y = 0;
		radius = 0;
	}

	public void draw(Graphics2D g2d, Color color)
	{
		g2d.setColor(color);
		g2d.drawOval(x, y, 2 * radius, 2 * radius);
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public void setLocation(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public void setRadius(int radius)
	{
		this.radius = radius;
	}

	public Point getCenter()
	{
		return new Point(x + radius, y + radius);
	}
	
	public int getRadius()
	{
		return radius;
	}
	
	public Color getColor()
	{
		List<Integer> rgbs = new ArrayList<Integer>();
		WritableRaster raster = source.getRaster();
		rgbs.addAll(Utils.arrayToList(raster.getPixel(getCenter().x, getCenter().y, (int[]) null)));
		rgbs.addAll(Utils.arrayToList(raster.getPixel(getCenter().x + getRadius()/2, getCenter().y, (int[]) null)));
		rgbs.addAll(Utils.arrayToList(raster.getPixel(getCenter().x - getRadius()/2, getCenter().y, (int[]) null)));
		rgbs.addAll(Utils.arrayToList(raster.getPixel(getCenter().x, getCenter().y + getRadius()/2, (int[]) null)));
		rgbs.addAll(Utils.arrayToList(raster.getPixel(getCenter().x, getCenter().y - getRadius()/2, (int[]) null)));
		return getAverageColor(rgbs);
	}
	
	private static Color getAverageColor(List<Integer> rgbs)
	{
		int red = 0;
		int blue = 0;
		int green = 0;
		for(int i = 0; i < rgbs.size(); i += 3)
		{
			red += rgbs.get(i);
			blue += rgbs.get(i+1);
			green += rgbs.get(i+2);
		}
		red /= rgbs.size() / 3;
		blue /= rgbs.size() / 3;
		green /= rgbs.size() / 3;
		return new Color(red, blue, green);
	}
	
	public Rotation getRotation(Circle c2)
	{
		Location loc = new Location(getCenter().getX(), getCenter().getY());
		Location loc2 = new Location(c2.getCenter().getX(), c2.getCenter().getY());
		
		return Rotation.locationToRotation(loc.difference(loc2));
	}
	
	public void setSource(BufferedImage source)
	{
		this.source = source;
	}
	
	public Location getLocation(Size mazeSize)
	{
		Point p = getCenter();
		return new Location((double) p.x / (double) this.source.getWidth() * mazeSize.width, 
							(double) p.y/ (double) this.source.getHeight() * mazeSize.height);
	}
}
