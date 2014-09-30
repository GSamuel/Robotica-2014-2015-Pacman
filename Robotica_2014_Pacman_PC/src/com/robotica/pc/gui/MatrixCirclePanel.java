package com.robotica.pc.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

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
		g2d.setStroke(new BasicStroke(3));
		g2d.setColor(Color.RED);
		g2d.fillOval(0,0,100,100);
		g2d.setColor(Color.BLUE);
		g2d.drawOval(0,0,100,100);
	}

}
