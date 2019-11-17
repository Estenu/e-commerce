/**
 * 
 */
package request_Manager;
import javax.persistence.Persistence;
/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import javax.naming.InitialContext;
import javax.naming.NamingException;*/
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import servlet_ecommerce.*;
import jpa_Manager.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author alvaro
 *
 */
public class Request_Manager {
	
	
	public Request_Manager() {
		
	}
	
/********************************INSERCION EN BASE DE DATOS POR JPA**********************************************************************************************************/
	
	public int crear_Producto(String idProducto,Usuario vendedor, int precio,int stock, String cat_Inf,String descripcion, String long_descripcion,byte[] imagen) {
		Producto newProduct=new Producto();
		newProduct.setDescription(descripcion);
		newProduct.setLongDesc(long_descripcion);
		newProduct.setPrecio(precio);
		newProduct.setStock(stock);
		newProduct.setImagen(imagen);
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("EjemploJPA");
		Cat_InfManager inf=new Cat_InfManager();
		inf.setEntityManagerFactory(factory);
		try {
			CategoríasInferiore cat=inf.findcategoríaInferiorById(cat_Inf);
			newProduct.setCategoríasInferiore(cat);
		}catch(Exception e) {
			System.out.println("Descripcion manager: "+ e.getMessage());
			return -1;
		}
		newProduct.setUsuario(vendedor);
		newProduct.setIdProducto(idProducto);
		
		
		ProductoManager manager = new ProductoManager();
		manager.setEntityManagerFactory(factory);
		try {
			manager.createProducto(newProduct);
			return 0;
		} catch (Exception e) {
			System.out.println("Descripción: " + e.getMessage());
			System.out.println("Descripcion manager: "+ e.getMessage());
			return -1;
		}

		
	}
	
	public Usuario crearUsuario(String email, String contraseña, int status, String Cpostal, String direccion, String apellido, String apellido2, String nombre) {
		Usuario newUser=new Usuario();
		newUser.setEmail(email);
		newUser.setApellido(apellido);
		newUser.setApellido2(apellido2);
		newUser.setContrasena(contraseña);
		newUser.setCpostal(Cpostal);
		newUser.setEstatus(status);
		newUser.setNombre(nombre);
		newUser.setDirección(direccion);
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("EjemploJPA");
		UsuariosManager manager=new UsuariosManager();
		manager.setEntityManagerFactory(factory);
		try {
			manager.createUsuario(newUser);
			return newUser;
		}catch(Exception e) {
			System.out.println("Descripcion manager: " + e.getMessage());
			return null;
		}
	}
	
	public int crearCatInf(String nombre_Cat_inf,String nombre_Cat_Sup) {
		CategoríasInferiore newCat_Inf=new CategoríasInferiore();
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("EjemploJPA");
		Cat_SupManager sup=new Cat_SupManager();
		sup.setEntityManagerFactory(factory);
		try {
			CategoríasSuperiore cat=sup.findcategoríaSuperiorById(nombre_Cat_Sup);
			newCat_Inf.setCategoríasSuperiore(cat);
		
		}catch(Exception e) {
			System.out.println("Descripción: "+ e.getMessage());
			return -1;
		}
		
		Cat_InfManager manager=new Cat_InfManager();
		manager.setEntityManagerFactory(factory);
		try {
			manager.createCategoríasInferiores(newCat_Inf);
			return 0;
		}catch(Exception e) {
			System.out.println("Descripción: " + e.getMessage());
			return -1;
		}
	}
		
	public int crearCatSup(String nombre_Cat_Sup) {
		CategoríasSuperiore newCat_Sup=new CategoríasSuperiore();
		newCat_Sup.setNombre_Cat_Sup(nombre_Cat_Sup);
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("EjemploJPA");
		Cat_SupManager manager=new Cat_SupManager();
		manager.setEntityManagerFactory(factory);
		try {
			manager.createCategoríasSuperiores(newCat_Sup);
			return 0;
		
		}catch(Exception e) {
			System.out.println("Descripción: "+ e.getMessage());
			return -1;
		}
	
		
	}
	
