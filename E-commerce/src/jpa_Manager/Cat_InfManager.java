package jpa_Manager;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import servlet_ecommerce.CategoríasInferiore;
import servlet_ecommerce.Producto;
public class Cat_InfManager {
	private EntityManagerFactory emf;

	public Cat_InfManager() {

	}

	public Cat_InfManager(EntityManagerFactory emf) {
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

	public String createCategoríasInferiores(CategoríasInferiore categoríaInferior) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(categoríaInferior);
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

	public String deletecategoríaInferior(CategoríasInferiore categoríaInferior) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			categoríaInferior = em.merge(categoríaInferior);
			em.remove(categoríaInferior);
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

	public String updatecategoríaInferior(CategoríasInferiore categoríaInferior) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			categoríaInferior = em.merge(categoríaInferior);
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
	public List<CategoríasInferiore> findAll() {// recupera todas las categorías inferiores
		List<CategoríasInferiore> resultado;
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createNamedQuery("CategoríasInferiore.findAll",CategoríasInferiore.class);
			resultado = query.getResultList();
		} finally {
			em.close();
		}
		return resultado;

	}

	public CategoríasInferiore findcategoríaInferiorById(String id) {
		CategoríasInferiore categoríaInferior = null;
		EntityManager em = getEntityManager();
		try {
			categoríaInferior = (CategoríasInferiore) em.find(CategoríasInferiore.class, id);
		} finally {
			em.close();
		}
		return categoríaInferior;
	}

	public CategoríasInferiore getNewcategoríaInferior() {

		CategoríasInferiore categoríaInferior = new CategoríasInferiore();

		return categoríaInferior;
	}
}
