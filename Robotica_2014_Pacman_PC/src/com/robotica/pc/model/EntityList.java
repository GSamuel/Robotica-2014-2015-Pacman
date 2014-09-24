package com.robotica.pc.model;

import java.util.Arrays;
import java.util.Iterator;

public class EntityList implements Iterable<Entity>, Cloneable
{
	private Entity[] entities;
	
	public EntityList(Entity[] entities)
	{
		this.entities = entities.clone();
	}

	public EntityList(EntityList list)
	{
		this.entities = list.entities.clone();
	}

	@Override
	public Iterator<Entity> iterator()
	{
		return Arrays.asList(entities).iterator();
	}
	
	public EntityList clone()
	{
		return new EntityList(this);
	}
}
