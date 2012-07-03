import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;


public class SuperTile {

	Tile[][] sTile= new Tile[100][100];
	int[] iArray = new int[20];
	ArrayList<TileObject> sList= new ArrayList<TileObject>();
	ArrayList<Creature> cList= new ArrayList<Creature>();
	int iMax=0;
	int iZones=0;
	int iTypeBase=-1;
	int iCount=1;
	int iMod=-1;
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
	Random rand = new Random();
	int iBuffer=-1;
	int iBuffer2=-1;
	boolean bBuffer=false;
	ArrayList<Creature> creaturelistBuffer= new ArrayList<Creature>();
	boolean bGen = false;
	Creature cBuffer= new Creature(0,0,0,0);
	
	
	public ArrayList<Creature> creaturesCorrect()
	{
		creaturelistBuffer.clear();
		
		for(int a=0;a<cList.size();a++)
		{
			cBuffer=cList.get(a);
			bBuffer=true;
			
			if(bBuffer&&cBuffer.getiX()<0)
			{
				
				cBuffer.setiMoveSuperTile(1);
				cBuffer.setiX(cBuffer.getiX()+100);
				cBuffer.setbAlive(true);
				creaturelistBuffer.add(cBuffer);
				bBuffer=false;
				System.out.println(cList.get(a).isbAlive()+" , "+cBuffer.isbAlive()+ " , "+bBuffer);	
			}
			if(bBuffer&&cBuffer.getiX()>99)
			{
				cBuffer.setiMoveSuperTile(2);
				cBuffer.setiX(cBuffer.getiX()-100);
				cBuffer.setbAlive(true);
				creaturelistBuffer.add(cBuffer);
				bBuffer=false;
				System.out.println(cList.get(a).isbAlive()+" , "+cBuffer.isbAlive()+ " , "+bBuffer);
			}
			if(bBuffer&&cBuffer.getiY()<0)
			{
				cBuffer.setiMoveSuperTile(3);
				cBuffer.setiY(cBuffer.getiY()+100);
				cBuffer.setbAlive(true);
				creaturelistBuffer.add(cBuffer);
				bBuffer=false;
			}
			if(bBuffer&&cBuffer.getiY()>99)
			{
				cBuffer.setiMoveSuperTile(4);
				cBuffer.setiY(cBuffer.getiY()-100);
				cBuffer.setbAlive(true);
				creaturelistBuffer.add(cBuffer);
				bBuffer=false;
			}
			
			
		}
		
		return creaturelistBuffer;
	}
	
	
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
		//System.out.println("SuperTile Number :"+ iIdentity+ " --> " +iLeft+ " , "+iRight+ " , "+iTop+ " , "+iBottom+ " , "+iTLCorner+ " , "+iTRCorner+ " , "+iBLCorner+ " , "+iBRCorner);
		
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
	
	public Creature getCreature(int a)
	{
		return cList.get(a);
	}
	
	public SuperTile()
	{
		
	}
	
