package servlet_ecommerce;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the categor�as_superiores database table.
 * 
 */
@Entity
@Table(name="categor�as_superiores")
@NamedQuery(name="Categor�asSuperiore.findAll", query="SELECT c FROM Categor�asSuperiore c")
public class Categor�asSuperiore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String nombre_Cat_Sup;

	//bi-directional many-to-one association to Categor�asInferiore
	@OneToMany(mappedBy="categor�asSuperiore")
	private List<Categor�asInferiore> categor�asInferiores;

	public Categor�asSuperiore() {
	}

	public String getNombre_Cat_Sup() {
		return this.nombre_Cat_Sup;
	}

	public void setNombre_Cat_Sup(String nombre_Cat_Sup) {
		this.nombre_Cat_Sup = nombre_Cat_Sup;
	}

	public List<Categor�asInferiore> getCategor�asInferiores() {
		return this.categor�asInferiores;
	}

	public void setCategor�asInferiores(List<Categor�asInferiore> categor�asInferiores) {
		this.categor�asInferiores = categor�asInferiores;
	}

	public Categor�asInferiore addCategor�asInferiore(Categor�asInferiore categor�asInferiore) {
		getCategor�asInferiores().add(categor�asInferiore);
		categor�asInferiore.setCategor�asSuperiore(this);

		return categor�asInferiore;
	}

	public Categor�asInferiore removeCategor�asInferiore(Categor�asInferiore categor�asInferiore) {
		getCategor�asInferiores().remove(categor�asInferiore);
		categor�asInferiore.setCategor�asSuperiore(null);

		return categor�asInferiore;
	}

}