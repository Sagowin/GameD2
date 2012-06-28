
public class TileObject {
	
	public int iX=0; //Tile X in SuperTile
	public int iY=0; //Tile Y in SuperTile
	public int iPixelX=0;
	public int iPixelY=0;
	public int iSuperTile=0; //Which Super Tile it is In
	
	public TileObject(int x, int y, int ST)
	{
		iX=x;
		iY=y;
	}
	
	
	
	public int getiPixelX() {
		return iPixelX;
	}



	public int getiPixelY() {
		return iPixelY;
	}



	public int getiSuperTile() {
		return iSuperTile;
	}
	public void setiSuperTile(int iSuperTile) {
		this.iSuperTile = iSuperTile;
	}
	public void printPosition()
	{
		System.out.println(iX+" "+iY);
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
	public void diX(int d)
	{
		iX+=d;
	}
	public void diY(int d)
	{
		iY+=d;
	}
}
