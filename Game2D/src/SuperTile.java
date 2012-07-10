import java.util.Random;


public class SuperTile {

	Tile[][] tTileArray= new Tile[100][100];		//Holds the tile references
	int iX=0;										//X position in map
	int iY=0;										//Y Position in map
	int iXBL=0;	
	int iYBL=0;
	int iXBuffer=0;									//Buffers Position of new Tile
	int iYBuffer=0;									//Buffers Position of new Tile
	Random rand = new Random();
	
	SuperTile stL;
	SuperTile stR;
	SuperTile stT;
	SuperTile stB;
	SuperTile stTL;
	SuperTile stTR;
	SuperTile stBL;
	SuperTile stBR;
	boolean bPatched=false;
	
	public SuperTile()
	{
		
	}
	
	public SuperTile(int x, int y)
	{
		iX=x;
		iY=y;
		iXBL=x*100;
		iYBL=y*100;
		
		System.out.println("SuperTile created at "+iX+" , "+iY);
		
		//Initialize Array
		for(int a=0;a<100;a++)
		{
			for(int b=0;b<100;b++)
			{
				tTileArray[a][b]= new Tile();
			}
		}
	}
	
	public void addTile(int x, int y)
	{
		iXBuffer=x;
		iYBuffer=y;
		
		if(x<0)
		{
			x=100+x;
		}
		if(y<0)
		{
			y=100+y;
		}
		
		tTileArray[x][y]= new Tile(iXBuffer,iYBuffer,rand.nextInt(3));
		patchnewTile(x,y);
	}
	
	public Tile getTile(int x, int y)
	{
		if(x<0)
		{
			x=100+x;
		}
		if(y<0)
		{
			y=100+y;
		}
		return tTileArray[x][y];
	}
	
