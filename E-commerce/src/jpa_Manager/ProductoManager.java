package jpa_Manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


import servlet_ecommerce.Producto;

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

