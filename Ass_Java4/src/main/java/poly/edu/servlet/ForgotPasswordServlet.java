package poly.edu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poly.dao.UserDAO;

import poly.edu.domain.Email;
import poly.edu.entity.User;
import poly.edu.utils.EmailUtils;

/**
 * Servlet implementation class ForgotPasswordServlet
 */
@WebServlet("/ForgotPassword")
public class ForgotPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ForgotPasswordServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/forgot-password.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String emailAddress = request.getParameter("email");
			String userId = request.getParameter("userId");
			UserDAO dao = new UserDAO();
			User user = dao.findByUserIdAndEmail(userId, emailAddress);
			if (user == null) {
				request.setAttribute("error", "UserId or email are incorrect");
			} else {
				Email email = new Email();
				email.setForm("bachquockhanh21052002@gmail.com");
				email.setFromPassword("dlngofhynuinkvrj");
				email.setTo(emailAddress);
				email.setSubject("Forgot Password Function");
				StringBuilder sb = new StringBuilder();
				sb.append("Dear: ").append(userId).append("<br>");
				sb.append("You are used the forgot password function. <br> ");
				sb.append("Your password is <br>").append(user.getPassword()).append("<br>");
				sb.append("Regards<br>");
				sb.append("Administrator");
				email.setContent(sb.toString());
				EmailUtils.send(email);
				request.setAttribute("message",
						"Email sent to the email Address" + "Please check and get your Password");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		request.getRequestDispatcher("/views/forgot-password.jsp").forward(request, response);
	}

}
