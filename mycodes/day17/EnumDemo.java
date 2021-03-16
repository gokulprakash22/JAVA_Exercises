package demo;

public class EnumDemo {
	public static void main(String[] args) {
		Cars car;
		car=Cars.maruti;
		met(car);
	}
	
	public static void met(Cars car) {
		if(car==Cars.maruti) {
			System.out.println("its a maruti.........");
		}
		else if(car==Cars.fiat) {
			System.out.println("its a fiat...");
		}
		
		switch(car){
		case maruti:{
			System.out.println("switch case - its a maruti....");
			break;
		}
		case fiat:{
			System.out.println("switch case- its a fiat....");
		}
		default:{
			System.out.println("unknown..........");
		}
		}
		
		Cars c[]=Cars.values();
		for(Cars cc:c) {
			System.out.println(cc.getPrize());
		}
		Cars.maruti.setPrize(500);
		System.out.println("maruti prize..:"+Cars.maruti.getPrize());
	}
}
enum Cars{
	maruti(1000),suzuki(2000),porsche(3000),fiat(4000),tata(9000);
	private int prize;
	Cars(int prize){
		this.prize=prize;
	}
	
	public int getPrize() {
		return this.prize;
	}
	public void setPrize(int prize) {
		this.prize=prize;
	}
}