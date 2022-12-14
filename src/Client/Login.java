package Client;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Login {

	private JDialog dialog;
	private JTextField idField;
	private JPasswordField pwdField;
	private Connection con = null;
	private Statement stmt=null;
	private PreparedStatement pstm=null;
	private ResultSet rs=null;
	
	private Socket logSock;
	private Scanner in;
	private PrintWriter out;
	
	/**
	 * Launch the application.
	 */
	
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public Login(Socket tmpSock) throws Exception, IOException {
		
		logSock=tmpSock;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws Exception, IOException{

		try {
			
			in=new Scanner(logSock.getInputStream()); 
			out=new PrintWriter(logSock.getOutputStream(),true);
			
			dialog = new JDialog();
			dialog.setBounds(850, 250, 314, 406);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.getContentPane().setLayout(null);
			
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setBounds(0, 0, 303, 359);
			dialog.getContentPane().add(panel);
			panel.setLayout(null);
			
			idField = new JTextField("Id or E_mail");
			idField.setBounds(59, 135, 190, 32);
			panel.add(idField);
			idField.setColumns(10);
			idField.setForeground(SystemColor.activeCaptionBorder);
			idField.setFont(new Font("HY견고딕", Font.BOLD, 17));
			
			pwdField = new JPasswordField();
			pwdField.setBounds(59, 179, 190, 32);
			panel.add(pwdField);
			pwdField.setFont(new Font("HY견고딕", Font.BOLD, 17));
			pwdField.setColumns(10);
			pwdField.setForeground(Color.BLACK);
			pwdField.setEchoChar('*');
			
			RoundedButton btnNewButton = new RoundedButton("Login");
			btnNewButton.setBounds(59, 223, 190, 27);
			panel.add(btnNewButton);
			
			btnNewButton.addActionListener(new Add());
			
			
			JLabel lblNewLabel = new JLabel("or");
			lblNewLabel.setFont(new Font("HY견고딕", Font.BOLD, 16));
			lblNewLabel.setBounds(143, 250, 25, 18);
			panel.add(lblNewLabel);
			
			RoundedButton btnNewButton_1 = new RoundedButton("Sign Up");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				
					dialog.setVisible(false);
					new SignUp(logSock);
				}
			});
			btnNewButton_1.setBounds(59, 274, 190, 27);
			panel.add(btnNewButton_1);
			
			JLabel lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/PngFile/Login_Img.png")));
			lblNewLabel_1.setBounds(49, 12, 211, 115);
			panel.add(lblNewLabel_1);
			
			
			idField.addMouseListener(new MouseListener(){
					
					@Override
					public void mouseClicked(MouseEvent e){
						idField.setText("");
						idField.setForeground(Color.BLACK);
						idField.setFont(new Font("HY견고딕", Font.PLAIN, 17));
					}
					
					@Override
					public void mousePressed(MouseEvent e){
						
						
					}
					
					@Override
					public void mouseReleased(MouseEvent e){
						
						
					}
					
					@Override
					public void mouseEntered(MouseEvent e){
						
						
					}
					@Override
					public void mouseExited(MouseEvent e){
						
						
					}
					
					
				});
			
			
			pwdField.addMouseListener(new MouseListener(){
				
				@Override
				public void mouseClicked(MouseEvent e){
					pwdField.setText("");
					pwdField.setForeground(Color.BLACK);
					pwdField.setFont(new Font("HY견고딕", Font.PLAIN, 17));
					
				}
				
				@Override
				public void mousePressed(MouseEvent e){
					
					
				}
				
				@Override
				public void mouseReleased(MouseEvent e){
					
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e){
					
					
				}
				@Override
				public void mouseExited(MouseEvent e){
					
					
				}
				
				
			});
			
			JButton WithdrawalBT = new JButton("Withdrawal"); // button for Membership Withdrawal
			WithdrawalBT.setFont(new Font("HY견고딕", Font.BOLD, 15));
			WithdrawalBT.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dialog.setVisible(false);
					new Withdrawal(logSock);
					
				}
			});
			WithdrawalBT.setBackground(Color.WHITE);
			WithdrawalBT.setBorderPainted(false);
			WithdrawalBT.setContentAreaFilled(false);
			WithdrawalBT.setFocusPainted(false);
			WithdrawalBT.setBounds(59, 309, 190, 27);
			panel.add(WithdrawalBT);
			
			dialog.setVisible(true);
		}
		catch(Exception e) { 
			System.out.println("Error:" + logSock);
		}
		/*finally {
			try { logSock.close(); } catch (IOException e) {} 
			System.out.println("Closed: " + logSock);
				
		}*/
		
		
		
		
	}
	
	
	
	// 로그인 버튼을 눌렀을 때 서버로 부터 로그인 가능 여부를 확인하고, 로그인이 진행되는 부분
	class Add implements ActionListener{ 

		public void actionPerformed(ActionEvent arg0) {
		
			
			String tmpId=idField.getText();  // 입력한 아이디를 받는 변수
			idField.setFont(new Font("HY견고딕", Font.BOLD, 17));
			idField.setForeground(SystemColor.activeCaptionBorder);
			idField.setText("Id or E_mail");
			char[] tmpPwd=pwdField.getPassword();  // 입력한 비밀번호를 받는 변수
			pwdField.setText("");
			String tmpPwd_2=new String(tmpPwd); // tmpPwd를 문자열 형태로 변환해서 저장하는 변수
			
			
			
			
			
			try {
				
				out.println("333"); // 로그인 시도
				
				out.println(tmpId); 
				
				
				out.println(calculate.encode(tmpPwd_2,tmpPwd_2.length())); // RSA 알고리즘을 사용해서 비밀번호를 암호화 한 뒤에 보내게 된다
				
				
				out.println(Integer.toString(logSock.getLocalPort()));
				
				
				out.println(logSock.getLocalAddress().toString());
				
				
				
				
				String loginCheckCode= in.nextLine();	// 로그인 성공 여부 코드 받기

				if(loginCheckCode.equals("100")) { // 로그인 성공시
					
					// 메인 대시보드로 들어가진다
					
					User_2.id=in.nextLine();
                    User_2.pwd=in.nextLine();
					
					
					JOptionPane.showMessageDialog(null,"Login Success!!!");
					
					dialog.setVisible(false);
					new mainBoard(logSock,tmpId); // 메인 보드창 열기
				}
				else if(loginCheckCode.equals("201")) { // 존재하지 않는 ID일 경우
					JOptionPane.showMessageDialog(null,"ID Doesn't Exist! Try Again!");
				}
				else if(loginCheckCode.equals("202")) { // ID에 해당 패스워드가 불일치 할경우
					JOptionPane.showMessageDialog(null,"Wrong PassWord! Try Again!");
				}
				
				
				
			}catch(Exception e) { 
				System.out.println("Error:" + logSock);
			}
			/*finally {
				try { logSock.close(); } catch (IOException e) {} 
				System.out.println("Closed: " + logSock);
					
			}*/
			
			
			
		
			
			
			
			
		
	}
	
	}
	
	
	
