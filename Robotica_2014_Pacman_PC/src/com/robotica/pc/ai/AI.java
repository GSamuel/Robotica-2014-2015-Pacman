package com.robotica.pc.ai;

import com.robotica.pc.model.World;

public abstract class AI
{
	protected World w;
	protected int entity;
	public AI(World w, int entity)
	{
		this.w = w;
		this.entity = entity;
	}
	
	public abstract void createPath();
}
