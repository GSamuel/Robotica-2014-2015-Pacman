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

		int num = 0;
		Scanner input = new Scanner(System.in);
		while (num != -1)
		{
			System.out.println("-1 to disconnect from NXT");
			for (NXTCommand command : NXTCommand.values())
				System.out.println(command.ordinal() + ": " + command.name());

			System.out.println("type 2 numbers: command_number value");
			
			int command = input.nextInt();
			num = command;
			if(num == -1)
				break;
			
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
				} catch (IOException e1)
				{
					System.out.println("cant close outputstream");
				}
				num = -1;
			}

		}
		
		System.out.println("program finished");
	}
}
