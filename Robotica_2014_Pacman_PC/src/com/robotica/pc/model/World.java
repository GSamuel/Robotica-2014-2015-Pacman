package com.robotica.pc.model;

import java.util.ArrayList;
import java.util.Observable;

import org.opencv.highgui.VideoCapture;

public class World extends Observable
{
	private Maze maze;
	private ArrayList<ConnectedEntity> connEntities;
	public MatrixContainer container;
	public VideoCapture camera;
	
	public World()
	{
		this.maze = new Maze(0,0);
		this.connEntities = new ArrayList<ConnectedEntity>();
		this.container = new MatrixContainer();
	}
	
	public World(Maze maze, ArrayList<ConnectedEntity> connEntities)
	{
		this.maze = maze;
		this.connEntities = connEntities;
		this.container = new MatrixContainer();
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
	
	public Maze getMaze()
	{
		return maze.clone();
	}
	
	public void setCamera(VideoCapture camera)
	{
		this.camera = camera;
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
