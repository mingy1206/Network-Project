package Client;


import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.json.simple.parser.ParseException;

public class ApiWeather {
	static String[][] data = new String[6][2];

    public static String[][] getWeather() throws IOException, ParseException{
        String apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst";
        // Ȩ���������� ���� Ű 
        String serviceKey = "z1yF8I0dKCXiMtqLxrOB50flBkAdB1HRrNdmRSJxZY8dQbsZQG5pFrABuegUpQYFod5EBzSbhMzkubgPDwNxqg%3D%3D";
        String nx = "37.4556163";// ����
        String ny = "127.133144";// �浵
        TransLocalPoint.LatXLngY data1;
        data1 = TransLocalPoint.convertGRID_GPS(0, Double.parseDouble(nx), Double.parseDouble(ny));
        System.out.println(data1.lat);
        System.out.println(data1.lng);
        System.out.println(data1.x);
        System.out.println(data1.y);
        nx = String.valueOf((int)data1.x);
        ny = String.valueOf((int)data1.y);
        System.out.println("nx : " + nx + "ny : " + ny);
        String baseDate = "20221213";// ��ȸ�ϰ���� ��¥
        String baseTime = "1800";// ��ȸ�ϰ���� �ð�
        String type = "JSON";// Ÿ�� xml, json ��� ..
        StringBuilder urlBuilder1 = new StringBuilder(apiUrl);
        urlBuilder1.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + serviceKey);
        urlBuilder1.append("&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); // �浵 
        urlBuilder1.append("&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); // ����
        urlBuilder1.append("&" + URLEncoder.encode("base_date", "UTF-8") + "=" + URLEncoder.encode(baseDate, "UTF-8")); /* ��ȸ�ϰ���� ��¥ */
        urlBuilder1.append("&" + URLEncoder.encode("base_time", "UTF-8") + "=" + URLEncoder.encode(baseTime, "UTF-8")); /* ��ȸ�ϰ���� �ð� AM 02�ú��� 3�ð� ���� */
        urlBuilder1.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8"));/* Ÿ�� */
        /* GET������� �����ؼ� �Ķ���� �޾ƿ��� */
        URL url = new URL(urlBuilder1.toString());
        // ��� �Ѿ���� Ȯ���ϰ� ������ �Ʒ� ��º� �ּ� ����
        // System.out.println(url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        // System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        String result = sb.toString();
        // System.out.println(result);
        
        // Json parser�� ����� ������� ���ڿ� �����͸� ��üȭ
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(result); 
		// response Ű�� ������ �����͸� �Ľ�
		JSONObject parse_response = (JSONObject) obj.get("response"); 
		// response �� ���� body ã��
		JSONObject parse_body = (JSONObject) parse_response.get("body"); 
		// body �� ���� items ã�� 
		JSONObject parse_items = (JSONObject) parse_body.get("items");

		// items�� ���� itemlist �� �ޱ� 
		JSONArray parse_item = (JSONArray) parse_items.get("item");
        String category;
		JSONObject weather; // parse_item�� �迭�����̱� ������ �ϳ��� �����͸� �ϳ��� �����ö� ���
		// ī�װ��� ���� �޾ƿ���
		String day="";
		String time="";
        // data [���, 1�ð� ������, ����, ��������, ǳ��, ǳ��]
        // data [T1H, RN1, REH, PTY, VEC, WSD]
        // String[] cate = {"T1H(���, ��)", "RN1(1�ð� ������, mm)", "REH(����, %)", "PTY(�ڵ尪)", "VEC(ǳ��, deg)", "WSD(ǳ��, m/s)"};
		String[] cate = {"���", "1�ð� ������", "����", "��������", "ǳ��", "ǳ��"};
		for(int i=0; i<6; i++) {
        	data[i][0] = cate[i];
        }
		for(int i = 0 ; i < parse_item.size(); i++) {
			weather = (JSONObject) parse_item.get(i);
            System.out.println(weather);
			Object fcstValue = weather.get("obsrValue");
			Object fcstDate = weather.get("baseDate");
			Object fcstTime = weather.get("baseTime");
			//double������ �ް������ �Ʒ����� �ּ� ����
			//double fcstValue = Double.parseDouble(weather.get("fcstValue").toString());
			category = (String)weather.get("category"); 
            // System.out.println(category);
			// ���
			if(!day.equals(fcstDate.toString())) {
				day=fcstDate.toString();
			}
			if(!time.equals(fcstTime.toString())) {
				time=fcstTime.toString();
				System.out.println(day+"  "+time);
			}

            if(category.equals("T1H")){
                data[0][1] = (String)fcstValue;
            }
            if(category.equals("RN1")){
                data[1][1] = (String)fcstValue;
            }
            if(category.equals("REH")){
                data[2][1] = (String)fcstValue;
            }
            if(category.equals("PTY")){
                int temp = Integer.parseInt((String) fcstValue);
                String temps = "";
                if(temp==1){
                    temps = "��";
                } else if(temp==2){
                    temps = "��/��";
                } else if(temp==3){
                    temps = "��";
                } else if(temp==5){
                    temps = "�����";
                } else if(temp==6){
                    temps = "����ﴫ����";
                } else if(temp==7){
                    temps = "������";
                } else{
                    temps = "����";
                }
                data[3][1] = temps;
            }
            if(category.equals("VEC")){
                int temp = Integer.parseInt((String) fcstValue);
                String temps = "";
                if(temp > 0 && temp < 45){
                    temps = "��-�ϵ�(N-NE)";
                } else if(temp == 45){
                    temps = "�ϵ�(NE)";
                } else if(temp > 45 && temp < 90){
                    temps = "�ϵ�-��(NE-E)";
                } else if(temp == 90){
                    temps = "��(E)";
                } else if(temp > 90 && temp < 135){
                    temps = "��-����(E-SE)";
                } else if(temp == 135){
                    temps = "����(SE)";
                } else if(temp > 135 && temp < 180){
                    temps = "����-��(SE-S)";
                } else if(temp == 180){
                    temps = "��(S)";
                } else if(temp > 180 && temp < 225){
                    temps = "��-����(S-SW)";
                } else if(temp == 225){
                    temps = "����(SW)";
                } else if(temp > 225 && temp < 270){
                    temps = "����-��(SW-W)";
                } else if(temp == 270){
                    temps = "��(W)";
                } else if(temp > 270 && temp < 315){
                    temps = "��-�ϼ�(W-NW)";
                } else if(temp == 315){
                    temps = "�ϼ�(NW)";
                } else if(temp > 315 && temp < 360){
                    temps = "�ϼ�-��(NW-N)";
                } else {
                    temps = "N";
                }
                data[4][1] = temps;
            }
            if(category.equals("WSD")){
                data[5][1] = (String)fcstValue;
            }
			// System.out.print("\tcategory : "+ category);
			// System.out.print(", fcst_Value : "+ fcstValue);
			// System.out.print(", fcstDate : "+ fcstDate);
			// System.out.println(", fcstTime : "+ fcstTime);
		}
        // for(int i=0; i<6; i++){
        //     System.out.println(data[i][0] + " : " + data[i][1]);
        // }
        return data;
    }
}