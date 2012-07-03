
public class Tile {

	int type=-1;
	int tileX=0;
	int tileY=0;
	final int GRASS=1;
	final int FOREST=0;
	final int DESERT=3;
	final int ICE=4;
	final int DIRT=2;
	
	public Tile()
	{
		
	}
	
	public Tile(int t)
	{
		setType(t);
	}
	
	public void setType(int t)
	{
		type=t;
		switch(type)
		{
		case GRASS:
		tileX=0;
		break;
		case FOREST: //grass
		tileX=50;
		break;
		case DESERT: //desert
		tileX=100;
		break;
		case ICE:
		tileX=150;
		break;
		case DIRT:
		tileX=200;
		break;
		}
	}
	
	public int getType()
	{
		return type;
	}
	
	public int getX()
	{
		return tileX;
	}
	
	public int getY()
	{
		return tileY;
	}
	
	
}
