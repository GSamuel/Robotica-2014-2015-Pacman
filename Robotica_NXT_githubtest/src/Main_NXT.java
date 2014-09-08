import com.robotica.nxt.button.ButtonController;
import com.robotica.nxt.button.NXTButtonController;
import com.robotica.nxt.button.NXTButtonListener;
import com.robotica.nxt.mvc.BrickModel;
import com.robotica.nxt.mvc.BrickView;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;

public class Main_NXT
{

	public static void main(String[] args)
	{
		
		BrickModel model = new BrickModel();
		NXTButtonController controller = new ButtonController(model);

		BrickView view = new BrickView(model,controller);
		
		while(true)
		{
			try
			{
				view.update();
				Thread.sleep(100);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
