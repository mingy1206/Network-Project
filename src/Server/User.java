package Server;

import java.net.InetAddress;

public class User {
    public  String id; // ���̵�
    public  String name; // �̸�
    public  String nickName; // ����
    public  String email; // �̸���
    public  String phone; // �޴��� ��ȣ
    public  String birthdate; // �������
    public  String connect; // ��������, 'online'�̸� ����X, 'offline'�̸� ����O
    public  String clientIp;// Ŭ���̾�Ʈ IP
    public  int clientPort; // Ŭ���̾�Ʈ Port ��ȣ
    
    public  InetAddress serverIp;// ���� ���� IP
    public  int serverPort; // ���� Port ��ȣ

    public  String lastLoginTime;// ������ �α��� �ð�
    public  String logOutTime;// �α��� �ƿ��� �ð�
    public  String todayWords;// ������ �Ѹ���
    public  int numOfLogins;// �α��� Ƚ��
    
    
    
}