package poly.edu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.poly.dao.UserDAO;

import poly.edu.entity.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet({ "/UserServlet","/UserServlet/insert", "/UserServlet/update", "/UserServlet/delete", "/UserServlet/edit", "/UserServlet/reset",})
public class UserServlet extends HttpServlet {
	UserDAO dao = new UserDAO();
	User user = new User();
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		String uri = request.getRequestURI().toString();
		User nd = null;
		if (uri.contains("edit")) {
			edit(request,response);
		} else if (uri.contains("delete")) {
			delete(request,response);
		} else if (uri.contains("reset")) {
			request.setAttribute("nguoi", nd);
		} 
		fillTable(request, response);
		request.getRequestDispatcher("/views/admin/user.jsp").forward(request, response);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		try {
			String useId = request.getParameter("userId");
			UserDAO dao = new UserDAO();
			dao.delete(useId);
			request.setAttribute("message", "Xóa thành công");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Lỗi");
		}
		
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) {
		try {
			String useId = request.getParameter("userId");
			UserDAO dao = new UserDAO();
			User us = dao.findById(useId);
			request.setAttribute("nguoi", us);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Lỗi");
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI().toString();
		if (uri.contains("/UserServlet/insert")) {
			insert(request,response);
		} else if (uri.contains("/UserServlet/update")) {
			update(request,response);
		} else if (uri.contains("edit")) {
			edit(request,response);
		} 
		fillTable(request, response);
		request.getRequestDispatcher("/views/admin/user.jsp").forward(request, response);
	}
	private void update(HttpServletRequest request, HttpServletResponse response) {
		try {
			User us = new User();
			BeanUtils.populate(us, request.getParameterMap());
			UserDAO dao = new UserDAO();
			dao.update(us);
			request.setAttribute("message", "Nhập thành công");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Lỗi");
		}
		
	}


	private void insert(HttpServletRequest request, HttpServletResponse response) {
		try {
			User us = new User();
			BeanUtils.populate(us, request.getParameterMap());
			UserDAO dao = new UserDAO();
			dao.insert(us);
			request.setAttribute("message", "Thêm thành công");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Lỗi");
		}
		
	}


	protected void fillTable(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		try {
			UserDAO dao = new UserDAO();
			List<User> list = dao.findAll();
			request.setAttribute("items", list);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Lỗi");
		}
	}

}
