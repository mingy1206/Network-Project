package Client;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


	


// Ŭ���̾�Ʈ
public class Client {

    private static String clientId;

    public static void main(String[] args) throws Exception, IOException{
    	
    	

        String fileName="server_info.dat"; // ������ IP�ּҿ� Port ��ȣ�� �����ִ� ������ �̸�

        DataInputStream inputStream = null; // ������ ������ �о���� ���� ���
        String ip="";// ������ IP�ּ�
        int port=0; // ������ Port ��ȣ

        try {
            inputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName)));

            ip=inputStream.readUTF(); // ���Ϸκ��� IP�ּ� �о����
            port=inputStream.readInt(); // ���Ϸκ��� Port ��ȣ �о����

        }
        catch(FileNotFoundException e) { // ������ ã�� ������ ���

            ip="localhost"; 
            port=3321;
        }
        finally {

            //inputStream.close();
            Socket socket = new Socket(ip,port); // Ŭ���̾�Ʈ ����
            Scanner in=new Scanner(socket.getInputStream()); // �����κ��� ��� ����� �о�� �� ����� ����
            PrintWriter out=new PrintWriter(socket.getOutputStream(),true); // ������ ������ ���� �� ����� ����

           
           
            	
            	new Login(socket);

            
            

            
        }
    }

}