	public int crearPedido(int tipo, String usuario, int cantidad, String producto) {
		Pedido newPedido=new Pedido();
		newPedido.setTipo(tipo);
		newPedido.setCantidad(cantidad);
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("EjemploJPA");
		UsuariosManager usu=new UsuariosManager();
		usu.setEntityManagerFactory(factory);
		try {
			Usuario user=usu.findusuarioById(usuario);
			newPedido.setUsuario(user);
		}catch(Exception e) {
			System.out.println("Descripción: "+ e.getMessage());
			return -1;

		}
		ProductoManager prod=new ProductoManager();
		prod.setEntityManagerFactory(factory);
		try {
			Producto product=prod.findproductoById(producto);
			newPedido.setProducto(product);
		}catch(Exception e) {
			System.out.println("Descripción: "+ e.getMessage());
			return -1;

		}
		PedidosManager pedido=new PedidosManager();
		pedido.setEntityManagerFactory(factory);
		try {
			pedido.createPedido(newPedido);
			return 0;
		}catch(Exception e) {
			System.out.println("Descripción: "+ e.getMessage());
			return -1;

		}
	}

/***************************************ELIMINACION EN BASE DE DATOS POR JPA****************************************************/
	public int eliminarPedido(int pedido) {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("EjemploJPA");
		Pedido oldPedido;
		PedidosManager ped=new PedidosManager();
		ped.setEntityManagerFactory(factory);
		try {
			oldPedido=ped.findpedidoById(pedido);
			if(oldPedido!=null) {
				ped.deletepedido(oldPedido);
				return 0;
			}
		}catch(Exception e) {
			System.out.println("Descripcion manager: "+e.getMessage());
			return -1;
		}
		return -1;
	}
	
	public void eliminarUsuario(Usuario user) {
		
		
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("EjemploJPA");
		UsuariosManager manager=new UsuariosManager();
		manager.setEntityManagerFactory(factory);
		try {
			manager.deleteusuario(user);
		}catch(Exception e) {
			System.out.println("Descripcion manager: " + e.getMessage());
		}
	
		
	}
	
	public int eliminarProducto(String idProducto) {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("EjemploJPA");
		Producto oldProducto=null;
		ProductoManager prod=new ProductoManager();
		prod.setEntityManagerFactory(factory);
		try {
			oldProducto=prod.findproductoById(idProducto);
		}catch(Exception e) {
			System.out.println("Descripcion manager:  "+ e.getMessage());
			return -1;
		}
		if(oldProducto!=null) {
			List<Pedido> listaPedidos=oldProducto.getPedidos();
			try {
				if(listaPedidos!=null) {
					for(int i=0;i<listaPedidos.size();i++) {
						eliminarPedido(listaPedidos.get(i).getNºPedido());
					}
				}
				try {
					prod.deleteproducto(oldProducto);
				}catch(Exception e) {
					System.out.println("Descripcion manager: " + e.getMessage());
				}
				return 0;
			}catch(Exception e) {
				System.out.println("Descripcion manager: "+e.getMessage());
				return -1;
			}
		}
		
		return -1;	
	}
	
	public int eliminarCat_Inf(String nombre_Cat_Inf) {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("EjemploJPA");
		CategoríasInferiore oldCatInf=null;
		Cat_InfManager catInf=new Cat_InfManager();
		catInf.setEntityManagerFactory(factory);
		try {
			oldCatInf=catInf.findcategoríaInferiorById(nombre_Cat_Inf);
		}catch(Exception e) {
			System.out.println("Descripcion manager:  "+ e.getMessage());
			return -1;
		}
		if(oldCatInf!=null) {
			List<Producto> listaProductos=oldCatInf.getProductos();
			try {
				if(listaProductos!=null) {
					for(int i=0;i<listaProductos.size();i++) {
						eliminarProducto(listaProductos.get(i).getIdProducto());
					}
					return 0;
				}
				return 0;
			}catch(Exception e) {
				System.out.println("Descripcion manager: "+e.getMessage());
				return -1;
			}
		}
		return -1;
	}
	
