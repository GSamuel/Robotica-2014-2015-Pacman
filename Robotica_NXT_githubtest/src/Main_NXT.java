import lejos.nxt.Button;

public class Main_NXT
{

	public static void main(String[] args)
	{
		System.out.println("test");
		System.out.println("druk op een knop");

		Button.waitForAnyPress();

		System.out.println("knop ingedrukt");
		System.out.println("druk op een knop");

		Button.waitForAnyPress();

	}

}
