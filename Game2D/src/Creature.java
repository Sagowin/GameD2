import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;


public class Creature extends TileObject{
	int iScreenX=0;
	int iScreenY=0;
	int iSize=0;
	int iXinTile=25;
	int iYinTile=25;
	int iXinSuperTile=0;
	int iYinSuperTile=0;
	int iCreatureSpeed=0;
	boolean bChange=false;
	boolean bAlive=true;
	int type=0;
	boolean bMove=false;
	int iSightRange=0;
	int iAttack=0;
	int iPredLevel=0;
	int iMoveDir=-1;
	int iBuffer=0;
	int iBuffer2=0;
	String sBuffer="";
	int iMoveBehavior=-1; //0=meander 1=relaxed 10=hunting
	int iNaturalMoveBehavior=-1;
	int iPreyNumber=-1;
	final int MOLE =1;
	final int FOX =2;
	Random rand = new Random();  //need to change to "rand" and remove Random paramaters from all other methods
	int iMoveSuperTile=0; //1-> left 2->right 3->up 4->down
	
	
	public int getiMoveSuperTile() {
		return iMoveSuperTile;
	}

	public void setiMoveSuperTile(int iMoveSuperTile) {
		this.iMoveSuperTile = iMoveSuperTile;
	}

	public Creature(int x, int y, int ST, int type)
	{
		super(x,y, ST);
		assessType(type);
		
	}
	
	//checkSuperTile is unused
	/*public void checkSuperTile(ArrayList<SuperTile> map)
	{
		if(iX<0)
		{
			if(map.get(iSuperTile).getiLeft()>0)
			{
				iSuperTile=map.get(iSuperTile).getiLeft();
				iX+=100;
			}
			if(map.get(iSuperTile).getiLeft()<0)
			{
				bAlive=false;
			}
		}
		
		if(iX>99)
		{
			if(map.get(iSuperTile).getiRight()>0)
			{
				iSuperTile=map.get(iSuperTile).getiRight();
				iX-=100;
			}
			if(map.get(iSuperTile).getiRight()<0)
			{
				bAlive=false;
			}
		}
		if(iY<0)
		{
			if(map.get(iSuperTile).getiTop()>0)
			{
				iSuperTile=map.get(iSuperTile).getiTop();
				iY+=100;
			}
			if(map.get(iSuperTile).getiTop()<0)
			{
				bAlive=false;
			}
		}
		if(iY>99)
		{
			if(map.get(iSuperTile).getiBottom()>0)
			{
				iSuperTile=map.get(iSuperTile).getiBottom();
				iY-=100;
			}
			if(map.get(iSuperTile).getiBottom()<0)
			{
				bAlive=false;
			}
		}
		
		
	}*/
	public void drawCreature(Graphics g, BufferedImage tile, int xFrame, int yFrame, int xEdgeAdjust, int yEdgeAdjust)
	{
		g.drawImage(tile, 	(iX-xFrame+10)*50-xEdgeAdjust+(iXinTile-25),
							(iY-yFrame+10)*50-yEdgeAdjust+(iYinTile-25),
							(iX-xFrame+10)*50+50-xEdgeAdjust+(iXinTile-25),
							(iY-yFrame+10)*50+50-yEdgeAdjust+(iYinTile-25),
							iPixelX,
							iPixelY,
							iPixelX+50,
							iPixelY+50,null);
	}
	public void moveCreature()
	{
		switch(iMoveBehavior)
		{
		case 0: moveMeander();break;
		case 1: moveRelaxed();break;
		}
		
		assessPositioninTile();
			
	}
	public void moveCreature(Creature c)
	{
		switch(iMoveBehavior)
		{
		case 10: moveHunting(c);break;
		}
		
		assessPositioninTile();
	}
	public void moveHunting(Creature c)
	{
		if(getXPosition()>c.getXPosition())
		{
			iXinTile-=iCreatureSpeed;
		}
		if(getXPosition()<c.getXPosition())
		{
			iXinTile+=iCreatureSpeed;
		}
		if(getYPosition()>c.getYPosition())
		{
			iYinTile-=iCreatureSpeed;
		}
		if(getYPosition()<c.getYPosition())
		{
			iYinTile+=iCreatureSpeed;
		}			
		
	}
	public void moveRelaxed()
	{
		
		iBuffer=rand.nextInt(4);
		iBuffer2=rand.nextInt(160);
		
		
		
		if(!bMove&&iBuffer2==0)
		{
			iMoveDir=iBuffer;
			bMove=true;
			iBuffer2=rand.nextInt(160);
		}
				
		if(bMove&&iBuffer2<4)
		{
			bMove=false;
		}
		
		if(bMove)
		{
			iBuffer2= rand.nextInt(10);
			if(iBuffer2==0)
			{
				iMoveDir=iBuffer;
			}
			switch(iMoveDir)
			{
			case 0: iXinTile-=iCreatureSpeed;break;
			case 1: iXinTile+=iCreatureSpeed;break;
			case 2: iYinTile-=iCreatureSpeed;break;
			case 3: iYinTile+=iCreatureSpeed;break;
			
			}
		}
		
				
		
		
		
		
	}
	public void moveMeander()
	{
		iBuffer=rand.nextInt(4);
		
		if(!bMove)
		{
			iMoveDir=iBuffer;
			bMove=true;
		}else
		{
			iBuffer2= rand.nextInt(10);
			if(iBuffer2==0)
			{
				iMoveDir=iBuffer;
			}
		}
		
				
		
		switch(iMoveDir)
		{
		case 0: iXinTile-=iCreatureSpeed;break;
		case 1: iXinTile+=iCreatureSpeed;break;
		case 2: iYinTile-=iCreatureSpeed;break;
		case 3: iYinTile+=iCreatureSpeed;break;
		
		}
	}
	public boolean assessPositioninTile()
	{
		bChange=false;
		
		if(iXinTile>50)
		{
			iXinTile-=50;
			iX++;
			bChange=true;
		}
		if(iXinTile<0)
		{
			iXinTile+=50;
			iX--;
			bChange=true;
		}
		if(iYinTile>50)
		{
			iYinTile-=50;
			iY++;
			bChange=true;
		}
		if(iYinTile<0)
		{
			iYinTile+=50;
			iY--;
			bChange=true;
		}
		return bChange;
	}
	public void assessInteraction(Creature a)
	{
		if(iAttack>a.getiAttack())
		{
			a.setbAlive(false);
			
			
			if(iPreyNumber!=-1)
			{
				//System.out.println("I killed " + iPreyNumber);
				iPreyNumber=-1;
				iMoveBehavior=iNaturalMoveBehavior;
				iAttack++;
				iSightRange+=5;
				iPredLevel++;
			}
			
		}
		
		if(iAttack<a.getiAttack())
		{
			bAlive=false;
			if(a.getiPreyNumber()!=-1)
			{
				//System.out.println("I killed " + a.getiPreyNumber());
				a.setiPreyNumber(-1);
				a.setiMoveBehavior(a.getiNaturalMoveBehavior());
				a.setiAttack(a.getiAttack()+1);
				a.setiSightRange(a.getiSightRange()+1);
				a.setiPredLevel(a.getiPredLevel()+1);
				
				
			}
		}
		
		if(iAttack==a.getiAttack())
		{
			iBuffer=rand.nextInt(2);
			
			if(iBuffer==0)
			{
				bAlive=false;
				if(a.getiPreyNumber()!=-1)
				{
					a.setiPreyNumber(-1);
					a.setiMoveBehavior(a.getiNaturalMoveBehavior());
				}
				
			}else
			{
				a.setbAlive(false);
				if(iPreyNumber!=-1)
				{
					iPreyNumber=-1;
					iMoveBehavior=iNaturalMoveBehavior;
				}
			}
		}
		
	}
	public void assessType(int t)
	{
		switch(t)
		{
		case MOLE:
			iPixelY=50;
			iSize=10;
			iCreatureSpeed=1;
			iMoveBehavior=0;
			iNaturalMoveBehavior=0;
			iAttack=1;
			iPredLevel=0;
			break;
		
		case FOX:
			iPixelY=50;
			iPixelX=50;
			iSize=20;
			iCreatureSpeed=5;
			iMoveBehavior=1;
			iNaturalMoveBehavior=1;
			iAttack=5;
			iPredLevel=5;
			iSightRange=300;
			break;
		}
		
	}

