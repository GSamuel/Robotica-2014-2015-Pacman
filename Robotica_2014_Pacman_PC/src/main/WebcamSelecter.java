package main;

import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;

public class WebcamSelecter
{

	public static void main(String[] args)
	{
		List<Webcam> webcams = Webcam.getWebcams();

		Scanner input = new Scanner(System.in);
		int select = 0;

		while (true)
		{
			select = -1;
			webcams = Webcam.getWebcams();
			System.out.println("-1: EXIT");
			while ((select < 0 || select >= webcams.size())
					&& webcams.size() != 0)
			{
				for (int i = 0; i < webcams.size(); i++)
					System.out.println(i + ": " + webcams.get(i).getName());
				select = input.nextInt();
				if(select == -1)
				{
					input.close();
					System.exit(0);
				}
			}

			Webcam webcam = webcams.get(select);
			WebcamPanel panel = new WebcamPanel(webcam);
			panel.setFPSDisplayed(true);

			JFrame window = new JFrame("Test webcam panel");
			window.add(panel);
			window.setResizable(false);
			window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			window.pack();
			window.setVisible(true);
		}
	}

}
