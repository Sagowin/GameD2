
public class Tile {

	int type=-1;
	int iIdentity=0;
	int iX=0;
	int iY=0;
	int iPixelX=0;
	int iPixelY=0;
	
	final int FOREST=0;
	final int GRASS=1;
	final int DIRT=2;
	final int DESERT=3;
	final int ICE=4;
	
	Tile tL;
	Tile tR;
	Tile tT;
	Tile tB;
	Tile tTL;
	Tile tTR;
	Tile tBL;
	Tile tBR;
	
	boolean bL=false;
	boolean bR=false;
	boolean bT=false;
	boolean bB=false;
	boolean bTL=false;
	boolean bTR=false;
	boolean bBR=false;
	boolean bBL=false;
	
	
	boolean bEmpty=true;
	
	//Buffers
	boolean bBuffer=false;
	int iBuffer=0;
	int iXBuffer=0;
	int iYBuffer=0;
	
	public Tile()
	{
		
	}
	
	public Tile(int t)
	{
		setType(t);
	}
	
		
	public Tile(int x, int y, int t)
	{
		iX=x;
		iY=y;
		bEmpty=false;
		setType(t);
	}
	
	
	public void setType(int t)
	{
		type=t;
		switch(type)
		{
		case GRASS:
		
		break;
		case FOREST: //grass
		iPixelX=50;
		break;
		case DESERT: //desert
		iPixelX=100;
		break;
		case ICE:
		iPixelX=150;
		break;
		case DIRT:
		iPixelX=200;
		break;
		}
	}
	
	
	
	
	//Getters and Setters
	public int getType()
	{
		return type;
	}
	public int getX()
	{
		return iX;
	}
	public int getY()
	{
		return iY;
	}


	public int getiIdentity() {
		return iIdentity;
	}

	public void setiIdentity(int iIdentity) {
		this.iIdentity = iIdentity;
	}

	public int getiPixelX() {
		return iPixelX;
	}

	public void setiPixelX(int iPixelX) {
		this.iPixelX = iPixelX;
	}

	public int getiPixelY() {
		return iPixelY;
	}

	public void setiPixelY(int iPixelY) {
		this.iPixelY = iPixelY;
	}
	
	
}