	public void trackCreature(Graphics g)
	{
		sBuffer=("Creature is at position "+getXPosition()+" , "+getYPosition()+" and Tiles: "+iX+" , "+iY);
		g.drawString(sBuffer, 1000 ,250);
		
	}
	

	
	
	
	public int getiCreatureSpeed() {
		return iCreatureSpeed;
	}

	public void setiCreatureSpeed(int iCreatureSpeed) {
		this.iCreatureSpeed = iCreatureSpeed;
	}

	public boolean isbAlive() {
		return bAlive;
	}

	public void setbAlive(boolean bAlive) {
		this.bAlive = bAlive;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public int getXPosition()
	{
		iBuffer=iX*50+(iXinTile-25);
		return iBuffer;
	}
	public int getYPosition()
	{
		iBuffer=iY*50+(iYinTile-25);
		return iBuffer;
	}

	public int getiSize() {
		return iSize;
	}

	public void setiSize(int iSize) {
		this.iSize = iSize;
	}

	public int getiAttack() {
		return iAttack;
	}

	public void setiAttack(int iAttack) {
		this.iAttack = iAttack;
	}

	public int getiSightRange() {
		return iSightRange;
	}

	public void setiSightRange(int iSightRange) {
		this.iSightRange = iSightRange;
	}

	public int getiPredLevel() {
		return iPredLevel;
	}

	public void setiPredLevel(int iPredLevel) {
		this.iPredLevel = iPredLevel;
	}

	public int getiMoveBehavior() {
		return iMoveBehavior;
	}

	public void setiMoveBehavior(int iMoveBehavior) {
		this.iMoveBehavior = iMoveBehavior;
	}

	public int getiPreyNumber() {
		return iPreyNumber;
	}

	public void setiPreyNumber(int iPreyNumber) {
		this.iPreyNumber = iPreyNumber;
	}

	public int getiNaturalMoveBehavior() {
		return iNaturalMoveBehavior;
	}

	public void setiNaturalMoveBehavior(int iNaturalMoveBehavior) {
		this.iNaturalMoveBehavior = iNaturalMoveBehavior;
	}
	
	
	

}
