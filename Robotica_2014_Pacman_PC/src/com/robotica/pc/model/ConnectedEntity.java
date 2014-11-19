package com.robotica.pc.model;

import java.util.Observable;
import java.util.Observer;

import com.robotica.pc.remotecontrol.PCConnector;

public class ConnectedEntity extends Observable implements Observer
{
	private Entity entity;
	private PCConnector connector;

	public ConnectedEntity(Entity entity,PCConnector connector)
	{
		this.entity = entity;
		this.connector = connector;
		entity.addObserver(this);
	}
	
	public boolean isConnected()
	{
		return connector.isConnected();
	}
	
	public void setDeviceName(String deviceName)
	{
		connector.setDeviceName(deviceName);
		this.setChanged();
		this.notifyObservers();
	}
	
	public void connect()
	{
		connector.connect();
	}
	
	public void connectWithBluetooth()
	{
		connector.connectWithBluetooth();
		this.setChanged();
		this.notifyObservers();
	}
	
	public void connectWithUSB()
	{
		connector.connectWithUSB();
		this.setChanged();
		this.notifyObservers();
	}
	
	public void closeConnection()
	{
		connector.closeConnection();
		this.setChanged();
		this.notifyObservers();
	}
	
	public String getDeviceName()
	{
		return connector.getDeviceName();
	}

	public PCConnector getConnector()
	{
		return connector;
	}

	public Entity getEntity()
	{
		return entity;
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
		this.setChanged();
		this.notifyObservers();
	}
}
