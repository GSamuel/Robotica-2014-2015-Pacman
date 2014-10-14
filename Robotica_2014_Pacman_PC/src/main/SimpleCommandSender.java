package main;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

import lejos.pc.comm.NXTConnector;

import com.robotica.nxt.remotecontrol.NXTCommand;

public class SimpleCommandSender
{

	public static void main(String[] args)
	{
		NXTConnector link = new NXTConnector();

		if (!link.connectTo("usb://"))
		{
			System.out.println("\nNo NXT find using USB");
			System.exit(0);
		}

		DataOutputStream outData = new DataOutputStream(link.getOutputStream());
		System.out.println("\nNXT is Connected");

		//
		//

		Scanner input = new Scanner(System.in);
		while (true)
		{
			for (NXTCommand command : NXTCommand.values())
				System.out.println(command.ordinal() + ": " + command.name());

			System.out.println("type 2 numbers: command_number value");
			
			int command = input.nextInt();
			
			int value = input.nextInt();
			
			try
			{
				outData.writeInt(command);
				outData.writeInt(value);
				outData.flush();
			} catch (IOException e)
			{
				System.out.println("something went wrong writing data");
				try
				{
					outData.close();
					link.close();
				} catch (IOException e1)
				{
					System.out.println("cant close outputstream");
				}
			}
			if(command == NXTCommand.EXIT.ordinal())
				break;

		}
		
		System.out.println("program finished");
	}
}
