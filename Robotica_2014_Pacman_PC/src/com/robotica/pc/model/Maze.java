package com.robotica.pc.model;

import org.opencv.core.Size;

public class Maze implements Cloneable
{
	private Tile[][] map;
	private int N,M;

	public Maze(int N, int M)
	{
		this.N = N;
		this.M = M;
		map = new Tile[N][M];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				map[i][j] = Tile.nullTile();
	}

	public Maze(Maze maze)
	{
		this.N = maze.N;
		this.M = maze.M;
		this.map = new Tile[N][M];
		for(int i = 0; i < N; i++)
			for(int j = 0; j<M; j++)
				map[i][j] = maze.map[i][j];
	}

	public Tile getTile(int x, int y)
	{
		return map[x][y];
	}

	public Tile getTile(Location loc)
	{
		return map[loc.getGridX()][loc.getGridY()];
	}

	public void setTile(int x, int y, Tile tile)
	{
		map[x][y] = tile;
	}

	public int getWidth()
	{
		return map.length;
	}

	public int getHeight()
	{
		if(getWidth()<=0)
			return 0;
		return map[0].length;
	}
	
	public Size getSize()
	{
		return new Size(getWidth(), getHeight());
	}

	public Maze clone()
	{
		return new Maze(this);
	}

	public boolean inMaze(Location loc)
	{
		return (loc.getGridX() >= 0 && loc.getGridX() < getWidth()
				&& loc.getGridY() >= 0 && loc.getGridY() < getHeight());
	}

	public void show()
	{
		for (int j = 0; j < getHeight(); j++)
		{
			for (int i = 0; i < getWidth(); i++)
				System.out.print(map[i][j].toString());
			System.out.println();
		}
		System.out.println();
	}
}
