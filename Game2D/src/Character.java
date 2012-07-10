
public class Character {
	
	int iPosX=0;
	int iPosY=0;
	int iScreenX=500;
	int iScreenY=500;
	int iXTile=0;
	int iYTile=0;
	int posinTileX=25;
	int posinTileY=25;
	
	
	
	public Character(int x, int y) {
		
		iXTile=x;
		iYTile=y;
		
						
	}
	
	
	public void correctCharacter(int fX, int fY, int xAd, int yAd)
	{
		
	
		iScreenX=500+iPosX-50*fX-xAd;
		iScreenY=500-iPosY+50*fY-yAd;
		
		if(iPosX>-1)
		{
			iXTile=iPosX/50;
		}else
		{
			iXTile=(iPosX-50)/50;
		}
		if(iPosY>-1)
		{
			iYTile=iPosY/50;
		}else
		{
			iYTile=(iPosY-50)/50;
		}
		
		posinTileX=iPosX&50;
		posinTileY=iPosY%50;
		
	}
	
	
	
	public int getiXTile() {
		return iXTile;
	}
	public void setiXTile(int iXTile) {
		this.iXTile = iXTile;
	}
	public int getiYTile() {
		return iYTile;
	}
	public void setiYTile(int iYTile) {
		this.iYTile = iYTile;
	}
	public int getPosinTileX() {
		return posinTileX;
	}
	public void setPosinTileX(int posinTileX) {
		this.posinTileX = posinTileX;
	}
	public int getPosinTileY() {
		return posinTileY;
	}
	public void setPosinTileY(int posinTileY) {
		this.posinTileY = posinTileY;
	}
		
	
}