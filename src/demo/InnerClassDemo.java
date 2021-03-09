package demo;

public class InnerClassDemo {
	public static void main(String[] args) {
		Pepsi pepsico=new Pepsi();
		Kalimark annachi=new Kalimark();
		pepsico.makePepsiCola();
		annachi.makeBovontoCola();
	}
}
abstract class Cola {
	public abstract void makeCola();
}
class Pepsi {
	public void makePepsiCola() {
		//Cola cola = new Kalimark().new CampaCola();
        //Cola cola = new Kalimark().getColaTrojan();
        //cola.makeCola();
        //System.out.println("pepsi sells the campa cola in pepsi bottle....");
	}
}

class Kalimark {
	int money;
	
	public void makeBovontoCola() {
		//anonymous inner class
		new Cola() {			
			@Override
			public void makeCola() {
				System.out.println("campa cola cola now belong to kalimark..its now kalimark cola...");
			}
		}.makeCola();
	}
//	public void makeBovontoCola() {
//		//local inner class
//		class CampaCola extends Cola{
//			int campamoney=1000;
//			@Override
//			public void makeCola() {
//				System.out.println("cola made by campa cola...."+money);
//			}
//		}
//		Cola cola=new CampaCola();
//		cola.makeCola();
//		
//		System.out.println("kalimark sells the campa cola in bovonto bottle..."+new CampaCola().campamoney);
//	}
	
	//inner class
//	class CampaCola extends Cola{
//		int campamoney=1000;
//		@Override
//		public void makeCola() {
//			System.out.println("cola made by campa cola...."+money);
//		}
//	}
//public Cola getColaTrojan() {
//	return new CampaCola();
//}
}
