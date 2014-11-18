package com.robotica.pc.keyboardInput;

import java.util.Scanner;

import com.robotica.nxt.remotecontrol.Command;
import com.robotica.nxt.remotecontrol.NXTCommand;

public class KeyboardInput
{
	public static Command getCommand()
	{
		Scanner input = new Scanner(System.in);
		for (NXTCommand command : NXTCommand.values())
			System.out.println(command.ordinal() + ": " + command.name());

		System.out.println("type 2 numbers: command_number value");
		int[] data = new int[2];
		data[0] = input.nextInt();
		data[1] = input.nextInt();

		Command command = new Command();
		command.constructFromInteger(data);
		
		return command;
	}
}
