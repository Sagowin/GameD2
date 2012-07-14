import java.util.Random;


public class Creature {

	//Ever New Class of Creature Must Have:
	//	iSpeed=
	//	iPixelX=
	//	iPixelY=
	//  iSightRange=
	//  iSize=
	//  iPredator=
	
	Tile tile;
	
	int iPosX=0;
	int iPosY=0;
	
	int iPixelX=0;
	int iPixelY=0;
	
	int iTileX=0;
	int iTileY=0;
	
	int iSpeed=0;
	int iDirection=-1;
	
	boolean bAlive=true;
	
	int iSize=0;
	
	int iPredator=0;
	
	int iSightRange=0;
	Creature cTarget;
	boolean bTarget=false;
	
	Random rand = new Random();
	
	public Creature(Tile t)
	{
		tile=t;
		
		if(tile.iX<0)
		{
			iTileX=100*tile.sIdentity.iX+tile.iX-100;
		}else
		{
			iTileX=tile.iX+100*tile.sIdentity.iX;
		}
		
		if(tile.iY<0)
		{
			iTileY=100*tile.sIdentity.iY+tile.iY-100;
		}else
		{
			iTileY=tile.iY+100*tile.sIdentity.iY;
		}
		
		iPosX=iTileX*50;
		iPosY=iTileY*50;		
		
		System.out.println(iPosX+" , "+iPosY);
		System.out.println(iTileX+" , "+iTileY);
	}
	
	public void move()
	{
		
	}
	public void seeCreature(Creature c)
	{
		
	}
	public void hunt()
	{
		
	}
	
	public void compare(Creature c)
	{
		if((iPosX-c.iPosX)*(iPosX-c.iPosX)+(iPosY-c.iPosY)*(iPosY-c.iPosY)<(iSize+c.iSize)*(iSize+c.iSize))
		{
			if(iPredator>c.iPredator)
			{
				c.bAlive=false;
				cTarget=null;
				
			}
			if(iPredator<c.iPredator)
			{
				bAlive=false;
				c.cTarget=null;
				
			}
		}
	}
}
