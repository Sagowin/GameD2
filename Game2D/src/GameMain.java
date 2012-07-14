

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


	

	Character hero = new Character(0,0);				//Constructs the Central Character

	int FrameX=0;											//Position of the Center Tile in Frame X (hopefully)
	int FrameY=0;											//Position of the Center Tile in Frame Y (hopefully)
	
	static GameMain m = new GameMain(0,0);					//The Grand Pooba (<-fuck you this is a word)
	
	boolean rEdge=false;									//true--Close to Right Edge
	boolean lEdge=false;									//true--Close to Left Edge
	boolean tEdge=false;									//true--Close to Top Edge
	boolean bEdge=false;									//true--Close to Bottom Edge
	
	boolean bClickMove=false;
	int iClickX=0;
	int iClickY=0;
	
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

	int iBuffer2=-1;										//Secondary General Integer Buffer

	boolean bBuffer=false;									//General Boolean Buffer
	
	Random rand = new Random();								//Generates Randomizing Object
	
	
	


	int iSwitch=1;											//Controls the switchboard 0=start menu 1=drawmatrix

	
	GameInput input;
	GameDisplay display;

	
	int iTileCount=0;
	
	boolean bOrigin=false;
	
	
	
	
	
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
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}



	
	public void createOrigin()
	{
		
		//gameMap.makeOrigin();
		
		
			
		//Blanks the Rendered Frame
		for(int a=0;a<tArrayFrame.length;a++)
		{
			for(int b=0;b<tArrayFrame[0].length;b++)
			{
				tArrayFrame[a][b]=new Tile();
			}
		}
		
		//Loads the Rendered Frame
		for(int a=0;a<tArrayFrame.length;a++)
		{
			for(int b=0;b<tArrayFrame[0].length;b++)
			{
				tArrayFrame[a][b]=gameMap.getTile((a-11) , (b-5));
			}
		}
		
		
		
	}
	

	public void doEvent()
	{
		if(!bOrigin)
		{
			createOrigin();
			bOrigin=true;
		}
		if(bClickMove)
		{	
			input.edgeDetect();
			input.correctEdgeAdjust();
			input.clickMove(iClickX, iClickY);
			
		}
		
		gameMap.generateCreatures();
		gameMap.moveCreatures();
		//gameMap.compareCreatures();
		gameMap.seeCreatures();
		
	}

	
	@Override
	
	public void keyPressed(KeyEvent k) {
		
		

		
		
		
				
	}
	@Override
	public void keyReleased(KeyEvent k) {
		/*switch(k.getKeyChar())
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
		}*/
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
			
	}
	public void mouseClicked(MouseEvent k) {		
		
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