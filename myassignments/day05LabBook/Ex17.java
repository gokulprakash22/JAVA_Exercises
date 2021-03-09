package day5LabBook;

class Base {}
class Sub extends Base {}
class Sub2 extends Base {}
public class Ex17{
 public static void main(String argv[]){
 Base b=new Base();
 Sub s=(Sub) b; //Cannot able to cast
 } 
}
//Run time exception