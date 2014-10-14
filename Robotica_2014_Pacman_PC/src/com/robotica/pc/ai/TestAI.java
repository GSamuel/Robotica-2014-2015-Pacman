package com.robotica.pc.ai;

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
		w.getMaze();
		w.getEntities();
	}

}
