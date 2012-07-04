import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;


public class GameDisplay extends JFrame {
	GameMain game;
	GameInput input;
	
	int iDrawX=0;											//Integer Draw Buffer X
	int iDrawY=0;											//Integer Draw Buffer Y
	int iDrawST=-1;											//Integer Draw Buffer SuperTile Position in "map"
	
	int iFrameXSize=20;										//How many tiles in X Direction Rendered per loop
	int iFramePixelsX= iFrameXSize*50;					//How many pixels in the X Direction of visible map
	int iFrameYSize=15;										//How many tiles in Y Direction Rendered per loop
	int iFramePixelsY= iFrameYSize*50;					//How many pixels in the Y Direction of visible map
	
	BufferedImage tile = null;								//Image Buffer
	
	String sBuffer="Hello World";							//General String Buffer
	
	JPanel globalContainer;
	JPanel mapDisplay;
	JPanel sidebar;
	
	JLabel playerPos;
	JLabel mapPos;
	JLabel curSuperTile;
	
	Image mapDisplayImg; 
	
	public GameDisplay(GameMain g, GameInput in){
		game = g;
		input = in;

		loadResources();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(0,0,1300,750);
		createBufferStrategy(3);

	//	addKeyListener(input);
	//	addMouseListener(input);
		
//		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		globalContainer = new JPanel();
		
		add(globalContainer);
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		globalContainer.setLayout(new BoxLayout(globalContainer, BoxLayout.X_AXIS));
		
		mapDisplay = new JPanel();
		mapDisplay.setPreferredSize(new Dimension(iFramePixelsX, iFramePixelsY));
		mapDisplay.setMaximumSize(new Dimension(iFramePixelsX, iFramePixelsY));
		
		sidebar = new JPanel();
		sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
		
//		JLabel label1 = new JLabel("Hello world");
//		mapDisplay.add(label1);
		
		mapDisplay.setRequestFocusEnabled(true);
		
		
		
		playerPos = new JLabel();
		mapPos = new JLabel();
		curSuperTile = new JLabel();

		sidebar.add(playerPos);
		sidebar.add(mapPos);
		sidebar.add(curSuperTile);
		
/*		JButton button1 = new JButton("Do something");
		sidebar.add(button1);
		button1.setFocusable(false);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("You clicked the button");
                System.out.println("WHY DID YOU CLICK THE BUTTON!");
                System.out.println("WHYYYYYYYYYY!!!!!!!!!!!!");
                
                mapDisplay.grabFocus();
            }
        });*/

		sidebar.setPreferredSize(new Dimension(300, getHeight()));
		sidebar.setMaximumSize(new Dimension(300, getHeight()));
		sidebar.setSize(new Dimension(300, getHeight()));
		
		globalContainer.add(mapDisplay);
		globalContainer.add(sidebar);
		
		mapDisplayImg = mapDisplay.createImage(iFramePixelsX, iFramePixelsY);
		
		addKeyListener(input);
		
		mapDisplay.addKeyListener(input);
		mapDisplay.addMouseListener(input);
		
		

	}
	
	public void drawSidebar(Graphics g){
//		g.setColor(Color.BLACK);
//		g.fillRect(0,  0, 800, 600);
	}
	
