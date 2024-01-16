import java.util.Scanner;

public class Ex02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("배열의 크기를 입력하시오 : ");
		Scanner scanner=new Scanner(System.in);
		int answer=scanner.nextInt();
		int arr[]=new int[answer];
		System.out.println("배열의 입력값을 띄어쓰기를 활용하여 입력하시오 : ");
		for(int i=0; i<answer; i++)
		{
			arr[i]=scanner.nextInt();
		}
		int single=singleNumber(arr);
		System.out.println("결과 : "+single);
		scanner.close();
	}
	
	public static int singleNumber(int arr[])
	{
		int single=-1;
		for(int i=0; i<arr.length; i++)
		{
			int n=arr[i];
			int count=1;
			for(int j=0; j<arr.length; j++)
			{
				if(j==i)continue;
				if(n==arr[j])
				{
					count++;
				}
			}
			
			if(count!=2)
			{
				single=n;
			}
		}
		return single;
	}

}
