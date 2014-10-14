package main;
import java.awt.Dimension;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.VideoCapture;

import com.robotica.pc.gui.CirclePanel;
import com.robotica.pc.gui.MatrixCirclePanel;
import com.robotica.pc.gui.MatrixPanel;
import com.robotica.pc.gui.PacmanWindow;
import com.robotica.pc.imageprocessing.Filter;
import com.robotica.pc.model.MatrixContainer;

public class ImageProcessingMain
{

	public static void main(String[] args)
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		circlesTest();
		// matrixPanelTest();
		// circlePanelTest();
	}

	private static void circlesTest()
	{
		PacmanWindow pw = new PacmanWindow();
		MatrixContainer container = new MatrixContainer();
		VideoCapture capture = new VideoCapture(3);
		Mat mat = new Mat();
		MatrixCirclePanel circlePanel = new MatrixCirclePanel("color","blur",container);
		pw.add(circlePanel);

		while (true)
		{
			capture.read(mat);
			container.addMatrix("color", mat);
			container.addMatrix("grey", Filter.createGrayImage(mat));
			container.addMatrix("blur", Filter.createBlurred(container.getMatrix("grey")));
		}
	}

	private static void matrixPanelTest()
	{
		PacmanWindow pw = new PacmanWindow();
		MatrixContainer mC = new MatrixContainer();

		VideoCapture videoCapture = new VideoCapture(0);
		Mat mat = new Mat();
		videoCapture.read(mat);

		mC.addMatrix("img01", mat);
		mC.addMatrix("img02", Filter.createGrayImage(mat));

		MatrixPanel p1 = new MatrixPanel("img01", mC);
		pw.add(p1);

		MatrixPanel p2 = new MatrixPanel("img02", mC);
		pw.add(p2);

		MatrixCirclePanel ePT = new MatrixCirclePanel("img02","img02", mC);
		pw.add(ePT);

		pw.revalidate();
	}

	private static void circlePanelTest()
	{
		PacmanWindow pw = new PacmanWindow(); // model

		CirclePanel cP = new CirclePanel();
		cP.setPreferredSize(new Dimension(80, 100));
		pw.add(cP);

		CirclePanel cP2 = new CirclePanel();
		cP2.setPreferredSize(new Dimension(100, 100));
		pw.add(cP2);
		CirclePanel cP3 = new CirclePanel();
		cP3.setPreferredSize(new Dimension(200, 50));
		pw.add(cP3);
		CirclePanel cP4 = new CirclePanel();
		cP4.setPreferredSize(new Dimension(100, 100));
		pw.add(cP4);
	}

}
