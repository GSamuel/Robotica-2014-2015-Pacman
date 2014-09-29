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
}
