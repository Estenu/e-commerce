

package servlet_ecommerce;

import java.io.IOException;
import java.util.List;

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
import org.apache.commons.codec.binary.*;

import jpa_Manager.PedidosManager;
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
		
/***********************************CONTROL DE USUARIOS***********************************************/	
		
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
					List<Pedido> carrito=myManager.getCarrito(email);
					if(carrito!=null) {
						List<Producto>productoscarrito=myManager.getProductos(carrito);
						session.setAttribute("carrito", carrito); 
						session.setAttribute("productoscarrito", productoscarrito);
					}
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
		
		
		
		
/******************************CONTROL DE CARRITO Y LISTA DE DE DESEOS***********************************/
			
		}else if("add_to_cart".equalsIgnoreCase(action)) {
			HttpSession session = request.getSession();
			int counter=Integer.parseInt(request.getParameter("counter"));
			List<Pedido> wishlist = (List<Pedido>) session.getAttribute("wishlist");
			Pedido pedido_a_carrito=wishlist.get(counter);
			pedido_a_carrito.setTipo(1);
			Request_Manager manager=new Request_Manager();
			Pedido nuevo=manager.modificarPedido(pedido_a_carrito);
			if(nuevo!=null) {
				Usuario user =(Usuario)session.getAttribute("user");
				List<Pedido> carrito=manager.getCarrito(user.getEmail());
				List<Pedido>wishlist1=manager.getWishlist(user.getEmail());
				List<Producto>productoscarrito=manager.getProductos(carrito);
				List<Producto>productoswishlist=manager.getProductos(wishlist1);
				session.setAttribute("wishlist", wishlist1);
				session.setAttribute("carrito", carrito); 
				session.setAttribute("productoscarrito", productoscarrito);
				session.setAttribute("productoswishlist", productoswishlist);
			}
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/carrito.jsp");
			rd.forward(request,response);
			
		}else if("add_to_cart_all".equalsIgnoreCase(action)){
			HttpSession session = request.getSession();
			List<Pedido> wishlist = (List<Pedido>) session.getAttribute("wishlist");
			Request_Manager manager=new Request_Manager();
			Usuario user =(Usuario)session.getAttribute("user");
			for(int i=0;i<wishlist.size();i++) {
				Pedido pedido=wishlist.get(i);
				pedido.setTipo(1);
				Pedido nuevo=manager.modificarPedido(pedido);
				if(nuevo==null) {
					
					List<Pedido> carrito=manager.getCarrito(user.getEmail());
					List<Pedido>wishlist1=manager.getWishlist(user.getEmail());
					List<Producto>productoscarrito=manager.getProductos(carrito);
					session.setAttribute("carrito", carrito); 
					List<Producto>productoswishlist=manager.getProductos(wishlist1);
					session.setAttribute("wishlist", wishlist1);
					session.setAttribute("productoscarrito", productoscarrito);
					session.setAttribute("producto_erroneo", wishlist.get(i));
					session.setAttribute("productoswishlist", productoswishlist);
					response.setContentType("text/html");
					RequestDispatcher rd=request.getRequestDispatcher("/wishlist.jsp");
					rd.forward(request,response);
				}
			}
			List<Pedido> carrito=manager.getCarrito(user.getEmail());
			List<Pedido>wishlist1=manager.getWishlist(user.getEmail());
			List<Producto>productoscarrito=manager.getProductos(carrito);
			session.setAttribute("carrito", carrito); 
			List<Producto>productoswishlist=manager.getProductos(wishlist1);
			session.setAttribute("wishlist", wishlist1);
			session.setAttribute("productoscarrito", productoscarrito);
			session.setAttribute("productoswishlist", productoswishlist);
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/carrito.jsp");
			rd.forward(request,response);
			
		}else if("place_order".equalsIgnoreCase(action)) {
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/checkout.jsp");
			rd.forward(request, response);
		}else if("quitar_de_carrito".equalsIgnoreCase(action)) {
			int indice=Integer.parseInt(request.getParameter("counter1"));
			Request_Manager manager=new Request_Manager();
			HttpSession session = request.getSession();
			List<Pedido> pedio = (List<Pedido>) session.getAttribute("carrito");
			if(indice>-1 && indice<pedio.size()) {
				manager.quitardeCarrito(pedio.get(indice));
				
				Usuario user =(Usuario)session.getAttribute("user");
				List<Pedido> carrito=manager.getCarrito(user.getEmail());
				List<Producto>productoscarrito=manager.getProductos(carrito);
				session.setAttribute("carrito", carrito); 
				session.setAttribute("productoscarrito", productoscarrito);
			}
			
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/carrito.jsp");
			rd.forward(request, response);
		}else if("quitar_de_wishlist".equalsIgnoreCase(action)) {
			int indice=Integer.parseInt(request.getParameter("counter"));
			System.out.println("CONTADOR DE CALVOS: "+indice);
			Request_Manager manager=new Request_Manager();
			HttpSession session = request.getSession();
			List<Pedido> pedio = (List<Pedido>) session.getAttribute("wishlist");
			if(indice>-1 && indice<pedio.size()) {
				manager.quitardeWishlist(pedio.get(indice));
				Usuario user =(Usuario)session.getAttribute("user");
				List<Pedido> wishlist=manager.getWishlist(user.getEmail());
				List<Producto>productoswishlist=manager.getProductos(wishlist);
				session.setAttribute("wishlist", wishlist); 
				session.setAttribute("productoswishlist", productoswishlist);
			}
			
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/wishlist.jsp");
			rd.forward(request, response);
		}else if("carrito".equalsIgnoreCase(action)){
			
			HttpSession session = request.getSession();
			Usuario usuario = (Usuario) session.getAttribute("user");
			Request_Manager myManager = new Request_Manager();
			List<Pedido> carrito=myManager.getCarrito(usuario.getEmail());
			if(carrito!=null) {
				List<Producto>productoscarrito=myManager.getProductos(carrito);
				session.setAttribute("carrito", carrito); 
				session.setAttribute("productoscarrito", productoscarrito);
			}
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/carrito.jsp");
			rd.forward(request, response);
		}else if("wishlist".equalsIgnoreCase(action)) {
			
			HttpSession session = request.getSession();
			Usuario usuario = (Usuario) session.getAttribute("user");
			Request_Manager myManager = new Request_Manager();
			List<Pedido> wishlist=myManager.getWishlist(usuario.getEmail());
			if(wishlist!=null) {
				List<Producto>productoswishlist=myManager.getProductos(wishlist);
				session.setAttribute("wishlist", wishlist); 
				session.setAttribute("productoswishlist", productoswishlist);
			}
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/wishlist.jsp");
			rd.forward(request, response);
		
		
/**********************************CONTROL DE PRODUCTOS***************************************************/
		}else if("products".equalsIgnoreCase(action)) {
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/products.jsp");
			rd.forward(request, response);
		
		}else if("productpage".equalsIgnoreCase(action)) {
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/product-page.jsp");
			rd.forward(request, response);
			
		}else if("createProduct".equalsIgnoreCase(action)) {
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/create-product.jsp");
			rd.forward(request,response);
		}else if("manageProduct".equalsIgnoreCase(action)) {
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/modify-product.jsp");
			rd.forward(request,response);
		}else if("catalogoBBDD".equalsIgnoreCase(action)){
			Request_Manager myManager = new Request_Manager();
			HttpSession session = request.getSession();
			Usuario user = (Usuario) session.getAttribute("user");
			myManager.getProductosUsuario(user);
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/modify-product.jsp");
			rd.forward(request, response);
		}else if("Register_product".equalsIgnoreCase(action)){
			Request_Manager myManager = new Request_Manager();
			Part filePart = request.getPart("fileToUpload");
		    byte[] data = new byte[(int) filePart.getSize()];
		    filePart.getInputStream().read(data, 0, data.length);
		    HttpSession session = request.getSession();
			Usuario user = (Usuario) session.getAttribute("user");
			
			myManager.crear_Producto(request.getParameter("IdProduct"), user, Integer.parseInt(request.getParameter("precio")), Integer.parseInt(request.getParameter("stock")), request.getParameter("selector"), request.getParameter("desc"), request.getParameter("longDesc"), data);
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/create-product.jsp");
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