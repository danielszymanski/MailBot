import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	
	JLabel lLogin,lPassword;
	JTextField tLogin;
	JPasswordField tPassword;
	JButton bSign, bCancel;
	
	public LoginWindow() {
		
		super("Sign in");
		setSize(300,200);
		setLayout(null);
		setLocationRelativeTo(null);
		
		lLogin = new JLabel("Login:");
		lPassword = new JLabel("Password:");
		tLogin = new JTextField("");
		tPassword = new JPasswordField();
		bSign = new JButton("Sign in");	
		bCancel = new JButton("Cancel");
		
		lLogin.setBounds(20,30,100,20);
		lPassword.setBounds(20,60,100,20);
		tLogin.setBounds(120,30,150,20);
		tPassword.setBounds(120,60,150,20);
		bSign.setBounds(30,110,100,20);
		bCancel.setBounds(160,110,100,20);
		
		add(lLogin);
		add(lPassword);
		add(tLogin);
		add(tPassword);
		add(bSign);
		add(bCancel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setVisible(true);
	}
}
