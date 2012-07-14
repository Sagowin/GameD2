
public class Mole extends Creature{
	
	public Mole(Tile t)
	{
		super(t);
		
		iPixelX=0;
		iPixelY=50;
		iSize=5;
		iSpeed=1;
		
	}
	
	public void move()
	{
		switch(rand.nextInt(20))
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
	}
}
