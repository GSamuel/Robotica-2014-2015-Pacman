package com.robotica.pc.model;

import java.util.ArrayList;
import java.util.Iterator;

public class EntityList implements Iterable<Entity>, Cloneable
{
	private ArrayList<Entity> entities;
	
	public EntityList()
	{
		this.entities = new ArrayList<Entity>();
	}
	
	public EntityList(ArrayList<Entity> entities)
	{
		this.entities = new ArrayList<Entity>();
		for(Entity e: entities)
			this.entities.add(e);
	}

	public EntityList(EntityList list)
	{
		this.entities = new ArrayList<Entity>();
		for(Entity e: list.entities)
			this.entities.add(e);
	}
	
	public void add(Entity e)
	{
		entities.add(e);
	}
	
	public void remove(Entity e)
	{
		entities.remove(e);
	}

	@Override
	public Iterator<Entity> iterator()
	{
		return entities.iterator();
	}
	
	public EntityList clone()
	{
		return new EntityList(this);
	}
}
