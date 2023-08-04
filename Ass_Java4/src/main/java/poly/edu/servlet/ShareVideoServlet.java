package poly.edu.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poly.dao.ShareDAO;
import com.poly.dao.UserDAO;

import poly.edu.domain.Email;
import poly.edu.entity.User;
import poly.edu.entity.Video;
import poly.edu.entity.Share;
import poly.edu.utils.EmailUtils;
import poly.edu.utils.SessionUtils;

/**
 * Servlet implementation class ShareVideoServlet
 */
@WebServlet("/ShareVideo")
public class ShareVideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShareVideoServlet() {
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
		String videoId = request.getParameter("videoId");
		if (videoId == null) {
			response.sendRedirect("/homepage");
			return;
		}
		request.setAttribute("videoId", videoId);
		request.getRequestDispatcher("/views/share.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String emailAddress = request.getParameter("email");
			String videoId = request.getParameter("videoId");
			if (videoId == null) {
				request.setAttribute("error", "Video Id is null");
			} else {
				Email email = new Email();
				email.setForm("bachquockhanh21052002@gmail.com");
				email.setFromPassword("dlngofhynuinkvrj");
				email.setTo(emailAddress);
				email.setSubject("Share thông tin Video");
				StringBuilder sb = new StringBuilder();
				sb.append("Dear Ms/Mr, <br> ");
				sb.append("The video is more interesting and I want to share with you <br> ");
				sb.append("Please click the link ").append(String.format("<a href=''>View Video</a><br>",videoId));
				sb.append("Regards<br>");
				sb.append("Administrator");
				email.setContent(sb.toString());
				EmailUtils.send(email);
				
				ShareDAO dao = new ShareDAO();
				Share share = new Share();
				share.setEmails(emailAddress);
				share.setShareDate(new Date());
				
				String userId = SessionUtils.getLoginedUsername(request);
				User user = new User();
				user.setUserId(userId);
				
				share.setUser(user);
				Video video = new Video();
				video.setVideoId(videoId);
				share.setVideo(video);
				
				dao.insert(share);
				request.setAttribute("message",
						"Video link has been sent");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		request.getRequestDispatcher("/views/share.jsp").forward(request, response);
	}

}
