import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class MainWindow extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	JTextField tHeader;
	JTextArea tMessage, tMails;
	JButton bSend, bOpen;
	JLabel lHeader, lRecievers, lCounter, lNumber;
	

	ArrayList<String> mails = new ArrayList<String>();
	
	public MainWindow()
	{
		super("Main Window");
		setSize(1050,500);
		setLayout(null);
		setLocationRelativeTo(null);
		
		lHeader = new JLabel("Header:");
		lRecievers = new JLabel("Recievers");
		lCounter = new JLabel("Counter:");
		lNumber = new JLabel("0");
		
		tMessage = new JTextArea("");
		tMails = new JTextArea("");
		tHeader = new JTextField("");		
		bSend = new JButton("Send");
		bOpen = new JButton("Choose file");
		
		lHeader.setBounds(50,60,200,20);
		lRecievers.setBounds(830,90,200,20);
		lCounter.setBounds(800,400,200,20);
		lNumber.setBounds(900,400,200,20);
		tHeader.setBounds(100,60,500,20);
		tMessage.setBounds(70, 120, 650, 250);
		tMails.setBounds(750, 120, 220, 250);
		bSend.setBounds(340,400,100,20);
		bOpen.setBounds(810,60,100,20);
		
		add(lHeader);
		add(lRecievers);
		add(lCounter);
		add(lNumber);
		add(tHeader);
		add(bSend);
		add(tMessage);
		add(tMails);
		add(bOpen);
	
		bOpen.addActionListener(this);
		tMails.setEditable(false);
		
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
			
			JScrollPane scroll = new JScrollPane(tMails);
			scroll.setBounds(750, 120, 220, 250);
			scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
			getContentPane().add(scroll);
			
			
			lNumber.setText(String.valueOf(mails.size()));
		}
		
	}
	
}
