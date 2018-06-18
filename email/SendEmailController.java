import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.mail.iap.Response;
import com.yash.serviceimpl.SendEmailServiceImpl;


/**
 * Servlet implementation class SendEmailController
 */
@WebServlet("/SendEmailController")
public class SendEmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private SendEmailServiceImpl sendEmailServiceImpl;

	/**
	 * Here emailService is injected in constructor so that it will be available
	 * whenever we need it.
	 */
	public SendEmailController() {
		sendEmailServiceImpl = new SendEmailServiceImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String recipientAddress = req.getParameter("recipientAddress");
		String subject = req.getParameter("subject");
		String textToSend = req.getParameter("textToSend");
		if (sendEmailServiceImpl.sendEmail(recipientAddress, subject, textToSend)) {
			System.out.println("You're mail has been sent.");
			res.sendRedirect("index.jsp?msg= You're mail has been sent.");
		}else{
			System.out.println("Error in smtp properties.");
			res.sendRedirect("index.jsp?msg= Error in smtp properties.");
		}
	}

}
