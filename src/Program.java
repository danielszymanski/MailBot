import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class Program {

	static ArrayList<String> mails = new ArrayList<String>();
	static ArrayList<String> messageText = new ArrayList<String>();
	private static BufferedReader br;
	private static String username,password;
	private static LoginWindow loginWindow;
	private static MainWindow mainWindow;
	
	private static int times = 1;
	
	public static void main(String[] args) {
		
		mainWindow = new MainWindow();	
		loginWindow = new LoginWindow();
		
		mainWindow.bSend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				

	            //if(mails.isEmpty())
	            	//JOptionPane.showMessageDialog(null, "Add e-mails first");
	            
	            if(mainWindow.tHeader.getText().isEmpty())
	            	JOptionPane.showMessageDialog(null, "Please insert your header");
	            
	            else if(mainWindow.tMessage.getText().isEmpty())
	            	JOptionPane.showMessageDialog(null, "Please insert your message");
	            
	            else if(mainWindow.mails.isEmpty())
	            	JOptionPane.showMessageDialog(null, "Please insert recievers");
	            
	            else
	            {
	            	loginWindow.setVisible(true);
	            	mails = mainWindow.mails;
	            }
			}
		});
		

		loginWindow.bSign.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				username = loginWindow.tLogin.getText();
				password = String.valueOf(loginWindow.tPassword.getPassword());
				
			    String host = "localhost";
				Properties properties = System.getProperties();
				properties.setProperty("mail.smtp.host", host);
				
				sendEmail(username,password);
			}
		});
		
		loginWindow.bCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				loginWindow.tLogin.setText("");
				loginWindow.tPassword.setText("");
				loginWindow.setVisible(false);
			}
		});
	}
		
	static void ReadFromFile(ArrayList<String> list,String fileName)
	{

		try {
			File file = new File(fileName);
			FileReader fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line;
			
			while((line = br.readLine()) != null){
				list.add(line);
				}
			}
		catch(Exception e)
		{
			System.err.print(e.getMessage());
            System.exit(0);
			}
	}

    public static void sendEmail(String username, String password)
    {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {
            Message message = new MimeMessage(session);
            
            message.setFrom(new InternetAddress(username));
            message.setSubject(mainWindow.tHeader.getText());
            

        	String endMessage = mainWindow.tMessage.getText();           
            message.setText(endMessage);
            
            for (String to : mails) {
            	message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(to));
                    
            for(int i=0;i<times;i++)
            	Transport.send(message);
            }
            
            JOptionPane.showMessageDialog(null, "E-mails sent");
        } 

        catch (MessagingException e) 
        {
        	JOptionPane.showMessageDialog(null, "Cannot connect to your e-mail");
            System.err.println(e.getMessage());
        }
    }
}
	

