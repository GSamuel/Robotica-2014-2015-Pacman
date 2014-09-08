import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.comm.NXTConnection;
import lejos.nxt.comm.USB;

public class CommTest
{
	public static void main(String[] args)
	{
		TouchSensor touch1 = new TouchSensor(SensorPort.S1);
		TouchSensor touch2 = new TouchSensor(SensorPort.S2);

		NXTConnection connection = null;

		do
		{
			System.out.println("waiting for USB");
			connection = USB.waitForConnection();

			DataOutputStream dataOut = connection.openDataOutputStream();
			DataInputStream dataIn = connection.openDataInputStream();

			try
			{

				while (!touch1.isPressed())
				{
					dataOut.writeInt(1234);
					dataOut.flush();
					Thread.sleep(100);

				}
				connection.close();
				System.out.println("close connection");
			} catch (IOException e)
			{
				System.out.println(" write error " + e);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("touch2:"+touch2.isPressed());
		} while (touch2.isPressed());

		Button.waitForAnyPress();

	}

}
