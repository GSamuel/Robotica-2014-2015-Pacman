package com.robotica.nxt.button;

import com.robotica.nxt.mvc.BrickModel;


public class ButtonController implements NXTButtonController
{

	private BrickModel model;
	
	public ButtonController(BrickModel brickModel)
	{
		this.model = brickModel;
	}

	@Override
	public void enterButtonPressed()
	{
		model.execute();
	}

	@Override
	public void enterButtonReleased()
	{
	}

	@Override
	public void leftButtonPressed()
	{
		model.moveSelectorLeft();
	}

	@Override
	public void leftButtonReleased()
	{
	}

	@Override
	public void rightButtonPressed()
	{
		model.moveSelectorRight();
	}

	@Override
	public void rightButtonReleased()
	{
	}

	@Override
	public void backButtonPressed()
	{
		model.setData("backP");
	}

	@Override
	public void backButtonReleased()
	{
		model.setData("backR");
	}

}
