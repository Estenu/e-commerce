package servlet_ecommerce;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the categor�as_inferiores database table.
 * 
 */
@Entity
@Table(name="categor�as_inferiores")
@NamedQuery(name="Categor�asInferiore.findAll", query="SELECT c FROM Categor�asInferiore c")
public class Categor�asInferiore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String nombre_Cat_Inf;

	//bi-directional many-to-one association to Categor�asSuperiore
	@ManyToOne
	@JoinColumn(name="nombre_Cat_Sup")
	private Categor�asSuperiore categor�asSuperiore;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="categor�asInferiore")
	private List<Producto> productos;

	public Categor�asInferiore() {
	}

	public String getNombre_Cat_Inf() {
		return this.nombre_Cat_Inf;
	}

	public void setNombre_Cat_Inf(String nombre_Cat_Inf) {
		this.nombre_Cat_Inf = nombre_Cat_Inf;
	}

	public Categor�asSuperiore getCategor�asSuperiore() {
		return this.categor�asSuperiore;
	}

	public void setCategor�asSuperiore(Categor�asSuperiore categor�asSuperiore) {
		this.categor�asSuperiore = categor�asSuperiore;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setCategor�asInferiore(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setCategor�asInferiore(null);

		return producto;
	}

}