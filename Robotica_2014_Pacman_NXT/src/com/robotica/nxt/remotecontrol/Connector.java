package com.robotica.nxt.remotecontrol;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public abstract class Connector
{
	protected DataOutputStream dataOut;
	protected DataInputStream dataIn;
	protected Type connectionType = Type.BT;

	public enum Type
	{
		USB, BT
	}

	public abstract void connectWithUSB();

	public abstract void connectWithBluetooth();

	public abstract boolean isConnected();

	public abstract void closeConnection();

	public void connect()
	{
		if (connectionType == Type.USB)
			connectWithUSB();
		else
			connectWithBluetooth();
	}

	public DataOutputStream getDataOut()
	{
		return dataOut;
	}

	public DataInputStream getDataIn()
	{
		return dataIn;
	}

	public void setType(Type type)
	{
		this.connectionType = type;
	}
}
