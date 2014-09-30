package com.robotica.pc.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import org.opencv.core.Mat;

import com.robotica.pc.imageprocessing.Utils;
import com.robotica.pc.model.MatrixContainer;

public class MatrixPanel extends JPanel implements Observer
{
	private String key;
	private MatrixContainer mCon;
	private boolean drawBorder = true;

	public MatrixPanel(String key, MatrixContainer mCon)
	{
		super();
		this.key = key;
		this.mCon = mCon;
		mCon.addObserver(this);
		updateSize();
	}

	public void paintComponent(Graphics g)
	{
		Mat mat = mCon.getMatrix(key);
		Graphics2D g2d = (Graphics2D) g;

		if (mat != null)
		{
			BufferedImage currentImage = Utils.matToBufferedImage(mat);
			g2d.drawImage(currentImage, 0, 0, currentImage.getWidth(),
					currentImage.getHeight(), this);
		}

		if (drawBorder)
			drawBox(g2d);
	}

	private void drawBox(Graphics2D g2d)
	{
		g2d.setStroke(new BasicStroke(2));
		g2d.setColor(Color.BLACK);
		g2d.drawRect(0, 0, getWidth(), getHeight());
	}

	public void setDrawBorder(boolean bool)
	{
		this.drawBorder = bool;
	}

	@Override
	public void update(Observable o, Object arg)
	{
		String key = arg.toString();
		System.out.println("key");
		if (this.key.compareTo(key) == 0);
		{
			updateSize();
		}
	}
	
	private void updateSize()
	{
		Mat mat = mCon.getMatrix(key);
		if(mat != null)
		if(mat.width() != getWidth() || mat.height() != getHeight())
		{
			this.setPreferredSize(new Dimension(mat.width(), mat.height()));
			this.revalidate();
		}
		repaint();
	}

}
