package com.robotica.nxt.action;

import lejos.nxt.comm.NXTConnection;

public abstract class MakeConnection implements Action
{
	private String name;
	protected NXTConnection connection;
	
	public MakeConnection(String name)
	{
		this.name = name;
	}
	
	public String toString()
	{
		return name;
	}
}
