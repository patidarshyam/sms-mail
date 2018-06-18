import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailServiceImpl {

	/**
	 * This is the sender's Email Address through which the email will be send.
	 * This email address is also used to authenticate the Session which will be
	 * used in MIME Message.
	 */
	private String senderAddress = "shyam.46.cse@gmail.com";

	/**
	 * This is the sender's Password through which the email will be send. This
	 * password is also used to authenticate the Session which will be used in
	 * MIME Message.
	 */
	private final String password = "sp.Shrutee7";

	public String getSenderAddress() {
		return senderAddress;
	}

	public String getPassword() {
		return password;
	}

	/**
	 * this method will send the email on the given address with provided
	 * subject and text
	 * 
	 *  
	 * 
	 * @param recipientAddress
	 *            - the address on which the email has to send.
	 * @param subject 
	 *            - subject of the email
	 *  @param textToSend               
	 *            - message to be sent 
	 *            
	 * @return true if email is sent successfully or false if email is not send
	 * @exception java.lang.AssertionError
	 *                It will throw AssertionError if the credentials are
	 *                incorrect. Also if the properties are incorrect.
	 */
	public boolean sendEmail(String recipientAddress, String subject, String textToSend) {

		Session session = getMailSession();
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderAddress));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientAddress));
			message.setSubject(subject);
			message.setText(textToSend);
			Transport.send(message);
			return true;

		} catch (MessagingException e) {
			System.err.println(e);
			return false;
		}
	}

	/**
	 * this method will provide the session to gmail smtp server whenever needed
	 * 
	 * @return javax.mail.Session - It will return the Session Object if the
	 *         properties and email and password are correct. - It will return
	 *         null if the properties and email and password are incorrect.
	 * 
	 * @exception java.lang.AssertionError
	 *                It will throw AssertionError if the credentials are
	 *                incorrect. Also if the properties are incorrect.
	 */
	public Session getMailSession() {

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		return Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(getSenderAddress(), getPassword());
			}
		});
	}

	/**
	 * this method will check whether the recipient address provided is valid or
	 * not.
	 * 
	 * @param email
	 * @return boolean true for valid false for invalid
	 */
	public boolean isEmailValid(String email) {
		String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

}
