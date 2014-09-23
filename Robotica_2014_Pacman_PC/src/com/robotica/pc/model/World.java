package com.robotica.pc.model;

import java.util.ArrayList;
import java.util.Iterator;

public class World implements Iterable<Entity>
{
	private ArrayList<Entity> entities;
	private Maze maze;
	
	public World()
	{
		entities = new ArrayList<Entity>();
	}
	
	public void addEntity(Entity e)
	{
		entities.add(e);
	}
	
	public void removeEntity(Entity e)
	{
		entities.remove(e);
	}

	@Override
	public Iterator<Entity> iterator()
	{
		return entities.iterator();
	}
	
	public Maze getMaze()
	{
		return maze.clone();
	}
	
	public void show()
	{
		System.out.println(this);
	}
	
	public String toString()
	{
		String s = "Entities:\n";
		for(Entity en: entities)
			s = s+ en+'\n';
		
		return s;
	}
}
