package com.robotica.pc.gui;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PacmanWindow extends JFrame
{
	public PacmanWindow()
	{
		init();
	}

	private void init()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1280, 720);
		this.getContentPane().setBackground(Color.WHITE);
		
		this.setLayout(new FlowLayout());
		
		this.setVisible(true);
		this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	}
	
	private void sysoPanel(JPanel panel)
	{	
		System.out.println(panel.getX()+" "+panel.getY()+" "+panel.getWidth()+" "+panel.getHeight());
	}
}
