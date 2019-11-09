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
 * @author �lvaro
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
			Categor�asInferiore cat=inf.findcategor�aInferiorById(cat_Inf);
			newProduct.setCategor�asInferiore(cat);
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
			System.out.println("Descripci�n: " + e.getMessage());
		}

		
	}
	
	public void crearUsuario(String email, String contrase�a, int status, String Cpostal, String direccion, String apellido, String apellido2, String nombre) {
		Usuario newUser=new Usuario();
		newUser.setEmail(email);
		newUser.setApellido(apellido);
		newUser.setApellido2(apellido2);
		newUser.setContrasena(contrase�a);
		newUser.setCpostal(Cpostal);
		newUser.setEstatus(status);
		newUser.setNombre(nombre);
		newUser.setDirecci�n(direccion);
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
		Categor�asInferiore newCat_Inf=new Categor�asInferiore();
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("EjemploJPA");
		Cat_SupManager sup=new Cat_SupManager();
		sup.setEntityManagerFactory(factory);
		try {
			Categor�asSuperiore cat=sup.findcategor�aSuperiorById(nombre_Cat_Sup);
			newCat_Inf.setCategor�asSuperiore(cat);
		
		}catch(Exception e) {
			System.out.println("Descripci�n: "+ e.getMessage());
		}
		
		Cat_InfManager manager=new Cat_InfManager();
		manager.setEntityManagerFactory(factory);
		try {
			manager.createCategor�asInferiores(newCat_Inf);
		}catch(Exception e) {
			System.out.println("Descripci�n: " + e.getMessage());
		}
	}
		
	public void crearCatSup(String nombre_Cat_Sup) {
		Categor�asSuperiore newCat_Sup=new Categor�asSuperiore();
		newCat_Sup.setNombre_Cat_Sup(nombre_Cat_Sup);
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("EjemploJPA");
		Cat_SupManager manager=new Cat_SupManager();
		manager.setEntityManagerFactory(factory);
		try {
			manager.createCategor�asSuperiores(newCat_Sup);
			
		
		}catch(Exception e) {
			System.out.println("Descripci�n: "+ e.getMessage());
		}
	
		
	}
	
	public void crearPedido(int tipo, String usuario, int cantidad, String producto, int numeropedido) {
		Pedido newPedido=new Pedido();
		newPedido.setTipo(tipo);
		newPedido.setCantidad(cantidad);
		newPedido.setN�Pedido(numeropedido);
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("EjemploJPA");
		UsuariosManager usu=new UsuariosManager();
		usu.setEntityManagerFactory(factory);
		try {
			Usuario user=usu.findusuarioById(usuario);
			newPedido.setUsuario(user);
		}catch(Exception e) {
			System.out.println("Descripci�n: "+ e.getMessage());

		}
		ProductoManager prod=new ProductoManager();
		prod.setEntityManagerFactory(factory);
		try {
			Producto product=prod.findproductoById(producto);
			newPedido.setProducto(product);
		}catch(Exception e) {
			System.out.println("Descripci�n: "+ e.getMessage());

		}
		PedidosManager pedido=new PedidosManager();
		pedido.setEntityManagerFactory(factory);
		try {
			pedido.createPedido(newPedido);
		}catch(Exception e) {
			System.out.println("Descripci�n: "+ e.getMessage());

		}
	}

/***************************************ELIMINACION EN BASE DE DATOS POR JPA****************************************************/
	public void eliminarPedido(int pedido) {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("EjemploJPA");

	}
	
	public void eliminarUsuario() {
		
	}
	
	public void eliminarProducto() {
		
	}
	
	public void eliminarCat_Inf() {
		
	}
	
	public void eliminarCat_Sup() {
		
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
