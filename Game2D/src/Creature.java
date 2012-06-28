import java.util.ArrayList;


public class Creature extends TileObject{
	int iScreenX=0;
	int iScreenY=0;
	int iSize=0;
	int iXinTile=25;
	int iYinTile=25;
	int iCreatureSpeed=0;
	boolean bChange=false;
	boolean bAlive=true;
	int type=0;
	final int MOLE =1;
	
	
	public Creature(int x, int y, int ST, int type)
	{
		super(x,y, ST);
		assessType(type);
		
	}
	
	public void checkUp(ArrayList<SuperTile> map)
	{
			
		if(assessPositioninTile())
		{
			checkSuperTile(map);
		}
		
		
	}
	public void checkSuperTile(ArrayList<SuperTile> map)
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
	public void assessType(int t)
	{
		if(t==1)
		{
			iPixelY=50;
			iSize=10;
		}
		
	}
}