	public void patchnewTile(int x, int y)
	{
		
		//Left
		if(x>0&&tTileArray[x-1][y]!=null)
		{
		tTileArray[x][y].tL=tTileArray[x-1][y];
		tTileArray[x-1][y].tR=tTileArray[x][y];
		
		tTileArray[x][y].bL=true;
		tTileArray[x-1][y].bR=true;
		
		}
		if(x==0&&stL!=null&&stL.tTileArray[99][y]!=null)
		{
			tTileArray[x][y].tL=stL.tTileArray[99][y];
			stL.tTileArray[99][y].tR=tTileArray[x][y];
			
			tTileArray[x][y].bL=true;
			stL.tTileArray[99][y].bR=true;
		}
		
		//Top Left
		if(x>0&&y<99&&tTileArray[x-1][y+1]!=null)
		{
		tTileArray[x][y].tTL=tTileArray[x-1][y+1];
		tTileArray[x-1][y+1].tBR=tTileArray[x][y];
		
		tTileArray[x][y].bTL=true;
		tTileArray[x-1][y+1].bBR=true;
		}
		if(x==0&&y<99&&stL!=null&&stL.tTileArray[99][y+1]!=null)
		{
			tTileArray[x][y].tTL=stL.tTileArray[99][y+1];
			stL.tTileArray[99][y+1].tBR=tTileArray[x][y];
			
			tTileArray[x][y].bTL=true;
			stL.tTileArray[99][y+1].bBR=true;
		}
		if(x>0&&y==99&&stT!=null&&stT.tTileArray[x-1][0]!=null)
		{
			tTileArray[x][y].tTL=stT.tTileArray[x-1][0];
			stT.tTileArray[x-1][0].tBR=tTileArray[x][y];
			
			tTileArray[x][y].bTL=true;
			stT.tTileArray[x-1][0].bBR=true;
			
		}
		if(x==0&&y==99&&stTL!=null&&stTL.tTileArray[99][0]!=null)
		{
			tTileArray[x][y].tTL=stTL.tTileArray[99][0];
			stTL.tTileArray[99][0].tBR=tTileArray[x][y];
			
			tTileArray[x][y].bTL=true;
			stTL.tTileArray[99][0].bBR=true;
		}		
		
		//Top
		if(y<99&&tTileArray[x][y+1]!=null)
		{
		tTileArray[x][y].tT=tTileArray[x][y+1];
		tTileArray[x][y+1].tB=tTileArray[x][y];
		
		tTileArray[x][y].bT=true;
		tTileArray[x][y+1].bB=true;
		}
		if(y==99&&stT!=null&&stT.tTileArray[x][0]!=null)
		{
			tTileArray[x][y].tT=stT.tTileArray[x][0];
			stT.tTileArray[x][0].tB=tTileArray[x][y];
			
			tTileArray[x][y].bT=true;
			stT.tTileArray[x][0].bB=true;
		}
		
		//Top Right
		if(x<99&&y<99&&tTileArray[x+1][y+1]!=null)
		{
		tTileArray[x][y].tTR=tTileArray[x+1][y+1];
		tTileArray[x+1][y+1].tBL=tTileArray[x][y];
		
		tTileArray[x][y].bTR=true;
		tTileArray[x+1][y+1].bBL=true;
		}
		if(x==99&&y<99&&stR!=null&&stR.tTileArray[0][y+1]!=null)
		{
			tTileArray[x][y].tTR=stR.tTileArray[0][y+1];
			stR.tTileArray[0][y+1].tBL=tTileArray[x][y];
			
			tTileArray[x][y].bTR=true;
			stR.tTileArray[0][y+1].bBL=true;
		}
		if(x<99&&y==99&&stT!=null&&stT.tTileArray[x+1][0]!=null)
		{
			tTileArray[x][y].tTR=stT.tTileArray[x+1][0];
			stT.tTileArray[x+1][0].tBL=tTileArray[x][y];
			
			tTileArray[x][y].bTR=true;
			stT.tTileArray[x+1][0].bBL=true;
		}
		if(x==99&&y==99&&stTR!=null&&stTR.tTileArray[0][0]!=null)
		{
			tTileArray[x][y].tTR=stTR.tTileArray[0][0];
			stTR.tTileArray[0][0].tBL=tTileArray[x][y];
			
			tTileArray[x][y].bTR=true;
			stTR.tTileArray[0][0].bBL=true;
		}
		//Right
		if(x<99&&tTileArray[x+1][y]!=null)
		{
		tTileArray[x][y].tR=tTileArray[x+1][y];
		tTileArray[x+1][y].tL=tTileArray[x][y];
		
		tTileArray[x][y].bR=true;
		tTileArray[x+1][y].bL=true;
		}
		if(x==99&&stR!=null&&stR.tTileArray[0][y]!=null)
		{
			tTileArray[x][y].tR=stR.tTileArray[0][y];
			stR.tTileArray[0][y].tL=tTileArray[x][y];
			
			tTileArray[x][y].bR=true;
			stR.tTileArray[0][y].bL=true;
			
		}
		//Bottom Right
		if(x<99&&y>0&&tTileArray[x+1][y-1]!=null)
		{
		tTileArray[x][y].tBR=tTileArray[x+1][y-1];
		tTileArray[x+1][y-1].tTL=tTileArray[x][y];

		tTileArray[x][y].bBR=true;
		tTileArray[x+1][y-1].bTL=true;
		}
		if(x==99&&y>0&&stR!=null&&stR.tTileArray[0][y-1]!=null)
		{
			tTileArray[x][y].tBR=stR.tTileArray[0][y-1];
			stR.tTileArray[0][y-1].tTL=tTileArray[x][y];
			
			tTileArray[x][y].bBR=true;
			stR.tTileArray[0][y-1].bTL=true;
		}
		if(x<99&&y==0&&stB!=null&&stB.tTileArray[x+1][99]!=null)
		{
			tTileArray[x][y].tBR=stB.tTileArray[x+1][99];
			stB.tTileArray[x+1][99].tTL=tTileArray[x][y];
			
			tTileArray[x][y].bBR=true;
			stB.tTileArray[x+1][99].bTL=true;
		}
		if(x==99&&y==0&&stBR!=null&&stBR.tTileArray[0][99]!=null)
		{
			tTileArray[x][y].tBR=stBR.tTileArray[0][99];
			stBR.tTileArray[0][99].tTL=tTileArray[x][y];
			
			tTileArray[x][y].bBR=true;
			stBR.tTileArray[0][99].bTL=true;
		}
		//Bottom
		if(y>0&&tTileArray[x][y-1]!=null)
		{
		tTileArray[x][y].tB=tTileArray[x][y-1];
		tTileArray[x][y-1].tT=tTileArray[x][y];
		
		tTileArray[x][y].bB=true;
		tTileArray[x][y-1].bT=true;
		}
		if(y==0&&stB!=null&&stB.tTileArray[x][99]!=null)
		{
			tTileArray[x][y].tB=stB.tTileArray[x][99];
			stB.tTileArray[x][99].tT=tTileArray[x][y];
			
			tTileArray[x][y].bB=true;
			stB.tTileArray[x][99].bT=true;
		}
		//Bottom Left
		if(x>0&&y>0&&tTileArray[x-1][y-1]!=null)
		{
		tTileArray[x][y].tBL=tTileArray[x-1][y-1];
		tTileArray[x-1][y-1].tTR=tTileArray[x][y];
		
		tTileArray[x][y].bBL=true;
		tTileArray[x-1][y-1].bTR=true;
		}
		if(x==0&&y>0&&stL!=null&&stL.tTileArray[99][y-1]!=null)
		{
			tTileArray[x][y].tBL=stL.tTileArray[99][y-1];
			stL.tTileArray[99][y-1].tTR=tTileArray[x][y];
			
			tTileArray[x][y].bBL=true;
			stL.tTileArray[99][y-1].bTR=true;
		}
		if(x>0&&y==0&&stB!=null&&stB.tTileArray[x-1][99]!=null)
		{
			tTileArray[x][y].tBL=stB.tTileArray[x-1][99];
			stB.tTileArray[x-1][99].tTR=tTileArray[x][y];
			
			tTileArray[x][y].bBL=true;
			stB.tTileArray[x-1][99].bTR=true;
		}
		if(x==0&&y==0&&stBL!=null&&stBL.tTileArray[99][99]!=null)
		{
			tTileArray[x][y].tBL=stBL.tTileArray[99][99];
			stBL.tTileArray[99][99].tTR=tTileArray[x][y];
			
			tTileArray[x][y].bBL=true;
			stBL.tTileArray[99][99].bTR=true;
		}
	}
	
	public void printPatch()//Prints SuperTiles this SuperTile is patched to
	{
		System.out.println(stTL.iX+" , "+stTL.iY+" || "+stT.iX+" , "+stT.iY+" || "+stTR.iX+" , "+stTR.iY);
		System.out.println(stL.iX+" , "+stL.iY+" || "+iX+" , "+iY+" || "+stR.iX+" , "+stR.iY);
		System.out.println(stBL.iX+" , "+stBL.iY+" || "+stB.iX+" , "+stB.iY+" || "+stBR.iX+" , "+stBR.iY);
	}
}
