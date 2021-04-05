package demo;

import javax.xml.ws.Endpoint;

public class Publisher {
 public static void main(String[] args) {
	Endpoint.publish("http://localhost:5000/ws/hello", new HelloServiceImpl());
	System.out.println("Service Published...");
}
}
