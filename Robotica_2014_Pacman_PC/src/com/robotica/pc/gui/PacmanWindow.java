package com.robotica.pc.gui;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;

import javax.swing.JFrame;

public class PacmanWindow extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9145403355212469159L;

	public PacmanWindow()
	{
		init();
	}

	private void init()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1720,900);
		this.getContentPane().setBackground(Color.WHITE);
		
		this.setLayout(new FlowLayout());
		
		this.setVisible(true);
		this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	}
}
