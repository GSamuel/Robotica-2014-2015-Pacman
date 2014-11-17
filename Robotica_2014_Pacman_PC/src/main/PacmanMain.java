package main;

import java.util.Scanner;

import org.opencv.core.Core;
import org.opencv.highgui.VideoCapture;

import com.robotica.nxt.remotecontrol.Command;
import com.robotica.nxt.remotecontrol.CommandCommunicator;
import com.robotica.nxt.remotecontrol.NXTCommand;
import com.robotica.pc.gui.PacmanWindow;
import com.robotica.pc.model.World;
import com.robotica.pc.remotecontrol.PCConnector;

public class PacmanMain
{
	public static void main(String[] args)
	{
		/*
		in herhaling:
		uitlezen camera ->
		bouw model met behulp van camera data ->
		gebruik model in de AI om een pad te generen ->
		vertaal pad naar NXT Commands ->
		stuur commands naar de juiste brick ->
		pas settings van robots aan afhankelijk van feedback
		
		weergeven:
		doolhof met up to date posities van entities.
		webcam beelden met evt. filters.
		entities met de bijbehorende bewegings vectoren/correcties.
		see which entities are connected.
		*/

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);//Library die we nodig hebben voor opencv etc.
		
		World world = new World(); // dataModel, all needed data should be in here.
		world.setCamera(new VideoCapture(3));
		PacmanWindow pacmanWindow = new PacmanWindow();// all gui elements should be in here
		
		//connect with devices:	
		PCConnector connector1 = new PCConnector("NXT_9_1");
		PCConnector connector2 = new PCConnector("Parasect"); 
		connector1.connectWithBluetooth();
		connector2.connectWithBluetooth();

		CommandCommunicator comm1 = new CommandCommunicator(connector1);
		CommandCommunicator comm2 = new CommandCommunicator(connector2);
		

		
		
		//read commands from user input and send commands to the bricks
		Scanner input = new Scanner(System.in);
		while (connector1.isConnected() && connector2.isConnected())
		{
			for (NXTCommand command : NXTCommand.values())
				System.out.println(command.ordinal() + ": " + command.name());

			System.out.println("type 2 numbers: command_number value");
			int[] data = new int[2];
			data[0] = input.nextInt();
			data[1] = input.nextInt();

			Command command = new Command();
			command.constructFromInteger(data);

			comm1.sendCommand(command);
			comm2.sendCommand(command);
			if (command.getNXTCommand() == NXTCommand.EXIT)
				break;

		}

		System.out.println("program finished");
		
		
	}
}
