package com.robotica.nxt.brick;

import com.robotica.nxt.settings.NXTAction;
import com.robotica.nxt.settings.Settings;

public abstract class Brick
{
	private Settings settings = new Settings();
	private NXTAction state = NXTAction.getDefault();

	public void update()
	{
		switch (state)
		{
		case MOVE_FORWARD:
			move_forwards();
			break;
		case TURN_LEFT:
			turn_left();
			break;
		case TURN_RIGHT:
			turn_right();
			break;
		case MOVE_BACKWARDS:
			move_backwards();
			break;
		case STAND_STILL:
			stand_still();
			break;
		}
	}

	protected abstract void move_forwards();

	protected abstract void turn_left();

	protected abstract void turn_right();

	protected abstract void move_backwards();

	protected abstract void stand_still();

	public Settings getSettings()
	{
		return settings.clone();
	}

	public void setSettings(Settings settings)
	{
		this.settings = settings;
	}

	public NXTAction getState()
	{
		return state;
	}

	public void setState(NXTAction state)
	{
		this.state = state;
	}
}
