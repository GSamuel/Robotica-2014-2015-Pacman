package controllers;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.robotica.pc.model.World;


public class MouseMatrixController implements MouseListener
{

	private boolean isSetting = false;
	private int currentIndex = 0;
	private World w;
	
	public MouseMatrixController(World w)
	{
		this.w = w;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		if(e.getButton() == 2) //middle mouse button
		{
			w.getMazeShape().clear();
			isSetting = true;
			currentIndex = 0;
			w.getMazeShape().show();
		}
		else if(isSetting && e.getButton() == 1 && currentIndex <4)
		{
			w.getMazeShape().setPoint(e.getPoint(), currentIndex++);
			w.getMazeShape().show();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

}
