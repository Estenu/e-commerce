package servlet_ecommerce;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.util.Enumeration;

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

import jhc.info.InformacionProperties;
import jpa_Manager.PedidosManager;
import request_Manager.*;
/**
 * Servlet implementation class E_commerce_servlet
 */

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
		
		if("login".equalsIgnoreCase(action)) { // redirecciona a la página de login
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/Login.jsp");
			rd.forward(request, response);
			
		}else if("Login_user".equalsIgnoreCase(action)) {// inicia sesion con el email y contraseña pasado si existen
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
					
					response.setContentType("text/html");
					RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
					rd.forward(request, response);

				}else {
					request.setAttribute("loginError", "\r\n" + "We didnï¿½t recognise your username or password");
					response.setContentType("text/html");
					RequestDispatcher rd=request.getRequestDispatcher("/Login.jsp");
					rd.forward(request, response);
				}
			}
			
		}else if("Register_user".equalsIgnoreCase(action)) {
			// se inserta un nuevo usuario en la base de datos con los datos pasados
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
			//se cierra la sesion del usuario actual
			HttpSession session = request.getSession();
			System.out.println(session.getId());
			Enumeration<String> atributes = session.getAttributeNames();
			System.out.println(atributes.nextElement());
			Usuario user = (Usuario) session.getAttribute("user");
			System.out.println(user.getEmail());
			session.removeAttribute("user");
			session.removeAttribute("wishlist");
			session.removeAttribute("carrito");
			session.removeAttribute("productoscarrito");
			session.removeAttribute("productoswishlist");
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}else if("checkout".equalsIgnoreCase(action)){
			//redireccion a jsp de checkout
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/checkout.jsp");
			rd.forward(request, response);
		}else if("create-account".equalsIgnoreCase(action)){
			// redireccion a jsp para crear una cuenta
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/create-account.jsp");
			rd.forward(request, response);
		}else if("myaccount".equalsIgnoreCase(action)){
			//redireccion a jsp para ver los detalles de la cuenta
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/myaccount.jsp");
			rd.forward(request, response);
		}else if("update_user".equalsIgnoreCase(action)) {			
			// modifica los datos de un usuario en la base de datos
			HttpSession session = request.getSession();
			Usuario usuarioAntiguo = (Usuario) session.getAttribute("user");
			Request_Manager myManager = new Request_Manager();
			Usuario user = myManager.modificarUsuario(usuarioAntiguo,request.getParameter("nombre"), request.getParameter("apellido1"), request.getParameter("apellido2"), request.getParameter("contrasena"), request.getParameter("direccion"),request.getParameter("CPostal"));
			session.setAttribute("user", user); 
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		
		}else if("delete_user".equalsIgnoreCase(action)) {
			// borra una cuenta de usuario en la base de datos
			HttpSession session = request.getSession();
			Usuario usuarioAntiguo = (Usuario) session.getAttribute("user");
			Request_Manager myManager = new Request_Manager();
			myManager.eliminarUsuario(usuarioAntiguo);
			
			if(session!=null) {
					session.invalidate();
			}
			
			session = request.getSession();
			List<Producto> elementos = myManager.getProductosAll();
			List<CategoríasInferiore> catInf = myManager.getCatInfAll();
			List<CategoríasSuperiore> catSup = myManager.getCatSupAll();
			session.setAttribute("catalogo", elementos);
			session.setAttribute("catInf", catInf);
			session.setAttribute("catSup", catSup);
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);


