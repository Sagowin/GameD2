import java.awt.Color;
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
	
	int iFrameXSize=20;										//How many tiles in X Direction Rendered per loop
	int iFramePixelsX= iFrameXSize*50;					//How many pixels in the X Direction of visible map
	int iFrameYSize=15;										//How many tiles in Y Direction Rendered per loop
	int iFramePixelsY= iFrameYSize*50;					//How many pixels in the Y Direction of visible map
	
	
	int iDrawBufferX=0;
	int iDrawBufferY=0;
	int iSpeedBuffer=0;

	Color cBuffer= new Color(0,0,0);


	
	
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
		createBufferStrategy(2);

	
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
		
		/*JButton button1 = new JButton("Do something");
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
		addMouseListener(input);
		
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

		drawMatrix2();

		
		
		switch(game.iSwitch)
		{
			case 0:
				startMenu();
				break;
			case 1:
				drawMatrix2();
				break;
		}
		
		

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
	

	
	
	public void startMenu()
	{
		Graphics g = getBufferStrategy().getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0,0,1300,750);
		
		g.setFont(new Font("New Courier",Font.BOLD,20));
		g.setColor(new Color(25,150,75));
		sBuffer= "Start Menu ";
		g.drawString(sBuffer, 500,150);
		g.dispose();
		getBufferStrategy().show();
	}

	
	public void drawMatrix2()
	{
		Graphics g = getBufferStrategy().getDrawGraphics();
		g.setFont(new Font("New Courier",Font.BOLD,15));
		Character hero = game.hero;
		
				
		//iDrawBufferX=game.iArrayPositionX;
		//iDrawBufferY=game.iArrayPositionY;

		
		for(int a=0;a<22;a++)
		{
			for(int b=0;b<17;b++)
			{
				
				
				
				g.drawImage(tile,	(a-1)*50-game.xEdgeAdjust,
									(b-1)*50-game.yEdgeAdjust,
									a*50-game.xEdgeAdjust,
									b*50-game.yEdgeAdjust,
									game.tArrayFrame[a][16-b].iPixelX, 
									game.tArrayFrame[a][16-b].iPixelY,
									game.tArrayFrame[a][16-b].iPixelX+49, 
									game.tArrayFrame[a][16-b].iPixelY+49, null);
				
				sBuffer=game.tArrayFrame[a][16-b].iX+" , "+game.tArrayFrame[a][16-b].iY;
				g.drawString(sBuffer, (a-1)*50-game.xEdgeAdjust,(b-1)*50-game.yEdgeAdjust+25);
				
			}
			
			
		}
		
						
		g.drawImage(tile, game.hero.iScreenX,game.hero.iScreenY , game.hero.iScreenX+50, game.hero.iScreenY+50, 0,100,49,149,null);
		
		//Draw Creatures
		for(int c=0;c<game.gameMap.cAL.size();c++)
		{
				if(		game.gameMap.cAL.get(c).bAlive&&
						500+game.gameMap.cAL.get(c).iPosX-50*game.FrameX>0&&
						500+game.gameMap.cAL.get(c).iPosX-50*game.FrameX<1050&&
						500-game.gameMap.cAL.get(c).iPosY+50*game.FrameY>0&&
						500-game.gameMap.cAL.get(c).iPosY+50*game.FrameY<800
						)
				{
					g.drawImage(tile, 	500+game.gameMap.cAL.get(c).iPosX-50*game.FrameX-game.xEdgeAdjust,
										500-game.gameMap.cAL.get(c).iPosY+50*game.FrameY-game.yEdgeAdjust,
										550+game.gameMap.cAL.get(c).iPosX-50*game.FrameX-game.xEdgeAdjust,
										550-game.gameMap.cAL.get(c).iPosY+50*game.FrameY-game.yEdgeAdjust,
										game.gameMap.cAL.get(c).iPixelX,
										game.gameMap.cAL.get(c).iPixelY,
										game.gameMap.cAL.get(c).iPixelX+49,
										game.gameMap.cAL.get(c).iPixelY+49,null);
				}
			
			

		}
		//End Draw Creatures
		
		g.clearRect(1000, 0, 300, 750);
		

						
		g.drawImage(tile, game.hero.iScreenX,game.hero.iScreenY , game.hero.iScreenX+50, game.hero.iScreenY+50, 0,100,49,149,null);
		
		
		
		g.clearRect(1000, 0, 300, 750);

		sBuffer="Position: "+game.hero.iXTile+" , "+game.hero.iYTile;
		g.drawString(sBuffer, 1100,100);
		sBuffer="Frame: "+game.FrameX+" , "+game.FrameY;
		g.drawString(sBuffer, 1100,200);
		sBuffer="ArrayAdjust: "+game.iArrayPositionX+" , "+game.iArrayPositionY;
		g.drawString(sBuffer, 1100,300);
		
		
			

		//playerPos.setText("("+hero.iXTile+" , "+hero.iYTile+")");
		

		//mapPos.setText("");
		
		g.dispose();
		getBufferStrategy().show();
		
	}
	
	
}
	
	
