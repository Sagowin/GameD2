
public class Character {
	int iPosX=0;
	int iPosY=0;
	int iTileX=50;
	int iTileY=50;
	int posinTileX=25;
	int posinTileY=25;
	int iSuperTile=0;
	int iXinSuperTile=50*50;
	int iYinSuperTile=50*50;
	
	public Character(int x, int y) {
		
		iPosX=x;
		iPosY=y;
						
	}
	
	//Misc. Methods
	
	public void assessPosition(int iFrameX, int iFrameY, int xEdgeAdjust, int yEdgeAdjust)
	{
		iTileX=iXinSuperTile/50;
		iTileY=iYinSuperTile/50;
		posinTileX=iXinSuperTile%50;
		posinTileY=iYinSuperTile%50;
		iPosX=50*(iTileX-iFrameX+10)+posinTileX-xEdgeAdjust;
		iPosY=50*(iTileY-iFrameY+10)+posinTileY-yEdgeAdjust;
	}
	public int getiXinSuperTile() {
		return iXinSuperTile;
	}
	public void setiXinSuperTile(int iXinSuperTile) {
		this.iXinSuperTile = iXinSuperTile;
	}
	public int getiYinSuperTile() {
		return iYinSuperTile;
	}
	public void setiYinSuperTile(int iYinSuperTile) {
		this.iYinSuperTile = iYinSuperTile;
	}

	//Good Get/Set Methods
	public int getX()
	{
		return iPosX;
	}
	public int getY()
	{
		return iPosY;
	}
	public void addX(int x)
	{
		iPosX+=x;
	}
	public void addY(int y)
	{
		iPosY+=y;
	}
	public int getiTileX() {
		return iTileX;
	}
	public void setiTileX(int iTileX) {
		this.iTileX = iTileX;
	}
	public int getiTileY() {
		return iTileY;
	}
	public void setiTileY(int iTileY) {
		this.iTileY = iTileY;
	}
	public void addiTileX(int a)
	{
		iTileX+=a;
	}
	public void addiTileY(int a)
	{
		iTileY+=a;
	}
	public int getiSuperTile() {
		return iSuperTile;
	}
	public void setiSuperTile(int iSuperTile) {
		this.iSuperTile = iSuperTile;
	}
	public void addiXinSuperTile(int a)
	{
		iXinSuperTile+=a;
	}
	public void addiYinSuperTile(int a)
	{
		iYinSuperTile+=a;
	}
	
	public void breakPoint()
	{
		
	}
	//Bad Methods
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
