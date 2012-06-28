import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class SuperTile {

	Tile[][] sTile= new Tile[100][100];
	ArrayList<TileObject> sList= new ArrayList<TileObject>();
	ArrayList<TileObject> cList= new ArrayList<TileObject>();
	
	int iIdentity=0;
	int iLeft=-1;
	int iRight=-1;
	int iTop=-1;
	int iBottom=-1;
	int iTLCorner=-1;
	int iTRCorner=-1;
	int iBLCorner=-1;
	int iBRCorner=-1;
	int iPatchProgress=1;
	/* 	2-->	WEST
		3-->	NORTH WEST
		5-->	NORTH
		7-->	NORTH EAST
		11-->	EAST
		13-->	SOUTH EAST
		17-->	SOUTH
		19-->	SOUTH WEST
	*/
	boolean bPatched=false;
	int iX=0;
	int iY=0;
	
	
	
	
	public int getiX() {
		return iX;
	}

	public void setiX(int iX) {
		this.iX = iX;
	}

	public int getiY() {
		return iY;
	}

	public void setiY(int iY) {
		this.iY = iY;
	}

	public void checkPatched()
	{
		System.out.println("SuperTile Number :"+ iIdentity+ " --> " +iLeft+ " , "+iRight+ " , "+iTop+ " , "+iBottom+ " , "+iTLCorner+ " , "+iTRCorner+ " , "+iBLCorner+ " , "+iBRCorner);
		
		if(		iLeft>0&&
				iRight>0&&
				iTop>0&&
				iBottom>0&&
				iTLCorner>0&&
				iTRCorner>0&&
				iBLCorner>0&&
				iBRCorner>0)
		{
			bPatched=true;
			System.out.println("SuperTile " + iIdentity + " is fully patched");
		}
		
		
	}
	public boolean isbPatched()
	{
		return bPatched;
	}

	public int getiTLCorner() {
		return iTLCorner;
	}

	public void setiTLCorner(int iTLCorner) {
		this.iTLCorner = iTLCorner;
	}

	public int getiTRCorner() {
		return iTRCorner;
	}

	public void setiTRCorner(int iTRCorner) {
		this.iTRCorner = iTRCorner;
	}

	public int getiBLCorner() {
		return iBLCorner;
	}

	public void setiBLCorner(int iBLCorner) {
		this.iBLCorner = iBLCorner;
	}

	public int getiBRCorner() {
		return iBRCorner;
	}

	public void setiBRCorner(int iBRCorner) {
		this.iBRCorner = iBRCorner;
	}
	
		
		
	public int getiIdentity() {
		return iIdentity;
	}

	public void setiIdentity(int iIdentity) {
		this.iIdentity = iIdentity;
	}

	public int getiLeft() {
		return iLeft;
	}

	public void setiLeft(int iLeft) {
		this.iLeft = iLeft;
	}

	public int getiRight() {
		return iRight;
	}

	public void setiRight(int iRight) {
		this.iRight = iRight;
	}

	public int getiTop() {
		return iTop;
	}

	public void setiTop(int iTop) {
		this.iTop = iTop;
	}

	public int getiBottom() {
		return iBottom;
	}

	public void setiBottom(int iBottom) {
		this.iBottom = iBottom;
	}
	public SuperTile(int i)
	{
		iIdentity=i;
		for(int a=0;a<100;a++)
		{
			for(int b=0;b<100;b++)
			{
				sTile[a][b] = new Tile();
			}
		}
		
		for(int a=0;a<95;a+=5)
		{
			for(int b=0;b<95;b+=5)
			{
				sTile[a][b].setType(1);
			}
		}
		
		sTile[50][50].setType(2);
		
	}
	public void addTile(int x, int y, int t)
	{
		
		sTile[x][y].setType(t);
	}	
	public Tile getTile(int x, int y)
	{
		//System.out.println(x+" "+ y+" "+sTile.length + " " +sTile[0].length);
		return sTile[x][y];
	}
	public void addTileObj(int x, int y)
	{
		TileObject t = new TileObject(x,y,iIdentity);
		sList.add(t);
				
	}
	public void addCreature(int x, int y, int t)
	{
		Creature c = new Creature(x,y,iIdentity, t);
		cList.add(c);
	}
	public void drawCreatures(Graphics g, BufferedImage tile, int xFrame, int yFrame, int frameSizeX, int frameSizeY, int xEdgeAdjust, int yEdgeAdjust)
	{
		for(int a=0;a<cList.size();a++)
		{
			if(	cList.get(a).getiX()>(xFrame-10)&&
				cList.get(a).getiX()<(xFrame+frameSizeX-10)&&
				cList.get(a).getiY()>(yFrame-10)&&
				cList.get(a).getiY()<(yFrame+frameSizeY-10))
			{
				g.drawImage(tile, 	(cList.get(a).getiX()-xFrame+10)*50-xEdgeAdjust,
									(cList.get(a).getiY()-yFrame+10)*50-yEdgeAdjust,
									(cList.get(a).getiX()-xFrame+10)*50+50-xEdgeAdjust,
									(cList.get(a).getiY()-yFrame+10)*50+50-yEdgeAdjust,
									cList.get(a).getiPixelX(),
									cList.get(a).getiPixelY(),
									cList.get(a).getiPixelX()+50,
									cList.get(a).getiPixelY()+50,null);
			}
		}
	}
	
}
