import com.robotica.nxt.brick.Brick;
import com.robotica.nxt.brick.PrintBrick;


public class MaintTest
{

	public static void main(String[] args)
	{
		Brick brick = new PrintBrick();
		while(true)
		{
			brick.update();
		}

	}

}
