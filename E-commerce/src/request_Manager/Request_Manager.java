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
import servlet_ecommerce.*;
import jpa_Manager.*;
import java.util.List;

/**
 * @author Álvaro
 *
 */
public class Request_Manager {
	
	
	public Request_Manager() {
		
	}
	
/********************************INSERCION EN BASE DE DATOS POR JPA**********************************************************************************************************/
	
	public void crear_Producto(String idProducto,String vendedor, int precio,int stock, String cat_Inf,String descripcion, String long_descripcion,byte[] imagen) {
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
			
		}
		UsuariosManager user=new UsuariosManager();
		inf.setEntityManagerFactory(factory);
		try {
			Usuario usuario=user.findusuarioById(vendedor);
			newProduct.setUsuario(usuario);
		}catch(Exception e) {
			
		}
		
		
		newProduct.setIdProducto(idProducto);
		
		
		ProductoManager manager = new ProductoManager();
		manager.setEntityManagerFactory(factory);
		try {
			manager.createProducto(newProduct);
		} catch (Exception e) {
			System.out.println("Descripción: " + e.getMessage());
		}

		
	}
	
	public void crearUsuario(String email, String contraseña, int status, String Cpostal, String direccion, String apellido, String apellido2, String nombre) {
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
		}catch(Exception e) {
			System.out.println("Descripcion manager: " + e.getMessage());
		}
	}
	
	public void crearCatInf(String nombre_Cat_inf,String nombre_Cat_Sup) {
		CategoríasInferiore newCat_Inf=new CategoríasInferiore();
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("EjemploJPA");
		Cat_SupManager sup=new Cat_SupManager();
		sup.setEntityManagerFactory(factory);
		try {
			CategoríasSuperiore cat=sup.findcategoríaSuperiorById(nombre_Cat_Sup);
			newCat_Inf.setCategoríasSuperiore(cat);
		
		}catch(Exception e) {
			System.out.println("Descripción: "+ e.getMessage());
		}
		
		Cat_InfManager manager=new Cat_InfManager();
		manager.setEntityManagerFactory(factory);
		try {
			manager.createCategoríasInferiores(newCat_Inf);
		}catch(Exception e) {
			System.out.println("Descripción: " + e.getMessage());
		}
	}
		
	public void crearCatSup(String nombre_Cat_Sup) {
		CategoríasSuperiore newCat_Sup=new CategoríasSuperiore();
		newCat_Sup.setNombre_Cat_Sup(nombre_Cat_Sup);
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("EjemploJPA");
		Cat_SupManager manager=new Cat_SupManager();
		manager.setEntityManagerFactory(factory);
		try {
			manager.createCategoríasSuperiores(newCat_Sup);
			
		
		}catch(Exception e) {
			System.out.println("Descripción: "+ e.getMessage());
		}
	
		
	}
	
	public void crearPedido(int tipo, String usuario, int cantidad, String producto, int numeropedido) {
		Pedido newPedido=new Pedido();
		newPedido.setTipo(tipo);
		newPedido.setCantidad(cantidad);
		newPedido.setNºPedido(numeropedido);
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("EjemploJPA");
		UsuariosManager usu=new UsuariosManager();
		usu.setEntityManagerFactory(factory);
		try {
			Usuario user=usu.findusuarioById(usuario);
			newPedido.setUsuario(user);
		}catch(Exception e) {
			System.out.println("Descripción: "+ e.getMessage());

		}
		ProductoManager prod=new ProductoManager();
		prod.setEntityManagerFactory(factory);
		try {
			Producto product=prod.findproductoById(producto);
			newPedido.setProducto(product);
		}catch(Exception e) {
			System.out.println("Descripción: "+ e.getMessage());

		}
		PedidosManager pedido=new PedidosManager();
		pedido.setEntityManagerFactory(factory);
		try {
			pedido.createPedido(newPedido);
		}catch(Exception e) {
			System.out.println("Descripción: "+ e.getMessage());

		}
	}

