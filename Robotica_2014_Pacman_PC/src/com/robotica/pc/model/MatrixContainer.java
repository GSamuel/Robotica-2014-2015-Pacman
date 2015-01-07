package com.robotica.pc.model;

import java.util.HashMap;
import java.util.Observable;

import org.opencv.core.Mat;

public class MatrixContainer extends Observable
{
	private HashMap<String, Mat> map;
	
	public MatrixContainer()
	{
		map = new HashMap<String, Mat>();
	}
	
	public void clear()
	{
		map.clear();
	}
	
	public void addMatrix(String key, Mat mat)
	{
		map.put(key, mat);
		this.setChanged();
		this.notifyObservers(key);
	}
	
	public Mat getMatrix(String key)
	{
		Mat m = map.get(key);
		if (m == null)
			return new Mat();
		return m;
	}
}
