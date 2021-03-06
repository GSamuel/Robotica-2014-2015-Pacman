package com.robotica.pc.imageprocessing;

import java.awt.Point;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import com.robotica.pc.model.Trapezium;

public class Filter
{
	// This is an example of returning a new gray matrix ;
	public static Mat createGrayImage(Mat mat)
	{
		Mat grayImage = new Mat();
		Imgproc.cvtColor(mat, grayImage, Imgproc.COLOR_BGR2GRAY);
		return grayImage;
	}

	// Here the old matrix will be changed to reflect the change.
	public static void convertToGrayImage(Mat mat)
	{
		Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2GRAY);
	}

	public static Mat treshholdGrayImage(Mat mat, int diff)
	{
		Mat newMat = mat.clone();
		for (int i = 0; i < newMat.width(); i++)
			for (int j = 0; j < newMat.height(); j++)
			{
				int rest = (int) newMat.get(j, i)[0] % diff;
				newMat.put(j, i, newMat.get(j, i)[0] - rest);
			}

		return newMat;
	}

	public static Mat createBlurred(Mat mat)
	{
		Mat blurred = new Mat();
		Imgproc.GaussianBlur(mat, blurred, new Size(9, 9), 2, 2);
		return blurred;
	}

	public static void convertToBlurred(Mat mat)
	{
		Imgproc.GaussianBlur(mat, mat, new Size(9, 9), 2, 2);
	}

	public static Mat getCircles(Mat mat)
	{
		return getCircles(mat, 5, 200);
	}

	public static Mat getCircles(Mat mat, int minRadius, int maxRadius)
	{
		Mat circles = new Mat();
		Imgproc.HoughCircles(mat, circles, Imgproc.CV_HOUGH_GRADIENT, 1, 20,
				200, 30, 0, 40);
		return circles;
	}

	public static Mat blackWhite(Mat mat)
	{
		Mat newMat = mat.clone();
		for (int y = 0; y < newMat.rows(); y++)
		{
			for (int x = 0; x < newMat.cols(); x++)
			{
				newMat.put(y, x, new byte[]
				{ (byte) ((newMat.get(y, x)[0] > 127.0) ? 255 : 0) });
			}
		}
		return newMat;
	}

	public static Mat differenceGrey(Mat m1, Mat m2)
	{
		if (m1 == null || m2 == null)
			return null;
		Mat newMat = m1.clone();

		double diff;

		for (int i = 0; i < newMat.width(); i++)
			for (int j = 0; j < newMat.height(); j++)
			{
				
				diff = (m1.get(j,i)[0] > m2.get(j, i)[0] ? m1.get(j, i)[0] - m2.get(j, i)[0] : m2.get(j, i)[0] - m1.get(j, i)[0]);
				newMat.put(j, i, diff);
			}

		return newMat;
	}

	public static Mat createWarpedImage(Mat img, Size size, Trapezium mazeShape)
	{
		Point topLeft = mazeShape.getPoint(0);
		Point topRight = mazeShape.getPoint(1);
		Point botRight = mazeShape.getPoint(2);
		Point botLeft = mazeShape.getPoint(3);

		Mat goal = new Mat(4, 1, CvType.CV_32FC2);
		Mat source = new Mat(4, 1, CvType.CV_32FC2);
		source.put(0, 0, topLeft.x, topLeft.y, topRight.x, topRight.y,
				botLeft.x, botLeft.y, botRight.x, botRight.y);
		goal.put(0, 0, 0, 0, size.width, 0, 0, size.height, size.width,
				size.height);
		// source.put(0, 0, topLeft.y, topLeft.x, topRight.y, topRight.x,
		// botLeft.y, botLeft.x, botRight.y, botRight.x);
		// goal.put(0, 0, 0, 0, 0, size.width, size.height, 0, size.width,
		// size.height);
		Mat transformationMatrix = Imgproc
				.getPerspectiveTransform(source, goal);
		Mat result = img.clone();
		Imgproc.warpPerspective(img, result, transformationMatrix, size);
		return result;
	}

}
