package com.robotica.nxt.remotecontrol;

import com.robotica.nxt.brick.Brick;
import com.robotica.nxt.settings.NXTAction;

public class CommandExecuter
{
	public static void executeCommand(Brick brick, Command command)
	{
		switch (command.getNXTCommand())
		{
		case SET_STATE:
			for(NXTAction action: NXTAction.values())
				if(action.ordinal() == command.getValue())
				{
					System.out.println(action.name());
					brick.setState(action);
					brick.update();
				}
			break;
		case SET_MOTORSPEED:
			brick.getSettings().setMotorSpeed(command.getValue());
			break;
		case SET_ACCELERATION:
			brick.getSettings().setAcceleration(command.getValue());
			break;

		default:
			break;
		}
	}
}
