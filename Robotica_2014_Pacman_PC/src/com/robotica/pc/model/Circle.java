package com.robotica.pc.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;

import com.robotica.pc.imageprocessing.Utils;

public class Circle
{
	private int x, y, radius;
	private final int ROTATION_PRECISION = 16;
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
	
	public Rotation getRotation()
	{
		double angle = (1 / ROTATION_PRECISION) * 2.0 * Math.PI;
		ArrayList<Float> hues = new ArrayList<Float>();
		for(double alpha = 0; alpha < 2 * Math.PI; alpha += angle)
		{
			double x = Math.cos(angle) * (7.0 / 8.0) * radius;
			double y = Math.sin(angle) * (7.0 / 8.0) * radius;
			int color  = source.getRGB(this.x + (int) Math.round(x), this.y + (int) Math.round(y));
			hues.add(Color.RGBtoHSB((color >> 16) & 0xff, (color >> 8) & 0xff, color & 0xff, null)[0]);
		}
		float average = 0f;
		for(float hue : hues)
		{
			average += hue;
		}
		average /= (float) hues.size();
		
		int indexLargestDif = -1;
		float largestDif = 0f;
		for(int i = 0; i < hues.size(); i++)
		{
			if(Math.abs(average - hues.get(i)) > largestDif)
			{
				largestDif = Math.abs(average - hues.get(i));
				indexLargestDif = i;
			}
		}
		
		return new Rotation(angle * indexLargestDif);
	}
	
	public void setSource(BufferedImage source)
	{
		this.source = source;
	}
}
