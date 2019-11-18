package jpa_Manager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import servlet_ecommerce.CategoríasInferiore;
import servlet_ecommerce.Producto;
import servlet_ecommerce.Usuario;

//@SuppressWarnings("unchecked")


public class ProductoManager {

	private EntityManagerFactory emf;

	public ProductoManager() {

	}

	public ProductoManager(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public void setEntityManagerFactory(EntityManagerFactory emf) {
		this.emf = emf;
	}

	private EntityManager getEntityManager() {
		if (emf == null) {
			throw new RuntimeException(
					"The EntityManagerFactory is null.  This must be passed in to the constructor or set using the setEntityManagerFactory() method.");
		}
		return emf.createEntityManager();
	}

	public String createProducto(Producto producto) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(producto);
			em.getTransaction().commit();
		} catch (Exception ex) {
			try {
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			} catch (Exception e) {
				ex.printStackTrace();
				throw e;
			}
			throw ex;
		} finally {
			em.close();
		}
		return "";
	}

	public String deleteproducto(Producto producto) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			producto = em.merge(producto);
			em.remove(producto);
			em.getTransaction().commit();
		} catch (Exception ex) {
			try {
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			} catch (Exception e) {
				ex.printStackTrace();
				throw e;
			}
			throw ex;
		} finally {
			em.close();
		}
		return "";
	}

	public String updateproducto(Producto producto) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			producto = em.merge(producto);	
			em.getTransaction().commit();
		} catch (Exception ex) {
			try {
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			} catch (Exception e) {
				ex.printStackTrace();
				throw e;
			}
			throw ex;
		} finally {
			em.close();
		}
		return "";
	}


	
	@SuppressWarnings("unchecked")
	public List<Producto> findAllUser(Usuario usuario) {// recupera todos los productos de un vendedor
		List<Producto> resultado;
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createNamedQuery("Producto.findAllUser",Producto.class);
			query.setParameter("usuario", usuario);
			resultado = query.getResultList();
		} finally {
			em.close();
		}
		return resultado;

	}
	@SuppressWarnings("unchecked")
	public List<Producto> findAll() {// recupera todos los productos
		List<Producto> resultado;
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createNamedQuery("Producto.findAll",Producto.class);
			resultado = query.getResultList();
		} finally {
			em.close();
		}
		return resultado;

	}
	
	@SuppressWarnings("unchecked")
	public List<Producto> findBySimilarName(String name) {// recupera todos los productos con un nombre similar al pasado por parametro
		List<Producto> resultado;
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createNamedQuery("Producto.findBySimilarName",Producto.class);
			query.setParameter("name","%"+name+"%");
			resultado = query.getResultList();
		} finally {
			em.close();
		}
		return resultado;

	}
	
	@SuppressWarnings("unchecked")
	public List<Producto> findByCatInf(CategoríasInferiore name) {// recupera todos los productos con la misma categoría
		List<Producto> resultado;
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createNamedQuery("Producto.findByCatInf",Producto.class);
			query.setParameter("catInf", name);
			resultado = query.getResultList();
		} finally {
			em.close();
		}
		return resultado;

	}
	
	@SuppressWarnings("unchecked")
	public List<Producto> findByNameAndCat(String name, CategoríasInferiore catName) {// recupera todos los productos de una misma categoria con un nombre similar al pasado por parametro
		List<Producto> resultado;
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createNamedQuery("Producto.findByNameAndCat",Producto.class);
			query.setParameter("name","%"+name+"%");
			query.setParameter("catInf", catName);
			resultado = query.getResultList();
		} finally {
			em.close();
		}
		return resultado;

	}
	
	public Producto findproductoById(String id) {
		Producto producto = null;
		EntityManager em = getEntityManager();
		try {
			producto = (Producto) em.find(Producto.class, id);
		} finally {
			em.close();
		}
		return producto;
	}
	
	
	
	public Producto getNewproducto() {

		Producto producto = new Producto();

		return producto;
	}

}

