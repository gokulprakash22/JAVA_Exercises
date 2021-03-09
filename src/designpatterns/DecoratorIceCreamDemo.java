package designpatterns;

public class DecoratorIceCreamDemo {
	public static void main(String[] args) {		
		IceCream cream=new Nuts(new Vanila(new DryFruit(new TruityFruity())));
		System.out.println("Cost of vanila icecream...:"+cream.cost());
	}
}
abstract class IceCream{
	public abstract int cost();
}
abstract class Cream extends IceCream{}
abstract class IceCreamIngredients extends IceCream{}
class Vanila extends Cream{
	IceCream iceCream;
	public Vanila() {
		// TODO Auto-generated constructor stub
	}
	public Vanila(IceCream iceCream) {
		this.iceCream=iceCream;
	}
	@Override
	public int cost() {
		// TODO Auto-generated method stub
		if(this.iceCream==null) {
			return 10;
		}
		else {
			return 10+this.iceCream.cost();
		}
	}
}
class StrawBerry extends Cream{
	IceCream iceCream;
	public StrawBerry() {
		// TODO Auto-generated constructor stub
	}
	public StrawBerry(IceCream iceCream) {
		this.iceCream=iceCream;
	}
	@Override
	public int cost() {
		// TODO Auto-generated method stub
		if(this.iceCream==null) {
			return 15;
		}
		else {
			return 15+this.iceCream.cost();
		}
	}
}
class TruityFruity extends Cream{
	IceCream iceCream;
	public TruityFruity() {
		// TODO Auto-generated constructor stub
	}
	public TruityFruity(IceCream iceCream) {
		this.iceCream=iceCream;
	}
	@Override
	public int cost() {
		// TODO Auto-generated method stub
		if(this.iceCream==null) {
			return 20;
		}
		else {
			return 20+this.iceCream.cost();
		}
	}
}
class DryFruit extends IceCreamIngredients{
	IceCream iceCream;
	
	public DryFruit(IceCream iceCream) {
		this.iceCream=iceCream;
	}
	@Override
	public int cost() {
		// TODO Auto-generated method stub
		if(this.iceCream==null) {
			return 5;
		}
		else {
			return 5+this.iceCream.cost();
		}
	}
}
class Nuts extends IceCreamIngredients{
	IceCream iceCream;
	
	public Nuts(IceCream iceCream) {
		this.iceCream=iceCream;
	}
	@Override
	public int cost() {
		// TODO Auto-generated method stub
		if(this.iceCream==null) {
			return 10;
		}
		else {
			return 10+this.iceCream.cost();
		}
	}
}
