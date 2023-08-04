package poly.edu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import poly.edu.utils.CookieUtils;
import poly.edu.utils.SessionUtils;

/**
 * Servlet implementation class LogoffServlet
 */
@WebServlet("/Logoff")
public class LogoffServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CookieUtils.add("userId", null, 0, response);
		SessionUtils.invalidate(request);
		request.setAttribute("isLogin", false);
		request.getRequestDispatcher("/homepage").forward(request, response);
	}

}
