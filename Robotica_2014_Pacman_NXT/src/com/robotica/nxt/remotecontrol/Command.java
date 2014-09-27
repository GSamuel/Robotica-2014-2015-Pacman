package com.robotica.nxt.remotecontrol;

public class Command
{
	private NXTCommand command;
	private int value;

	public Command()
	{
		command = NXTCommand.getDefault();
		value = 0;
	}

	public Command(NXTCommand command, int value)
	{
		this.command = command;
		this.value = value;
	}
	
	public NXTCommand getNXTCommand()
	{
		return command;
	}
	
	public int getValue()
	{
		return value;
	}

	public int[] getDataAsInteger()
	{
		int[] data =
		{ command.ordinal(), value };
		return data;
	}

	public void constructFromInteger(int[] data)
	{
		boolean found = false;
		for(NXTCommand command: NXTCommand.values())
			if(command.ordinal() == data[0])
			{
				this.command = command;
				value = data[1];
				found = true;
				break;
			}
		if(!found)
			this.command = NXTCommand.getDefault();
	}

}
