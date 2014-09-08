package com.robotica.nxt.action;

import lejos.nxt.comm.USB;


public class MakeUSBConnection extends MakeConnection
{
	public MakeUSBConnection()
	{
		super("USB");
	}

	@Override
	public void execute()
	{
		connection = USB.waitForConnection();
	}
}
