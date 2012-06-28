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

//NEED TO FIX SCROLLING DEVIATIONS



public class GameMain extends JFrame implements KeyListener, MouseListener{

	Character hero = new Character(500,500);				//Constructs the Central Character
	int FrameX=50;											//Position of the Center Tile in Frame X (hopefully)
	int iFrameXSize=20;										//How many tiles in X Direction Rendered per loop
	int iFramePixelsX= iFrameXSize*50;					//How many pixels in the X Direction of visible map
	int iFrameYSize=15;										//How many tiles in Y Direction Rendered per loop
	int iFramePixelsY= iFrameYSize*50;					//How many pixels in the Y Direction of visible map
	int FrameY=50;											//Position of the Center Tile in Frame Y (hopefully)
	SuperTile origin = new SuperTile(0);					//Constructs the Original SuperTile
	ArrayList<SuperTile> map = new ArrayList<SuperTile>();	//Holds all the SuperTiles
	int iCurrentSuperTile=0;								//The Current Super Tile Inhabited by "hero"
	int iSuperTileCount=1;									//How many SuperTiles are in "map"
	static GameMain m = new GameMain(0,0);					//The Grand Pooba (<-fuck you this is a word)
	boolean rEdge=false;									//true--Close to Right Edge
	boolean lEdge=false;									//true--Close to Left Edge
	boolean tEdge=false;									//true--Close to Top Edge
	boolean bEdge=false;									//true--Close to Bottom Edge
	int xEdgeAdjust=0;										//Side Scrolling Modulator X
	int yEdgeAdjust=0;										//Side Scrolling Modulator Y
	int rawX=0;												//Position from Center X in Pixels
	int rawY=0;												//Position from Center Y in Pixels
	int iSpeed=10;											//"hero" run speed
	BufferedImage tile = null;								//Image Buffer
	int iDrawX=0;											//Integer Draw Buffer X
	int iDrawY=0;											//Integer Draw Buffer Y
	int iDrawST=-1;											//Integer Draw Buffer SuperTile Position in "map"
	int iBuffer=-1; 										//General Integer Buffer
	int iBuffer2=-1;										//Secondary General Integer Buffer
	SuperTile stBuffer = new SuperTile(iSuperTileCount);	 //General SuperTile Buffer
	SuperTile stBufferComp = new SuperTile(iSuperTileCount);	//General SuperTile Comparison Buffer
	int iPatchX=-1;											//Patch X Test
	int iPatchY=-1;											//Patch Y Test
	int iPatchProgressBuffer=0;								//Buffers Patch Progress
	boolean bBuffer=false;									//General Boolean Buffer
	boolean bCheckSuperTileAdd=false;						//If true, will output information about added SuperTiles
	boolean bCheckSuperTilePatch=false;						//If true, will output information about patch progress
	String sBuffer="Hello World";							//General String Buffer
	Random rand = new Random();								//Generates Randomizing Object
	ButtonHolder bhButtons =new ButtonHolder();				//Holds all the buttons
	
	
	
