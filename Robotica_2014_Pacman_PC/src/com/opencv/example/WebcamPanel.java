package com.opencv.example;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import com.robotica.pc.imageprocessing.Utils;

public class WebcamPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private Mat circles;
	private Mat currentMatImage;

	public WebcamPanel() {
		super();

		circles = new Mat();
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		if (currentMatImage != null) {
			// Draw the image to the screen
			BufferedImage currentImage = Utils
					.matToBufferedImage(currentMatImage);
			g2d.drawImage(currentImage, 0, 0, currentImage.getWidth(),
					currentImage.getHeight(), this);
			
			// Convert to grayscale for circle detection
			Mat grayImage = new Mat();
			Imgproc.cvtColor(currentMatImage, grayImage, Imgproc.COLOR_BGR2GRAY);
			
			//blur the gray image to reduce noise
			Imgproc.GaussianBlur(grayImage, grayImage, new Size(9,9), 2,2);

			
			int minRadius = 30;
			int maxRadius = 200;
			Imgproc.HoughCircles(grayImage, circles, Imgproc.CV_HOUGH_GRADIENT,
					1, 200, 200, 20, minRadius, maxRadius);

			
			if(!circles.empty()){
				// Draw the circles to the screen 
				g2d.setColor(Color.MAGENTA);
				g2d.setStroke(new BasicStroke(3));
				for (int i = 0; i < circles.cols(); i++) {
					double[] circle = circles.get(0, i);
					g2d.drawOval((int) circle[0] - (int) circle[2], (int) circle[1]
							- (int) circle[2], (int) circle[2] * 2,
							(int) circle[2] * 2);
				}
			}
		}

	}

	public void setMatImage(Mat img) {
		currentMatImage = img;
	}

	public void setCircles(Mat circles) {
		this.circles = circles;
	}
}
