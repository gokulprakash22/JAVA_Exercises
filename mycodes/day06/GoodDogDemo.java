package yourpack;
import java.util.Scanner;

public class GoodDogDemo {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		String obj="";
		obj="mypac."+scan.next();
		try {
			while(!(obj.equals("mypac.-1"))) {
				Item item=(Item)Class.forName(obj).newInstance();
				item.doAction();
				obj="mypac."+scan.next();
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}


abstract class Item
{
	abstract void doAction();
}
class Stone extends Item
{
	@Override
	void doAction() {
		System.out.println("you hit I bark.....");
	}
}

class Stick extends Item
{
	@Override
	void doAction() {
		System.out.println("you beat I bite......");
		
	}
}