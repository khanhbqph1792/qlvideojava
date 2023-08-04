package poly.edu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.poly.dao.FavoriteDAO;
import com.poly.dao.ShareDAO;
import com.poly.dao.VideoDAO;

import poly.edu.domain.FavoriteReport;
import poly.edu.domain.FavoriteUserReport;
import poly.edu.domain.ShareReport;
import poly.edu.entity.Video;
/**
 * Servlet implementation class ReportManagementServlet
 */
@WebServlet("/ReportManagementServlet")
public class ReportManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReportManagementServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reportFavoritesByVideos(request,response);
		reportFavoriteUsersByVideos(request, response);
		reportSharesByVideos(request, response);
		request.getRequestDispatcher("/views/reports.jsp").forward(request, response);
	}

	protected void reportFavoritesByVideos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			FavoriteDAO dao = new FavoriteDAO();
			List<FavoriteReport> list = dao.reportFavoritesByVideos();
			request.setAttribute("falist", list);
		} catch (Exception e) {
			request.setAttribute("error", "Error: " + e.getMessage());
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	protected void reportFavoriteUsersByVideos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			String videoUserId = request.getParameter("videoUserId");
			VideoDAO vidao = new VideoDAO();
			List<Video> vlist = vidao.findAll();
			if (videoUserId == null && vlist.size()>0) {
				videoUserId = vlist.get(0).getVideoId();
			}
			
			FavoriteDAO dao = new FavoriteDAO();
			List<FavoriteUserReport> list = dao.reportFavoriteUsersByVideos(videoUserId);
			request.setAttribute("videoUserId", videoUserId);
			request.setAttribute("vdlist", vlist);
			request.setAttribute("favUser", list);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
		
	}
	protected void reportSharesByVideos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			String videoUserId = request.getParameter("videoUserId");
			VideoDAO vidao = new VideoDAO();
			List<Video> vlist = vidao.findAll();
			if (videoUserId == null && vlist.size()>0) {
				videoUserId = vlist.get(0).getVideoId();
			}
			
			ShareDAO dao = new ShareDAO();
			List<ShareReport> list = dao.reportSharesByVideos(videoUserId);
			request.setAttribute("videoUserId", videoUserId);
			request.setAttribute("vdlist", vlist);
			request.setAttribute("shaUser", list);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
		
	}

}