	public int eliminarCat_Sup(String nombre_CatSup) {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("EjemploJPA");
		CategoríasSuperiore oldCatSup=null;
		Cat_SupManager catSup=new Cat_SupManager();
		catSup.setEntityManagerFactory(factory);
		try {
			oldCatSup=catSup.findcategoríaSuperiorById(nombre_CatSup);
		}catch(Exception e) {
			System.out.println("Descripcion manager:  "+ e.getMessage());
			return -1;
		}
		if(oldCatSup!=null) {
			List<CategoríasInferiore> listaCategoriasInferiores=oldCatSup.getCategoríasInferiores();
			try {
				if(listaCategoriasInferiores!=null) {
					for(int i=0;i<listaCategoriasInferiores.size();i++) {
						eliminarCat_Inf(listaCategoriasInferiores.get(i).getNombre_Cat_Inf());
					}
					return 0;
				}
				return 0;
			}catch(Exception e) {
				System.out.println("Descripcion manager: "+e.getMessage());
				return -1;
			}
		}
		return -1;
	}
	public void eliminarMensaje(int MensajeId) {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("EjemploJPA");
		Mensaje oldMensaje;
		MensajesManager men=new MensajesManager();
		men.setEntityManagerFactory(factory);
		try {
			oldMensaje=men.findmensajeById(MensajeId);
			if(oldMensaje!=null) {
				men.deletemensaje(oldMensaje);
			}
		}catch(Exception e) {
			System.out.println("Descripcion manager: "+e.getMessage());
		}
	}
	
	
/***************************************MODIFICACION EN BASE DE DATOS POR JPA*****************************************************/
	public Usuario modificarUsuario(Usuario oldUser,String nombre, String apellido1, String apellido2, String contrasena, String direccion, String CPostal) {
		
		if(!nombre.isEmpty()) {
			oldUser.setNombre(nombre);
		}
		if(!apellido1.isEmpty()) {
			oldUser.setApellido(apellido1);
		}
		if(!apellido2.isEmpty()) {
			oldUser.setApellido2(apellido2);
		}
		if(!contrasena.isEmpty()) {
			oldUser.setContrasena(contrasena);
		}
		if(!direccion.isEmpty()) {
			oldUser.setDirección(direccion);
		}
		if(!CPostal.isEmpty()) {
			oldUser.setCpostal(CPostal);
		}

		EntityManagerFactory factory=Persistence.createEntityManagerFactory("EjemploJPA");
		UsuariosManager manager=new UsuariosManager();
		manager.setEntityManagerFactory(factory);
		try {
			Usuario newUser = manager.updateusuario(oldUser);
			return newUser;
		}catch(Exception e) {
			System.out.println("Descripcion manager: " + e.getMessage());
			return null;
		}
		

	}
	
	public Pedido modificarPedido(Pedido Pedido) {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("EjemploJPA");
		ProductoManager prod=new ProductoManager();
		Producto product=null;
		PedidosManager manager=new PedidosManager();
		manager.setEntityManagerFactory(factory);
		prod.setEntityManagerFactory(factory);
		try {
			product=prod.findproductoById(Pedido.getProducto().getIdProducto());
		}catch(Exception e) {
			System.out.println("Descripcion manager: "+ e.getMessage());
		}
		if(product!=null) {
			if(product.getStock()>=Pedido.getCantidad()) {
				try {
					manager.updatepedido(Pedido);
					return Pedido;
				}catch(Exception e) {
					System.out.println("Descripcion manager: "+ e.getMessage());
				}
			}else {
				return null;
			}
		}
		
		return null;
	}
	
