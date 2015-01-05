import lejos.nxt.Button;
import utils.Exit;

import com.robotica.nxt.brick.Brick;
import com.robotica.nxt.brick.MotorTestBrick;
import com.robotica.nxt.remotecontrol.Command;
import com.robotica.nxt.remotecontrol.CommandCommunicator;
import com.robotica.nxt.remotecontrol.CommandExecuter;
import com.robotica.nxt.remotecontrol.NXTCommand;
import com.robotica.nxt.remotecontrol.NXTConnector;

public class Ghost
{

	public static void main(String[] args)
	{
		NXTConnector connector = new NXTConnector();
		connector.setType(NXTConnector.Type.BT);
		CommandCommunicator comCom = new CommandCommunicator(connector);
		comCom.connect();

		Brick brick = new MotorTestBrick();
		Command command;
		do
		{
			command = comCom.getNextCommand();
			System.out.println(command.getNXTCommand().name() + " "
					+ command.getValue());

			CommandExecuter.executeCommand(brick, command);

		} while (command.getNXTCommand() != NXTCommand.EXIT);

		System.out.println("Press to close connection");
		Button.waitForAnyPress();
		connector.closeConnection();

		Exit.exit();
	}

}
