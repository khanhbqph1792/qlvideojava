package poly.edu.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poly.dao.FavoriteDAO;

import poly.edu.entity.Favorite;
import poly.edu.entity.User;
import poly.edu.entity.Video;
import poly.edu.utils.SessionUtils;

/**
 * Servlet implementation class LikeVideoServlet
 */
@WebServlet("/LikeVideo")
public class LikeVideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikeVideoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!SessionUtils.isLogin(request)) {
			response.sendRedirect("Login");
			return;
		}
		String page = request.getParameter("page");
		String videoId = request.getParameter("videoId");
		if (videoId == null) {
			response.sendRedirect("/homepage");
			return;
		}
		
		try {
			FavoriteDAO dao = new FavoriteDAO();
			Favorite favorite = new Favorite();
			Video video = new Video();
			video.setVideoId(videoId);
			favorite.setVideo(video);
			
			String userId = SessionUtils.getLoginedUsername(request);
			User user = new User();
			user.setUserId(userId);
			favorite.setUser(user);
			favorite.setLikeDate(new Date());
			dao.insert(favorite);
			request.setAttribute("message",
					"Video is added to Favorite");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		if (page == null) {
			page = "/homepage";
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
