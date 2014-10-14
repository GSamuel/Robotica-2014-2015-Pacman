import lejos.nxt.Button;

import com.robotica.nxt.brick.Brick;
import com.robotica.nxt.brick.MotorTestBrick;
import com.robotica.nxt.brick.PrintBrick;
import com.robotica.nxt.remotecontrol.Command;
import com.robotica.nxt.remotecontrol.CommandCommunicator;
import com.robotica.nxt.remotecontrol.NXTConnector;
import com.robotica.nxt.remotecontrol.NXTCommand;
import com.robotica.nxt.settings.NXTAction;

public class Ghost
{

	public static void main(String[] args)
	{
		NXTConnector connector = new NXTConnector();
		connector.setType(NXTConnector.Type.USB);
		CommandCommunicator comCom = new CommandCommunicator(connector);

		Brick brick = new MotorTestBrick();
		Command com;
		do
		{
			com = comCom.getNextCommand();
			System.out.println(com.getNXTCommand().name() + " "
					+ com.getValue());

			if (com.getNXTCommand() == NXTCommand.SET_STATE)
			{
				for(NXTAction action: NXTAction.values())
					if(action.ordinal() == com.getValue())
					{
						System.out.println(action.name());
						brick.setState(action);
						brick.update();
					}
			}

		} while (com.getNXTCommand() != NXTCommand.EXIT);

		/*
		 * Brick brick = new PrintBrick(); brick.update();
		 */

		System.out.println("Press to close connection");
		Button.waitForAnyPress();
		connector.closeConnection();
		System.out.println("Press the 'any' key to exit");
		Button.waitForAnyPress();
		System.exit(0);
	}

}
