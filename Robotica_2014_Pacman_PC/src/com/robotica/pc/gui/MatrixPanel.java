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
import com.robotica.pc.model.World;

public class MatrixPanel extends JPanel implements Observer
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2434663422894120325L;
	protected String key;
	protected World world;
	protected MatrixContainer mCon;
	private boolean drawBorder = true;

	public MatrixPanel(String key, World world)
	{
		super();
		this.key = key;
		this.world = world;
		this.mCon = world.getMatrixContainer();
		mCon.addObserver(this);
		updateSize();
	}

	public void paintComponent(Graphics g)
	{
		Mat mat = mCon.getMatrix(key);
		Graphics2D g2d = (Graphics2D) g;

		if (mat.width() >0 && mat.height() > 0)
		{
			BufferedImage currentImage = Utils.matToBufferedImage(mat); 
			g2d.drawImage(currentImage , 0, 0, currentImage.getWidth(),
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
		if (this.key.compareTo(key) == 0);
		{
			updateSize();
		}
	}
	
	private void updateSize()
	{
		Mat mat = mCon.getMatrix(key);
		if(mat.width() != getWidth() || mat.height() != getHeight())
		{
			this.setPreferredSize(new Dimension(mat.width(), mat.height()));
			this.revalidate();
		}
		repaint();
	}
}