public class RoundedButton extends JButton {
    public RoundedButton() { super(); decorate(); } 
    public RoundedButton(String text) { super(text); decorate(); } 
    public RoundedButton(Action action) { super(action); decorate(); } 
    public RoundedButton(Icon icon) { super(icon); decorate(); } 
    public RoundedButton(String text, Icon icon) { super(text, icon); decorate(); } 
    protected void decorate() { setBorderPainted(false); setOpaque(false); }
    @Override 
    protected void paintComponent(Graphics g) {
       Color c=new Color(111,79,40); 
       Color o=new Color(255,255,255); 
       int width = getWidth(); 
       int height = getHeight(); 
       Graphics2D graphics = (Graphics2D) g; 
       graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
       if (getModel().isArmed()) { graphics.setColor(c.darker()); } 
       else if (getModel().isRollover()) { graphics.setColor(c.brighter()); } 
       else { graphics.setColor(c); } 
       graphics.fillRoundRect(0, 0, width, height, 10, 10); 
       FontMetrics fontMetrics = graphics.getFontMetrics(); 
       Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds(); 
       int textX = (width - stringBounds.width) / 2; 
       int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent(); 
       graphics.setColor(o); 
       graphics.setFont(getFont()); 
       graphics.drawString(getText(), textX, textY); 
       graphics.dispose(); 
       super.paintComponent(g); 
       }
    }

	
	
}


