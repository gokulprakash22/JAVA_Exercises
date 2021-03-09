package day04LabBook;

public class Program06 {

}
class BaseClass extends Exception{
	public BaseClass(String s) {
		System.out.println("Base class exception caught");
	}
}
class DerievedClass extends Program06{
	public void DerivedClass() {
		try {
			throw new BaseClass("error");
		}
		catch(BaseClass e){
			e.printStackTrace();
		}
	}
}