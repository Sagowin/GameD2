
public class Tile {

	int type=0;
	int tileX=0;
	int tileY=0;
	final int GRASS=0;
	final int DESERT=1;
	final int ICE=2;
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
		case 1: //grass
		tileX=100;
		break;
		case 2: //desert
		tileX=150;
		break;
		}
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
