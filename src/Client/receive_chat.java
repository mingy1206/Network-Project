package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class receive_chat extends Thread {
	Socket socket;
	String message =null;

	receive_chat(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			Scanner in = new Scanner(socket.getInputStream());
			while(in.hasNextLine())
			{
				if(in.nextLine().equals("7700"))
				{
					message = in.nextLine();
					//text field¿¡ ¶ç¿ì±â
				}
				else if(in.nextLine().equals("7701"))
				{
					message = "EXIT CHAT";
					//text field¿¡ ¶ç¿ì±â	
					break;
				}
				

				
			}
			socket.close();
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}