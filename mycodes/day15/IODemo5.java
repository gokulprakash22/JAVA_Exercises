package inputoutput;

import java.io.ByteArrayInputStream;
import java.io.SequenceInputStream;
import java.io.StringBufferInputStream;

public class IODemo5 {
	public static void main(String[] args) throws Exception {
		ByteArrayInputStream bis = new ByteArrayInputStream("helloWorld".getBytes());
		StringBufferInputStream sbis = new StringBufferInputStream("hello hai world");
		SequenceInputStream sis = new SequenceInputStream(bis,sbis);
		int c = 0;
		while((c=sis.read())!=-1) {
			System.out.print((char)c);
		}
	}
}
