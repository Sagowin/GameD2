
public class Fox extends Creature{

	public Fox(Tile t)
	{
		super(t);
		
		iPixelX=50;
		iPixelY=50;
		iSize=10;
		iSpeed=5;
		iPredator=1;
		iSightRange=500;
		
	}
	
	public void seeCreature(Creature c)
	{
		//Start Is Prey?
		if(iPredator>c.iPredator)
		{
			if(		(iPosX-c.iPosX)*(iPosX-c.iPosX)+(iPosY-c.iPosY)*(iPosY-c.iPosY)<(iSize+c.iSize+iSightRange)*(iSize+c.iSize+iSightRange))
			{
				if(cTarget==null)
				{
					cTarget=c;
					//bTarget=true;
				}
				if(!cTarget.bAlive)
				{
					cTarget=c;
				}
				
				if(cTarget!=null)//Start Is Better Prey?
				{
					if(		(iPosX-c.iPosX)*(iPosX-c.iPosX)+(iPosY-c.iPosY)*(iPosY-c.iPosY)<
							(iPosX-cTarget.iPosX)*(iPosX-cTarget.iPosX)+(iPosY-cTarget.iPosY)*(iPosY-cTarget.iPosY))
					{
						cTarget=c;
					}
				}//End is Better Prey?
				
				
				
			}
		}
		//End Is Prey?
	}
	
	
	
	public void hunt()
	{
		if(iPosX-cTarget.iPosX>0)
		{
			iPosX-=iSpeed;
		}
		if(iPosX-cTarget.iPosX<0)
		{
			iPosX+=iSpeed;
		}
		
		if(iPosY-cTarget.iPosY>0)
		{
			iPosY-=iSpeed;
		}
		if(iPosY-cTarget.iPosY<0)
		{
			iPosY+=iSpeed;
		}
	}
	
	public void relaxedMove()
	{
		if(iDirection<0)
		{
			switch(rand.nextInt(40))
			{
			case 0:			//Left
				iDirection=0;
				break;
			case 1:			//Right
				iDirection=1;
				break;
			case 2:			//Top
				iDirection=2;
				break;
			case 3:			//Bottom
				iDirection=3;
				break;
			}
		}else
		{
			if(rand.nextInt(80)>0)
			{
				switch(iDirection)
				{
				case 0:			//Left
					iPosX-=iSpeed;
					break;
				case 1:			//Right
					iPosX+=iSpeed;
					break;
				case 2:			//Top
					iPosY+=iSpeed;
					break;
				case 3:			//Bottom
					iPosY-=iSpeed;
					break;
				}
			}else
			{
				iDirection=-1;
			}
		}
	}
	
	public void move()
	{
		
		if(cTarget==null||!cTarget.bAlive)
		{
			relaxedMove();
		}
		
		if(cTarget!=null&&cTarget.bAlive)
		{
			hunt();
		}
	}
	
}
