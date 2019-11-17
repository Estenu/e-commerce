package servlet_ecommerce;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the productos database table.
 * 
 */
@Entity
@Table(name="productos")
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
@NamedQuery(name="Producto.findAllUser", query="SELECT p FROM Producto p where p.usuario=:usuario")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_producto")
	private String idProducto;

	private String description;

	@Lob
	private byte[] imagen;

	private String longDesc;

	private int precio;

	private int stock;

	//bi-directional many-to-one association to Pedido
	@OneToMany(mappedBy="producto")
	private List<Pedido> pedidos;

	//bi-directional many-to-one association to CategoríasInferiore
	@ManyToOne
	@JoinColumn(name="nombre_Cat_Inf")
	private CategoríasInferiore categoríasInferiore;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="email")
	private Usuario usuario;

	public Producto() {
	}

	public String getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getImagen() {
		return this.imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public String getLongDesc() {
		return this.longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	public int getPrecio() {
		return this.precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public List<Pedido> getPedidos() {
		return this.pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Pedido addPedido(Pedido pedido) {
		getPedidos().add(pedido);
		pedido.setProducto(this);

		return pedido;
	}

	public Pedido removePedido(Pedido pedido) {
		getPedidos().remove(pedido);
		pedido.setProducto(null);

		return pedido;
	}

	public CategoríasInferiore getCategoríasInferiore() {
		return this.categoríasInferiore;
	}

	public void setCategoríasInferiore(CategoríasInferiore categoríasInferiore) {
		this.categoríasInferiore = categoríasInferiore;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}