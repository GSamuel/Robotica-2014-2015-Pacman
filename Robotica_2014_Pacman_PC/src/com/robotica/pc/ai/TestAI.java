package com.robotica.pc.ai;

import com.robotica.pc.model.Entity;
import com.robotica.pc.model.EntityType;
import com.robotica.pc.model.World;

public class TestAI extends AI
{
	public TestAI(World w)
	{
		super(w);
	}

	@Override
	public void createPath()
	{
		for(Entity e: w.getEntities())
		{
			if(e.getType() == EntityType.PACMAN);
		}
	}

}
