import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class GameInput implements KeyListener, MouseListener{

	GameMain game;
	
	public GameInput(GameMain g){
		game = g;
	}
	
	public void keyPressed(KeyEvent k) {
		
		if(!game.bMoveTest)
		{
			moveWorking(k); //Does the bugged out movement
		}
		else
		{
			testMove(k);	//Does new code
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
	
	
	
	public void moveWorking(KeyEvent k)
	{
		switch(k.getKeyCode())
		{
		case KeyEvent.VK_LEFT:
			
			
			game.rEdge=false;
			
			
			game.hero.dPosX(-game.iSpeed);				//Moves Hero left by "iSpeed" in the tile
			
			if(game.hero.getinTilePosX()<0)
			{
				game.hero.subTileX();
				game.hero.dPosX(50);
			}
			
			if(game.hero.getX()<150)
			{
				game.lEdge=true;						//sets lEdge to true if "hero" is close enough to edge
			}
			
			if(!game.lEdge)
			{
				game.hero.setX(game.hero.getX()-game.iSpeed);  //Moves "hero" if not near Edge
			}
			
			if(game.lEdge)
			{
				game.xEdgeAdjust-=game.iSpeed;			//Scrolls Matrix
			}
			
			if(game.xEdgeAdjust<-50)
			{
				game.xEdgeAdjust+=50;				//Resets Edge Adjust Once Reaching Next Time
				
				if(game.FrameX>0)
				{
					game.FrameX--;					//Moves Frame to the Left within SuperTile
				}
				if(game.FrameX==0)
				{
					game.iCurrentSuperTile=game.map.get(game.iCurrentSuperTile).getiLeft();		
					game.FrameX+=100;				//Moves Frame to the Rightmost Tile of the SuperTile to the Left ST	
					game.hero.addTileX(99);			//Moves "hero" to the Rightmost Tile of the SuperTile to the Left ST
				}
			}
						
			break;
		case KeyEvent.VK_RIGHT:
			
			
			game.lEdge=false;
			
			game.hero.dPosX(game.iSpeed);
			if(game.hero.getinTilePosX()>50)
			{
				game.hero.addTileX();
				game.hero.dPosX(-50);
			}
			
			if(game.hero.getX()>(game.display.iFramePixelsX-150))
			{
				game.rEdge=true;
			}
			
			
			
			
			
			if(!game.rEdge)
			{
				game.hero.setX(game.hero.getX()+game.iSpeed);
			}
			
			if(game.rEdge)
			{
				game.xEdgeAdjust+=game.iSpeed;
			}
			
			if(game.xEdgeAdjust>50)
			{
				game.xEdgeAdjust-=50;
				if(game.FrameX<99)
				{
					game.FrameX++;
				}
				if(game.FrameX==99)
				{
					game.iCurrentSuperTile=game.map.get(game.iCurrentSuperTile).getiRight();
					game.FrameX-=99;
					game.hero.subTileX(99);
				}
				
			}
			
			
			break;
		case KeyEvent.VK_UP:
			
			game.hero.dPosY(-game.iSpeed);
			if(game.hero.getinTilePosY()<0)
			{
				game.hero.subTileY();
				game.hero.dPosY(50);
			}
			
			if(game.hero.getY()<150)
			{
				game.tEdge=true;
			}
			
			game.bEdge=false;
			if(!game.tEdge)
			{
				game.hero.setY(game.hero.getY()-game.iSpeed);
			}
			if(game.tEdge)
			{
				game.yEdgeAdjust-=game.iSpeed;
			}
			if(game.yEdgeAdjust<-50)
			{
				game.yEdgeAdjust+=50;
				if(game.FrameY>0)
				{
					game.FrameY--;
				}
				
				if(game.FrameY==0)
				{
					game.iCurrentSuperTile=game.map.get(game.iCurrentSuperTile).getiTop();
					game.FrameY+=100;
					game.hero.addTileY(99);
				}
				
			}
				
			
			break;
		case KeyEvent.VK_DOWN:
			
			game.hero.dPosY(game.iSpeed);
			if(game.hero.getinTilePosY()>50)
			{
				game.hero.addTileY();
				game.hero.dPosY(-50);
			}
			
			if(game.hero.getY()>(game.display.iFramePixelsY-150))
			{
				game.bEdge=true;
			}
			
			game.tEdge=false;
			if(!game.bEdge)
			{
				game.hero.setY(game.hero.getY()+game.iSpeed);
			}
			if(game.bEdge)
			{
				game.yEdgeAdjust+=game.iSpeed;
			}
			
			if(game.yEdgeAdjust>50)
			{
				game.yEdgeAdjust-=50;
				if(game.FrameY<99)
				{
					game.FrameY++;
				}
				if(game.FrameY==99)
				{
					game.iCurrentSuperTile=game.map.get(game.iCurrentSuperTile).getiBottom();
					game.FrameY-=99;
					game.hero.subTileY(99);
				}
			}
			
			
			break;
		
		}
	}
	
	//Need to add switching to new SuperTiles and reseting "hero" frames
	
	public void testMove(KeyEvent k)
	{
		switch(k.getKeyCode())
		{
		case KeyEvent.VK_LEFT:		
			game.hero.addiXinSuperTile(-game.iSpeed);
			game.edgeDetect();
			if(game.lEdge)
			{	
				//hero.addiXinSuperTile(iSpeed);
				game.xEdgeAdjust-=game.iSpeed;
			}
			game.correctFrame();
			break;
		case KeyEvent.VK_RIGHT:
			game.hero.addiXinSuperTile(game.iSpeed);
			game.edgeDetect();
			if(game.rEdge)
			{
				//hero.addiXinSuperTile(-iSpeed);
				game.xEdgeAdjust+=game.iSpeed;
			}
			game.correctFrame();
			break;
		case KeyEvent.VK_UP:
			game.hero.addiYinSuperTile(-game.iSpeed);
			game.edgeDetect();
			if(game.tEdge)
			{
				//hero.addiYinSuperTile(iSpeed);
				game.yEdgeAdjust-=game.iSpeed;
			}
			game.correctFrame();
			break;
		case KeyEvent.VK_DOWN:
			game.hero.addiYinSuperTile(game.iSpeed);
			game.edgeDetect();
			if(game.bEdge)
			{
				//hero.addiYinSuperTile(-iSpeed);
				game.yEdgeAdjust+=game.iSpeed;
			}
			game.correctFrame();
			break;
		}
		game.hero.assessPosition(game.FrameX, game.FrameY,game.xEdgeAdjust,game.yEdgeAdjust);
	}
	
	
	
}
