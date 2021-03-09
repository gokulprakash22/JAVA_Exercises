package mypac;
import java.util.StringJoiner;
import java.util.StringTokenizer;

/*
 * % - symbol after which the formating instruction follows
 * [flag] - + (default) or - padding to the right side...  + padding to the left sife...
 * [width] - in padding how many output characters you want....(space)
 * [.] - precision - this is only for floating points...
 * s,d,f - to represent string,integer or float
 *
 */
public class StringFormating {
	public static void main(String[] args) {
		//System.out.printf("formating instruction","input" );
		System.out.printf("Integer value...:%d......%s......is....%f.... the value",200,"hello world",12.4f);
		String s=String.format("\nString:%s,Integer:%06d,Float:%.3f", "hello world",90,12.341178);
		
		System.out.println(s);
		
		System.out.printf("\n%-15s%-15s%s","Column1","Column2","Column3");
		// \n, \t, \f, \b
		
		
		met(1,2,3,4,5);
		
		s="hello world";
		String ss=new String("hello world");//two representation - one in heap and another in stack..
		
		//strings are non mutable..
		String temp=ss;
		ss="world world"+ss;
		
		System.out.println(temp);
		
		System.out.println(ss);
		
		StringBuilder sb=new StringBuilder("hello world from string builder");  //non synchronized		
		System.out.println(sb);		
		sb.append("new value appended");		
		System.out.println(sb);
		
		StringBuffer sbf=new StringBuffer("hello world from string buffer");//synchronized...
		sbf.append("new value for buffer");
		System.out.println(sbf);
		
		StringJoiner sJoiner=new StringJoiner(",");
		System.out.println(sJoiner);
		sJoiner.add("hello");
		sJoiner.add("world");
		System.out.println(sJoiner);
		
		sJoiner=new StringJoiner(",","[","]");
		sJoiner.add("hello");
		sJoiner.add("world");
		sJoiner.add("hai");
		sJoiner.add("earth");
		System.out.println(sJoiner);
		
		StringTokenizer sToken=new StringTokenizer(sJoiner.toString(),",");
		while(sToken.hasMoreTokens()) {
			System.out.println(sToken.nextToken());
		}
	}
	
	public static void met(int... i) {
		for(int ii:i) {
			System.out.println(ii);
		}
	}
}