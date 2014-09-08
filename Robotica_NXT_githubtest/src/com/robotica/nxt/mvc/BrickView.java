package com.robotica.nxt.mvc;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;

import com.robotica.nxt.button.ButtonController;
import com.robotica.nxt.button.NXTButtonController;
import com.robotica.nxt.button.NXTButtonListener;

public class BrickView
{

	private BrickModel model;
	private NXTButtonController controller;
	public BrickView(BrickModel model, NXTButtonController controller)
	{
		this.model = model;
		this.controller = controller;
		init();
	}
	private void init()
	{
		ButtonListener buttonListener = new NXTButtonListener(controller);
		
		Button.ENTER.addButtonListener(buttonListener);
		Button.ESCAPE.addButtonListener(buttonListener);
		Button.LEFT.addButtonListener(buttonListener);
		Button.RIGHT.addButtonListener(buttonListener);
	}

	public void update()
	{
		LCD.clear();
		LCD.drawString(model.getData(), 0, 0);
	}
}
