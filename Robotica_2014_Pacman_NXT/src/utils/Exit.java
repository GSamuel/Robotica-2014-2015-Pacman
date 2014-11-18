package utils;

import lejos.nxt.Button;

public class Exit
{
	public static void exit()
	{
		System.out.println("Press the 'any' key to exit");
		Button.waitForAnyPress();
		System.exit(0);
	}
}
