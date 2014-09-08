package com.robotica.nxt.mvc;

import com.robotica.nxt.action.Action;
import com.robotica.nxt.action.MakeBluetoothConnection;
import com.robotica.nxt.action.MakeUSBConnection;

public class BrickModel implements Action
{
	private String data;
	private Action[] action = new Action[2];
	private int selected = 0;
	
	public BrickModel()
	{
		this.data = "test";
		action[0] = new MakeUSBConnection();
		action[1] = new MakeBluetoothConnection();
	}
	
	public void moveSelectorRight()
	{
		selected++;
		if(selected >= action.length)
			selected = 0;
	}
	
	public void moveSelectorLeft()
	{
		selected--;
		if(selected <0)
			selected = action.length-1;
	}
	
	
	
	public void setData(String data)
	{
		this.data = data;
	}
	
	public String getData()
	{
		return action[selected].toString();
	}

	@Override
	public void execute()
	{
		action[selected].execute();
	}
}
