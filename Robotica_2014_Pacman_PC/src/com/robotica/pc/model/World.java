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
	private Location pacmanGoal;
	
	public World()
	{
		this.maze = new Maze(0,0);
		this.connEntities = new ArrayList<ConnectedEntity>();
		this.container = new MatrixContainer();
		this.mazeShape = new Trapezium();
		this.pacmanGoal = new Location();
	}
	
	public World(Maze maze, ArrayList<ConnectedEntity> connEntities)
	{
		this.maze = maze;
		this.connEntities = connEntities;
		this.container = new MatrixContainer();
		this.mazeShape = new Trapezium();
		this.pacmanGoal = new Location();
	}

	public void setMaze(Maze maze)
	{
		this.maze = maze;
		this.setChanged();
		this.notifyObservers();
	}
	
	public void setPacmanGoal(double x, double y)
	{
		pacmanGoal.setLocation(x,y);
	}
	
	public Location getPacmanGoal()
	{
		return pacmanGoal;
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
	/*
	public Maze getMazeWithEntities()
	{
		Maze m = maze.clone();
		for(ConnectedEntity ce: connEntities)
		{
			int x = ce.getEntity().getLocation().getGridX();
			int y = ce.getEntity().getLocation().getGridY();
			m.setTile(x, y, Tile.ENTITY);
		}
		
		return m;
	}*/
	
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
