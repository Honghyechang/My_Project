
public class Ex05 {
	
	public static void main(String[] args)
	{
		
	}

}

class Polygon
{
	private int x[];
	private int y[];
	private int size1=5;
	private int size2=5;
	public Polygon()
	{
		x=new int[size1];
		y=new int[size2];
	}
	
	public Polygon(int x[],int y[])
	{
		this.x=x;
		this.y=y;
		size1=x.length;
		size2=y.length;
	}
	
	public String toString()
	{
		String str="배열 1 : ";
		for(int i=0; i<size1; i++)
		{
			str+=Integer.toString(x[i]);
		}
		 str+="배열 2 : ";
		for(int i=0; i<size2; i++)
		{
			str+=Integer.toString(y[i]);
		}
		
		return str;
	}
	
	public void euqals()
	{
		boolean result=true;
		
		if(size1!=size2)
		{
			//size
		}
	}

}
