
public class Stepe extends Biome {
	
	
	
	public Stepe(SuperTile ST)
	{
		super(ST);
	}

	public void biomeFill(Map m)
	{
		
		//Set the Seed Tile
		if(tSeed==null)
		{
			getEmpty();
			
			if(rand.nextInt(10)>0)
			{
				stSeed.addTile(tSeed.iXST,tSeed.iYST,2, this);
				iVolume--;
				m.iTileCount++;
				
			}else
			{
				stSeed.addTile(tSeed.iXST,tSeed.iYST,0, this);
				iVolume--;
				m.iTileCount++;
			}
			
		}
		//End Set the Seed Tile
		
		
		for(int ST=0;ST<m.stAL.size();ST++)
		{
			 for(int a=0;a<100;a++)
			 {
				 for(int b=0;b<100;b++)
				 {
					 
					 if(m.stAL.get(ST).tTileArray[a][b] instanceof EmptyTile)
					 {
						 int bCount=0;
					 							
						 if(m.stAL.get(ST).tTileArray[a][b].tL!=null&&m.stAL.get(ST).tTileArray[a][b].tL.biome==this)
						 {
							bCount++; 
						 }
						
						 if(m.stAL.get(ST).tTileArray[a][b].tR!=null&&m.stAL.get(ST).tTileArray[a][b].tR.biome==this)
							{
								bCount++; 
							}
						 if(m.stAL.get(ST).tTileArray[a][b].tT!=null&&m.stAL.get(ST).tTileArray[a][b].tT.biome==this)
							{
								bCount++; 
							}
						 if(m.stAL.get(ST).tTileArray[a][b].tB!=null&&m.stAL.get(ST).tTileArray[a][b].tB.biome==this)
							{
								bCount++; 
							}
						 if(m.stAL.get(ST).tTileArray[a][b].tTL!=null&&m.stAL.get(ST).tTileArray[a][b].tTL.biome==this)
							{
								bCount++; 
							}
						 if(m.stAL.get(ST).tTileArray[a][b].tTR!=null&&m.stAL.get(ST).tTileArray[a][b].tTR.biome==this)
							{
								bCount++; 
							}
						 if(m.stAL.get(ST).tTileArray[a][b].tBL!=null&&m.stAL.get(ST).tTileArray[a][b].tBL.biome==this)
							{
								bCount++; 
							}
						 if(m.stAL.get(ST).tTileArray[a][b].tBR!=null&&m.stAL.get(ST).tTileArray[a][b].tBR.biome==this)
							{
								bCount++; 
							}
						 
						 if((rand.nextInt(8))<bCount)
						 {
							 if(rand.nextInt(10)>0)
								{
								 m.stAL.get(ST).addTile(a,b,2, this);
									iVolume--;
									m.stAL.get(ST).iEmpty--;
									m.iTileCount++;
									
									
								}else
								{
									m.stAL.get(ST).addTile(a,b,0, this);
									iVolume--;
									m.stAL.get(ST).iEmpty--;
									m.iTileCount++;
								}
						 }
						 
						 
					 }
					 
				 }
			 }
		}
	}
	
	
}
