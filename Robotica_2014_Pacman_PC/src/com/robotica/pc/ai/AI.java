package com.robotica.pc.ai;

import com.robotica.pc.model.AINode;
import com.robotica.pc.model.World;

public abstract class AI
{
	protected World w;
	public AI(World w)
	{
		this.w = w;
	}
	
	public abstract AINode createPath();
}
