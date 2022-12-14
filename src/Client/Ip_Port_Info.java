package Client;
import java.io.*;
import java.util.*;

// server_info.dat �̶�� ������ IP�ּҿ� Port ��ȣ�� �����ϴ� ���� ����
public class Ip_Port_Info {

    public static void main(String[] args) throws IOException {

    String fileName="server_info.dat"; // server_info.dat �̶�� ������ IP�ּҿ� Port ��ȣ�� �����ϴ� ������ �̸� 

    DataOutputStream outputStream = null; // ���Ͽ� ������ ���� ���� ���

    try {

        outputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
        outputStream.writeUTF("127.0.0.1"); // IP�ּҸ� ���Ͽ� ����
        outputStream.writeInt(3321); // Port ��ȣ�� ���Ͽ� ����

    }
    catch(FileNotFoundException e){ // ������ ã�� ������ ���

        System.out.println("Problem opening the file" + fileName);

    }
    finally {

        outputStream.close();
    }

    }
}