package com.robotica.pc.model;

import java.awt.Point;

public class AINode implements Cloneable
{
	private Direction direction;
	private Location location;
	private AINode parent;

	public AINode(AINode parent, Direction direction, Location location)
	{
		this.parent = parent;
		this.direction = direction;
		this.location = location;
	}

	public AINode(AINode aiNode)
	{
		this.parent = aiNode.parent;
		this.direction = aiNode.direction;
		this.location = aiNode.location.clone();
	}

	public void setDirection(Direction newDirection)
	{
		this.direction = newDirection;
	}

	public Direction getDirection()
	{
		return direction;
	}

	public Location getLocation()
	{
		return location;
	}
	
	public void setParent(AINode parent)
	{
		this.parent = parent;
	}

	public boolean hasParent()
	{
		return parent != null;
	}

	public int getCost()
	{
		if (!hasParent())
			return 0;
		else
			return parent.getCost() + 1;
	}

	public AINode[] getSuccesors()
	{
		AINode[] succ = new AINode[Action.values().length];
		for (Action a : Action.values())
		{
			AINode newNode = this.clone();
			newNode.setParent(this);
			switch (a)
			{
			case FORWARD:
				Point p = direction.getDirectionVector();
				newNode.getLocation().translate(p.x, p.y);
				succ[a.ordinal()] = newNode;
				break;

			case TURN_LEFT:
				newNode.setDirection(direction.turnLeft());
				succ[a.ordinal()] = newNode;
				break;

			case TURN_RIGHT:
				newNode.setDirection(direction.turnRight());
				succ[a.ordinal()] = newNode;
				break;
			}
		}
		return succ;
	}

	public AINode clone()
	{
		return new AINode(this);
	}

	public void show()
	{
		System.out.println(location.getGridX() + " " + location.getGridY()
				+ " " + direction);
	}

	public void showChain()
	{
		this.show();
		if (hasParent())
			parent.showChain();
	}

}
