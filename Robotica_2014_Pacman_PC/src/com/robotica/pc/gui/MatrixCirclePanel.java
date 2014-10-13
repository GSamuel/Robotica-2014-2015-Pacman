package com.robotica.pc.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import org.opencv.core.Mat;

import com.robotica.pc.imageprocessing.Filter;
import com.robotica.pc.imageprocessing.Utils;
import com.robotica.pc.model.MatrixContainer;

public class MatrixCirclePanel extends MatrixPanel
{
	private String keyBlurred;
	private ArrayList<Circle> circles = new ArrayList<Circle>();

	public MatrixCirclePanel(String keyColor, String keyBlurred,
			MatrixContainer mCon)
	{
		super(keyColor, mCon);
		this.keyBlurred = keyBlurred;
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		Mat mat = this.mCon.getMatrix(keyBlurred);
		if (mat != null)
		{
			circles = Utils.getCirclesFromMat(Filter.getCircles(mat));
			removeCirclesOutsideImg();
			for (Circle circle : circles)
			{
				circle.draw(g2d, Color.BLUE);
			}
		}
	}
	
	private void removeCirclesOutsideImg()
	{
		BufferedImage img = Utils.matToBufferedImage(this.mCon.getMatrix("color"));
		ArrayList<Circle> toBeRemoved = new ArrayList<Circle>();
		for(int i = 0; i < circles.size(); i++)
		{
			Circle circle = circles.get(i);
			if(circle.getCenter().x + circle.getRadius() > img.getWidth() || 
			   circle.getCenter().x - circle.getRadius() < 0 ||
			   circle.getCenter().y + circle.getRadius() > img.getHeight() ||
			   circle.getCenter().y - circle.getRadius() < 0)
			{
				toBeRemoved.add(circles.get(i));
			}
				
		}
		for (Circle circle : toBeRemoved)
		{
			circles.remove(circle);
		}
	}
	
	public ArrayList<Circle> getCircles()
	{
		return circles;
	}

}
