import java.util.ArrayList;


//

public class Map {
	
	ArrayList<SuperTile> stAL = new ArrayList<SuperTile>();		//SuperTileArrayList
	
	boolean bBuffer=false;
	
	
	int iSTCount=0;
	int iXBuffer=0;
	int iYBuffer=0;
	
	public Map()
	{
		
	}
	
	public int size()
	{
		return stAL.size();
	}
	
	public void printMapPositions()
	{
		for(int a=0;a<stAL.size();a++)
		{
			System.out.println("{"+stAL.get(a).iX+" , "+stAL.get(a).iY+"}");
		}
	
	}
	
	public void patchMap()
	{
		//Need to patch to tiles in SuperTile and to tiles in Adjacent SuperTile
	}
	
	public void addTile(int x, int y)
	{
		bBuffer=false;
		
		//Begin x,y Adjust (if you don't do this, then negative numbers are put into SuperTiles with X+1 and Y+1
		if(x<0)
		{
			x-=100;
		}
		if(y<0)
		{
			y-=100;
		}
		//End x,y adjust
		
		
		for(int a=0;a<stAL.size();a++) //First Pass
		{
			if(stAL.size()>0)
			{
				if(x/100==stAL.get(a).iX&&y/100==stAL.get(a).iY)
				{
					stAL.get(a).addTile(x%100,y%100);
					bBuffer=true;
				}
			}
		}//End First Pass
		
		
		//There is no SuperTile for this position yet
		if(!bBuffer)
		{
			System.out.println(x+" "+y);
			
			stAL.add(new SuperTile(x/100,y/100));
			patchSuperTiles();
			stAL.get(iSTCount).addTile(x%100,y%100);
			iSTCount++;
		}
		
		
	}
	
	public Tile getTile(int x, int y)
	{
			//Begin x,y Adjust (if you don't do this, then negative numbers are put into SuperTiles with X+1 and Y+1
				if(x<0)
				{
					x-=100;
				}
				if(y<0)
				{
					y-=100;
				}
				//End x,y adjust
				
				for(int a=0;a<stAL.size();a++) //First Pass
				{
					if(stAL.size()>0)
					{
						if(x/100==stAL.get(a).iX&&y/100==stAL.get(a).iY)
						{
							return stAL.get(a).getTile(x%100,y%100);
						}
					}
				}
		
		Tile tBuff = new Tile();	//Required so that a non-existent SuperTile doesn't make an error
		
		return tBuff;
				
	}
	
	public void patchSuperTiles()
	{
		//Start for loop
		for(int b=0;b<stAL.size();b++)
		{
			iXBuffer=stAL.get(iSTCount).iX-stAL.get(b).iX;
			iYBuffer=stAL.get(iSTCount).iY-stAL.get(b).iY;
			
			//Start Switches
			switch(iXBuffer)
			{
			case 1:
				switch(iYBuffer)
				{
				case 1://Bottom Left
					stAL.get(iSTCount).stBL=stAL.get(b);
					stAL.get(b).stTR=stAL.get(iSTCount);
					
					break;
				case 0://Left
					stAL.get(iSTCount).stL=stAL.get(b);
					stAL.get(b).stR=stAL.get(iSTCount);
					break;
				case -1://Top Left
					stAL.get(iSTCount).stTL=stAL.get(b);
					stAL.get(b).stBR=stAL.get(iSTCount);
					break;
				}
				break;
			case 0:
				switch(iYBuffer)
				{
				case 1://Bottom
					stAL.get(iSTCount).stB=stAL.get(b);
					stAL.get(b).stT=stAL.get(iSTCount);
					break;
				case -1://Top
					stAL.get(iSTCount).stT=stAL.get(b);
					stAL.get(b).stB=stAL.get(iSTCount);
					break;
				}
				break;
			case -1:
				switch(iYBuffer)
				{
				case 1://Bottom Right from Top Right
					stAL.get(iSTCount).stBR=stAL.get(b);
					stAL.get(b).stTL=stAL.get(iSTCount);
					break;
				case 0://Right
					stAL.get(iSTCount).stR=stAL.get(b);
					stAL.get(b).stL=stAL.get(iSTCount);
					break;
				case -1://Top Right from Bottom Right
					stAL.get(iSTCount).stTR=stAL.get(b);
					stAL.get(b).stBL=stAL.get(iSTCount);
					break;
				}
				break;
			}//End Both Switches
			
		}//End for loop
		
				
	}

}
