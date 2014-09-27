package com.robotica.nxt.remotecontrol;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.microedition.io.Connection;

import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.USB;
import lejos.nxt.comm.USBConnection;

public class Connector
{
	private Connection connection;
	private DataOutputStream dataOut;
	private DataInputStream dataIn;

	public void connectWithUSB()
	{

		if (!isConnected())
		{
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
			System.out.println("Waiting for BT connection");

			BTConnection BTLink = Bluetooth.waitForConnection();

			dataOut = BTLink.openDataOutputStream();
			dataIn = BTLink.openDataInputStream();

			this.connection = BTLink;

			System.out.println("Connected");
		}
	}

	private boolean isConnected()
	{
		return (connection != null && dataOut != null && dataIn != null);
	}
	
	public DataOutputStream getDataOut()
	{
		return dataOut;
	}
	
	public DataInputStream getDataIn()
	{
		return dataIn;
	}

	public void closeConnection()
	{
		try
		{
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
