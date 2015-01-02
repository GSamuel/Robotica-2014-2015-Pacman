package controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.robotica.pc.gui.MazePanel;
import com.robotica.pc.model.World;

public class MousePanelController implements MouseListener
{
	private World w;
	private MazePanel mazePanel;
	
	public MousePanelController(World w, MazePanel mazePanel)
	{
		this.w = w;
		this.mazePanel = mazePanel;
	}
	@Override
	public void mouseClicked(MouseEvent e)
	{
	}
	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e)
	{
		int x = e.getX() * w.getMaze().getWidth() / mazePanel.getWidth();
		int y = e.getY()  * w.getMaze().getHeight()/ mazePanel.getHeight();	
		System.out.println(x+" "+y);	
		w.setPacmanGoal(x, y);
	}
	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

}