	public Producto modificarProducto(Producto productomod, Producto productoold) {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("EjemploJPA");
		ProductoManager prod=new ProductoManager();
		prod.setEntityManagerFactory(factory);
		Producto old=null;
		try {
			old=prod.findproductoById(productoold.getIdProducto());
		}catch(Exception e) {
			System.out.println("Descripcion manager: "+e.getMessage());
		}
		if(old!=null) {
			if(!productomod.getDescription().equals("")) {
				productoold.setDescription(productomod.getDescription());
			}
			if(!productomod.getLongDesc().equals("")) {
				productoold.setLongDesc(productomod.getLongDesc());
			}
			if(productomod.getPrecio()!=-404) {
				productoold.setPrecio(productomod.getPrecio());
			}
			if(productomod.getStock()!=-404) {
				productoold.setStock(productomod.getStock());
			}
			if(productomod.getImagen().length != 0) {
				productoold.setImagen(productomod.getImagen());
			}
			try {
				prod.updateproducto(productoold);
				List<Pedido>pedidos=productoold.getPedidos();
				for(int i=0;i<productoold.getPedidos().size();i++) {
					pedidos.get(i).setProducto(productoold);
					modificarPedido(pedidos.get(i));
				}
			}catch(Exception e) {
				System.out.println("Descripcion manager: "+e.getMessage());
			}
		}
		
		return null;
	}
	
/**************************************CONSULTA A BASE DE DATOS POR JPA***************************************************************/
	public List <Producto> getProductos(int numero) { 
		String numeros=String.valueOf(numero);
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("EjemploJPA");
		EntityManager em=factory.createEntityManager();
		try {
			TypedQuery <Producto> q2 =em.createQuery("SELECT c FROM Productos c where ROWNUM <="+numeros+";", Producto.class);
			return q2.getResultList();
		}catch(Exception e) {
			
		}
		return null;
	
	}
	
	public List <Producto> getProductosUsuario(Usuario user) { 
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("EjemploJPA");
		ProductoManager myManager = new ProductoManager();
		myManager.setEntityManagerFactory(factory);
		List<Producto> lista = myManager.findAllUser(user);
		user.setProductos(lista);
		return null;
	}
	
	public Usuario findusuarioById(String id) {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("EjemploJPA");
		UsuariosManager manager=new UsuariosManager();
		manager.setEntityManagerFactory(factory);
		try {
			Usuario user = manager.findusuarioById(id);
			return user;
		}catch(Exception e) {
			System.out.println("Descripcion manager: " + e.getMessage());
			return null;
		}
	}
	
	public int comprarProductos(String producto,int cantidad,String comprador, int numeropedido) {
		Producto productocomprado=null;
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("EjemploJPA");
		ProductoManager prod=new ProductoManager();
		prod.setEntityManagerFactory(factory);
		try {
			productocomprado=prod.findproductoById(producto);
			
		}catch(Exception e) {
			System.out.println("Descripcion manager: "+e.getMessage());
			return -1;
		}
		int stock=productocomprado.getStock();
		if(cantidad<=stock) {
			productocomprado.setStock(productocomprado.getStock()-cantidad);
			try {
				modificarProducto(productocomprado,productocomprado);
				crearPedido(2, comprador, cantidad, producto);
				return 0;
			}catch(Exception e) {
				System.out.println("Descripcion manager: "+e.getMessage());
				return -1;
			}
		}
		
		return -1;
	}
	
	public int añadiraWishlist(Pedido pedido) {
		int resultado=crearPedido(0,pedido.getUsuario().getEmail(),pedido.getCantidad(),pedido.getProducto().getIdProducto());
		return resultado;
	}
	public int añadirCarrito(Pedido pedido) {
		int resultado=crearPedido(0,pedido.getUsuario().getEmail(),pedido.getCantidad(),pedido.getProducto().getIdProducto());
		return resultado;
	}
	public int quitardeWishlist(Pedido pedido) {
		int resultado=eliminarPedido(pedido.getNºPedido());
		return resultado;
	}
	
