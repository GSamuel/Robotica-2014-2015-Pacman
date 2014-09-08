package com.robotica.nxt.button;
import lejos.nxt.Button;
import lejos.nxt.ButtonListener;


public class NXTButtonListener implements ButtonListener
{
	private NXTButtonController controller;
	public NXTButtonListener(NXTButtonController controller)
	{
		this.controller = controller;
	}

	@Override
	public void buttonPressed(Button b)
	{
		switch(b.getId())
		{
		case 1:
			controller.enterButtonPressed();
			break;
		case 2:
			controller.leftButtonPressed();
			break;
		case 4:
			controller.rightButtonPressed();
			break;
		case 8: 
			controller.backButtonPressed();
			break;
		}
	}

	@Override
	public void buttonReleased(Button b)
	{
		switch(b.getId())
		{
		case 1:
			controller.enterButtonReleased();
			break;
		case 2:
			controller.leftButtonReleased();
			break;
		case 4:
			controller.rightButtonReleased();
			break;
		case 8: 
			controller.backButtonReleased();
			break;
		}
		
	}

}
