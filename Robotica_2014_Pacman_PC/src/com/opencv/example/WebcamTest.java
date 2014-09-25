package com.opencv.example;

import java.util.Scanner;

import javax.swing.JFrame;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.VideoCapture;


public class WebcamTest {
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		JFrame frame = new JFrame("OpenCV circles test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter camera number: ");
		int cameraIndex = scanner.nextInt();
		scanner.close();
		
		VideoCapture videoCapture = new VideoCapture(cameraIndex);
		WebcamPanel webcamPanel = new WebcamPanel();
		frame.add(webcamPanel);
		frame.setSize(1280, 720);
		frame.setVisible(true);
		
		while(true){
			Mat image = new Mat();
			if (videoCapture.read(image)){
				webcamPanel.setMatImage(image);
				webcamPanel.repaint();
				frame.repaint();
			}
		}
	}

}