	public int quitardeCarrito(Pedido pedido) {
		int resultado=eliminarPedido(pedido.getNºPedido());
		return resultado;
	}
	
	public List<Pedido> getCarrito(String usuario){
		List<Pedido> wishlist=new ArrayList<Pedido>();
		String username="root";
		String password="root";
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("EjemploJPA");
		ProductoManager prod=new ProductoManager();
		prod.setEntityManagerFactory(factory);
		String url="jdbc:mysql://localhost/"+"ejemplojpa"+"?user="+username+"&password= "+password+"&useSSL=false&serverTimezone=UTC";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion=DriverManager.getConnection(url);
			Statement myStatement=conexion.createStatement();
			ResultSet rs;
			rs=myStatement.executeQuery("Select * from pedidos where email= '"+usuario+"'and tipo=1");
			while(rs.next()) {
				Pedido pedio=new Pedido();
				pedio.setCantidad(rs.getInt("cantidad"));
				pedio.setNºPedido(rs.getInt("nº_pedido"));
				String producto=rs.getString("id_producto");
				Producto prodi=prod.findproductoById(producto);
				pedio.setProducto(prodi);
				pedio.setTipo(rs.getInt("tipo"));
				String email=rs.getString("email");
				UsuariosManager us=new UsuariosManager();
				us.setEntityManagerFactory(factory);
				Usuario user=us.findusuarioById(email);
				pedio.setUsuario(user);
				wishlist.add(pedio);
			}
			myStatement.close();
			conexion.close();
			return wishlist;
		}catch(Exception e) {
			System.out.println("Descripcion manager: "+e.getMessage());
			return null;
		}
	}
	public List<Pedido>getWishlist(String usuario){
		List<Pedido> wishlist=new ArrayList<Pedido>();
		String username="root";
		String password="root";
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("EjemploJPA");
		ProductoManager prod=new ProductoManager();
		prod.setEntityManagerFactory(factory);
		String url="jdbc:mysql://localhost/"+"ejemplojpa"+"?user="+username+"&password= "+password+"&useSSL=false&serverTimezone=UTC";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion=DriverManager.getConnection(url);
			Statement myStatement=conexion.createStatement();
			ResultSet rs;
			rs=myStatement.executeQuery("Select * from pedidos where email= '"+usuario+"'and tipo=0");
			while(rs.next()) {
				Pedido pedio=new Pedido();
				pedio.setCantidad(rs.getInt("cantidad"));
				pedio.setNºPedido(rs.getInt("nº_pedido"));
				String producto=rs.getString("id_producto");
				Producto prodi=prod.findproductoById(producto);
				pedio.setProducto(prodi);
				pedio.setTipo(rs.getInt("tipo"));
				String email=rs.getString("email");
				UsuariosManager us=new UsuariosManager();
				us.setEntityManagerFactory(factory);
				Usuario user=us.findusuarioById(email);
				pedio.setUsuario(user);
				wishlist.add(pedio);
			}
			myStatement.close();
			conexion.close();
			return wishlist;
		}catch(Exception e) {
			System.out.println("Descripcion manager: "+e.getMessage());
			return null;
		}
		
	}
	
	public List<Producto>getProductos(List<Pedido>lista){
		List<Producto>productos=new ArrayList<Producto>();
		for(int i=0;i<lista.size();i++) {
			productos.add(lista.get(i).getProducto());
		}
		
		return productos;
		
	}
	public List<Producto> getAllProductos(){
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("EjemploJPA");
		ProductoManager myManager = new ProductoManager();
		myManager.setEntityManagerFactory(factory);
		List<Producto> lista = myManager.findAll();
		return lista;
	}
}
