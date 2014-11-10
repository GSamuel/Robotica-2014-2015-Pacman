package com.robotica.pc.imageprocessing;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class Filter {
	// This is an example of returning a new gray matrix ;
	public static Mat createGrayImage(Mat mat) {
		Mat grayImage = new Mat();
		Imgproc.cvtColor(mat, grayImage, Imgproc.COLOR_BGR2GRAY);
		return grayImage;
	}

	// Here the old matrix will be changed to reflect the change.
	public static void convertToGrayImage(Mat mat) {
		Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2GRAY);
	}

	public static Mat createBlurred(Mat mat) {
		Mat blurred = new Mat();
		Imgproc.GaussianBlur(mat, blurred, new Size(9, 9), 2, 2);
		return blurred;
	}

	public static void convertToBlurred(Mat mat) {
		Imgproc.GaussianBlur(mat, mat, new Size(9, 9), 2, 2);
	}

	public static Mat getCircles(Mat mat) {
		return getCircles(mat, 5, 200);
	}

	public static Mat getCircles(Mat mat, int minRadius, int maxRadius) {
		Mat circles = new Mat();
		Imgproc.HoughCircles(mat, circles, Imgproc.CV_HOUGH_GRADIENT, 1, 20, 200, 40, minRadius, maxRadius);
		return circles;
	}

	public static Mat createWarpedImage(Mat img, Size size, Point topLeft, Point topRight, Point botLeft, Point botRight) {
		Mat goal = new Mat(4, 1, CvType.CV_32FC2);
		Mat source = new Mat(4, 1, CvType.CV_32FC2);
		source.put(0, 0, topLeft.y, topLeft.x, topRight.y, topRight.x, botLeft.y, botLeft.x, botRight.y, botRight.x);
		goal.put(0, 0, 0, 0, 0, size.width, size.height, 0, size.width, size.height);
		Mat transformationMatrix = Imgproc.getPerspectiveTransform(source, goal);
		Mat result = img.clone();
		Imgproc.warpPerspective(img, result, transformationMatrix, size);
		return result;
	}

	public static void blackWhite(BufferedImage img) {
		WritableRaster raster = img.getRaster();
		for (int y = 0; y < raster.getHeight(); y++) 
		{
			for (int x = 0; x < raster.getWidth(); x++) 
			{
				raster.setPixel(x, y, (isLight(raster.getPixel(x, y, (int[]) null)) ? new int[] { 255, 255, 255 } : new int[] { 0, 0, 0 }));
			}
		}
	}

	private static boolean isLight(int[] color) {
		int total = color[0] + color[1] + color[2];
		return total >= 383;
	}
}
