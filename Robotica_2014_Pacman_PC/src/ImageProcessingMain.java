import java.awt.Dimension;

import com.robotica.pc.gui.CirclePanel;
import com.robotica.pc.gui.PacmanWindow;



public class ImageProcessingMain
{

	public static void main(String[] args)
	{
		PacmanWindow pw = new PacmanWindow();
		CirclePanel cP = new CirclePanel();
		cP.setPreferredSize(new Dimension(80,100));		
		pw.add(cP);
		CirclePanel cP2 = new CirclePanel();
		cP2.setPreferredSize(new Dimension(100,100));	
		pw.add(cP2);
	}

}
