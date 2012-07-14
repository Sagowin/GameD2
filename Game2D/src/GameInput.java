import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class GameInput implements KeyListener, MouseListener{

	GameMain game;
	
	int iBuffer=0;
	
	public GameInput(GameMain g){
		game = g;
	}
	
	public void edgeDetect()
	{
		if(game.hero.iScreenX<250)
		{
			game.lEdge=true;
		}else
		{
			game.lEdge=false;
		}
		
		if(game.hero.iScreenX>750)
		{
			game.rEdge=true;
		}else
		{
			game.rEdge=false;
		}
		if(game.hero.iScreenY<250)
		{
			game.tEdge=true;
		}else
		{
			game.tEdge=false;
		}
		if(game.hero.iScreenY>500)
		{
			game.bEdge=true;
		}else
		{
			game.bEdge=false;
		}
		
		
	}
	
	public void correctEdgeAdjust()
	{
		
		if(game.xEdgeAdjust>50)
		{
			
			game.xEdgeAdjust-=50;	//Re-adjusts Draw Function for Frame Shift
			game.FrameX++;			//Shifts From to the Right
			redrawFrame();
		}
		
		if(game.xEdgeAdjust<-50)
		{
			game.xEdgeAdjust+=50;		//Re-adjusts Draw Function for Frame Shift
			game.FrameX--;				//Shifts From to the Left
			redrawFrame();
		}
		
		if(game.yEdgeAdjust>50)
		{
			game.yEdgeAdjust-=50;		//Re-adjusts Draw Function for Frame Shift
			game.FrameY--;				//Shifts Frame Down
			redrawFrame();
					
		}
		
		if(game.yEdgeAdjust<-50)
		{
			
			game.yEdgeAdjust+=50;	//Re-adjusts Draw Function for Frame Shift
			game.FrameY++;			//Shifts Frame down
			

			game.yEdgeAdjust+=50;	//Re-adjusts Draw Function for Frame Shift
			game.FrameY++;			//Shifts Frame down
			redrawFrame();
		}
		
		
	}
			
	//Redraws Frame (brute-force method) Currently in Use to load the Frame that is drawn when the screen moves a tile
	public void redrawFrame()
	{
		for(int a=0;a<game.tArrayFrame.length;a++)
		{
			for(int b=0;b<game.tArrayFrame[0].length;b++)
			{
				game.tArrayFrame[a][b]=game.gameMap.getTile((a-11+game.FrameX) , (b-5+game.FrameY));

			}
		}
	}
	
	public void clickMove(int x, int y)
	{
		if(game.hero.iPosX>x)
		{
			game.hero.iPosX-=game.iSpeed;
			if(game.lEdge)
			{
				game.xEdgeAdjust-=game.iSpeed;
			}
		}
		if(game.hero.iPosX<x)
		{
			game.hero.iPosX+=game.iSpeed;
			if(game.rEdge)
			{
				game.xEdgeAdjust+=game.iSpeed;
			}
		}
		if(game.hero.iPosY>y)
		{
			game.hero.iPosY-=game.iSpeed;
			
			if(game.bEdge)
			{
				game.yEdgeAdjust+=game.iSpeed;
								
			}
		}
		if(game.hero.iPosY<y)
		{
			game.hero.iPosY+=game.iSpeed;
			
			if(game.tEdge)
			{
				game.yEdgeAdjust-=game.iSpeed;
							
			}
		}
		
		game.hero.correctCharacter(game.FrameX,game.FrameY,game.xEdgeAdjust,game.yEdgeAdjust);
		
	}
	
	
	@Override
	public void keyPressed(KeyEvent k) {
		

		switch(k.getKeyCode())
		{
		case KeyEvent.VK_LEFT:
			game.bClickMove=false;
			game.hero.iPosX-=game.iSpeed;
			
			if(game.lEdge)
			{
				game.xEdgeAdjust-=game.iSpeed;
				//game.hero.iPosX+=game.iSpeed;
				
			}
			game.hero.correctCharacter(game.FrameX, game.FrameY, game.xEdgeAdjust,game.yEdgeAdjust);

			correctEdgeAdjust();
			

			edgeDetect();
			
			break;
			
		case KeyEvent.VK_RIGHT:
			game.bClickMove=false;
			game.hero.iPosX+=game.iSpeed;
			if(game.rEdge)
			{
				game.xEdgeAdjust+=game.iSpeed;
				//game.hero.iPosX-=game.iSpeed;
				
			}
			game.hero.correctCharacter(game.FrameX, game.FrameY,game.xEdgeAdjust,game.yEdgeAdjust);
			correctEdgeAdjust();
			edgeDetect();
			
			
			break;
				
		case KeyEvent.VK_UP:
			game.bClickMove=false;
			game.hero.iPosY+=game.iSpeed;
			
			if(game.tEdge)
			{
				game.yEdgeAdjust-=game.iSpeed;
			
				//game.hero.iPosY+=game.iSpeed;
				
			}
			game.hero.correctCharacter(game.FrameX, game.FrameY, game.xEdgeAdjust,game.yEdgeAdjust);
			correctEdgeAdjust();
			edgeDetect();
			
			
			break;
			
		case KeyEvent.VK_DOWN:
			game.bClickMove=false;
			game.hero.iPosY-=game.iSpeed;
			
			if(game.bEdge)
			{
				game.yEdgeAdjust+=game.iSpeed;
				//game.hero.iPosY-=game.iSpeed;
				
			}
			game.hero.correctCharacter(game.FrameX, game.FrameY, game.xEdgeAdjust,game.yEdgeAdjust);
			correctEdgeAdjust();
			edgeDetect();
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
	public void mouseClicked(MouseEvent m) {
		
		
		
		/*if(m.getButton()==MouseEvent.BUTTON1)
		{
			game.iClickX=m.getX()-500+50*game.FrameX+game.xEdgeAdjust;
			game.iClickY=500+50*game.FrameY-game.yEdgeAdjust-m.getY();
			game.bClickMove=true;
		}*/
	
		
		/*if(k.getButton()==MouseEvent.BUTTON3) //BUTTON1 = Left and BUTTON3 = Right
		{
			System.out.println("One");
		}*/
		
	//	System.out.println("Got mouse click " + m);		
	
	}



	
	
	@Override

	public void mouseEntered(MouseEvent arg0) {
		
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		
		
	}
	@Override
	public void mousePressed(MouseEvent k) {
		
		if(k.getButton()==MouseEvent.BUTTON1)
		{
			game.iClickX=k.getX()-500+50*game.FrameX+game.xEdgeAdjust;
			game.iClickY=500+50*game.FrameY-game.yEdgeAdjust-k.getY();
			game.bClickMove=true;
		}
		
		/*if(k.getButton()==MouseEvent.BUTTON3) //BUTTON1 = Left and BUTTON3 = Right
		{
			System.out.println("One");
		}*/
		
		//System.out.println("Got mouse click " + k);
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		
		
	}

	
	
	
	
	
}
