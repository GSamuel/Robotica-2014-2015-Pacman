package com.robotica.pc.model;

import java.util.ArrayList;
import java.util.Observable;

import org.opencv.highgui.VideoCapture;

public class World extends Observable
{
	private Maze maze;
	private ArrayList<ConnectedEntity> connEntities;
	public MatrixContainer container;
	private Trapezium mazeShape;
	public VideoCapture camera;
	
	public World()
	{
		this.maze = new Maze(0,0);
		this.connEntities = new ArrayList<ConnectedEntity>();
		this.container = new MatrixContainer();
		this.mazeShape = new Trapezium();
	}
	
	public World(Maze maze, ArrayList<ConnectedEntity> connEntities)
	{
		this.maze = maze;
		this.connEntities = connEntities;
		this.container = new MatrixContainer();
		this.mazeShape = new Trapezium();
	}

	public void setMaze(Maze maze)
	{
		this.maze = maze;
		this.setChanged();
		this.notifyObservers();
	}
	
	public void addConnectedntity(ConnectedEntity connEntity)
	{
		connEntities.add(connEntity);
	}
	
	public Trapezium getMazeShape()
	{
		return mazeShape;
	}
	
	public Maze getMaze()
	{
		return maze.clone();
	}
	
	public void setCamera(VideoCapture camera)
	{
		this.camera = camera;
	}
	
	public ConnectedEntity entityWithID(int id)
	{
		for(ConnectedEntity e: connEntities)
			if(e.getEntity().getID()==id)
				return e;
		return null;
	}
	
	public ArrayList<ConnectedEntity> getConnectedEntities()
	{
		return connEntities;
	}
	
	public MatrixContainer getMatrixContainer()
	{
		return container;
	}
}
