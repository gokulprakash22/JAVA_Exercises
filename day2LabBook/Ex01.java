package day2LabBook;

public class Ex01 {
	public static void main(String[] args) {
		int a=1;
		int[] arr= new int[] {1,2,3,4,5};
		System.out.println("The value of integer variable a before calling function "+a);
		passByValue(a);
		System.out.println("The value of integer variable a after calling function "+a);
		System.out.println("The value of array before calling function ");
		for(int i:arr)
			System.out.print(i);
		System.out.println();
		passByReference(arr);
		System.out.println("The value of array after calling function ");
		for(int i:arr)
			System.out.print(i);
	}
	public static void passByValue(int a) //pass by value
	{
		a=2;
	}
	public static void passByReference(int arr[]) //pass by reference
	{
		arr[4]=6;
	}
}