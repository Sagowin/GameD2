import java.util.ArrayList;
import java.util.Random;


public class Biome {

	Random rand = new Random();
	
	int iVolume=-1;
	int iVolumeReserve=0;
	
	int iTileX=-1;
	int iTileY=-1;
	
	boolean bLocked=false; //when true, the biome does not do passes of the SuperTiles
	
	int iBuffer=0;
	
	SuperTile stSeed;
	SuperTile stBuffer;
	Tile tSeed;
	
	
	
	public Biome(SuperTile ST)
	{
		stSeed=ST;
		
		int iBuffer=rand.nextInt(10);
		
		if(iBuffer<3) 			//  3/10 chances for a small biome 1000-2500
		{
			iVolume=1000+rand.nextInt(1500);
					
		}else if(iBuffer<8) 	//  3/5 chance for medium biome 2500-10000
		{
			iVolume=2500+rand.nextInt(7500);
					
		}else					//  1/10 chance for large biome 10000-30000
		{
			iVolume=10000+rand.nextInt(20000);
		}
		
	}
	
	public void biomeFill(Map m)
	{
		
	}
	
	public void getEmpty()
	{
		boolean bBuffer=true;
		int X=0;
		int Y=0;
		
		while(bBuffer)
		{
			
		X=rand.nextInt(100);
		Y=rand.nextInt(100);
		
			if(stSeed.tTileArray[X][Y] instanceof EmptyTile)
			{
				tSeed=stSeed.tTileArray[X][Y];
				bBuffer=false;
			}
		
		}
		
		
	}
	
		
}
