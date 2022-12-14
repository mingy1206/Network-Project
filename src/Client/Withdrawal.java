package Client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Withdrawal {

	private JFrame frame;
	private JTextField deleteId;
	private JPasswordField deletePwd;
	
	private Socket deleteSock;
	private Scanner in;
	private PrintWriter out;
	
	

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Withdrawal window = new Withdrawal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public Withdrawal(Socket tmpSock) {
		deleteSock=tmpSock;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			
		in=new Scanner(deleteSock.getInputStream()); 
		out=new PrintWriter(deleteSock.getOutputStream(),true);
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 481, 392);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 463, 343);
		panel.setBackground(new Color(231,223,216));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel signImage = new JLabel("");
		signImage.setIcon(new ImageIcon(Withdrawal.class.getResource("/PngFile/SignUp_Img.png")));
		signImage.setBounds(153, 0, 150, 150);
		
		panel.add(signImage);
		
		deleteId = new JTextField();
		deleteId.setFont(new Font("HY�߸���", Font.BOLD, 19));
		deleteId.setText("ID");
		deleteId.setBounds(115, 162, 234, 32);
		panel.add(deleteId);
		deleteId.setColumns(10);
		deleteId.setForeground(SystemColor.activeCaptionBorder);
		
		deletePwd = new JPasswordField();
		deletePwd.setFont(new Font("HY�߸���", Font.BOLD, 19));
		deletePwd.setText("");
		deletePwd.setColumns(10);
		deletePwd.setBounds(115, 216, 234, 32);
		panel.add(deletePwd);
		deletePwd.setEchoChar('*');
		deletePwd.setForeground(SystemColor.activeCaptionBorder);
		
		RoundedButtonF WithdrawalBT = new RoundedButtonF("Withdrawal");
		WithdrawalBT.setFont(new Font("HY�߰��", Font.BOLD, 19));
		WithdrawalBT.setBounds(115, 272, 234, 40);
		panel.add(WithdrawalBT);
		WithdrawalBT.addActionListener(new sendWithdrawAction());
		WithdrawalBT.setBackground(new Color(111,79,40));
		
		deleteId.addMouseListener(new MouseListener(){
			
			@Override
			public void mouseClicked(MouseEvent e){
				deleteId.setText("");
				deleteId.setForeground(Color.BLACK);
				deleteId.setFont(new Font("HY�߰��", Font.PLAIN, 19));
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
		
		deletePwd.addMouseListener(new MouseListener(){
			
			@Override
			public void mouseClicked(MouseEvent e){
				deletePwd.setText("");
				deletePwd.setForeground(Color.BLACK);
				deletePwd.setFont(new Font("HY�߰��", Font.PLAIN, 19));
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
		
		
		frame.setVisible(true);
		
		}catch(Exception e) { 
			System.out.println("Error:" +deleteSock);
		}
		
	}
	
	
	class sendWithdrawAction implements ActionListener{ 

		public void actionPerformed(ActionEvent arg0) {
		
			String tmpId=deleteId.getText();// �Է��� ���̵� �޴� ����
			char[] tmpPwd_2=deletePwd.getPassword();  // �Է��� ��й�ȣ�� �޴� ����
			String tmpPwd_=new String(tmpPwd_2); // tmpPwd�� ���ڿ� ���·� ��ȯ�ؼ� �����ϴ� ����
			
			try {
				//String loginCheckCode= in.nextLine();	// ���̵� ��� ���� ���� ���� �ڵ� �ޱ�
				
				if(tmpPwd_.equals("")) {
					
					JOptionPane.showMessageDialog(null,"You Didn't Write PassWord! Write it!");
				}
				else if(tmpId.equals("ID")) {
					
					JOptionPane.showMessageDialog(null,"You Didn't Write Your ID! Write it!");
				}
				else {
					out.println("505");
					out.println(tmpId);
					out.println(calculate.encode(tmpPwd_,tmpPwd_.length()));
					
					
					String canDeleted = in.nextLine();
					
					if(canDeleted.equals("515")) {
						
						JOptionPane.showMessageDialog(null,"Your Information is deleted!");
						frame.setVisible(false);
                        new Login(deleteSock);
						
					}
					else if(canDeleted.equals("518")) {
						
						JOptionPane.showMessageDialog(null,"the Id doesn't exist!");
						
					}
					else if(canDeleted.equals("519")) {
						
						JOptionPane.showMessageDialog(null,"ID and PassWord mismatch!");
						
					}
					
				}
				
				frame.setVisible(true);
				
			}catch(Exception e) { 
				System.out.println("Error:" + deleteSock);
			}
			/*finally {
				try { signSock.close(); } catch (IOException e) {} 
				System.out.println("Closed: " + signSock);
					
			}*/
			
			
			
		
			
			
			
			
		
	}
	
	}
	
	public class RoundedButtonF extends JButton {


	    public RoundedButtonF() {
	        super();
	        decorate();
	    }

	    public RoundedButtonF(String text) {
	        super(text);
	        decorate();
	    }

	    public RoundedButtonF(Action action) {
	        super(action);
	        decorate();
	    }

	    public RoundedButtonF(Icon icon) {
	        super(icon);
	        decorate();
	    }

	    public RoundedButtonF(String text, Icon icon) {
	        super(text, icon);
	        decorate();
	    }

	    protected void decorate() {
	        setBorderPainted(false);
	        setOpaque(false);
	    }
	    @Override
		protected void paintComponent(Graphics g) {
		    int width = getWidth();
		    int height = getHeight();

		    Graphics2D graphics = (Graphics2D) g;

		    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		    if (getModel().isArmed()) {
		        graphics.setColor(getBackground().darker());
		    } else if (getModel().isRollover()) {
		        graphics.setColor(getBackground().brighter());
		    } else {
		        graphics.setColor(getBackground());
		    }

		    graphics.fillRoundRect(0, 0, width, height, 10, 10);

		    FontMetrics fontMetrics = graphics.getFontMetrics();
		    Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds();

		    int textX = (width - stringBounds.width) / 2;
		    int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent();

		    graphics.setColor(getForeground());
		    graphics.setFont(getFont());
		    graphics.drawString(getText(), textX, textY);
		    graphics.dispose();

		    super.paintComponent(g);
		}
	}
	
}
