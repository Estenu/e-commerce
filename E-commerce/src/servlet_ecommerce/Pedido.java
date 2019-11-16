package servlet_ecommerce;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pedidos database table.
 * 
 */
@Entity
@Table(name="pedidos")
@NamedQueries({
	@NamedQuery(name="Pedido.findAll", query="SELECT p FROM Pedido p"),
	@NamedQuery(name="Pedido.whislit",query="Select p from Pedido p where p.tipo=:tipo and p.usuario=:email")
})

public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n�_pedido")
	private int n튡edido;

	private int cantidad;

	private int tipo;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto producto;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="email")
	private Usuario usuario;

	public Pedido() {
	}

	public int getN튡edido() {
		return this.n튡edido;
	}

	public void setN튡edido(int n튡edido) {
		this.n튡edido = n튡edido;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getTipo() {
		return this.tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}