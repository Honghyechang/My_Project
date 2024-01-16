
public class Ex03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int arr1[]= {1,2,3,4,5};
		int arr2[]= {1,2,3,4,6};
		
		boolean result=equalInt(arr1, arr2);
		System.out.println("둘이 같나요???"+result);

	}

	public static boolean equalInt(int arr1[],int arr2[])
	{
		boolean result =true;
		if(arr1.length!=arr2.length)return false;
		
		for(int i=0; i<arr1.length; i++)
		{
			if(arr1[i]!=arr2[i])
			{
				result=false;
				break;
			}
		}
		return result;
	}
}
