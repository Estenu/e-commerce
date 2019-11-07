package servlet_ecommerce;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the categorías_superiores database table.
 * 
 */
@Entity
@Table(name="categorías_superiores")
@NamedQuery(name="CategoríasSuperiore.findAll", query="SELECT c FROM CategoríasSuperiore c")
public class CategoríasSuperiore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String nombre_Cat_Sup;

	//bi-directional many-to-one association to CategoríasInferiore
	@OneToMany(mappedBy="categoríasSuperiore")
	private List<CategoríasInferiore> categoríasInferiores;

	public CategoríasSuperiore() {
	}

	public String getNombre_Cat_Sup() {
		return this.nombre_Cat_Sup;
	}

	public void setNombre_Cat_Sup(String nombre_Cat_Sup) {
		this.nombre_Cat_Sup = nombre_Cat_Sup;
	}

	public List<CategoríasInferiore> getCategoríasInferiores() {
		return this.categoríasInferiores;
	}

	public void setCategoríasInferiores(List<CategoríasInferiore> categoríasInferiores) {
		this.categoríasInferiores = categoríasInferiores;
	}

	public CategoríasInferiore addCategoríasInferiore(CategoríasInferiore categoríasInferiore) {
		getCategoríasInferiores().add(categoríasInferiore);
		categoríasInferiore.setCategoríasSuperiore(this);

		return categoríasInferiore;
	}

	public CategoríasInferiore removeCategoríasInferiore(CategoríasInferiore categoríasInferiore) {
		getCategoríasInferiores().remove(categoríasInferiore);
		categoríasInferiore.setCategoríasSuperiore(null);

		return categoríasInferiore;
	}

}