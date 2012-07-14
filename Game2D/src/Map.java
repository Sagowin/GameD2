import java.util.ArrayList;

import java.util.Random;





public class Map {
	
	ArrayList<SuperTile> stAL = new ArrayList<SuperTile>();		//SuperTileArrayList

	

	ArrayList<Biome> bAL = new ArrayList<Biome>();				//Biome Array List
	ArrayList<Creature> cAL = new ArrayList<Creature>();
	
	boolean bBuffer=false;
	
	Random rand = new Random();
	
	SuperTile stBuffer;
	int iBuffer=0;
	int iSTCount=0;
	int iXBuffer=0;
	int iYBuffer=0;
	int iTileCount=0;

	
	public Map()
	{
		makeOrigin();

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
	


		//Need to patch to tiles in SuperTile and to tiles in Adjacent SuperTile

	public void makeOrigin()
	{
		for(int a=-1;a<2;a++)
		{
			for(int b=-1;b<2;b++)
			{
				stAL.add(new SuperTile(a,b));
				patchSuperTiles();
				iSTCount++;
			}
		}
		
		smoothMap();
		
		cAL.add(new Fox(stAL.get(4).tTileArray[5][5]));
		cAL.add(new Mole(stAL.get(4).tTileArray[1][1]));
		
		/*cAL.get(0).cTarget=cAL.get(1);
		System.out.println(cAL.get(0).cTarget.iSpeed);
		cAL.get(0).cTarget=null;
		System.out.println(cAL.get(1).iSpeed);
		*/
		
	}
	
	public void smoothMap()
	{
		int X=0;
		int Y=0;
		Tile tBuffer;
		
		for(int i=0;i<500000;i++)
		{
			X=rand.nextInt(100);
			Y=rand.nextInt(100);
			
			tBuffer=stAL.get(rand.nextInt(stAL.size())).tTileArray[X][Y];
			
			switch(rand.nextInt(8))
			{
			case 0://L
				if(tBuffer.tL!=null)
				{
				tBuffer.setType(tBuffer.tL.type);
				//System.out.println("L");
				}
				break;
			case 1://TL
				if(tBuffer.tTL!=null)
				{
				tBuffer.setType(tBuffer.tTL.type);
				//System.out.println("TL");
				}
				break;
			case 2://T
				if(tBuffer.tT!=null)
				{
				tBuffer.setType(tBuffer.tT.type);
				//System.out.println("T");
				}
				break;
			case 3://TR
				if(tBuffer.tTR!=null)
				{
				tBuffer.setType(tBuffer.tTR.type);
				//System.out.println("TR");
				}
				break;
			case 4://R
				if(tBuffer.tR!=null)
				{
				tBuffer.setType(tBuffer.tR.type);
				//System.out.println("R");
				}
				break;
			case 5://BR
				if(tBuffer.tBR!=null)
				{
				tBuffer.setType(tBuffer.tBR.type);
				//System.out.println("BR");
				}
				break;
			case 6://B
				if(tBuffer.tB!=null)
				{
				tBuffer.setType(tBuffer.tB.type);
				//System.out.println("B");
				}
				break;
			case 7://BL
				if(tBuffer.tBL!=null)
				{
				tBuffer.setType(tBuffer.tBL.type);
				//System.out.println("BL");
				}
				break;
			}
		}
	}
	
	public void generateCreatures()
	{
		iBuffer=rand.nextInt(101);
		
		
		
		if(iBuffer%50==0)
		{
			stBuffer=stAL.get(rand.nextInt(stAL.size()));
			int X=rand.nextInt(100);
			int Y=rand.nextInt(100);
			
			if(stBuffer.tTileArray[X][Y].type==1)
			{
				//for(int c =0;c<cAL.size();c++)
				//{
					cAL.add(new Mole(stBuffer.tTileArray[X][Y]));
				//}
			}
		}
		if(iBuffer==0)
		{
			stBuffer=stAL.get(rand.nextInt(stAL.size()));
			int X=rand.nextInt(100);
			int Y=rand.nextInt(100);
			
			if(stBuffer.tTileArray[X][Y].type==1||stBuffer.tTileArray[X][Y].type==0)
			{
				cAL.add(new Fox(stBuffer.tTileArray[X][Y]));
			}
		}
			
		
	}
	
	public void moveCreatures()
	{
		for(int c=0;c<cAL.size();c++)
		{
			cAL.get(c).move();
		}
	}

	
	//Now in seeCreatures
	public void compareCreatures()
	{
		for(int c=0;c<cAL.size();c++)
		{
			for(int d=0;d<cAL.size();d++)
			{
				cAL.get(c).compare(cAL.get(d));
			}
		}
	}
	
	public void seeCreatures()
	{
		for(int c=0;c<cAL.size();c++)
		{
			if(cAL.get(c).iSightRange>0)
				
				{
					for(int d=0;d<cAL.size();d++)
					{
					
						if(cAL.get(c)!=null&&cAL.get(d)!=null&&c!=d)
						{
							if(!cAL.get(c).bTarget)
							{
								cAL.get(c).seeCreature(cAL.get(d));
							}
							
							cAL.get(c).compare(cAL.get(d));
						}
						
						while(d<cAL.size()&&!cAL.get(d).bAlive)
						{
							d++;
						}
					}
			}
			while(c<cAL.size()&&!cAL.get(c).bAlive)
			{
				c++;
			}
			
			
		}
	}
	
	//Does not work, use at you're own risk!
	public void biomeInit()
	{
		bBuffer=false;				//In this method bBuffer buffers whether or not all the SuperTiles are full or not
		
		while(!bBuffer)
		{
			
			//Need a fill code
			for(int b=0; b<bAL.size();b++)
			{
				bAL.get(b).biomeFill(this);
			}
			
			
			//Begin Check to See if Tiles are Empty
			bBuffer=true;
			
			for(int f= 0;f<stAL.size();f++)
			{
				if(stAL.get(f).iEmpty>0);
				{
					bBuffer=false;
				}
			}
			//End Check to See if Tiles are Empty
			
			
			//Start Make a New Biome if Current Biomes Dont's Fill
			if(!bBuffer)
			{
				iBuffer=rand.nextInt(3);
				
				switch(iBuffer)
				{
				case 0:
					for(int t=0;t<stAL.size();t++)
					{
						if(stAL.get(rand.nextInt(stAL.size())).iEmpty>0)
						{
							bAL.add(new Grassland(stAL.get(t)));
							t=stAL.size();
						}
						
					}
					break;
				
				case 1:
					for(int t=0;t<stAL.size();t++)
					{
						if(stAL.get(t).iEmpty>0)
						{
							bAL.add(new Forest(stAL.get(t)));
							t=stAL.size();
						}
						
					}
					break;
			
				case 2:
					for(int t=0;t<stAL.size();t++)
					{
						if(stAL.get(t).iEmpty>0)
						{
							bAL.add(new Stepe(stAL.get(t)));
							t=stAL.size();
						}
					}
					break;
				}
				
				System.out.println(iTileCount);
			}
			//End Make a New Biome if Current Biomes Don't Fill
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
