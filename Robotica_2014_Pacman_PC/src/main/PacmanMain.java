package main;

import org.opencv.core.Core;
import org.opencv.highgui.VideoCapture;

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
		gebruik pad om NXT aan te sturen. ->
		
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
		
		PCConnector connector = new PCConnector("NXT_9_1");//connect with specific 
		connector.connectWithBluetooth();//NXT over Bluetooth
		
		
	}
}
