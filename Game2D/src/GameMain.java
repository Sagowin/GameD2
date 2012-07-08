

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class GameMain extends JFrame implements KeyListener, MouseListener{

	Character hero = new Character(500,500);				//Constructs the Central Character
	int FrameX=0;											//Position of the Center Tile in Frame X (hopefully)
	int FrameY=0;											//Position of the Center Tile in Frame Y (hopefully)
	static GameMain m = new GameMain(0,0);					//The Grand Pooba (<-fuck you this is a word)
	
	boolean rEdge=false;									//true--Close to Right Edge
	boolean lEdge=false;									//true--Close to Left Edge
	boolean tEdge=false;									//true--Close to Top Edge
	boolean bEdge=false;									//true--Close to Bottom Edge
	
	int xEdgeAdjust=0;										//Side Scrolling Modulator X
	int yEdgeAdjust=0;										//Side Scrolling Modulator Y
	
	int rawX=0;												//Position from Center X in Pixels
	int rawY=0;												//Position from Center Y in Pixels
	
	int iArrayPositionX=0;
	int iArrayPositionY=0;
	
	Map gameMap = new Map();
	
	Tile[][] tArrayFrame= new Tile[22][17];
	
	int iSpeed=10;											//"hero" run speed

	int iBuffer=-1; 										//General Integer Buffer
	int iBuffer2=-1;										//Secondary General Integer Buffe
	boolean bBuffer=false;									//General Boolean Buffer
	
	Random rand = new Random();								//Generates Randomizing Object
	
	
	int iSwitch=0;											//Controls the switchboard 0=draw matrix
	
	GameInput input;
	GameDisplay display;
	
	int iTileCount=0;
	
	
	
	public static void main(String[] args) {
		
		clientInit(m);
	}
	public GameMain(int x, int y)
	{	
		input = new GameInput(this);
		display = new GameDisplay(this, input);
		
	}
	public static void clientInit(GameMain main)
	{		
		main.createOrigin();
		while(true)
		{
				main.doEvent();
				main.display.display();
				
				
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	
	
	
	//This is a work in progress as well
	
	
	
	
	
	public void createOrigin()
	{
		
		for(int x=-20;x<=20;x++)
		{
			for(int y=-20;y<=20;y++)
			{
				gameMap.addTile(x, y);
				iTileCount++;
				
			}
		}
		
		System.out.println(iTileCount);
		
		for(int a=0;a<tArrayFrame.length;a++)
		{
			for(int b=0;b<tArrayFrame[0].length;b++)
			{
				tArrayFrame[a][b]=new Tile();
			}
		}
		
		for(int a=0;a<tArrayFrame.length;a++)
		{
			for(int b=0;b<tArrayFrame[0].length;b++)
			{
				tArrayFrame[a][b]=gameMap.getTile((a-10) , (b-10));
			}
		}
		
		
		
	}
	
	public void extendMap()
	{
		for(int a=-20;a<20;a++)
		{
			for(int b=-20;b<20;b++)
			{
				if(gameMap.getTile((FrameX+a),(FrameY+b)).bEmpty)
						{
							gameMap.addTile((FrameX+a),(FrameY+b));
						}
			}
		}
	}
	
	public void doEvent()
	{
		
	}
	
	
	@Override
	
	public void keyPressed(KeyEvent k) {
		
		

		
		
		
				
	}
	@Override
	public void keyReleased(KeyEvent k) {
		switch(k.getKeyChar())
		{
		case 'i':
			if(iSwitch==1)
			{
				iSwitch=0;
			}else
			{
				iSwitch=1;
			}
			break;
		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
			
	}
	@Override
	public void mouseClicked(MouseEvent k) {
		
		//k.getX();
		//k.getY();
		
		/*if(k.getButton()==MouseEvent.BUTTON3) //BUTTON1 = Left and BUTTON3 = Right
		{
			System.out.println("One");
		}*/
		
		
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		
		
	}

}