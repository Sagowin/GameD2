
public class Button {
	int iX=0;
	int iY=0;
	int iXSize= 0;
	int iYSize= 0;
	String sMessage="";
	int iFontSize=0;
	final int SMALL=1;
	final int MEDIUM=2;
	final int LARGE=3;
	
	public Button(int x, int y, int size, String s)
	{
		iX=x;
		iY=y;
		sMessage=s;
		
		switch(size)
		{
		case 1:
		{
		iXSize=100;
		iYSize=30;
		break;
		}
		}
		
		iFontSize=0;
		
		
		
		//while()
		
		/*System.out.println("Button Constructed at "+iX+" , "+iY+" with dimensions "+ iXSize+" by "+iYSize+
				" and a font size of "+iFontSize);*/
		
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


	public int getiXSize() {
		return iXSize;
	}


	public void setiXSize(int iXSize) {
		this.iXSize = iXSize;
	}


	public int getiYSize() {
		return iYSize;
	}


	public void setiYSize(int iYSize) {
		this.iYSize = iYSize;
	}


	public String getsMessage() {
		return sMessage;
	}


	public void setsMessage(String sMessage) {
		this.sMessage = sMessage;
	}


	public int getiFontSize() {
		return iFontSize;
	}


	public void setiFontSize(int iFontSize) {
		this.iFontSize = iFontSize;
	}
	
	
	
}
