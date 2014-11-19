package com.robotica.pc.remotecontrol;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.robotica.nxt.remotecontrol.Connector;

import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTConnector;
import lejos.pc.comm.NXTInfo;

public class PCConnector extends Connector
{
	private NXTConnector connection;
	private String deviceName;
	
	public PCConnector(String deviceName)
	{
		this.deviceName = deviceName;
	}
	
	public void setDeviceName(String deviceName)
	{
		this.deviceName = deviceName;
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
			} else
				System.out.println("No NXT found using USB");

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
				NXTInfo[] nxtInfo = nxtComm.search(deviceName);

				NXTConnector BTLink = new NXTConnector();

				if (nxtInfo.length > 0)
					if (BTLink.connectTo(nxtInfo[0], 0))
					{
						System.out.println("connecting to: " + nxtInfo[0].name);
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

	public String getDeviceName()
	{
		return deviceName;		
	}
}
