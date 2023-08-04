package poly.edu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.poly.dao.UserDAO;

import poly.edu.domain.LoginForm;
import poly.edu.entity.User;
import poly.edu.utils.CookieUtils;
import poly.edu.utils.SessionUtils;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = CookieUtils.get("userId", request);
		if (userId == null) {
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			return;
		}
		SessionUtils.add(request, "userId", userId);
		request.getRequestDispatcher("/homepage").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			LoginForm form = new LoginForm();
			BeanUtils.populate(form, request.getParameterMap());
			UserDAO dao = new UserDAO();
			User user = dao.findById(form.getUserId());
			if (user != null && user.getPassword().equals(form.getPassword())) {
				SessionUtils.add(request, "userId", user.getUserId());
				if (form.isRemember()) {
					CookieUtils.add("userId", form.getUserId(), 24, response);
				}else {
					CookieUtils.add("userId", form.getUserId(), 0, response);
				}
				request.setAttribute("isLogin", true);
				request.setAttribute("name", user.getFullname());
				request.setAttribute("message", "Login Success!");
				request.getRequestDispatcher("/homepage").forward(request, response);
				return;
			}
			request.setAttribute("error", "invalid userId or pass");
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
		}
		request.getRequestDispatcher("/views/login.jsp").forward(request, response);

	}

}
