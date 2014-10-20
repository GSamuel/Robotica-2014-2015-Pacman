package com.robotica.pc.model;

import java.util.Observable;

public class World extends Observable
{
	private Maze maze;
	private EntityList entities;
	private MatrixContainer mC;
	
	public World()
	{
		this.maze = new Maze(0,0);
		this.entities = new EntityList();
		this.mC = new MatrixContainer();
	}
	
	public World(Maze maze, EntityList entities)
	{
		this.maze = maze;
		this.entities = entities;
		this.mC = new MatrixContainer();
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
	
	public MatrixContainer getMatrixContainer()
	{
		return mC;
	}
}
