package com.robotica.pc.model;

public class World implements Cloneable
{
	private Maze maze;
	private EntityList entities;
	
	public World(Maze maze, EntityList entities)
	{
		this.maze = maze;
		this.entities = entities;
	}
	
	public World(World world)
	{
		this.maze = world.maze.clone();
		this.entities = world.entities.clone();
	}

	public void setMaze(Maze maze)
	{
		this.maze = maze;
	}
	
	public void setEntities(EntityList entities)
	{
		this.entities = entities;
	}
	
	public Maze getMaze()
	{
		return maze.clone();
	}
	
	public EntityList getEntities()
	{
		return entities.clone();
	}
	
	public World clone()
	{
		return new World(this);
	}
}
