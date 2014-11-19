package main;

import java.util.Scanner;

import com.robotica.nxt.remotecontrol.Command;
import com.robotica.nxt.remotecontrol.CommandCommunicator;
import com.robotica.nxt.remotecontrol.Connector;
import com.robotica.nxt.remotecontrol.NXTCommand;
import com.robotica.pc.remotecontrol.PCConnector;

public class SimpleCommandSender
{

	public static void main(String[] args)
	{
		PCConnector connector = new PCConnector("NXT_9_1");
		connector.setType(Connector.Type.BT);
		CommandCommunicator comm = new CommandCommunicator(connector);

		Scanner input = new Scanner(System.in);
		while (connector.isConnected())
		{
			for (NXTCommand command : NXTCommand.values())
				System.out.println(command.ordinal() + ": " + command.name());

			System.out.println("type 2 numbers: command_number value");
			int[] data = new int[2];
			data[0] = input.nextInt();
			data[1] = input.nextInt();

			Command command = new Command();
			command.constructFromInteger(data);

			comm.sendCommand(command);
			if (command.getNXTCommand() == NXTCommand.EXIT)
				break;

		}

		System.out.println("program finished");
	}
}
