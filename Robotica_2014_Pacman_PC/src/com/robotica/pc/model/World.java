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
	
	public World(World world)
	{
		this.maze = world.maze;
		this.entities = new ArrayList<Entity>();
		for(Entity ent: world.entities)
			this.entities.add(ent.clone());
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
		return maze;
	}
	
	public World clone()
	{
		return new World(this);
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
