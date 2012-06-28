import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;


public class ButtonHolder {
	
	ArrayList<Button> b = new ArrayList<Button>();
	//Button testButton= new Button(1100, 300, 50 ,40,"1234567890");
	boolean bBuffer=true;
	
	public ButtonHolder()
	{
		//b.add(testButton);
	}
	
	public void drawButtons(Graphics g)
	{
		
		
		for(int a=0;a<b.size();a++)
		{
			
			g.drawRect(	b.get(a).getiX(),
						b.get(a).getiY(),
						b.get(a).getiXSize(),
						b.get(a).getiYSize());
			
			g.setFont(new Font("New Courier",Font.BOLD,b.get(a).getiFontSize()));
			
			g.drawString(b.get(a).getsMessage(),
					b.get(a).getiX()/*+b.get(a).getiXSize()/8*/,
					b.get(a).getiY()+b.get(a).getiYSize()*5/8 );
			
		}
	}
	
	public void add(Button newB)
	{
		b.add(newB);
	}
	
	public Button get(int i)
	{
		return b.get(i);
	}

}
