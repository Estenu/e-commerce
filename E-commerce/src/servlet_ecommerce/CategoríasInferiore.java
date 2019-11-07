package servlet_ecommerce;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the categorías_inferiores database table.
 * 
 */
@Entity
@Table(name="categorías_inferiores")
@NamedQuery(name="CategoríasInferiore.findAll", query="SELECT c FROM CategoríasInferiore c")
public class CategoríasInferiore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String nombre_Cat_Inf;

	//bi-directional many-to-one association to CategoríasSuperiore
	@ManyToOne
	@JoinColumn(name="nombre_Cat_Sup")
	private CategoríasSuperiore categoríasSuperiore;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="categoríasInferiore")
	private List<Producto> productos;

	public CategoríasInferiore() {
	}

	public String getNombre_Cat_Inf() {
		return this.nombre_Cat_Inf;
	}

	public void setNombre_Cat_Inf(String nombre_Cat_Inf) {
		this.nombre_Cat_Inf = nombre_Cat_Inf;
	}

	public CategoríasSuperiore getCategoríasSuperiore() {
		return this.categoríasSuperiore;
	}

	public void setCategoríasSuperiore(CategoríasSuperiore categoríasSuperiore) {
		this.categoríasSuperiore = categoríasSuperiore;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setCategoríasInferiore(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setCategoríasInferiore(null);

		return producto;
	}

}