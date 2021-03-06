package com.robotica.pc.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.robotica.nxt.remotecontrol.Command;
import com.robotica.nxt.remotecontrol.CommandCommunicator;
import com.robotica.nxt.remotecontrol.NXTCommand;
import com.robotica.pc.model.ConnectedEntity;

public class ConnectedEntityPanel extends JPanel implements Observer, ActionListener
{
	private static final long serialVersionUID = -1358499148193705488L;
	private ConnectedEntity entity;
	
	private static final int FONT_SIZE = 20;
	private int count = 0;
	
	private JButton connectButton;
	private JButton speedButton;
	private JButton exitButton;
	private JTextField motorSpeed, turnSpeed;
	private CommandCommunicator commandSender;
	private int fSpeed=250, tSpeed=75;

	public ConnectedEntityPanel(ConnectedEntity entity)
	{
		this.commandSender = new CommandCommunicator(entity.getConnector());
		
		this.entity = entity;
		this.entity.addObserver(this);
		
		connectButton = new JButton("conn");
		connectButton.setLocation(50, 100);
		connectButton.addActionListener(this);
		
		motorSpeed = new JTextField(""+fSpeed);
		motorSpeed.setPreferredSize(new Dimension(40,20));
		turnSpeed = new JTextField(""+tSpeed);
		turnSpeed.setPreferredSize(new Dimension(40,20));
		
		speedButton = new JButton("speed");
		speedButton.setLocation(100, 100);
		speedButton.addActionListener(this);
		

		exitButton = new JButton("exit");
		exitButton.setLocation(150, 100);
		exitButton.addActionListener(this);
		
		this.add(connectButton);
		this.add(motorSpeed);
		this.add(turnSpeed);
		this.add(speedButton);
		this.add(exitButton);
		this.revalidate();
		
		setPreferredSize(new Dimension(300,250));
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, FONT_SIZE));
		
		count = 2;

		drawString(g2d, "devName:"+entity.getDeviceName());
		drawString(g2d, "connected:"+Boolean.toString(entity.isConnected()));
		drawString(g2d, "id:"+entity.getEntity().getID()+" "+entity.getEntity().getType().toString());
		drawString(g2d, "r:"+entity.getEntity().getGuiColor().getRed()+" g:"+entity.getEntity().getGuiColor().getGreen()+" b:"+entity.getEntity().getGuiColor().getBlue());
		drawString(g2d, "r:"+entity.getEntity().getCamColor().getX()+" g:"+entity.getEntity().getCamColor().getY()+" b:"+entity.getEntity().getCamColor().getZ());
		drawString(g2d, "x:"+entity.getEntity().getX()+" y:"+entity.getEntity().getY());
		drawString(g2d, ""+entity.getEntity().getDirection().toString()+" rot:"+entity.getEntity().getRotation());
		drawString(g2d, "forwardSpd:"+fSpeed+" turnSpd:"+tSpeed);
	}
	
	private void drawString(Graphics2D g2d, String s)
	{
		g2d.drawString(s,10,FONT_SIZE*++count);
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().compareTo("conn") == 0)
		{
			entity.connect();
			fSpeed = Integer.valueOf(motorSpeed.getText());
			tSpeed = Integer.valueOf(turnSpeed.getText());
			commandSender.sendCommand(new Command(NXTCommand.SET_MOTORSPEED,fSpeed));
			commandSender.sendCommand(new Command(NXTCommand.SET_TURNSPEED,tSpeed));
		}
		else if(e.getActionCommand().compareTo("exit") == 0)
			commandSender.sendCommand(new Command(NXTCommand.EXIT,0));
		else if(commandSender.isConnected())
		{
			fSpeed = Integer.valueOf(motorSpeed.getText());
			tSpeed = Integer.valueOf(turnSpeed.getText());
			commandSender.sendCommand(new Command(NXTCommand.SET_MOTORSPEED,fSpeed));
			commandSender.sendCommand(new Command(NXTCommand.SET_TURNSPEED,tSpeed));
		}
	}

}
