package com.robotica.pc.gui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import com.robotica.pc.model.Entity;

public class EntityPanel extends JPanel implements Observer
{
	private Entity entity;
	public EntityPanel(Entity entity)
	{
		this.entity = entity;
		this.entity.addObserver(this);
	}
	
	@Override
	public void update(Observable arg0, Object arg1)
	{

	}

}
