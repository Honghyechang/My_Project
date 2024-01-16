
public class Ex01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] arr=new int[10];
		int sum=0;
		for(int i=0; i<10; i++)
		{
			arr[i]=(int)(Math.random()*100)+101;
			System.out.print(" "+ arr[i]);
			sum+=arr[i];
		}
		double avg=(double)sum/arr.length;
		System.out.println("\n평균 : "+avg);
		
	}

}
