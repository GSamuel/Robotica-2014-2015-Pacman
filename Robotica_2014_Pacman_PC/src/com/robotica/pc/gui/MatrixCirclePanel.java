package com.robotica.pc.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.opencv.core.Mat;

import com.robotica.pc.imageprocessing.Filter;
import com.robotica.pc.imageprocessing.Utils;
import com.robotica.pc.model.MatrixContainer;

public class MatrixCirclePanel extends MatrixPanel
{
	public MatrixCirclePanel(String key, MatrixContainer mCon)
	{
		super(key, mCon);
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		Mat mat = this.mCon.getMatrix(key);
		Filter.getCircles(mat);
	}

}
