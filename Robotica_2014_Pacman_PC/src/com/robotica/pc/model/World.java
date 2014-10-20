package com.robotica.pc.model;

import java.util.Observable;

import org.opencv.highgui.VideoCapture;

public class World extends Observable
{
	private Maze maze;
	private EntityList entities;
	public MatrixContainer container;
	public VideoCapture camera;
	
	public World()
	{
		this.maze = new Maze(0,0);
		this.entities = new EntityList();
		this.container = new MatrixContainer();
	}
	
	public World(Maze maze, EntityList entities)
	{
		this.maze = maze;
		this.entities = entities;
		this.container = new MatrixContainer();
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
	
	public void setCamera(VideoCapture camera)
	{
		this.camera = camera;
	}
	
	public EntityList getEntities()
	{
		return entities.clone();
	}
	
	public MatrixContainer getMatrixContainer()
	{
		return container;
	}
}
