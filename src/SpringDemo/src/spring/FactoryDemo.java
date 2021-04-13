package spring;

public class FactoryDemo {
	public static void main(String[] args)throws Exception {
		
	}
}
abstract class Shoe{
	
}
class ShoeBuilder{
	
}
class LeatherShoe extends Shoe{	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "This is leather shoe made by bata...:";
	}	
}
class SportsShoe extends Shoe{
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "This is sports shoe made by lakhani...";
	}
}

interface Manufacturer{
	
}
interface ShoeManufacturer extends Manufacturer{
	public Shoe makeShoe();
}

abstract class ShoeFactory implements ShoeManufacturer{
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}

class BataShoeFactory extends ShoeFactory{
	public BataShoeFactory() {
		System.out.println("bata shoe factory object created...");
	}
	@Override
	public Shoe makeShoe() {
		return new LeatherShoe();
	}
}

class LakhaniShoeFactory extends ShoeFactory{
	@Override
	public Shoe makeShoe() {
		return new SportsShoe();
	}
}

abstract class Customer{
	private String name;
	public Customer(String name) {
		this.setName(name);
	}
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
}

class ShoeCustomer extends Customer{
	public ShoeCustomer(String name) {
		super(name);
	}
}

interface Seller{
	
}

interface ShoeSeller extends Seller{
	public Shoe sellShoe(Customer customer);
	public void test();
}

abstract class ShoeShop implements ShoeSeller{
	private ShoeFactory factory;
	private String key;
	
	public final ShoeFactory getFactory() {
		return factory;
	}

	public final void setFactory(ShoeFactory factory) {
		System.out.println("set factory called....:"+factory);
		this.factory = factory;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}

class GokulShoeShop extends ShoeShop implements Cloneable {
	
	public GokulShoeShop getClone() {
		try {
			return (GokulShoeShop)super.clone();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public GokulShoeShop() {
		System.out.println("gokul shoe shop created...............");
	}
	@Override
	public Shoe sellShoe(Customer customer) {
		// TODO Auto-generated method stub
		System.out.println("Shoe bought by...:"+customer.getName());
		return getFactory().makeShoe();
	}
	
	@Override
	public void test() {
		System.out.println("Test method called........");
	}
	
	public void setup() {
		System.out.println("Setup called......");
	}
	
	public void destroy() {
		System.out.println("Destroy called....");
	}
}

