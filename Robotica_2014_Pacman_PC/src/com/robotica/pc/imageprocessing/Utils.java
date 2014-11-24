package com.robotica.pc.imageprocessing;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;

import com.robotica.pc.model.Circle;
import com.robotica.pc.model.Maze;
import com.robotica.pc.model.Tile;

public class Utils {

	public static BufferedImage matToBufferedImage(Mat mat){
		int cols = mat.cols();
		int rows = mat.rows();
		int elemSize = (int) mat.elemSize();
		byte[] data = new byte[cols * rows * elemSize];
		int type;
		mat.get(0, 0, data);
		switch (mat.channels()) {
		case 1:
			type = BufferedImage.TYPE_BYTE_GRAY;
			break;
		case 3:
			type = BufferedImage.TYPE_3BYTE_BGR;
			//convert BGR to RGB
			byte b;
			for (int i = 0; i < data.length; i = i + 3) {
				b = data[i];
				data[i] = data[i + 2];
				data[i + 2] = b;
			}
			break;
		default:
			return null;
		}
		BufferedImage image2 = new BufferedImage(cols, rows, type);
		image2.getRaster().setDataElements(0, 0, cols, rows, data);
		return image2;
	}
	
	public static ArrayList<Circle> getCirclesFromMat(Mat circlesMat){
		ArrayList<Circle> circles = new ArrayList<Circle>();
		for (int i = 0; i < circlesMat.cols(); i++) {
			int[] circleCoor = doubleArrayToIntArray(circlesMat.get(0, i));
			Circle circle = new Circle();
			circle.setLocation(circleCoor[0] - circleCoor[2], circleCoor[1] - circleCoor[2]);
			circle.setRadius(circleCoor[2]);
			circles.add(circle);
		}	
		return circles;
	}
	
	private static int[] doubleArrayToIntArray(double[] array)
	{
		int[] newArray = new int[array.length];
		for (int i = 0; i < array.length; i++)
		{
			newArray[i] = (int) array[i];
		}
		return newArray;
	}
	
	public static ArrayList<Integer> arrayToList(int[] array)
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < array.length; i++)
		{
			list.add(array[i]);
		}
		return list;
	}
	
	public static Maze createMazePattern(Mat img, int columns, int rows)
	{
		Mat newMat = null;
		int xSize = img.width()/columns;
		int ySize = img.height()/rows;
		Maze maze = new Maze(columns,rows);
		for(int i =0; i < columns; i++)
		{
			for(int j =0; j < rows; j++)
			{
				newMat = new Mat(img, new Rect(i*xSize,j*ySize, xSize,ySize));
				Scalar scal = Core.mean(newMat);
				double total = 0.0;
				for(double d:scal.val)
					total += d;
				total /=3;
				if(total < 100)
				maze.setTile(i, j, Tile.WALL);
			}
		}
		
		return maze;
	}
	
}
