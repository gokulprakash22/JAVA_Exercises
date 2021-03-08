package inputoutput;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;

public class IODemo3 {
	public static void main(String[] args)throws Exception {
		String str="Jack and &copy jill went &copy; up the hill..............";
		
		ByteArrayInputStream bis=new ByteArrayInputStream(str.getBytes());
		BufferedInputStream bfs=new BufferedInputStream(bis);
		
		CharArrayReader car = new CharArrayReader(str.toCharArray());
		BufferedReader br = new BufferedReader(car);
		
		System.out.println(bfs.markSupported());
		int c=0;
		boolean mark=false;
		while((c=br.read())!=-1) {
			switch((char)c){	
				case '&':{
					if(!mark) {
						br.mark(32);
						mark=true;
					}
					break;
				}
				case ';':{
					if(mark) {
						System.out.print((char)169);
						mark=false;
					}
					break;
				}
				case ' ':{
					if(mark) {
						br.reset();
						mark=false;
						System.out.print("&");
					}
					else {
						System.out.print(" ");
					}
						break;
				}
				default:{
					if(!mark) {
						System.out.print((char)c);
					}
				}
			}
		}
	}
}