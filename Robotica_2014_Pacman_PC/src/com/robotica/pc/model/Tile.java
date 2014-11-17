package com.robotica.pc.model;

import java.awt.Color;

public enum Tile
{
	EMPTY, WALL;

	public Color getColor()
	{
		switch (this)
		{
		case WALL:
			return Color.BLACK;
		default:
			return Color.WHITE;
		}
	}

	public static Tile nullTile()
	{
		return EMPTY;
	}
	
	public String toString(){
		switch(this){
		case EMPTY:
			return ".";
		case WALL:
			return "#";
		default:
			return "ERROR";
		}
	}
}
