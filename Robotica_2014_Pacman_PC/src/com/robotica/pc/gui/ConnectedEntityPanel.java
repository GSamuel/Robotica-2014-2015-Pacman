package com.robotica.pc.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.robotica.pc.model.ConnectedEntity;

public class ConnectedEntityPanel extends JPanel implements Observer, ActionListener
{
	private static final long serialVersionUID = -1358499148193705488L;
	private ConnectedEntity entity;
	
	private static final int FONT_SIZE = 20;
	private int count = 0;
	
	private JButton connectButton;

	public ConnectedEntityPanel(ConnectedEntity entity)
	{
		this.entity = entity;
		this.entity.addObserver(this);
		
		connectButton = new JButton("connect");
		connectButton.setLocation(50, 100);
		connectButton.addActionListener(this);
		this.add(connectButton);
		this.revalidate();
		
		setPreferredSize(new Dimension(300,200));
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, FONT_SIZE));
		
		count = 2;

		drawString(g2d, "devName:"+entity.getDeviceName());
		drawString(g2d, "connected:"+Boolean.toString(entity.isConnected()));
		drawString(g2d, "id:"+entity.getEntity().getID()+" "+entity.getEntity().getType().toString());
		drawString(g2d, "r:"+entity.getEntity().getColor().getRed()+" g:"+entity.getEntity().getColor().getGreen()+" b:"+entity.getEntity().getColor().getBlue());
		drawString(g2d, "x:"+entity.getEntity().getX()+" y:"+entity.getEntity().getY());
		drawString(g2d, ""+entity.getEntity().getDirection().toString()+" rot:"+entity.getEntity().getRotation());
	}
	
	private void drawString(Graphics2D g2d, String s)
	{
		g2d.drawString(s,10,FONT_SIZE*++count);
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		entity.connect();
	}

}
