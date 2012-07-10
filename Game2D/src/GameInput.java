import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class GameInput implements KeyListener, MouseListener{

	GameMain game;
	
	public GameInput(GameMain g){
		game = g;
	}
	
	public void edgeDetect()
	{
		if(game.hero.iScreenX<150)
		{
			game.lEdge=true;
		}else
		{
			game.lEdge=false;
		}
		
		if(game.hero.iScreenX>850)
		{
			game.rEdge=true;
		}else
		{
			game.rEdge=false;
		}
		if(game.hero.iScreenY<150)
		{
			game.tEdge=true;
		}else
		{
			game.tEdge=false;
		}
		if(game.hero.iScreenY>600)
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
			
					
			if(game.iArrayPositionX==21) 	//21 is the maximum position in tFrameArray to moves to the right
			{								//[*note this ends up being a little counter intuitive*]
				game.iArrayPositionX=0;
			}else
			{
				game.iArrayPositionX++;
			}
			game.extendMap();
			shiftRight();		//Loads the new right most column at iArrayPositionX-1 replacing the "far left" column
		}
		
		if(game.xEdgeAdjust<-50)
		{
			game.xEdgeAdjust+=50;		//Re-adjusts Draw Function for Frame Shift
			game.FrameX--;				//Shifts From to the Left
			
						
			if(game.iArrayPositionX==0) 	//Moves the opposite of it's right adjust counterpart ^above^
			{
				game.iArrayPositionX=21;
			}else
			{
				game.iArrayPositionX--;
			}
			game.extendMap();
			shiftLeft();			//Loads the new left most column at iArrayPositionX replacing the "far right" column
			
			
		}
		
		if(game.yEdgeAdjust>50)
		{
			game.yEdgeAdjust-=50;		//Re-adjusts Draw Function for Frame Shift
			game.FrameY--;			//Shifts Frame Down
			
					
			if(game.iArrayPositionY==16)
			{
				game.iArrayPositionY=0;
			}else
			{
				game.iArrayPositionY++;
			}
			game.extendMap();
			shiftDown();		//Loads the new bottom most column at iArrayPositionY-1 replacing the "far top" column
			
		}
		
		if(game.yEdgeAdjust<-50)
		{
			
			
			game.yEdgeAdjust+=50;	//Re-adjusts Draw Function for Frame Shift
			game.FrameY++;			//Shifts Frame down
			
						
			if(game.iArrayPositionY==0)
			{
				game.iArrayPositionY=16;
			}else
			{
				game.iArrayPositionY--;
			}
			game.extendMap();
			shiftUp();				//Loads the new top most column at iArrayPositionY replacing the "far bottom" column
		}
		
		
	}
	
	//Reworked and Working
	public void shiftDown()
	{
		for(int a=0;a<21;a++)
		{
			if(game.iArrayPositionY>1)
			{
				game.tArrayFrame[a][game.iArrayPositionY-1]=game.tArrayFrame[a][game.iArrayPositionY-2].tB;
				
			}else if(game.iArrayPositionY==1)
			{
				game.tArrayFrame[a][game.iArrayPositionY-1]=game.tArrayFrame[a][16].tB;
				
			}else if (game.iArrayPositionY==0)
			{
				game.tArrayFrame[a][16]=game.tArrayFrame[a][15].tB;
			}
		}	
	}
	
	//Reworked and Working
	public void shiftUp()
	{
		for(int a=0;a<21;a++)
		{
			if(game.iArrayPositionY<16)
			{
				game.tArrayFrame[a][game.iArrayPositionY]=game.tArrayFrame[a][game.iArrayPositionY+1].tT;
			}else
			{
				game.tArrayFrame[a][game.iArrayPositionY]=game.tArrayFrame[a][0].tT;
			}
		}
	}
	
	//Reworked and Working
	public void shiftLeft()
	{
		for(int a=0;a<17;a++)
		{
			if(game.iArrayPositionX<21)
			{
				game.tArrayFrame[game.iArrayPositionX][a]=game.tArrayFrame[game.iArrayPositionX+1][a].tL;
			}else
			{
				game.tArrayFrame[game.iArrayPositionX][a]=game.tArrayFrame[0][a].tL;
			}
		}
		
	}
	
	//Redone Worked and Working
	public void shiftRight()
	{
		for(int a=0;a<17;a++)
		{
			if(game.iArrayPositionX>1)
			{
				game.tArrayFrame[game.iArrayPositionX-1][a]=game.tArrayFrame[game.iArrayPositionX-2][a].tR;
			}else if(game.iArrayPositionX==1)
			{
				game.tArrayFrame[game.iArrayPositionX-1][a]=game.tArrayFrame[21][a].tR;
				
			}else if(game.iArrayPositionX==0)
			{
				game.tArrayFrame[21][a]=game.tArrayFrame[20][a].tR;
			}
		}
	}
	
	
	
	public void keyPressed(KeyEvent k) {
		
		//game.extendMap();
		
		switch(k.getKeyCode())
		{
		case KeyEvent.VK_LEFT:
					
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
	public void mouseClicked(MouseEvent k) {
		
		//k.getX();
		//k.getY();
		
		/*if(k.getButton()==MouseEvent.BUTTON3) //BUTTON1 = Left and BUTTON3 = Right
		{
			System.out.println("One");
		}*/
		
		System.out.println("Got mouse click " + k);		
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
