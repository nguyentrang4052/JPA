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
@WebServlet(urlPatterns = { "/admin/videos", "/admin/video/insert"})
public class VideoController extends HttpServlet{
	
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
		}else if (url.contains("insert")) {
			List<Category> listcate = cateService.findAll();
		    req.setAttribute("listcate", listcate);
			req.getRequestDispatcher("/views/admin/video-insert.jsp").forward(req, resp);
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
					String ext = filename.substring(index+1);
					fname = System.currentTimeMillis() + "." + ext;
					
					part.write(uploadPath + "/" + fname);
					
					video.setPoster(fname);					
				}else {
					video.setPoster("avata.png");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
					
			videoService.insert(video);
			resp.sendRedirect(req.getContextPath()+"/admin/videos");			
		}
	}
}
