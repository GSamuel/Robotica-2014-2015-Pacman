package main;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.VideoCapture;

import com.robotica.pc.gui.MatrixPanel;
import com.robotica.pc.gui.PacmanWindow;
import com.robotica.pc.imageprocessing.Filter;
import com.robotica.pc.model.World;

public class WarpPerspectiveTest
{

	public static void main(String[] args)
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		World world = new World();
		world.setCamera(new VideoCapture(3));

		PacmanWindow pw = new PacmanWindow();

		MatrixPanel circlePanel = new MatrixPanel("color", world.container);
		pw.add(circlePanel);

		while (true) {
			Mat mat = new Mat();
			world.camera.read(mat);
			world.container.addMatrix("color", mat);
			world.container.addMatrix("grey", Filter.createGrayImage(mat));
			world.container.addMatrix("blur", Filter.createBlurred(world.container.getMatrix("grey")));
		}
	}

}
