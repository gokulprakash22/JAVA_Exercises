package mypac;
import java.util.ArrayList;

public class GenericsDemo {
	public static void main(String[] args) {
		 PaintBrush<Water> brush=new PaintBrush<>();
		 brush.obj=new Water();
		 Water water=brush.getObj();
		 System.out.println(water);
		 PaintBrush<Paint> brush2=new PaintBrush<>();
		 brush2.obj=new RedPaint();
		 Paint paint=(Paint)brush2.getObj();
		 System.out.println(paint);
		 System.out.println("----------");
		 System.out.println("ArrayListDemo");
		 int arr[]=new int[5];
		 ArrayList<String> arrList=new ArrayList<>();
		 arrList.add("aaaa");
		// arrList.add(122);
		// arrList.add(1.3f);
		 for(int i=0;i<arrList.size();i++) {
			 String s=(String)arrList.get(i);
		 } 
		 
		 GenericsDemo2 obj=new GenericsDemo2();
		 obj.getObject(new String[] {"a","b","c"});
		 obj.getObject(new Integer[] {1,2,3,4});
	 }
}
class PaintBrush<T>{
	T obj;
	public T getObj() {
		return obj;
	}
	public void setObj(T obj) {
		this.obj=obj;
	}
}
abstract class Paint{

}
class RedPaint extends Paint{

}
class Water{

}

class GenericsDemo2 {
	public <E> E getObject(E[] elements){
		 for(E element:elements) {
			 System.out.println(element);
		 }
		 return null;
	 }
}