package servlet_ecommerce;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import request_Manager.*;
/**
 * Servlet implementation class E_commerce_servlet
 */
@WebServlet("/E_commerce_servlet")
@MultipartConfig
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
				
				Request_Manager myManager = new Request_Manager();
				Usuario user = myManager.findusuarioById(email);
				
				
				
				if (user!=null && (user.getEmail().equalsIgnoreCase(email) && user.getContrasena().equalsIgnoreCase(password))) {

					HttpSession session = request.getSession();
					session.setAttribute("user", user);

				}else {
					request.setAttribute("loginError", "\r\n" + "We didn’t recognise your username or password");
				}
			}

			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/Login.jsp");
			rd.forward(request, response);
		}else if("Register_user".equalsIgnoreCase(action)) {
			String email = request.getParameter("Email");
			String password = request.getParameter("Password");
			if(email!=null||password!=null) {
				HttpSession session = request.getSession();
				Request_Manager myManager = new Request_Manager();
				Usuario user = myManager.crearUsuario(request.getParameter("Email"), request.getParameter("Password"), Integer.parseInt(request.getParameter("selector")), request.getParameter("CP"), request.getParameter("Direccion"), request.getParameter("Apellido1"), request.getParameter("Apellido2"), request.getParameter("Nombre"));
				session.setAttribute("user", user);
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
		}else if("myaccount".equalsIgnoreCase(action)){
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/myaccount.jsp");
			rd.forward(request, response);
		}else if("update_user".equalsIgnoreCase(action)) {			
							
			HttpSession session = request.getSession();
			Usuario usuarioAntiguo = (Usuario) session.getAttribute("user");
			Request_Manager myManager = new Request_Manager();
			Usuario user = myManager.modificarUsuario(usuarioAntiguo,request.getParameter("nombre"), request.getParameter("apellido1"), request.getParameter("apellido2"), request.getParameter("contrasena"), request.getParameter("direccion"),request.getParameter("CPostal"));
			session.setAttribute("user", user); 
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		
		}else if("delete_user".equalsIgnoreCase(action)) {
			
			HttpSession session = request.getSession();
			Usuario usuarioAntiguo = (Usuario) session.getAttribute("user");
			Request_Manager myManager = new Request_Manager();
			myManager.eliminarUsuario(usuarioAntiguo);
			
			if(session!=null) {
					session.invalidate();
			}
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
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
		}else if("createProduct".equalsIgnoreCase(action)) {
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/create-product.jsp");
			rd.forward(request,response);
		}else if("Register_product".equalsIgnoreCase(action)){
			Request_Manager myManager = new Request_Manager();
			Part filePart = request.getPart("fileToUpload");
		    byte[] data = new byte[(int) filePart.getSize()];
		    filePart.getInputStream().read(data, 0, data.length);
		    HttpSession session = request.getSession();
			Usuario user = (Usuario) session.getAttribute("user");
			
			myManager.crear_Producto(request.getParameter("IdProduct"), user, Integer.parseInt(request.getParameter("precio")), Integer.parseInt(request.getParameter("stock")), request.getParameter("selector"), request.getParameter("desc"), request.getParameter("longDesc"), data);
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}else{
		
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