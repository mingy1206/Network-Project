package Server;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.IOException;


public class chatting_server {
	public static int port;
	public chatting_server(int p)
	{
		port = 5000;
	}
	public static void main(String[] args) throws Exception{
		
		ServerSocket welcomeSocket = new ServerSocket(5000);//welcome socket�� port nmber�� 1234�� ���� ������ ���õ� default���� 1234 
		System.out.println("chatting_server is runing");
		ExecutorService pool = Executors.newFixedThreadPool(20);//�ִ� 20���� client�� ���ÿ� ���� ����
		while(true) {//���ö����� ��ٸ�����
			Socket socket = welcomeSocket.accept(); //client���� �����û�� ������ ���� socket�� ���θ���
			pool.execute(new Operation(socket));//thread�� �����ϰ� ���� socket�� ����
		}
	}
	
	private static class Operation implements Runnable{
		private Socket socket;
		
		
		Operation(Socket socket){
			this.socket = socket;//thread�� socket�� ����Ǿ� �ִ� socket�� �˷���
		}
		@Override
		public void run() {
			System.out.println("Connected: "+ socket);//����Ǿ����� message
			try {
				String message = null;
				Scanner scanner = new Scanner(System.in);//message
				Scanner in = new Scanner(socket.getInputStream());// client���� inputstream
				PrintWriter out = new PrintWriter(socket.getOutputStream(),true);// client���� Outstream
				while (true) {
					if(scanner.hasNextLine())
					{
						message = in.nextLine();
						out.println(message);
					}
					if(in.hasNextLine()) {
						message = in.nextLine();
						System.out.println(message);
				}
					
					
					
					
					
				}
			}catch(Exception e) {
				System.out.println("Error" + socket);
			}finally {//����ó���� ���ý� socket�� �ݾ��ְ� �̸� �˷���
				try {socket.close();} catch(IOException e) {}
				System.out.println("Closed"+socket);
			}
			
		}
	}

}
