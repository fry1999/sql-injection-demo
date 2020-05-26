package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import dao.UserDAOImpl;

/**
 * Servlet implementation class LoginServlet
 */
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAOImpl userDAO = new UserDAOImpl();
	private List<Cart> cart = new ArrayList<Cart>();
	
	public ChangePasswordServlet() {
		
		super();
		
	}

	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String newPassword = request.getParameter("newPassword");
		String resetPassword = request.getParameter("resetPassword");
		
		System.out.println(newPassword + " " + resetPassword);
		String err = "";
		if (username.equals("") || password.equals("")) {
			err += "Phải nhập đầy đủ thông tin!";
		} /*
			 * else { if (userDAO.login(username, password) == false) { err +=
			 * "Tên đăng nhập hoặc mật khẩu không chính xác!"; } }
			 */
		
		if(newPassword.equals(resetPassword) == false) {
			err += "\n Mật khẩu mới không khớp!";
		}

		if (err.length() > 0) {
			request.setAttribute("err", err);
		}

		String url = "/change_password.jsp";
		try {
			if (err.length() == 0) {
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				session.setAttribute("cart", cart);
				userDAO.updatePasswordUser(username, password, newPassword);
				Cookie loginCookie = new Cookie("username",username);
	            //setting cookie to expiry in 30 mins
	            loginCookie.setMaxAge(30*60);
	            response.addCookie(loginCookie);
	            response.sendRedirect("index.jsp");
				url = "/index.jsp";
			} else {
				url = "/change_password.jsp";
				RequestDispatcher rd = getServletContext()
						.getRequestDispatcher(url);
				rd.forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/change_password.jsp");
		}
	}

}
