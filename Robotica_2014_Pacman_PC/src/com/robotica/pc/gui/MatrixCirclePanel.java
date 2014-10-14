package com.robotica.pc.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.opencv.core.Mat;

import com.robotica.pc.imageprocessing.Filter;
import com.robotica.pc.imageprocessing.Utils;
import com.robotica.pc.model.Circle;
import com.robotica.pc.model.MatrixContainer;

public class MatrixCirclePanel extends MatrixPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5553381751226293778L;
	private ArrayList<Circle> circles = new ArrayList<Circle>();
	private String keyBlur;

	public MatrixCirclePanel(String keyColor, String keyBlur, MatrixContainer mCon)
	{
		super(keyColor, mCon);
		this.keyBlur = keyBlur;
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		Mat mat = this.mCon.getMatrix(keyBlur);
		if (mat != null)
		{
			circles = Utils.getCirclesFromMat(Filter.getCircles(mat));
			removeCirclesOutsideImg();
			for (Circle circle : circles)
			{
				circle.draw(g2d, Color.BLUE);
				g2d.setColor(circle.getColor(Utils.matToBufferedImage(mCon.getMatrix("color"))));
				g2d.fillOval((int)circle.getX(), (int)circle.getY(), circle.getRadius()*2, circle.getRadius()*2);
			}
		}
	}
	
	private void removeCirclesOutsideImg()
	{
		BufferedImage img = Utils.matToBufferedImage(this.mCon.getMatrix(this.key));
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
