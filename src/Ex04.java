import java.util.Scanner;

public class Ex04 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);
		System.out.println("정답 입력 : ");
		int answer=scanner.nextInt();
		System.out.println("배열 크기 입력 : ");
		int size=scanner.nextInt();
		System.out.println("배열 값 입력 : ");
		int arr[]=new int[size];
		for(int i=0; i<arr.length; i++)
		{
			arr[i]=scanner.nextInt();
		}
		
		int index1=-1,index2=-1;
		boolean flag=false;
		for(int i=0; i<arr.length; i++)
		{
			
			for(int j=0; j<arr.length; j++)
			{
				if(i==j)continue;
				if(arr[i]+arr[j]==answer)
				{
					index1=i;
					index2=j;
					flag=true;
					break;
				}
			}
			if(flag==true)break;
		}
		
		System.out.println("결과 : ["+index1+","+index2+"]");
		scanner.close();
	}
	
	

}
