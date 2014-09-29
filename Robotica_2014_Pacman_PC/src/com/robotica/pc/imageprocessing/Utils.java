package com.robotica.pc.imageprocessing;

import java.awt.image.BufferedImage;

import org.opencv.core.Mat;

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
	
}
