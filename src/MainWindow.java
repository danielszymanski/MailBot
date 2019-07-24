import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainWindow extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	JTextField tHeader;
	JTextArea tMessage, tMails;
	JButton bSend, bOpen;
	JLabel lHeader, lRecievers;
	
	ArrayList<String> mails = new ArrayList<String>();
	
	public MainWindow()
	{
		super("Main Window");
		setSize(1000,500);
		setLayout(null);
		setLocationRelativeTo(null);
		
		lHeader = new JLabel("Header:");
		lRecievers = new JLabel("Recievers");
		tMessage = new JTextArea("");
		tMails = new JTextArea("");
		tHeader = new JTextField("");		
		bSend = new JButton("Send");
		bOpen = new JButton("Choose file");
		
		lHeader.setBounds(50,60,200,20);
		lRecievers.setBounds(830,90,200,20);
		tHeader.setBounds(100,60,200,20);
		tMessage.setBounds(70, 120, 650, 250);
		tMails.setBounds(750, 120, 220, 250);
		bSend.setBounds(340,400,100,20);
		bOpen.setBounds(810,60,100,20);

		add(lHeader);
		add(lRecievers);
		add(tHeader);
		add(bSend);
		add(tMessage);
		add(tMails);
		add(bOpen);
		
		bOpen.addActionListener(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		
		if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
		{
			File file = fc.getSelectedFile();
			
			Program.ReadFromFile(mails, file.getAbsolutePath());
			String text = "";
			
			for (String string : mails) {
				text+= string + "\n";
			}

			tMails.setText(text);
		}
		
	}
	
}
