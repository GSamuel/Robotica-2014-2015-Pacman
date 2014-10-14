package com.robotica.pc.remotecontrol;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTConnector;
import lejos.pc.comm.NXTInfo;

public class Connector
{
	private NXTConnector connection;

	private DataOutputStream dataOut;
	private DataInputStream dataIn;
	private Type connectionType = Type.BT;

	public enum Type
	{
		USB, BT
	}

	public void connectWithUSB()
	{
		if (!isConnected())
		{
			connectionType = Type.USB;
			System.out.println("Waiting for USB connection");

			NXTConnector USBLink = new NXTConnector();

			if (USBLink.connectTo("usb://"))
			{
				dataOut = new DataOutputStream(USBLink.getOutputStream());
				dataIn = new DataInputStream(USBLink.getInputStream());
				this.connection = USBLink;
				System.out.println("Connected");
			}

		}
	}

	public void connectWithBluetooth()
	{
		if (!isConnected())
		{
			connectionType = Type.BT;
			System.out.println("Waiting for BT connection");

			NXTComm nxtComm;
			try
			{
				nxtComm = NXTCommFactory
						.createNXTComm(NXTCommFactory.BLUETOOTH);
				NXTInfo[] nxtInfo = nxtComm.search(null);

				NXTConnector BTLink = new NXTConnector();

				if (nxtInfo.length > 0)
					if (BTLink.connectTo(nxtInfo[0], 0))
					{
						System.out.println("connecting to: "+nxtInfo[0].name);
						dataOut = new DataOutputStream(BTLink.getOutputStream());
						dataIn = new DataInputStream(BTLink.getInputStream());
						this.connection = BTLink;
						System.out.println("Connected");
					}

			} catch (NXTCommException e)
			{
			}

		}
	}

	public void connect()
	{
		if (connectionType == Type.USB)
			connectWithUSB();
		else
			connectWithBluetooth();
	}

	public boolean isConnected()
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

	public void setType(Type type)
	{
		this.connectionType = type;
	}
}
