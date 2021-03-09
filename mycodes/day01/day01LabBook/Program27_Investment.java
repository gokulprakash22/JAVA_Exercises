package day1;

public class Program27_Investment {
	public static void main(String[] args) {
		double amount = 14000+(14000*0.40);
		System.out.println("The amount is "+amount+" at the end of First Year");
		amount-=1500;
		System.out.println("The amount is "+amount+" at the end of Second Year");
		amount+=(amount*0.12);
		System.out.println("The amount is "+amount+" at the end of Third Year");
	}
}