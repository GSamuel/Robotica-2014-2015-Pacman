package com.robotica.pc.imageprocessing;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class Filter
{
	//This is an example of returning a new gray matrix ;
	public static Mat createGrayImage(Mat mat)
	{
		Mat grayImage = new Mat();
		Imgproc.cvtColor(mat, grayImage, Imgproc.COLOR_BGR2GRAY);
		return grayImage;
	}
	
	//Here the old matrix will be changed to reflect the change.
	public static void convertToGrayImage(Mat mat)
	{
		Mat grayImage = new Mat();
		Imgproc.cvtColor(mat, grayImage, Imgproc.COLOR_BGR2GRAY);
		mat.setTo(grayImage);
	}
	
	public static Mat getCircles(Mat mat)
	{
		Mat circles = new Mat();
		Imgproc.HoughCircles(mat, circles, Imgproc.CV_HOUGH_GRADIENT,
				1, 20, 200, 40, 5, 200);
		return circles;
	}
	
	public static Mat getCircles(Mat mat, int minRadius, int maxRadius)
	{
		Mat circles = new Mat();
		Imgproc.HoughCircles(mat, circles, Imgproc.CV_HOUGH_GRADIENT,
				1, 20, 200, 40, minRadius, maxRadius);
		return circles;
	}
}
