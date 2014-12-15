package com.robotica.pc.pathCalculator;

import com.robotica.pc.model.AINode;
import com.robotica.pc.model.World;

public class PathCalculator
{
	public static void calculate(AINode path, World w, int id)
	{

		AINode[] nodes = new AINode[path.getCost() + 1];

		boolean done = false;
		do
		{
			if (!path.hasParent())
				done = true;
			nodes[path.getCost()] = path;
			path = path.getParent();

		} while (!done);

		if (nodes.length > 2)
		{
			AINode begin = nodes[0];
			AINode next = nodes[1];
			
			
			if (begin.getLocation().distance(next.getLocation())> 0)
			{
				System.out.println("yay we hebben gelopen");
			}
			else
			{
				
			}
		}

		// nieuwe locatie berekenen.
		// nieuwe rotatie bepalen.
		// verschil locaties = afstand;
		// verschil rotaties berekenen;

		// resultaat return
	}
}
