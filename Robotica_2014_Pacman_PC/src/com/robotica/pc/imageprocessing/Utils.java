package com.robotica.pc.imageprocessing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Mat;

import com.robotica.pc.gui.Circle;

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
			double[] circleCoor = circlesMat.get(0, i);
			Circle circle = new Circle();
			circle.setLocation(circleCoor[0] - circleCoor[2], circleCoor[1] - circleCoor[2]);
			circle.setRadius(circleCoor[2]);
			circles.add(circle);
		}	
		return circles;
	}
	
	public static Color getColorFromCircle(Circle circle, BufferedImage img)
	{
		List<Integer> rgbs = new ArrayList<Integer>();
		WritableRaster raster = img.getRaster();
		rgbs.addAll(arrayToList(raster.getPixel(circle.getCenter().x, circle.getCenter().y, (int[]) null)));
		rgbs.addAll(arrayToList(raster.getPixel(circle.getCenter().x + circle.getRadius()/2, circle.getCenter().y, (int[]) null)));
		rgbs.addAll(arrayToList(raster.getPixel(circle.getCenter().x - circle.getRadius()/2, circle.getCenter().y, (int[]) null)));
		rgbs.addAll(arrayToList(raster.getPixel(circle.getCenter().x, circle.getCenter().y + circle.getRadius()/2, (int[]) null)));
		rgbs.addAll(arrayToList(raster.getPixel(circle.getCenter().x, circle.getCenter().y - circle.getRadius()/2, (int[]) null)));
		return getAverageColor(rgbs);
	}
	
	private static Color getAverageColor(List<Integer> rgbs)
	{
		int red = 0;
		int blue = 0;
		int green = 0;
		for(int i = 0; i < rgbs.size(); i += 3)
		{
			red += rgbs.get(i);
			blue += rgbs.get(i+1);
			green += rgbs.get(i+2);
		}
		red /= rgbs.size() / 3;
		blue /= rgbs.size() / 3;
		green /= rgbs.size() / 3;
		return new Color(red, blue, green);
	}
	
	private static ArrayList<Integer> arrayToList(int[] array)
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < array.length; i++)
		{
			list.add(array[i]);
		}
		return list;
	}
	
}
