package poly.edu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.poly.dao.VideoDAO;

import poly.edu.entity.Video;
import poly.edu.utils.UploadUtils;

/**
 * Servlet implementation class VideoServlet
 */
@WebServlet({ "/VideoServlet", "/VideoServlet/insert", "/VideoServlet/update", "/VideoServlet/delete",
		"/VideoServlet/reset", "/VideoServlet/edit" })
@MultipartConfig
public class VideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VideoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		if (url.contains("edit")) {
			edit(request, response);
			return;
		} else if (url.contains("delete")) {
			delete(request, response);
			return;
		} else if (url.contains("reset")) {
			delete(request, response);
			return;
		}
		Video video = new Video();
		video.setPoster("images/1001.jpg");
		findAll(request, response);
		request.setAttribute("video", video);
		findAll(request, response);
		request.getRequestDispatcher("/views/admin/video.jsp").forward(request, response);
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String videoId = request.getParameter("videoId");
		if (videoId == null) {
			request.setAttribute("error", "Video Id is required!");
			request.getRequestDispatcher("/views/video.jsp").forward(request, response);
			return;
		}
		
		try {
			VideoDAO dao = new VideoDAO();
			Video video = dao.findById(videoId);
			request.setAttribute("video", video);
			findAll(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " +e.getMessage());
		}
		findAll(request, response);
		request.getRequestDispatcher("/views/admin/video.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		if (url.contains("/VideoServlet/insert")) {
			insert(request, response);
		} else if (url.contains("delete")) {
			delete(request, response);
		} else if (url.contains("/VideoServlet/update")) {
			update(request, response);
		} else if (url.contains("reset")) {
			reset(request,response);
		}
		findAll(request, response);
		request.getRequestDispatcher("/views/admin/video.jsp").forward(request, response);
	}

	private void reset(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Video video = new Video();
		video.setPoster("images/1001.jpg");
		request.setAttribute("video", video);
		findAll(request, response);
		request.getRequestDispatcher("/views/admin/video.jsp").forward(request, response);
		
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Video video = new Video();
		try {
			BeanUtils.populate(video, request.getParameterMap());
			VideoDAO dao = new VideoDAO();
			Video oldvideo = dao.findById(video.getVideoId());
			if (request.getPart("cover").getSize()==0) {
				video.setPoster(oldvideo.getPoster());
			}else {
				video.setPoster("uploads/" + UploadUtils.processUploadField("cover", request,
						"/uploads", video.getVideoId()));
			}
			dao.update(video);
			request.setAttribute("video", video);
			request.setAttribute("message", "Video is updated! ");
			findAll(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " +e.getMessage());
		}
		request.getRequestDispatcher("/views/admin/video.jsp").forward(request, response);
	}
	private void findAll(HttpServletRequest request, HttpServletResponse response) {
		try {
			VideoDAO dao = new VideoDAO();
			List<Video> list = dao.findAll();
			request.setAttribute("items", list);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " +e.getMessage());
		}
	}
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String videoId = request.getParameter("videoId");
		if (videoId == null) {
			request.setAttribute("error", "Video Id is required!");
			request.getRequestDispatcher("/views/admin/video.jsp").forward(request, response);
			return;
		}
		
		try {
			VideoDAO dao = new VideoDAO();
			Video video = dao.findById(videoId);
			if (video == null) {
				request.setAttribute("error", "Video is not found!");
				request.getRequestDispatcher("/views/admin/video.jsp").forward(request, response);
				return;
			}
			dao.delete(videoId);
			request.setAttribute("message", "Video is deleted!");
			request.setAttribute("video", new Video());
			findAll(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " +e.getMessage());
		}
		request.getRequestDispatcher("/views/video.jsp").forward(request, response);

	}

	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Video video = new Video();
		try {
			BeanUtils.populate(video, request.getParameterMap());
			video.setPoster("uploads/" +UploadUtils.processUploadField("cover", request,
					"/uploads", video.getVideoId()));
			VideoDAO dao = new VideoDAO();
			dao.insert(video);
			request.setAttribute("video", video);
			request.setAttribute("message", "Them thanh cong");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Them that bai");
		}
		findAll(request, response);
		request.getRequestDispatcher("/views/admin/video.jsp").forward(request, response);
		

	}

}
