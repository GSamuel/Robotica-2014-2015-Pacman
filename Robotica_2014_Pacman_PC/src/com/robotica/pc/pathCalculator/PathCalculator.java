package com.robotica.pc.pathCalculator;

import com.robotica.pc.model.AINode;
import com.robotica.pc.model.ConnectedEntity;
import com.robotica.pc.model.Entity;
import com.robotica.pc.model.Location;
import com.robotica.pc.model.Rotation;
import com.robotica.pc.model.World;

public class PathCalculator
{
	public static double[] calculate(AINode path, World w, int id)
	{

		AINode[] nodes = new AINode[path.getCost() + 1];
		Entity e = null;
		for (ConnectedEntity en : w.getConnectedEntities())
			if (en.getEntity().getID() == id)
				e = en.getEntity();

		boolean done = false;
		do
		{
			if (!path.hasParent())
				done = true;
			nodes[path.getCost()] = path;
			path = path.getParent();

		} while (!done);

		e.getRotation();

		int i = 0;
		AINode begin = nodes[i++];
		AINode next;
		while (i<nodes.length)
		{
			next = nodes[i++];

			if (begin.getLocation().distance(next.getLocation()) > 0)
			{
				double[] tuple = new double[2];
				Location goal = next.getLocation();
				goal.setLocation(goal.getGridX()+0.5, goal.getGridY()+0.5);
				Location diff = goal.difference(e.getLocation());
				Rotation reqRot = Rotation.locationToRotation(diff);
				tuple[0] =reqRot.difference(e.getRotation()).getRotation();
				tuple[1] = diff.length();
				return tuple;
			}
		}
		return new double[2];
	}
}
