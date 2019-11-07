package servlet_ecommerce;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class E_commerce_servlet
 */
@WebServlet("/E_commerce_servlet")
public class E_commerce_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public E_commerce_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#getServletConfig()
	 */
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=request.getParameter("action");
		
		if("login".equalsIgnoreCase(action)) {
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/Login.jsp");
			rd.forward(request, response);
			
		}else if("Login_user".equalsIgnoreCase(action)) {
			String email = request.getParameter("Email");
			String password = request.getParameter("Password");
			if(email!=null||password!=null) {
				HttpSession session = request.getSession();
				session.setAttribute("user", email);
			}
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}else if("Register_user".equalsIgnoreCase(action)) {
			String email = request.getParameter("Email");
			String password = request.getParameter("Password");
			if(email!=null||password!=null) {
				HttpSession session = request.getSession();
				session.setAttribute("user", email);
			}
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}else if("logout".equalsIgnoreCase(action)) {
			HttpSession session = request.getSession(false);
			if(session!=null)
					session.invalidate();
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}else if("checkout".equalsIgnoreCase(action)){
		
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/checkout.jsp");
			rd.forward(request, response);
		}else if("create-account".equalsIgnoreCase(action)){
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/create-account.jsp");
			rd.forward(request, response);
		}
		else if("myaccount".equalsIgnoreCase(action)){
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/myaccount.jsp");
			rd.forward(request, response);
		}else if("carrito".equalsIgnoreCase(action)){
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/carrito.jsp");
			rd.forward(request, response);
		}else if("wishlist".equalsIgnoreCase(action)) {
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/wishlist.jsp");
			rd.forward(request, response);
		
		}else if("products".equalsIgnoreCase(action)) {
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/products.jsp");
			rd.forward(request, response);
		
		}else if("productpage".equalsIgnoreCase(action)) {
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/product-page.jsp");
			rd.forward(request, response);
		
		}else if("add_to_cart".equalsIgnoreCase(action)) {
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/carrito.jsp");
			rd.forward(request,response);
		}
		else{
		
		response.setContentType("text/html");
		RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
		}
		
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
