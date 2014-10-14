package com.robotica.nxt.remotecontrol;

import java.io.IOException;

import javax.microedition.io.Connection;

import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.USB;
import lejos.nxt.comm.USBConnection;

public class NXTConnector extends Connector
{
	private Connection connection;
	
	public void connectWithUSB()
	{
		if (!isConnected())
		{
			connectionType = Type.USB;
			System.out.println("Waiting for USB connection");

			USBConnection USBLink = USB.waitForConnection();

			dataOut = USBLink.openDataOutputStream();
			dataIn = USBLink.openDataInputStream();

			this.connection = USBLink;

			System.out.println("Connected");
		}
	}

	public void connectWithBluetooth()
	{
		if (!isConnected())
		{
			connectionType = Type.BT;
			System.out.println("Waiting for BT connection");

			BTConnection BTLink = Bluetooth.waitForConnection();

			dataOut = BTLink.openDataOutputStream();
			dataIn = BTLink.openDataInputStream();

			this.connection = BTLink;

			System.out.println("Connected");
		}
	}

	public boolean isConnected()
	{
		return (connection != null && dataOut != null && dataIn != null);
	}

	public void closeConnection()
	{
		try
		{
			if (dataOut != null)
				dataOut.close();
			if (dataIn != null)
				dataIn.close();
			if (connection != null)
				connection.close();
			System.out.println("disconnected");
		} catch (IOException e)
		{
			System.out.println("Unable to close the connection");
		} finally
		{
			connection = null;
			dataOut = null;
			dataIn = null;
		}
	}
}
