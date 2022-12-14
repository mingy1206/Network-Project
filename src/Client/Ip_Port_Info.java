package Client;
import java.io.*;
import java.util.*;

// server_info.dat 이라는 서버의 IP주소와 Port 번호를 저장하는 파일 생성
public class Ip_Port_Info {

    public static void main(String[] args) throws IOException {

    String fileName="server_info.dat"; // server_info.dat 이라는 서버의 IP주소와 Port 번호를 저장하는 파일의 이름 

    DataOutputStream outputStream = null; // 파일에 내용을 쓰기 위해 사용

    try {

        outputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
        outputStream.writeUTF("127.0.0.1"); // IP주소를 파일에 쓰기
        outputStream.writeInt(3321); // Port 번호를 파일에 쓰기

    }
    catch(FileNotFoundException e){ // 파일을 찾지 못했을 경우

        System.out.println("Problem opening the file" + fileName);

    }
    finally {

        outputStream.close();
    }

    }
}