	public SuperTile(int i)
	{
		System.out.println("SuperTile has been made with identity: "+i);
		iIdentity=i;
		for(int a=0;a<100;a++)
		{
			for(int b=0;b<100;b++)
			{
				sTile[a][b] = new Tile();
			}
		}
		
		if(!bGen)
		{
			genSuperTile();
			bGen=true;
		}
				
		//sTile[50][50].setType(2);
		//makeSuperTile();
		
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
		bBuffer=true;
		
		for(int a=0;a<cList.size();a++)
		{
			if(!(cList.get(a).isbAlive()))
			{
				cList.set(a, c);
				//System.out.println(a +" has been resurrected");
				a=cList.size();
				bBuffer=false;
				
			}
		}
		
		if(bBuffer)
		{
			cList.add(c);
		}
	}
	public void addCreature(Creature c)
	{
		bBuffer=true;
		
		for(int a=0;a<cList.size();a++)
		{
			if(!(cList.get(a).isbAlive()))
			{
				cList.set(a, c);
				//System.out.println(a +" has been resurrected");
				a=cList.size();
				bBuffer=false;
				
			}
		}
		
		if(bBuffer)
		{
			cList.add(c);
		}
	}
	public void drawCreatures(Graphics g, BufferedImage tile, int xFrame, int yFrame, int frameSizeX, int frameSizeY, int xEdgeAdjust, int yEdgeAdjust)
	{
		for(int a=0;a<cList.size();a++)
		{
			if(	cList.get(a).getiX()>(xFrame-11)&&
				cList.get(a).getiX()<(xFrame+frameSizeX-10)&&
				cList.get(a).getiY()>(yFrame-11)&&
				cList.get(a).getiY()<(yFrame+frameSizeY-10))
			{
				
				if(cList.get(a).isbAlive())
				{
				cList.get(a).drawCreature(g, tile, xFrame, yFrame, xEdgeAdjust, yEdgeAdjust);
				}
				/*g.drawImage(tile, 	(cList.get(a).getiX()-xFrame+10)*50-xEdgeAdjust,
									(cList.get(a).getiY()-yFrame+10)*50-yEdgeAdjust,
									(cList.get(a).getiX()-xFrame+10)*50+50-xEdgeAdjust,
									(cList.get(a).getiY()-yFrame+10)*50+50-yEdgeAdjust,
									cList.get(a).getiPixelX(),
									cList.get(a).getiPixelY(),
									cList.get(a).getiPixelX()+50,
									cList.get(a).getiPixelY()+50,null);*/
			}
		}
	}
	public void moveCreatures()
	{
		for(int a=0;a<cList.size();a++)
		{
			if(cList.get(a).isbAlive())
			{	
				iBuffer=cList.get(a).getiPreyNumber();
				
				if(iBuffer!=-1)
				{
					
					cList.get(a).moveCreature(cList.get(iBuffer));
					
				}else
				{
					cList.get(a).moveCreature();
				}
			}
		}
	}
	public void compareCreatures()
	{
		for(int a=0;a<cList.size();a++)
		{
			while(a<cList.size()&&!(cList.get(a).isbAlive()))
			{
				a++;
			}
			
			//Start is my prey dead?
			if(a<cList.size())
			{
				
				iBuffer=cList.get(a).getiPreyNumber();
			
				if(iBuffer>0&&!(cList.get(iBuffer).isbAlive()))
				{
					cList.get(a).setiPreyNumber(-1);
					//System.out.println(a+" says: my target is dead =(");
				}
			
			}
			//End is my prey dead?
			
			for(int b=0;b<cList.size();b++)
			{
				while(b<cList.size()&&!(cList.get(b).isbAlive()))
				{
					b++;
				}
				
				//start "I want to eat you" and initiating pathing
				if(a<cList.size()&&b<cList.size()&&a!=b&&cList.get(a).getiSightRange()>0&&cList.get(a).getiPredLevel()>cList.get(b).getiPredLevel()&&cList.get(a).iPreyNumber==-1)
				{
					if((cList.get(a).getXPosition()-cList.get(b).getXPosition())<(cList.get(a).getiSightRange())&&
							(cList.get(a).getXPosition()-cList.get(b).getXPosition())>(-cList.get(a).getiSightRange())&&
							(cList.get(a).getYPosition()-cList.get(b).getYPosition())<(cList.get(a).getiSightRange())&&
									(cList.get(a).getYPosition()-cList.get(b).getYPosition())>(-cList.get(a).getiSightRange()))
					{
						cList.get(a).setiPreyNumber(b);
						cList.get(a).setiMoveBehavior(10);
						//System.out.println(a+" says I see you "+b);
						//System.out.println("a-> "+ cList.get(a).getXPosition() + " , "+cList.get(a).getYPosition()+" b-> "+cList.get(b).getXPosition()+ " , "+cList.get(b).getYPosition());
					}
				}
				//end "I want to eat you" and initiating pathing
				
				//start "I eat you" vs. "you eat me"
				if(a<cList.size()&&b<cList.size()&&a!=b)
				{
				if(((cList.get(a).getXPosition()-cList.get(b).getXPosition())<(cList.get(a).getiSize()+cList.get(b).getiSize())&&
						(cList.get(a).getXPosition()-cList.get(b).getXPosition())>(-cList.get(a).getiSize()-cList.get(b).getiSize()))&&
						((cList.get(a).getYPosition()-cList.get(b).getYPosition())<(cList.get(a).getiSize()+cList.get(b).getiSize())&&
								(cList.get(a).getYPosition()-cList.get(b).getYPosition())>(-cList.get(a).getiSize()-cList.get(b).getiSize())))
				{
					cList.get(a).assessInteraction(cList.get(b));
					//System.out.println("a-> "+ cList.get(a).getXPosition() + " , "+cList.get(a).getYPosition()+" b-> "+cList.get(b).getXPosition()+ " , "+cList.get(b).getYPosition());
				}
				}
				//end "I eat you" vs. "you eat me"
				
				
				
			}
		}
	}

	public void genSuperTile()
	{
		iZones=rand.nextInt(8)+1;
		int[] iZoneArray= new int[iZones];
		int iWidth=0;
		int iHeight=0;
		int iiX=0;
		int iiY=0;
		int iBG=rand.nextInt(3); //sets fill tile type
		System.out.println("The background tileset is #"+iBG);
		
		for(int a=0;a<iZones;a++)
		{
			iZoneArray[a]=rand.nextInt(2); //0-> Field 1->Vein
		}
		
		for(int e=0;e<100;e++)
		{
			for(int f=0;f<100;f++)
			{
				
					sTile[e][f].setType(iBG);
					//System.out.println("The background tileset is now #"+iBuffer2);
			}
		}
		
		for(int b=0;b<iZones;b++)
		{
			if(iZoneArray[b]==0)
			{
				
				iBuffer=rand.nextInt(3);
				iWidth=rand.nextInt(10)+10;
				iHeight=rand.nextInt(10)+10;
				iiX=rand.nextInt(100);
				iiY=rand.nextInt(100);
				
				if((iiX+iWidth)>99)
				{
					iiX=99-iWidth;
				}
				if((iiY+iHeight)>99)
				{
					iiY=99-iHeight;
				}
				
				if(iBuffer!=iBG)
				{
					for(int c=iiX;c<(iiX+iWidth);c++)
					{
						for(int d=iiY;d<(iiY+iHeight);d++)
						{
								sTile[c][d].setType(iBuffer);
								
						}
					}
				
					System.out.println("A field has been drawn that is type "+iBuffer+ " Against a "+iBG+" background");
					System.out.println("at " +iiX +" , "+ iiY + "and size: "+iWidth+" by "+iHeight);
				}
				
				
				
			}
		}
		
		/*for(int b=0;b<iZones;b++)
		{
			if(iZoneArray[b]==1)
			{
				iWidth=rand.nextInt(5)+5;
				iHeight=rand.nextInt(5)+5;
				iX=rand.nextInt(100-iWidth);
				iY=rand.nextInt(100-iHeight);
			}
			
		}*/
		
		
		
		
		
		
	}
}
