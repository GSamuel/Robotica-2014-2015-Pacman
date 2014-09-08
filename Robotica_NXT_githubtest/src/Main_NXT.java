import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

public class Main_NXT
{

	public static void main(String[] args)
	{
		TouchSensor touch = new TouchSensor(SensorPort.S1);
		while(!touch.isPressed());

		System.out.println("test");
		System.out.println("druk op een knop");

		Button.waitForAnyPress();

		System.out.println("knop ingedrukt");
		System.out.println("druk op een knop");

		Button.waitForAnyPress();

	}

}
