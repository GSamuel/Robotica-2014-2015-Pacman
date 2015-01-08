package com.robotica.pc.model;

public class FPS
{
	private long start,stop;
	
	public void start()
	{
		start = System.currentTimeMillis();
	}
	
	public void stop()
	{
		stop = System.currentTimeMillis();
	}
	
	public void show()
	{
		stop();
        System.out.println("fps: "+1.0/(stop-start)*1000);
	}
}
