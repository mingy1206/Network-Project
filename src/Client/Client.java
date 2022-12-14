package Client;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


	


// 클라이언트
public class Client {

    private static String clientId;

    public static void main(String[] args) throws Exception, IOException{
    	
    	

        String fileName="server_info.dat"; // 서버의 IP주소와 Port 번호가 적혀있는 파일의 이름

        DataInputStream inputStream = null; // 파일의 내용을 읽어오기 위해 사용
        String ip="";// 서버의 IP주소
        int port=0; // 서버의 Port 번호

        try {
            inputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName)));

            ip=inputStream.readUTF(); // 파일로부터 IP주소 읽어오기
            port=inputStream.readInt(); // 파일로부터 Port 번호 읽어오기

        }
        catch(FileNotFoundException e) { // 파일을 찾지 못했을 경우

            ip="localhost"; 
            port=3321;
        }
        finally {

            //inputStream.close();
            Socket socket = new Socket(ip,port); // 클라이언트 소켓
            Scanner in=new Scanner(socket.getInputStream()); // 서버로부터 계산 결과를 읽어올 때 사용할 변수
            PrintWriter out=new PrintWriter(socket.getOutputStream(),true); // 서버로 계산식을 보낼 때 사용할 변수

           
           
            	
            	new Login(socket);

            
            

            
        }
    }

}