/****************************CONTROL DE MENSAJES+++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
		
		}else if("mymessages".equalsIgnoreCase(action)) {
			// redirecciona servlet de control de mensajes
			RequestDispatcher rd=request.getRequestDispatcher("jms_servlet?mode=read");
			rd.forward(request, response);
				
/******************************CONTROL DE CARRITO Y LISTA DE DE DESEOS***********************************/
			
		}else if("add_to_cart".equalsIgnoreCase(action)) {
			/*funcion que recibe un pedido por parametro, lo modifica para que sea
			 * un pedido de carrito y actualiza los objetos de sesion de wishlist y carrito
			 */
			HttpSession session = request.getSession();
			int counter=Integer.parseInt(request.getParameter("counter"));
			List<Pedido> wishlist = (List<Pedido>) session.getAttribute("wishlist");
			Pedido pedido_a_carrito=wishlist.get(counter);
			pedido_a_carrito.setTipo(1);
			Request_Manager manager=new Request_Manager();
			Pedido nuevo=manager.modificarPedido(pedido_a_carrito);
			if(nuevo!=null) {
				Usuario user =(Usuario)session.getAttribute("user");
				if(user!= null) {
					List<Pedido> carrito=manager.getCarrito(user.getEmail());
					List<Pedido>wishlist1=manager.getWishlist(user.getEmail());
					List<Producto>productoscarrito=manager.getProductos(carrito);
					List<Producto>productoswishlist=manager.getProductos(wishlist1);
					session.setAttribute("wishlist", wishlist1);
					session.setAttribute("carrito", carrito); 
					session.setAttribute("productoscarrito", productoscarrito);
					session.setAttribute("productoswishlist", productoswishlist);
				}else {
					response.setContentType("text/html");
					RequestDispatcher rd=request.getRequestDispatcher("/Login.jsp");
					rd.forward(request,response);
				}	
			}
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/carrito.jsp");
			rd.forward(request,response);
			
		}else if("add_to_cart_all".equalsIgnoreCase(action)){
			/*funcion que convierte toda la lista de deseos en pedidos de carrito.
			 * Una vez hecho actualiza los atributos de sesion carrito y wishlist
			 */
			HttpSession session = request.getSession();
			List<Pedido> wishlist = (List<Pedido>) session.getAttribute("wishlist");
			Request_Manager manager=new Request_Manager();
			Usuario user =(Usuario)session.getAttribute("user");
			if(user!= null) {
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
			}else {
				response.setContentType("text/html");
				RequestDispatcher rd=request.getRequestDispatcher("/Login.jsp");
				rd.forward(request,response);
			}	
		}else if("add_to_wishlist".equalsIgnoreCase(action)) {
			/*funcion que recibe un producto y un usuario y crea un pedido de tipo
			 * wishlist. Se actualiza atributo de sesion wishlist
			 * Si no se ha iniciado sesion redirige a pagina de iniciar sesion
			 */
			HttpSession session=request.getSession();
			int indice=Integer.parseInt(request.getParameter("productoawishlist"));
			Usuario user=(Usuario)session.getAttribute("user");
			if(user!= null) {
				Request_Manager manager=new Request_Manager();
				List<Producto>productos=manager.getProductosAll();
				manager.crearPedido(0,user.getEmail() , 1, productos.get(indice).getIdProducto());
				List<Pedido>wishlist=manager.getWishlist(user.getEmail());
				List<Producto>productoswishlist=manager.getProductos(wishlist);
				session.setAttribute("wishlist", wishlist);
				session.setAttribute("productoswishlist", productoswishlist);
			
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/products.jsp");
			rd.forward(request,response);
			}else {
				response.setContentType("text/html");
				RequestDispatcher rd=request.getRequestDispatcher("/Login.jsp");
				rd.forward(request,response);
			}
		}else if("add_to_cart_product".equalsIgnoreCase(action)) {
			/*funcion que recibe un producto y un usuario y crea un pedido de tipo carrito
			 * Se actualiza atributo de sesion carrito
			 * Si no se ha iniciado sesion se redirige a iniciar sesion
			 */
			HttpSession session=request.getSession();
			int indice=Integer.parseInt(request.getParameter("productoacarrito"));
			int cantidad = Integer.parseInt(request.getParameter("cantidad"));
			Usuario user=(Usuario)session.getAttribute("user");
			if(user!=null) {
				Request_Manager manager=new Request_Manager();
				List<Producto>productos=manager.getProductosAll();
				manager.crearPedido(1,user.getEmail() , cantidad, productos.get(indice).getIdProducto());
				List<Pedido>carrito=manager.getCarrito(user.getEmail());
				session.setAttribute("carrito", carrito);
				List<Producto>productoscarrito=manager.getProductos(carrito);
				session.setAttribute("productoscarrito", productoscarrito);
				
				response.setContentType("text/html");
				RequestDispatcher rd=request.getRequestDispatcher("/products.jsp");
				rd.forward(request,response);
			}
			else {
				response.setContentType("text/html");
				RequestDispatcher rd=request.getRequestDispatcher("/Login.jsp");
				rd.forward(request,response);
			}
		}else if("place_order".equalsIgnoreCase(action)) {
			//redireccion a pagina de checkout
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/checkout.jsp");
			rd.forward(request, response);
		}else if("quitar_de_carrito".equalsIgnoreCase(action)) {
			/* funcion que recibe un pedido de tipo carrito y elimina pedido
			 * se actualiza atributo de sesion carrito
			 */
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
			/* funcion que recibe un pedido de tipo wishlist y elimina pedido
			 * se actualiza atributo de sesion wishlist
			 */
			int indice=Integer.parseInt(request.getParameter("counter"));
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
			/* funcion que redirige a pagina de carrito
			 * crea los atributos de sesion carrito y productos del carrito
			 */
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
			/* funcion que redirige a pagina de wishlist
			 * crea los atributos de sesion wishlist y productos del wishlist
			 */
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
			/*funcion que redirige a pagina products 
			 * crea el atributo de sesion productos
			 */
			Request_Manager myManager = new Request_Manager();
			HttpSession session = request.getSession();
			List<CategoríasInferiore> catInf = myManager.getCatInfAll();
			session.setAttribute("catInf", catInf);
			List<Producto> elementos = myManager.getProductosAll();
			session.setAttribute("catalogo", elementos);
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/products.jsp");
			rd.forward(request, response);
		}else if("catalogoSearch".equalsIgnoreCase(action)) {
			/*funcion que implementa los buscadores de la web 
			 * busca en la base de datos productos por categoria y nombre y los devuelve a la jsp
			 */
			Request_Manager myManager = new Request_Manager();
			HttpSession session = request.getSession();
			List<Producto> elementos = null;
			if(request.getParameter("byName").equals("")) {
				if(request.getParameter("selector").equals("Todos")) {
					elementos = myManager.getProductosAll();
				}else {
					elementos = myManager.getProductosByCatInf(request.getParameter("selector"));
				}
			}else if(request.getParameter("selector").equals("Todos") && !request.getParameter("byName").equals("")) {
				elementos = myManager.getProductosSimilar(request.getParameter("byName"));
			}else if(!request.getParameter("selector").equals("Todos") && !request.getParameter("byName").equals("")){
				elementos = myManager.getProductosByNameAndCat(request.getParameter("byName"),request.getParameter("selector"));
			}
			session.setAttribute("catalogo", elementos);
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/products.jsp");
			rd.forward(request, response);
		}else if("productpage".equalsIgnoreCase(action)) {
			//funcion que recibe un producto y muestra la pagina individual de cada producto
			int position = Integer.parseInt(request.getParameter("counter"));
			System.out.println(position);
			HttpSession session = request.getSession();
			session.setAttribute("index", position);
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/product-page.jsp");
			rd.forward(request, response);
		}else if("createProduct".equalsIgnoreCase(action)) {
			/*funcion que redirige a jsp de crear producto
			 * crea un atributo con las categorías inferiores disponibles
			 */
			Request_Manager myManager = new Request_Manager();
			HttpSession session = request.getSession();
			List<CategoríasInferiore> catInf = myManager.getCatInfAll();
			session.setAttribute("catInf", catInf);
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/create-product.jsp");
			rd.forward(request,response);
		}else if("manageProduct".equalsIgnoreCase(action)) {
			//funcion que redirige a jsp de modificar productos y borrar productos
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/modify-product.jsp");
			rd.forward(request,response);
		}else if("editProductPage".equalsIgnoreCase(action)) {
			//funcion que redirige a jsp de editar productos
			int position = Integer.parseInt(request.getParameter("counter"));
			HttpSession session = request.getSession();
			session.setAttribute("index", position);
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/edit-product.jsp");
			rd.forward(request,response);
		}else if("editProduct".equalsIgnoreCase(action)) {
			Request_Manager myManager = new Request_Manager();
			/*Producto modificado*/
			Producto aux = new Producto();
			if(request.getParameter("precio").equals("")) {
				aux.setPrecio(-404);
			}else {
				aux.setPrecio(Integer.parseInt(request.getParameter("precio")));
			}
			if(request.getParameter("stock").equals("")) {
				aux.setStock(-404);
			}else {
				aux.setStock(Integer.parseInt(request.getParameter("stock")));
			}
			aux.setDescription(request.getParameter("desc"));
			aux.setLongDesc(request.getParameter("longDesc"));
			Part filePart = request.getPart("fileToUpload");
			
		    byte[] data = new byte[(int) filePart.getSize()];
		    filePart.getInputStream().read(data, 0, data.length);
			aux.setImagen(data);
			/*producto que quiero modificar*/
			
			HttpSession session = request.getSession();
			Usuario user = (Usuario) session.getAttribute("user");
			Object lista = user.getProductos();
			List<Producto> elementos = (List<Producto>)lista;
			int index = (Integer) session.getAttribute("index");
			Producto myOld = elementos.get(index);
			/*Modificamos el producto*/
			myManager.modificarProducto(aux, myOld);
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("E_commerce_servlet?action=catalogoBBDD");
			rd.forward(request, response);
		}else if("deleteProduct".equalsIgnoreCase(action)) {
			/*funcion que recibe un producto y lo borra de la base de datos
			 * actualiza el atributo de sesion catalogo
			 */
			Request_Manager myManager = new Request_Manager();
			int position = Integer.parseInt(request.getParameter("counter"));
			
			HttpSession session = request.getSession();
			Usuario user = (Usuario) session.getAttribute("user");
			Object lista = user.getProductos();
			List<Producto> elementos = (List<Producto>) lista;

			myManager.eliminarProducto(elementos.get(position).getIdProducto());
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("E_commerce_servlet?action=catalogoBBDD");
			rd.forward(request, response);
		}else if("catalogoBBDD".equalsIgnoreCase(action)){
			/*Actualiza los productos que se deben mostrar en "configurar productos" para los vendedores registrados en la 
			 * pagina web. Tambien se llama cuando se borran o modifican productos*/
			Request_Manager myManager = new Request_Manager();
			HttpSession session = request.getSession();
			Usuario user = (Usuario) session.getAttribute("user");
			myManager.getProductosUsuario(user);
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("/modify-product.jsp");
			rd.forward(request, response);
		}else if("Register_product".equalsIgnoreCase(action)){
			/*funcion que crea un nuevo producto en la base de datos*/
			Request_Manager myManager = new Request_Manager();
			Part filePart = request.getPart("fileToUpload");
		    byte[] data = new byte[(int) filePart.getSize()];
		    filePart.getInputStream().read(data, 0, data.length);
		    HttpSession session = request.getSession();
			Usuario user = (Usuario) session.getAttribute("user");
			myManager.crear_Producto(request.getParameter("IdProduct"), user, Integer.parseInt(request.getParameter("precio")), Integer.parseInt(request.getParameter("stock")), request.getParameter("selector"), request.getParameter("desc"), request.getParameter("longDesc"), data);
			
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("E_commerce_servlet?action=catalogoBBDD");
			rd.forward(request, response);
		
		
		}else if("mispedidos".equalsIgnoreCase(action)){
			
			
			String userName = InformacionProperties.getStrUser();
			String password = InformacionProperties.getStrPassword();
			String url = "jdbc:mysql://localhost/" + InformacionProperties.getStrDatabaseName() + "?user=" + userName
					+ "&password=" + password+ "&useSSL=false&serverTimezone=UTC";
			response.setContentType("text/html");
			ArrayList<String> empRecSet=new ArrayList<String>();
			  HttpSession session = request.getSession();
				Usuario user = (Usuario) session.getAttribute("user");
				String email=user.getEmail();
			try {
				Class.forName(InformacionProperties.getStrClassDriver());

				Connection conexion = DriverManager.getConnection(url);

				Statement myStatement = conexion.createStatement();
				ResultSet rs;
				System.out.println(email);
				rs= myStatement.executeQuery("SELECT * FROM pedidos");
				while (rs.next()) {//CategoriasSuperiores
					String email2 = rs.getString("email");
					int tipo2 = rs.getInt("tipo");
					if(email2.equalsIgnoreCase(email)&&tipo2==2) {
				String nombreCS = rs.getString("id_producto");//sacamos
				System.out.println(rs.getString("id_producto"));
				System.out.println(nombreCS);
				empRecSet.add(nombreCS);//anadimos en lista
				}
				}
				
				try {
					
					
					myStatement.close();
					conexion.close();
					
				} catch (Exception e) {
					System.out.println("Error al visualizar datos");
					}} catch (Exception e) {
						System.out.println("Error al visualizar datos");
						}
			
			request.setAttribute("ListaIDProductos", empRecSet);
			RequestDispatcher rd=request.getRequestDispatcher("/MisPedidos.jsp");
			rd.forward(request, response);	
		}else{
			/*Cuando se accede a la pagina web se devuelven los siguientes atributos de sesion
			 * catalogo: todos los productos de la base de datos
			 * catInf: todas las categorias inferiores
			 * catSup: todas las categorias superiores
			 */
			Request_Manager myManager = new Request_Manager();
			HttpSession session = request.getSession();
			List<Producto> elementos = myManager.getProductosAll();
			List<CategoríasInferiore> catInf = myManager.getCatInfAll();
			List<CategoríasSuperiore> catSup = myManager.getCatSupAll();
			session.setAttribute("catalogo", elementos);
			session.setAttribute("catInf", catInf);
			session.setAttribute("catSup", catSup);
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