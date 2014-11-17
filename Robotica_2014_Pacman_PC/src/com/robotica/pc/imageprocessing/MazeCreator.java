package com.robotica.pc.imageprocessing;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import com.robotica.pc.model.Maze;
import com.robotica.pc.model.Tile;

public class MazeCreator {

	public static Maze imageToMaze(Mat image, Size size)
	{
		Maze maze = new Maze((int) size.width, (int) size.height);
		Filter.blackWhite(image);
		Imgproc.resize(image, image, size);
		for(int y = 0; y < size.height; y++)
		{
			for(int x = 0; x < size.width; x++)
			{
				 if(image.get(x, y)[0] < 127){
					 maze.setTile(x, y, Tile.WALL);
				 }
			}
		}
		maze.show();
		return maze;
	}
}