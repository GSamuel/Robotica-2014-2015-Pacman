package com.robotica.pc.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import org.opencv.core.Mat;

import com.robotica.pc.imageprocessing.Filter;
import com.robotica.pc.imageprocessing.Utils;
import com.robotica.pc.model.MatrixContainer;

public class MatrixCirclePanel extends MatrixPanel
{
	private String keyBlurred;

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
			ArrayList<Circle> circles = Utils.getCirclesFromMat(Filter
					.getCircles(mat));
			for (Circle circle : circles)
			{
				circle.draw(g2d, Color.BLUE);
			}
		}
	}

}
