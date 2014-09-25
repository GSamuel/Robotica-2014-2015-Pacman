package com.opencv.example;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class WebcamPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private Mat circles;
	private Mat currentMatImage;

	public WebcamPanel() {
		super();
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		if(currentMatImage != null){
			Mat grayImage = new Mat();
			// Convert to grayscale for circledetection
			Imgproc.cvtColor(currentMatImage, grayImage, Imgproc.COLOR_BGR2GRAY);
			
			BufferedImage currentImage = Utils.matToBufferedImage(currentMatImage);
			g2d.drawImage(currentImage, 0, 0, currentImage.getWidth(),
					currentImage.getHeight(), this);
		}

	}

	public void setMatImage(Mat img) {
		currentMatImage = img;
	}

	public void setCircles(Mat circles) {
		this.circles = circles;
	}
}
