package poly.edu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.poly.dao.UserDAO;

import poly.edu.domain.ChangePasswordForm;
import poly.edu.utils.SessionUtils;

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet("/ChangePassword")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangePasswordServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = SessionUtils.getLoginedUsername(request);
		if (userId == null) {
			request.getRequestDispatcher("/Login").forward(request, response);
			return;
		}
		request.setAttribute("userId", userId);
		request.getRequestDispatcher("/views/change-password.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String userId = SessionUtils.getLoginedUsername(request);
			ChangePasswordForm form = new ChangePasswordForm();
			BeanUtils.populate(form, request.getParameterMap());
			request.setAttribute("userId", userId);
			if (!form.getConfirmPassword().equals(form.getPassword())) {
				request.setAttribute("error", "new password and new confirm password are not identical");
			} else {
				UserDAO dao = new UserDAO();
				dao.changePassword(form.getUserId(), form.getCurrentPassword(), form.getPassword());
				request.setAttribute("message", "Password has been changed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		request.getRequestDispatcher("/views/change-password.jsp").forward(request, response);
	}

}
