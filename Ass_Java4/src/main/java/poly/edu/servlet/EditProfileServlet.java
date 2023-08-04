package poly.edu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.poly.dao.UserDAO;

import poly.edu.entity.User;
import poly.edu.utils.SessionUtils;

/**
 * Servlet implementation class EditProfileServlet
 */
@WebServlet("/EditProfile")
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditProfileServlet() {
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

		try {
			UserDAO dao = new UserDAO();
			User user = dao.findById(userId);
			request.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());

		}
		request.getRequestDispatcher("/views/edit-profile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			User user = new User();
			BeanUtils.populate(user, request.getParameterMap());
			String userId = SessionUtils.getLoginedUsername(request);
			UserDAO dao = new UserDAO();
			User oldUser = dao.findById(userId);
			user.setAdmin(oldUser.isAdmin());
			dao.update(user);
			request.setAttribute("message", "Update Profile Success!");
			request.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		request.getRequestDispatcher("/views/edit-profile.jsp").forward(request, response);
	}

}
