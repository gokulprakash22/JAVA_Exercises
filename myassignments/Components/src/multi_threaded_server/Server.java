package multi_threaded_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server=null;
        Socket client=null;
        int clientcount=0;
        ExecutorService es = Executors.newFixedThreadPool(10);
    	server=new ServerSocket(5000);
    	System.out.println("Server Started");
    	
        while(true)
        {
            client=server.accept();
            clientcount++;
            ServerThread runnable= new ServerThread(client,clientcount);
            es.execute(runnable);
        }
    }
    
    private static class ServerThread implements Runnable {
        Socket client=null;
        BufferedReader cin;
        PrintWriter cout;
        Scanner sc=new Scanner(System.in);
        int id;
        String msg;
        
        ServerThread(Socket client, int count) throws IOException {
            this.client=client;
            this.id=count;
            System.out.println("Connection "+id+"established with client "+client);
            
            cin=new BufferedReader(new InputStreamReader(client.getInputStream()));
            cout=new PrintWriter(client.getOutputStream());
        
        }

        @Override
        public void run() {
           boolean flag=false;
           try{
        	   while(true){
        		   msg=cin.readLine();
        		   System. out.print("Client("+id+") :"+msg+"\n");
        		   System.out.print("Server : ");
                   msg=sc.nextLine();
                   if (msg.equalsIgnoreCase("bye")) {
                      cout.println("BYE");
                      flag = true;
                      System.out.println("Connection ended by server");
                      break;
                   }
        	   }
        	   if(flag) {
        		   System.exit(1);
        	   }
            
                cin.close();
                client.close();
                cout.close();
//                server.close();
         } 
         catch(Exception e){
             e.printStackTrace();
         }
       }
    }
}