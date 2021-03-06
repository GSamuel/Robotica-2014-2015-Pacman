package com.robotica.pc.pathCalculator;

import com.robotica.nxt.remotecontrol.Command;
import com.robotica.nxt.remotecontrol.CommandCommunicator;
import com.robotica.nxt.remotecontrol.NXTCommand;
import com.robotica.pc.model.ConnectedEntity;
import com.robotica.pc.model.World;


public class PathExecutor
{

	public static void execute(double[] rotPos, World w, int entityID)
	{
		ConnectedEntity en= w.entityWithID(entityID);
		
		CommandCommunicator comm = new CommandCommunicator(en.getConnector());
		if(rotPos[0] > Math.PI/16)
		{
			comm.sendCommand(new Command(NXTCommand.SET_STATE, 1));//turn left
			//en.getEntity().rotate(-0.2);
		}
		else if(rotPos[0] < -Math.PI/16)
		{
			comm.sendCommand(new Command(NXTCommand.SET_STATE, 2));//turn right
			//en.getEntity().rotate(0.2);
		}
		else if(rotPos[1] > 0.1)
		{
			comm.sendCommand(new Command(NXTCommand.SET_STATE,0)); // move forwards
			//Location locdiff = Rotation.rotationToLocationVector(en.getEntity().getRotation());
			//en.getEntity().translateLocation(locdiff.x*0.1, locdiff.y*0.1);
		}
		else
		{
			comm.sendCommand(new Command(NXTCommand.SET_STATE,4)); // stand still
		}
		
	}

	
}
