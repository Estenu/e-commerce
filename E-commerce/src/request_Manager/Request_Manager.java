/**
 * 
 */
package request_Manager;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import servlet_ecommerce.*;
import jpa_Manager.*;
/**
 * @author �lvaro
 *
 */
public class Request_Manager {
	
	
	public Request_Manager() {
		
	}
	
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
	
}
