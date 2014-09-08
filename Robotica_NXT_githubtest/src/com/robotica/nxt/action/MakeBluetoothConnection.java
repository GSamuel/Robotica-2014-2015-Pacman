package com.robotica.nxt.action;

import lejos.nxt.comm.Bluetooth;


public class MakeBluetoothConnection extends MakeConnection
{
	public MakeBluetoothConnection()
	{
		super("Bluetooth");
	}

	@Override
	public void execute()
	{
		connection = Bluetooth.waitForConnection();
	}
}
