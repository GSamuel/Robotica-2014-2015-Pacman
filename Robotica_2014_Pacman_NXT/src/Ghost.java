import lejos.nxt.Button;

import com.robotica.nxt.brick.Brick;
import com.robotica.nxt.brick.PrintBrick;
import com.robotica.nxt.remotecontrol.Connector;


public class Ghost
{

	public static void main(String[] args)
	{
		Connector connector = new Connector();
		connector.connectWithUSB();
		Brick brick = new PrintBrick();
		
		connector.closeConnection();
		System.out.println("Press the 'any' key to exit");
		Button.waitForAnyPress();
	}

}
