import lejos.nxt.Button;

import com.robotica.nxt.brick.Brick;
import com.robotica.nxt.brick.PrintBrick;
import com.robotica.nxt.remotecontrol.Command;
import com.robotica.nxt.remotecontrol.CommandCommunicator;
import com.robotica.nxt.remotecontrol.Connector;
import com.robotica.nxt.remotecontrol.NXTCommand;

public class Ghost
{

	public static void main(String[] args)
	{
		Connector connector = new Connector();
		connector.setType(Connector.Type.USB);
		CommandCommunicator comCom = new CommandCommunicator(connector);

		Command com;
		do
		{
			com = comCom.getNextCommand();
			System.out
					.println(com.getNXTCommand().name() + " " + com.getValue());
		} while (com.getNXTCommand() != NXTCommand.EXIT);
		

		Brick brick = new PrintBrick();
		brick.update();

		System.out.println("Press to close connection");
		Button.waitForAnyPress();
		connector.closeConnection();
		System.out.println("Press the 'any' key to exit");
		Button.waitForAnyPress();
		System.exit(0);
	}

}
