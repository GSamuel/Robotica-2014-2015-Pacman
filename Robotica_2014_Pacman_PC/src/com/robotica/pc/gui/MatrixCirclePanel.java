package com.robotica.pc.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import org.opencv.core.Mat;

import com.robotica.pc.imageprocessing.Filter;
import com.robotica.pc.imageprocessing.Utils;
import com.robotica.pc.model.Circle;
import com.robotica.pc.model.World;

public class MatrixCirclePanel extends MatrixPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5553381751226293778L;
	private ArrayList<Circle> circles = new ArrayList<Circle>();
	private String keyBlur, keyColor;

	public MatrixCirclePanel(String keyColor, String keyBlur, World world)
	{
		super(keyColor, world);
		this.keyBlur = keyBlur;
		this.keyColor = keyColor;
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		Mat mat = this.mCon.getMatrix(keyBlur);
		if (mat != null)
		{
			circles = Utils.getCirclesFromMat(Filter.getCircles(mat), mCon.getMatrix(keyColor));
			for (Circle circle : circles)
			{
				circle.draw(g2d, Color.BLUE);
				g2d.setColor(circle.getColor());
				g2d.fillOval((int)circle.getX(), (int)circle.getY(), circle.getRadius()*2, circle.getRadius()*2);
			}
		}
	}
	
	public ArrayList<Circle> getCircles()
	{
		return circles;
	}

}
