package com.robotica.pc.imageprocessing;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class Filter
{
	public static Mat createGrayImage(Mat mat)
	{
		Mat grayImage = new Mat();
		Imgproc.cvtColor(mat, grayImage, Imgproc.COLOR_BGR2GRAY);
		return grayImage;
	}
}
