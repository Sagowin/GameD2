
public class Character {
	int iPosX=0;
	int iPosY=0;
	int iTileX=50;
	int iTileY=50;
	int posinTileX=25;
	int posinTileY=25;
	
	public Character(int x, int y) {
		
		iPosX=x;
		iPosY=y;
						
	}
	public int getX()
	{
		return iPosX;
	}
	public int getY()
	{
		return iPosY;
	}
	public void setX(int x)
	{
		iPosX=x;
	}
	public void setY(int y)
	{
		iPosY=y;
	}
	public int getTileX()
	{
		return iTileX;
	}
	public int getTileY()
	{
		return iTileY;
	}
	public void subTileX()
	{
		iTileX--;
	}
	public void subTileX(int s)
	{
		iTileX-=s;
	}
	public void addTileX()
	{
		iTileX++;
	}
	public void addTileX(int a)
	{
		iTileX+=a;
	}
	public void subTileY()
	{
		iTileY--;
	}
	public void subTileY(int s)
	{
		iTileY-=s;
	}
	public void addTileY()
	{
		iTileY++;
	}
	public void addTileY(int a)
	{
		iTileY+=a;
	}
	public void dPosX(int a)
	{
		posinTileX+=a;
	}
	public void dPosY(int a)
	{
		posinTileY+=a;
	}
	public int getinTilePosX()
	{
		return posinTileX;
	}
	public int getinTilePosY()
	{
		return posinTileY;
	}
}
