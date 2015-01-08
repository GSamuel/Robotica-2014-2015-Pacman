import com.robotica.nxt.brick.AdvancedBrick;
import com.robotica.nxt.brick.Brick;
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

			Brick brick = new AdvancedBrick();
			Command command;
			do
			{
				command = comCom.getNextCommand();
				System.out.println(command.getNXTCommand().name() + " "
						+ command.getValue());

				CommandExecuter.executeCommand(brick, command);

			} while (command.getNXTCommand() != NXTCommand.EXIT);
			connector.closeConnection();

			System.exit(0);
		
	}

}
