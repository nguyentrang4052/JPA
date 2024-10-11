package vn.iotstar.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Video;
import vn.iotstar.services.ICategoryService;
import vn.iotstar.services.IVideoService;
import vn.iotstar.services.impl.CategoryService;
import vn.iotstar.services.impl.VideoService;
import static vn.iotstar.utils.Constant.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/videos", "/admin/video/insert", "/admin/video/update", "/admin/video/delete",
		"/admin/video/search" })
public class VideoController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7104954194196997145L;
	public IVideoService videoService = new VideoService();
	public ICategoryService cateService = new CategoryService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (url.contains("videos")) {
			List<Video> list = videoService.findAll();
			req.setAttribute("listvideo", list);

			req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
		} else if (url.contains("insert")) {
			List<Category> listcate = cateService.findAll();
			req.setAttribute("listcate", listcate);
			req.getRequestDispatcher("/views/admin/video-insert.jsp").forward(req, resp);
		} else if (url.contains("update")) {
			int id = Integer.parseInt(req.getParameter("id"));
			Video video = videoService.findById(id);
			req.setAttribute("video", video);

			List<Category> listcate = cateService.findAll();
			req.setAttribute("listcate", listcate);
			req.getRequestDispatcher("/views/admin/video-update.jsp").forward(req, resp);
		} else if (url.contains("delete")) {
			String id = req.getParameter("id");
			try {
				if (id != null) {
					videoService.delete(Integer.parseInt(id));
				}
				resp.sendRedirect(req.getContextPath() + "/admin/videos");
			} catch (Exception e) {

				e.printStackTrace();
			}
		} else if (url.contains("search")) {
			
			req.getRequestDispatcher("/views/admin/video-search.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();

		if (url.contains("insert")) {
			Video video = new Video();

			String title = req.getParameter("title");
			video.setTitle(title);

			String desc = req.getParameter("description");
			video.setDescription(desc);

			int views = Integer.parseInt(req.getParameter("views"));
			video.setViews(views);

			int categoryid = Integer.parseInt(req.getParameter("categoryid"));
			Category category = cateService.findById(categoryid);
			String categoryname = category.getCategoryname();
			video.setCategories(category);

			boolean active = req.getParameter("active").equals("1");
			video.setActive(active);

			String fname = "";
			String uploadPath = UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);

			if (uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("poster");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();

					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;

					part.write(uploadPath + "/" + fname);

					video.setPoster(fname);
				} else {
					video.setPoster("avata.png");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			videoService.insert(video);
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		} else if (url.contains("/admin/video/update")) {
			Video video = new Video();

			int videoid = Integer.parseInt(req.getParameter("videoid"));
			video.setVideoid(videoid);

			String title = req.getParameter("title");
			video.setTitle(title);

			String desc = req.getParameter("description");
			video.setDescription(desc);

			int views = Integer.parseInt(req.getParameter("views"));
			video.setViews(views);

			boolean active = req.getParameter("active").equals("1");
			video.setActive(active);

			int categoryid = Integer.parseInt(req.getParameter("categoryid"));
			Category category = cateService.findById(categoryid);
			String categoryname = category.getCategoryname();
			video.setCategories(category);

			// luu hinh cu
			Video old = videoService.findById(videoid);
			String fileold = old.getPoster();

			// xu ly images
			String fname = "";
			String uploadPath = UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);

			if (uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("poster");

				if (part.getSize() > 0) {

					/*
					 * if (!category.getImages().substring(0,5).equals("https")) {
					 * deleteFile(uploadPath + "\\" + fileold); }
					 */

					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();

					// doi ten
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;

					// up load file
					part.write(uploadPath + "/" + fname);

					// ghi ten file vao data
					video.setPoster(fname);

				} else {

					video.setPoster(fileold);
				}
			} catch (Exception e) {

				e.printStackTrace();
			}

			videoService.update(video);
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		} else if (url.contains("search")) {
			String title = req.getParameter("title");
			try {
				List<Video> list = videoService.findByVideoname(title);
				if (!list.isEmpty()) {
					req.setAttribute("listvideo", list);
					
				} else {
					String alertMsg = "Khong tim thay";
					req.setAttribute("alert",alertMsg);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
		}
	}
}