	public static void main(String[] args) {
		//Creature Jim = new Creature(30,50);
		//Jim.printPosition();
		clientInit(m);
		}
	public GameMain(int x, int y)
	{	
		//int i = 9699690;
		//System.out.println(i);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(x,y,1300,750);
		createBufferStrategy(2);
		addKeyListener(this);
		addMouseListener(this);
		map.add(origin);
		
		//System.out.println(rand.nextInt(100));
		
		try{
			tile = ImageIO.read(new File("Resources/Tiles.png"));
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	public static void clientInit(GameMain main)
	{		
		while(true)
		{
			
			main.drawMatrix();		
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	public void drawMatrix()
	{
		Graphics g = getBufferStrategy().getDrawGraphics();
		g.setFont(new Font("New Courier",Font.BOLD,20));
		
		//System.out.println(map.size());
				
		allTileCheckUp();
		
		for(int a=-11;a<iFrameXSize-9;a++)
		{
			for(int b=-11;b<iFrameYSize-9;b++)
			{	
				Tile drawTile = new Tile();
				iDrawX=a+FrameX;
				iDrawY=b+FrameY;
				iDrawST=iCurrentSuperTile;
							
				
					if(iDrawX<0)
					{	
						iDrawX+=100;
						iDrawST=map.get(iDrawST).getiLeft();
					}
				
						if(iDrawX>99)
						{
							iDrawX-=100;
							iDrawST=map.get(iDrawST).getiRight();
						}
				
						if(iDrawY<0)
						{
							iDrawY+=100;
							iDrawST=map.get(iDrawST).getiTop();
						}
					if(iDrawY>99)
					{
						iDrawY-=100;
						iDrawST=map.get(iDrawST).getiBottom();
					}
					
					if(iDrawX<0||iDrawX>99||iDrawY<0||iDrawY>99)
					{
							System.out.println(iDrawX+" , "+iDrawY + " , " + iDrawST);
					}
					
					drawTile = map.get(iDrawST).getTile(iDrawX,iDrawY);
					g.drawImage(tile, (a+10)*50-xEdgeAdjust,(b+10)*50-yEdgeAdjust,(a+10)*50+50-xEdgeAdjust,(b+10)*50+50-yEdgeAdjust,drawTile.getX(),drawTile.getY(),drawTile.getX()+49,drawTile.getY()+49, null);
					}
		}
		
		g.clearRect(1000, 0, 300, 750);
		
		g.drawString(sBuffer, iFramePixelsX,50);
		
		bhButtons.drawButtons(g);
		
		doEvent();
		drawCharacters(g);
		
		
		g.dispose();
		getBufferStrategy().show();
	}
	public void drawCharacters(Graphics g)
	{
		g.drawImage(tile, hero.getX(),hero.getY(),hero.getX()+50,hero.getY()+50,50,0,100,50, null);
		g.drawImage(tile, 1200,300,1249,349,0,50,49,99,null);
		map.get(iCurrentSuperTile).drawCreatures(g, tile, FrameX, FrameY, iFrameXSize, iFrameYSize, xEdgeAdjust, yEdgeAdjust);//needs EdgeAdjusts as well
	}
	public void doEvent()
	{
		iBuffer=rand.nextInt(100);
		
		if(iBuffer%10==0)
		{	
			iBuffer=rand.nextInt(99);
			iBuffer2=rand.nextInt(99);
			map.get(iCurrentSuperTile).addCreature(iBuffer, iBuffer2,1);
		}
	}
	public void allTileCheckUp()
	{
		leftTileCheckUp();
		rightTileCheckUp();
		upTileCheckUp();
		downTileCheckUp();
		upleftTileCheckUp();
		uprightTileCheckUp();
		downleftTileCheckUp();
		downrightTileCheckUp();
				
	}
	public void leftTileCheckUp()
	{
		if(FrameX<20&&map.get(iCurrentSuperTile).getiLeft()<0)
		{	
			patchWork();
			stBuffer= new SuperTile(iSuperTileCount);
			stBuffer.setiRight(iCurrentSuperTile);
			stBuffer.setiX(map.get(iCurrentSuperTile).getiX()-1);
			stBuffer.setiY(map.get(iCurrentSuperTile).getiY());
			map.get(iCurrentSuperTile).setiLeft(iSuperTileCount);
			map.add(stBuffer);
			iSuperTileCount++;
			//System.out.println("A Super Tile to the West has been made");
			patchWork();
			checkSuperGridMatrix();
			
		}
		
	}
	public void rightTileCheckUp()
	{
		if(FrameX>80&&map.get(iCurrentSuperTile).getiRight()<0)
		{
			patchWork();
			stBuffer = new SuperTile(iSuperTileCount);
			stBuffer.setiLeft(iCurrentSuperTile);
			stBuffer.setiX(map.get(iCurrentSuperTile).getiX()+1);
			stBuffer.setiY(map.get(iCurrentSuperTile).getiY());
			map.get(iCurrentSuperTile).setiRight(iSuperTileCount);
			map.add(stBuffer);
			iSuperTileCount++;
			//System.out.println("A Super Tile to the East has been made");
			patchWork();
			checkSuperGridMatrix();
			
		}
		
	}
	public void upTileCheckUp()
	{
		if(FrameY<20&&map.get(iCurrentSuperTile).getiTop()<0)
		{
			patchWork();
			stBuffer =new SuperTile(iSuperTileCount);
			stBuffer.setiBottom(iCurrentSuperTile);
			stBuffer.setiX(map.get(iCurrentSuperTile).getiX());
			stBuffer.setiY(map.get(iCurrentSuperTile).getiY()+1);
			map.get(iCurrentSuperTile).setiTop(iSuperTileCount);
			map.add(stBuffer);
			iSuperTileCount++;
			//System.out.println("A Super Tile to the North has been made");
			patchWork();
			checkSuperGridMatrix();
			
		}
		
	}
	public void downTileCheckUp()
	{
		if(FrameY>80&&map.get(iCurrentSuperTile).getiBottom()<0)
		{
			patchWork();
			stBuffer = new SuperTile(iSuperTileCount);
			stBuffer.setiTop(iCurrentSuperTile);
			stBuffer.setiX(map.get(iCurrentSuperTile).getiX());
			stBuffer.setiY(map.get(iCurrentSuperTile).getiY()-1);
			map.get(iCurrentSuperTile).setiBottom(iSuperTileCount);
			map.add(stBuffer);
			iSuperTileCount++;
			//System.out.println("A Super Tile to the South has been made");
			patchWork();
			checkSuperGridMatrix();
			
		}
		
	}
	public void upleftTileCheckUp()
	{
		if(FrameX<20&&FrameY<20&&map.get(iCurrentSuperTile).getiTLCorner()<0)
		{
			patchWork();
			stBuffer = new SuperTile(iSuperTileCount);
			stBuffer.setiBRCorner(iCurrentSuperTile);
			stBuffer.setiX(map.get(iCurrentSuperTile).getiX()-1);
			stBuffer.setiY(map.get(iCurrentSuperTile).getiY()+1);
			map.get(iCurrentSuperTile).setiTLCorner(iSuperTileCount);
			map.add(stBuffer);
			iSuperTileCount++;
			//System.out.println("A Super Tile to the North-West has been made");
			patchWork();
			checkSuperGridMatrix();
			
		}
	}
	public void uprightTileCheckUp()
	{
		if(FrameX>80&&FrameY<20&&map.get(iCurrentSuperTile).getiTRCorner()<0)
		{
			patchWork();
			stBuffer = new SuperTile(iSuperTileCount);
			stBuffer.setiBLCorner(iCurrentSuperTile);
			stBuffer.setiX(map.get(iCurrentSuperTile).getiX()+1);
			stBuffer.setiY(map.get(iCurrentSuperTile).getiY()+1);
			map.get(iCurrentSuperTile).setiTRCorner(iSuperTileCount);
			map.add(stBuffer);
			iSuperTileCount++;
			//System.out.println("A Super Tile to the North-East has been made");
			patchWork();
			checkSuperGridMatrix();
			
		}
	}
	public void downleftTileCheckUp()
	{
		if(FrameX<20&&FrameY>80&&map.get(iCurrentSuperTile).getiBLCorner()<0)
		{
			patchWork();
			stBuffer = new SuperTile(iSuperTileCount);
			stBuffer.setiTRCorner(iCurrentSuperTile);
			stBuffer.setiX(map.get(iCurrentSuperTile).getiX()-1);
			stBuffer.setiY(map.get(iCurrentSuperTile).getiY()-1);
			map.get(iCurrentSuperTile).setiBLCorner(iSuperTileCount);
			map.add(stBuffer);
			iSuperTileCount++;
			//System.out.println("A Super Tile to the South-West has been made");
			patchWork();
			checkSuperGridMatrix();
			
		}
	}
	public void downrightTileCheckUp()
	{
		if(FrameX>80&&FrameY>80&&map.get(iCurrentSuperTile).getiBRCorner()<0)
		{
			patchWork();
			stBuffer = new SuperTile(iSuperTileCount);
			stBuffer.setiTLCorner(iCurrentSuperTile);
			stBuffer.setiX(map.get(iCurrentSuperTile).getiX()+1);
			stBuffer.setiY(map.get(iCurrentSuperTile).getiY()-1);
			map.get(iCurrentSuperTile).setiBRCorner(iSuperTileCount);
			map.add(stBuffer);
			iSuperTileCount++;
			//System.out.println("A Super Tile to the South-East has been made");
			patchWork();
			checkSuperGridMatrix();
			
		}
	}
	public void patchWork()
	{
		System.out.println("Patch Work Initiated");
		int iSTX=0;
		int iSTY=0;
		
		for(int a=0;a<iSuperTileCount;a++)
		{
			stBuffer= map.get(a);
			if(!(stBuffer.isbPatched())) 	//If Tile Patch is Not Complete, Checks to See if it Is
			{
				stBuffer.checkPatched(); 	//Checks to see if SuperTile is Fully Patched Together
			}
			if(stBuffer.isbPatched())		//If Tile Patch is Complete, goes to next SuperTile to be patched
			{
				a++;
			}
			
			for(int b=0;b<iSuperTileCount;b++)
			{
				
				
				
				if(!(a==b)) //Makes Sure Not to Patch To Self
				{
					
					stBufferComp= map.get(b);
					iSTX=stBuffer.getiX()-stBufferComp.getiX();
					iSTY=stBuffer.getiY()-stBufferComp.getiY();
					//System.out.println(a+ " Being Compared to "+b);
					
					
					
					
					
					if(!(stBuffer.isbPatched()))	//If Tile Patch is Not Complete, Checks to See if the SuperGrid Being Compared is Patchable
					{
					switch(iSTX)
					{
						case 1://Comparison Tile is to the Left
						{
							
							
							switch(iSTY)
							{
								case 1: //Comparison Tile is Below
								{
									if(stBuffer.getiBLCorner()<0)
									{
										map.get(a).setiBLCorner(b);
										System.out.println("SuperTile "+a+" has patched SuperTile "+b+" to the South-West");
									}
									break;
								}
								case 0://Comparison Tile had the same Y-Coordinate
								{
									if(stBuffer.getiLeft()<0)
									{
										map.get(a).setiLeft(b);
										System.out.println("SuperTile "+a+" has patched SuperTile "+b+" to the West");
									}
									break;
								}
								case -1://Comparison Tile is Above
								{
									if(stBuffer.getiTLCorner()<0)
									{
										map.get(a).setiTLCorner(b);
										System.out.println("SuperTile "+a+" has patched SuperTile "+b+" to the North-West");
									}
									break;
								}
							}
							
							
							
							
							break;
						}
					
						case 0://Comparison Tile has same X-Coordinate
						{
							
							
							
							switch(iSTY)
							{
								case 1: //Comparison Tile is Below
								{
									if(stBuffer.getiBottom()<0)
									{
										map.get(a).setiBottom(b);
										System.out.println("SuperTile "+a+" has patched SuperTile "+b+" to the South");
									}
									break;
								}
								case -1://Comparison Tile is Above
								{
									if(stBuffer.getiTop()<0)
									{
										map.get(a).setiTop(b);
										System.out.println("SuperTile "+a+" has patched SuperTile "+b+" to the North");
									}
									break;
								}
							}
							
							
							
							break;
						}
						case -1://Comparison Tile is to the Right
						{
							
							
							
							switch(iSTY)
							{
								case 1: //Comparison Tile is Below
								{
									if(stBuffer.getiBRCorner()<0)
									{
										map.get(a).setiBRCorner(b);
										System.out.println("SuperTile "+a+" has patched SuperTile "+b+" to the South-East");
									}
									break;
								}
								case 0://Comparison Tile had the same Y-Coordinate
								{
									if(stBuffer.getiRight()<0)
									{
										map.get(a).setiRight(b);
										System.out.println("SuperTile "+a+" has patched SuperTile "+b+" to the East");
									}
									break;
								}
								case -1://Comparison Tile is Above
								{
									if(stBuffer.getiTRCorner()<0)
									{
										System.out.println("SuperTile "+a+" has patched SuperTile "+b+" to the North-East");
										map.get(a).setiTRCorner(b);
									}
									break;
								}
							}
							
							
							
							break;
						}
					}//end major switch
				
				}
				
				
				}
				
			}
			
		}
	}
	public void checkSuperGridMatrix()
	{
		if(bCheckSuperTileAdd)
			{
			System.out.println("Current SuperTile Coordinates: "+map.get(iCurrentSuperTile).getiX()+" , "+map.get(iCurrentSuperTile).getiY());
			iBuffer=map.get(iSuperTileCount-1).getiIdentity();
			System.out.println("Just Added SuperTile Coordinates: "+map.get(iBuffer).getiX()+" , "+map.get(iBuffer).getiY());
			}
		if(bCheckSuperTilePatch)
		{
			for(int a= 0;a<iSuperTileCount;a++)
				{
					System.out.println("["+a+"]---"+map.get(a).getiIdentity());
				}
		}
	}
	//PATCHWORK MECH:
				//CHECK ONLY WHEN NEW SUPERTILE IS ADDED
				//HAVE A WAY TO EXCLUDE FULLY PATCHED TILES (SURROUNDED ON 8 SIDES)
				//COMPARES ITSELF WITH NEARBY TILES TO SEE ITS RELATION TO THEIR CONNECTED TILES
	
	@Override
	
	public void keyPressed(KeyEvent k) {
		
		//System.out.println(k);
		//System.out.println("In Tile/Adjust X: "+ hero.getinTilePosX()+ " / "+xEdgeAdjust+"In Tile/Adjust Y: "+hero.getinTilePosY()+ " / "+yEdgeAdjust);
		//System.out.println("Hero Tile/Frame Tile: " + hero.getTileX()+ "/ "+ FrameX+ "Hero TIle/Frame Tile: "+ hero.getTileY()+ " / "+ FrameY);
		//rawX=(hero.getTileX()-FrameX)*50+hero.getinTilePosX()-xEdgeAdjust/2;
		//rawY=(hero.getTileY()-FrameY)*50+hero.getinTilePosY()+yEdgeAdjust/2;
		//System.out.println("Raw Position: "+ rawX+ " , "+rawY);
		
		switch(k.getKeyCode())
		{
		case KeyEvent.VK_LEFT:
			
						
			rEdge=false;
			
			
			hero.dPosX(-iSpeed);				//Moves Hero left by "iSpeed" in the tile
			
			if(hero.getinTilePosX()<0)
			{
				hero.subTileX();
				hero.dPosX(50);
			}
			
			if(hero.getX()<150)
			{
				lEdge=true;						//sets lEdge to true if "hero" is close enough to edge
			}
			
			if(!lEdge)
			{
				hero.setX(hero.getX()-iSpeed);  //Moves "hero" if not near Edge
			}
			
			if(lEdge)
			{
				xEdgeAdjust-=iSpeed;			//Scrolls Matrix
			}
			
			if(xEdgeAdjust<-50)
			{
				xEdgeAdjust+=50;				//Resets Edge Adjust Once Reaching Next Time
				
				if(FrameX>0)
				{
					FrameX--;					//Moves Frame to the Left within SuperTile
				}
				if(FrameX==0)
				{
					iCurrentSuperTile=map.get(iCurrentSuperTile).getiLeft();		
					FrameX+=100;				//Moves Frame to the Rightmost Tile of the SuperTile to the Left ST	
					hero.addTileX(99);			//Moves "hero" to the Rightmost Tile of the SuperTile to the Left ST
				}
			}
						
			break;
		case KeyEvent.VK_RIGHT:
			
			
			lEdge=false;
			
			hero.dPosX(iSpeed);
			if(hero.getinTilePosX()>50)
			{
				hero.addTileX();
				hero.dPosX(-50);
			}
			
			if(hero.getX()>(iFramePixelsX-150))
			{
				rEdge=true;
			}
			
			
			
			
			
			if(!rEdge)
			{
			hero.setX(hero.getX()+iSpeed);
			}
			
			if(rEdge)
			{
				xEdgeAdjust+=iSpeed;
			}
			
			if(xEdgeAdjust>50)
			{
				xEdgeAdjust-=50;
				if(FrameX<99)
				{
					FrameX++;
				}
				if(FrameX==99)
				{
					iCurrentSuperTile=map.get(iCurrentSuperTile).getiRight();
					FrameX-=99;
					hero.subTileX(99);
				}
				
			}
			
			
			break;
		case KeyEvent.VK_UP:
			
			hero.dPosY(-iSpeed);
			if(hero.getinTilePosY()<0)
			{
				hero.subTileY();
				hero.dPosY(50);
			}
			
			if(hero.getY()<150)
			{
				tEdge=true;
			}
			
			bEdge=false;
			if(!tEdge)
			{
			hero.setY(hero.getY()-iSpeed);
			}
			if(tEdge)
			{
				yEdgeAdjust-=iSpeed;
			}
			if(yEdgeAdjust<-50)
			{
				yEdgeAdjust+=50;
				if(FrameY>0)
				{
					FrameY--;
				}
				
				if(FrameY==0)
				{
					iCurrentSuperTile=map.get(iCurrentSuperTile).getiTop();
					FrameY+=100;
					hero.addTileY(99);
				}
				
			}
				
			
			break;
		case KeyEvent.VK_DOWN:
			
			hero.dPosY(iSpeed);
			if(hero.getinTilePosY()>50)
			{
				hero.addTileY();
				hero.dPosY(-50);
			}
			
			if(hero.getY()>(iFramePixelsY-150))
			{
				bEdge=true;
			}
			
			tEdge=false;
			if(!bEdge)
			{
			hero.setY(hero.getY()+iSpeed);
			}
			if(bEdge)
			{
				yEdgeAdjust+=iSpeed;
			}
			
			if(yEdgeAdjust>50)
			{
				yEdgeAdjust-=50;
				if(FrameY<99)
				{
					FrameY++;
				}
				if(FrameY==99)
				{
					iCurrentSuperTile=map.get(iCurrentSuperTile).getiBottom();
					FrameY-=99;
					hero.subTileY(99);
				}
			}
			
			
			break;
		
		}
		
				
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
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
