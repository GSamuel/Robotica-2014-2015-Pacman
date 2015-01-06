package com.robotica.pc.ai;

import java.util.ArrayList;

import com.robotica.pc.model.AINode;
import com.robotica.pc.model.ConnectedEntity;
import com.robotica.pc.model.Entity;
import com.robotica.pc.model.Location;
import com.robotica.pc.model.Maze;
import com.robotica.pc.model.Tile;
import com.robotica.pc.model.World;

public class PacmanAI extends AI
{
	private int entity;

	public PacmanAI(World w, int entity)
	{
		super(w);
		this.entity = entity;
	}

	@Override
	public AINode createPath()
	{
		Maze maze = w.getMaze();
		ArrayList<ConnectedEntity> entities = w.getConnectedEntities();
		AINode start = null, goal = null;
		Entity e = null;
		for (ConnectedEntity ce : entities)
		{
			e = ce.getEntity();
			if (e.getID() == entity)
				start = new AINode(null, e.getDirection(), e.getLastCheckpoint());
		}
		Location destination = w.getPacmanGoal();
		goal = new AINode(null, e.getDirection(), destination);

		ArrayList<AINode> explored = new ArrayList<AINode>();
		ArrayList<AINode> frontier = new ArrayList<AINode>();
		if (start != null)
			frontier.add(start);

		while (frontier.size() > 0)
		{
			AINode node = frontier.remove(0);
			explored.add(node);

			for (AINode n : node.getSuccesors())
			{
				if (n.getLocation().isTheSameGrid(goal.getLocation()))
					return n;

				boolean addToFrontier = true;

				if (!(maze.inMaze(n.getLocation())))
					addToFrontier = false;
				else if (maze.getTile(n.getLocation()) != Tile.EMPTY)
					addToFrontier = false;

				if (addToFrontier)
					for (AINode exN : explored)
					{
						if (n.getLocation().isTheSameGrid(exN.getLocation())
								&& n.getDirection() == exN.getDirection())
							addToFrontier = false;
					}

				if (addToFrontier)
					frontier.add(n);

			}
		}
		return null;

	}
}
