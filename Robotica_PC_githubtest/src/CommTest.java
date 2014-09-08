import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTInfo;

public class CommTest
{

	public static void main(String[] args)
	{
		NXTComm nxtComm = null;
		try
		{
			nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.USB);
			NXTInfo[] info = nxtComm.search("NXT_9_1");
			nxtComm.open(info[0]);
			
			

			DataInputStream istream = new DataInputStream(nxtComm.getInputStream());
			Thread.sleep(2200);
			System.out.println(istream.readInt());
			System.out.println(istream.readInt());
			System.out.println(istream.readInt());
			System.out.println(istream.readInt());
			System.out.println(istream.readInt());
			//nxtComm.getOutputStream();
			nxtComm.close();
			
		} catch (NXTCommException e)
		{

			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
