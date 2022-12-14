package Server;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import Client.Login;

public class chatting_client {




	public static int port=5000;
	public static String IP="localhost";

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception, IOException {
		Socket socket = null;
		socket = new Socket(IP,port);// ������ IP�ּ�
		Scanner scanner = new Scanner(System.in);
		Scanner in = new Scanner(socket.getInputStream()); // �����κ��� ��� ����� �о�� �� ����� ����
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // ������ ������ ���� �� ����� ����'
		
		
		while(true){//client�� �Է��ϴ� ���� ���������� 1�� ���
			if(scanner.hasNextLine())out.println(scanner.nextLine());// server�� ����
			if(in.hasNextLine())System.out.println(in.nextLine());// server���� �� ���� ���
		}

	}

}
