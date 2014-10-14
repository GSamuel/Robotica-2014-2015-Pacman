package com.robotica.nxt.remotecontrol;

import java.io.IOException;

public class CommandCommunicator
{
	private Connector connector;
	
	public CommandCommunicator(Connector connector)
	{
		this.connector = connector;
		if(!connector.isConnected())
			connector.connect();
	}
	
	public Command getNextCommand()
	{	
		if(connector.isConnected())
		{

			try
			{
				int[] data = {connector.getDataIn().readInt(), connector.getDataIn().readInt()};
				Command command = new Command();
				command.constructFromInteger(data);
				return command;
			} catch (IOException e)
			{
				System.out.println("cant read from stream");
				connector.closeConnection();
			}
		}
		
		return null;
	}
	
	public void sendCommand(Command command)
	{
		if(connector.isConnected())
		{
			int[] data = command.getDataAsInteger();
			try
			{
				connector.getDataOut().writeInt(data[0]);
				connector.getDataOut().writeInt(data[1]);
				connector.getDataOut().flush();
			} catch (IOException e)
			{
				System.out.println("cant send data");
				connector.closeConnection();
			}
		}
		else
		{
			System.out.println("cant send data, because not connected");
		}
	}
}
