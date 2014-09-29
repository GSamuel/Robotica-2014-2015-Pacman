package com.robotica.pc.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class CirclePanel extends JPanel
{
	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.MAGENTA);
		g2d.setStroke(new BasicStroke(3));
		//g2d.drawOval(getX(), getY(), getWidth(), getHeight());
		g2d.drawOval(0,0,100,100);
	}
}
