package designpatterns;
import java.util.Scanner;

public class GoodFanDemo {
	public static void main(String[] args) throws Exception{
		GoodFan fan= FanContainer.getFan();
		Scanner scan=new Scanner(System.in);
		String obj="";
		obj=scan.next();
		while(!(obj.equals("-1"))) {
			fan.pull();
			obj=scan.next();
		}
	}
}

class FanContainer{
	public static GoodFan getFan() throws Exception{
		GoodFan fan=new GoodFan();
		return fan;
	}
}

class GoodFan {
	State state=new SwitchOffState();
	public void pull() {
		state.changeFanState(this);
	}
}
abstract class State{
	public abstract void changeFanState(GoodFan fan);
}
class SwitchOffState extends State{
	@Override
	public void changeFanState(GoodFan fan) {
		System.out.println("switch on state.....");
		fan.state=new SwitchOnState();
	}
}
class SwitchOnState extends State{
	@Override
	public void changeFanState(GoodFan fan) {
		System.out.println("medium speed state...");
		fan.state=new MediumSpeedState();
	}
}
class MediumSpeedState extends State{
	@Override
	public void changeFanState(GoodFan fan) {
		// TODO Auto-generated method stub
		System.out.println("High speed state...");
		fan.state=new HighSpeedState();
		
	}
}
class HighSpeedState extends State{
	@Override
	public void changeFanState(GoodFan fan) {
		System.out.println("switch off state...");
		fan.state=new SwitchOffState();
	}
}