/***************************************ELIMINACION EN BASE DE DATOS POR JPA****************************************************/
	public void eliminarPedido(int pedido) {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("EjemploJPA");
		Pedido oldPedido;
		PedidosManager ped=new PedidosManager();
		ped.setEntityManagerFactory(factory);
		try {
			oldPedido=ped.findpedidoById(pedido);
			if(oldPedido!=null) {
				ped.deletepedido(oldPedido);
			}
		}catch(Exception e) {
			System.out.println("Descripcion manager: "+e.getMessage());
		}
	}
	
	public void eliminarUsuario(String email) {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("EjemploJPA");
		Usuario oldUsuario=null;
		UsuariosManager usu=new UsuariosManager();
		usu.setEntityManagerFactory(factory);
		try {
			oldUsuario=usu.findusuarioById(email);
		}catch(Exception e) {
			System.out.println("Descripcion manager:  "+ e.getMessage());
		}
		if(oldUsuario!=null) {
			List<Pedido> listaPedidos=oldUsuario.getPedidos();
			List<Producto>listaProductos=oldUsuario.getProductos();
			List<Mensaje>listaMensajes1=oldUsuario.getMensajes1();
			try {
				if(listaPedidos!=null) {
					for(int i=0;i<listaPedidos.size();i++) {
						eliminarPedido(listaPedidos.get(i).getNºPedido());
					}
				}
				if(listaProductos!=null) {
					for(int i=0;i<listaProductos.size();i++) {
						eliminarProducto(listaProductos.get(i).getIdProducto());
					}
				}
				if(listaMensajes1!=null) {
					for(int i=0;i<listaMensajes1.size();i++) {
						eliminarMensaje(listaMensajes1.get(i).getIdMensaje());
					}
				}	
			}catch(Exception e) {
				System.out.println("Descripcion manager: "+e.getMessage());
			}
		}
		
	}
	
	public void eliminarProducto(String idProducto) {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("EjemploJPA");
		Producto oldProducto=null;
		ProductoManager prod=new ProductoManager();
		prod.setEntityManagerFactory(factory);
		try {
			oldProducto=prod.findproductoById(idProducto);
		}catch(Exception e) {
			System.out.println("Descripcion manager:  "+ e.getMessage());
		}
		if(oldProducto!=null) {
			List<Pedido> listaPedidos=oldProducto.getPedidos();
			try {
				if(listaPedidos!=null) {
					for(int i=0;i<listaPedidos.size();i++) {
						eliminarPedido(listaPedidos.get(i).getNºPedido());
					}
				}
			}catch(Exception e) {
				System.out.println("Descripcion manager: "+e.getMessage());
			}
		}
		
	}
	
	public void eliminarCat_Inf(String nombre_Cat_Inf) {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("EjemploJPA");
		CategoríasInferiore oldCatInf=null;
		Cat_InfManager catInf=new Cat_InfManager();
		catInf.setEntityManagerFactory(factory);
		try {
			oldCatInf=catInf.findcategoríaInferiorById(nombre_Cat_Inf);
		}catch(Exception e) {
			System.out.println("Descripcion manager:  "+ e.getMessage());
		}
		if(oldCatInf!=null) {
			List<Producto> listaProductos=oldCatInf.getProductos();
			try {
				if(listaProductos!=null) {
					for(int i=0;i<listaProductos.size();i++) {
						eliminarProducto(listaProductos.get(i).getIdProducto());
					}
				}
			}catch(Exception e) {
				System.out.println("Descripcion manager: "+e.getMessage());
			}
		}
		
	}
	
	public void eliminarCat_Sup(String nombre_CatSup) {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("EjemploJPA");
		CategoríasSuperiore oldCatSup=null;
		Cat_SupManager catSup=new Cat_SupManager();
		catSup.setEntityManagerFactory(factory);
		try {
			oldCatSup=catSup.findcategoríaSuperiorById(nombre_CatSup);
		}catch(Exception e) {
			System.out.println("Descripcion manager:  "+ e.getMessage());
		}
		if(oldCatSup!=null) {
			List<CategoríasInferiore> listaCategoriasInferiores=oldCatSup.getCategoríasInferiores();
			try {
				if(listaCategoriasInferiores!=null) {
					for(int i=0;i<listaCategoriasInferiores.size();i++) {
						eliminarCat_Inf(listaCategoriasInferiores.get(i).getNombre_Cat_Inf());
					}
				}
			}catch(Exception e) {
				System.out.println("Descripcion manager: "+e.getMessage());
			}
		}
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
	public Usuario modificarUsuario() {
		Usuario usermod=new Usuario();
		return usermod;
	}
	
	public Pedido modificarPedido() {
		Pedido pedidomod=new Pedido();
		return pedidomod;
	}
	
	public Producto modificarProducto() {
		Producto productomod=new Producto();
		return productomod;
	}
	
/**************************************CONSULTA A BASE DE DATOS POR JPA***************************************************************/
	public List <Producto> getProductos() { //DEVUELVE 10 PRODUCTOS
		
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("EjemploJPA");
		EntityManager em=factory.createEntityManager();
		try {
			TypedQuery <Producto> q2 =em.createQuery("SELECT c FROM Productos c where ROWNUM <=10", Producto.class);
			return q2.getResultList();
		}catch(Exception e) {
			
		}
		return null;
	
	}

}
