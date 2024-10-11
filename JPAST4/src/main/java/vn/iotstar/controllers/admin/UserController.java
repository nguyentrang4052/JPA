package vn.iotstar.controllers.admin;

import static vn.iotstar.utils.Constant.UPLOAD_DIRECTORY;

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
import vn.iotstar.entity.User;
import vn.iotstar.entity.Video;
import vn.iotstar.services.IUserService;
import vn.iotstar.services.impl.UserService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/users", "/admin/user/insert", "/admin/user/update", "/admin/user/delete" })
public class UserController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2565562717195786030L;

	public IUserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (url.contains("users")) {
			List<User> list = userService.findAll();
			req.setAttribute("listuser", list);
			req.getRequestDispatcher("/views/admin/user-list.jsp").forward(req, resp);
		} else if (url.contains("insert")) {
			req.getRequestDispatcher("/views/admin/user-insert.jsp").forward(req, resp);
		} else if (url.contains("update")) {
			int id = Integer.parseInt(req.getParameter("id"));
			User user = userService.findById(id);
			req.setAttribute("user", user);
			req.getRequestDispatcher("/views/admin/user-update.jsp").forward(req, resp);

		} else if (url.contains("delete")) {
			String id = req.getParameter("id");
			try {
				if (id != null) {
					userService.delete(Integer.parseInt(id));
				}
				resp.sendRedirect(req.getContextPath() + "/admin/users");
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();

		if (url.contains("insert")) {

			User user = new User();

			String name = req.getParameter("name");
			user.setName(name);

			String username = req.getParameter("username");
			user.setUsername(username);
			
			String password = req.getParameter("password");
			user.setPassword(password);

			boolean active = req.getParameter("active").equals("1");
			user.setActive(active);

			String fname = "";
			String uploadPath = UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);

			if (uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("images");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();

					// doi ten
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;

					// up load file
					part.write(uploadPath + "/" + fname);

					// ghi ten file vao data
					user.setImages(fname);
				} else {
					user.setImages("avata.png");
				}
			} catch (Exception e) {

				e.printStackTrace();
			}

			userService.insert(user);
			resp.sendRedirect(req.getContextPath() + "/admin/users");
		} else if (url.contains("/admin/user/update")) {
			User user = new User();

			int userid = Integer.parseInt(req.getParameter("userid"));
			user.setUserid(userid);

			String name = req.getParameter("name");
			user.setName(name);

			String username = req.getParameter("username");
			user.setUsername(username);
			
			String password = req.getParameter("password");
			user.setPassword(password);

			boolean active = req.getParameter("active").equals("1");
			user.setActive(active);

			// luu hinh cu
			User old = userService.findById(userid);
			String fileold = old.getImages();

			// xu ly images
			String fname = "";
			String uploadPath = UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);

			if (uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("images");

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
					user.setImages(fname);

				} else {

					user.setImages(fileold);
				}
			} catch (Exception e) {

				e.printStackTrace();
			}

			userService.update(user);
			resp.sendRedirect(req.getContextPath() + "/admin/users");
		}
	}
}