	public void loadResources(){
		try{
			tile = ImageIO.read(new File("Resources/Tiles.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void display(){
		drawSidebar(sidebar.getGraphics());
		drawMatrix2(mapDisplayImg.getGraphics());
		mapDisplay.getGraphics().drawImage(mapDisplayImg, 0, 0, null);
	}
	
	public void drawMatrix(){
		drawSidebar(sidebar.getGraphics());
	}
	
	public void drawItems()
	{
		Graphics g = getBufferStrategy().getDrawGraphics();
		g.setFont(new Font("New Courier",Font.BOLD,20));
		sBuffer="Item Page";
		g.drawString(sBuffer, 600,100);
		g.dispose();
		getBufferStrategy().show();
	}

	
	public void drawMatrix2(Graphics g)
	{
		Character hero = game.hero;
		ArrayList<SuperTile> map = game.map;
		
//		Graphics g = getBufferStrategy().getDrawGraphics();
		for(int a=0;a<map.size();a++)
		{
			map.get(a).moveCreatures();
		}
		//System.out.println(map.size());
				
		
		//correctCreatures();
		
		for(int a=-11;a<iFrameXSize-9;a++)
		{
			for(int b=-11;b<iFrameYSize-9;b++)
			{	
				Tile drawTile = new Tile();
				iDrawX=a+game.FrameX;
				iDrawY=b+game.FrameY;
				iDrawST=game.iCurrentSuperTile;
							
				
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
					
					/*if(iDrawX<0||iDrawX>99||iDrawY<0||iDrawY>99)
					{
							System.out.println(iDrawX+" , "+iDrawY + " , " + iDrawST);
					}*/
					
					drawTile = map.get(iDrawST).getTile(iDrawX,iDrawY);
					g.drawImage(tile, (a+10)*50-game.xEdgeAdjust,(b+10)*50-game.yEdgeAdjust,(a+10)*50+50-game.xEdgeAdjust,(b+10)*50+50-game.yEdgeAdjust,drawTile.getX(),drawTile.getY(),drawTile.getX()+49,drawTile.getY()+49, null);
					}
		}
		
		
		
		drawCharacters(g);
		g.clearRect(1000, 0, 300, 750);
		
		playerPos.setText(hero.getTileX()+" , "+hero.getTileY()+" Frame: "+game.FrameX+" , "+game.FrameY);
		

		mapPos.setText(hero.getX()+ " , "+hero.getY()+" ("+hero.getiXinSuperTile()+" , "+hero.getiYinSuperTile()+")");
		curSuperTile.setText(""+game.iCurrentSuperTile+"");
	}
	public void drawCharacters(Graphics g)
	{

		g.drawImage(tile, 1200,300,1249,349,0,50,49,99,null);
		
		game.map.get(game.iCurrentSuperTile).drawCreatures(g, tile, game.FrameX, game.FrameY, iFrameXSize, iFrameYSize, game.xEdgeAdjust, game.yEdgeAdjust);
		drawsideCharacters(g);
		
		g.drawImage(tile, game.hero.getX(), game.hero.getY(), game.hero.getX()+50, game.hero.getY()+50,0,100,50,150, null);
	}
	
	
	//Below is a work in progress
	public void drawsideCharacters(Graphics g)
	{
		//Side by Side and Corners all are drawn
		
		if(game.FrameX<10)
		{
			game.iBuffer=game.map.get(game.iCurrentSuperTile).getiLeft();
			//System.out.println(iBuffer);
			
			if(game.iBuffer>-1)
			{
				game.map.get(game.iBuffer).drawCreatures(g, tile, game.FrameX+100, game.FrameY, iFrameXSize, iFrameYSize, game.xEdgeAdjust, game.yEdgeAdjust);
				
				if(game.FrameY<10)
				{
					game.iBuffer=game.map.get(game.iBuffer).getiTop();
					//System.out.println(iBuffer);
					
					if(game.iBuffer>-1)
					{
						game.map.get(game.iBuffer).drawCreatures(g, tile, game.FrameX+100, game.FrameY+100, iFrameXSize, iFrameYSize, game.xEdgeAdjust, game.yEdgeAdjust);
					}
				}
				if(game.FrameY>89)
				{
					game.iBuffer=game.map.get(game.iBuffer).getiBottom();
					//System.out.println(iBuffer);
					
					if(game.iBuffer>-1)
					{
						game.map.get(game.iBuffer).drawCreatures(g, tile, game.FrameX+100, game.FrameY-100, iFrameXSize, iFrameYSize, game.xEdgeAdjust, game.yEdgeAdjust);
					}
				}
				
			}
		}
		if(game.FrameX>89)
		{
			game.iBuffer=game.map.get(game.iCurrentSuperTile).getiRight();
			//System.out.println(iBuffer);
			
			if(game.iBuffer>-1)
			{
				game.map.get(game.iBuffer).drawCreatures(g, tile, game.FrameX-100, game.FrameY, iFrameXSize, iFrameYSize, game.xEdgeAdjust, game.yEdgeAdjust);
				
				if(game.FrameY<10)
				{
					game.iBuffer=game.map.get(game.iBuffer).getiTop();
					//System.out.println(iBuffer);
					
					if(game.iBuffer>-1)
					{
						game.map.get(game.iBuffer).drawCreatures(g, tile, game.FrameX-100, game.FrameY+100, iFrameXSize, iFrameYSize, game.xEdgeAdjust, game.yEdgeAdjust);
					}
				}
				if(game.FrameY>89)
				{
					game.iBuffer=game.map.get(game.iBuffer).getiBottom();
					//System.out.println(iBuffer);
					
					if(game.iBuffer>-1)
					{
						game.map.get(game.iBuffer).drawCreatures(g, tile, game.FrameX-100, game.FrameY-100, iFrameXSize, iFrameYSize, game.xEdgeAdjust, game.yEdgeAdjust);
					}
				}
			}
		}
		
				
		if(game.FrameY<10)
		{
			game.iBuffer=game.map.get(game.iCurrentSuperTile).getiTop();
			//System.out.println(iBuffer);
			
			if(game.iBuffer>-1)
			{
				game.map.get(game.iBuffer).drawCreatures(g, tile, game.FrameX, game.FrameY+100, iFrameXSize, iFrameYSize, game.xEdgeAdjust, game.yEdgeAdjust);
			}
		}
		if(game.FrameY>89)
		{
			game.iBuffer=game.map.get(game.iCurrentSuperTile).getiBottom();
			//System.out.println(iBuffer);
			
			if(game.iBuffer>-1)
			{
				game.map.get(game.iBuffer).drawCreatures(g, tile, game.FrameX, game.FrameY-100, iFrameXSize, iFrameYSize, game.xEdgeAdjust, game.yEdgeAdjust);
			}
		}
		
		
	}
	
	

	
}
