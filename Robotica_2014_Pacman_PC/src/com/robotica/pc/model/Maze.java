package com.robotica.pc.model;

public class Maze
{
	private Tile[][] map;
	
	public Maze(int N, int M)
	{
		map = new Tile[N][M];
		for(int i = 0; i<N; i++)
			for(int j=0; j <M; j++)
				map[i][j] = Tile.nullTile();
	}
	
	public Maze(Tile[][] map)
	{
		this.map = map.clone();
	}
	
	public Maze(Maze maze)
	{
		this.map = maze.map.clone();
	}
	
	public Tile getTile(int x, int y)
	{
		return map[x][y];
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
		return map[0].length;
	}

	public Maze clone()
	{
		return new Maze(this);
	}
	
	public void show()
	{
		for(int j=0; j <getHeight(); j++)
		{
			for(int i = 0; i<getWidth(); i++)
				System.out.print(map[i][j]+",");
			System.out.println();
		}
	}
}
