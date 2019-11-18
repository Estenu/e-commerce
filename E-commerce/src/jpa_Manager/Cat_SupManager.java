package jpa_Manager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import servlet_ecommerce.CategoríasInferiore;
import servlet_ecommerce.CategoríasSuperiore;
public class Cat_SupManager {
	private EntityManagerFactory emf;
	
	public Cat_SupManager() {

	}

	public Cat_SupManager(EntityManagerFactory emf) {
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

	public String createCategoríasSuperiores(CategoríasSuperiore categoríaSuperior) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(categoríaSuperior);
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

	public String deletecategoríaSuperior(CategoríasSuperiore categoríaSuperior) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			categoríaSuperior = em.merge(categoríaSuperior);
			em.remove(categoríaSuperior);
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

	public String updatecategoríaSuperior(CategoríasSuperiore categoríaSuperior) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			categoríaSuperior = em.merge(categoríaSuperior);
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
	public List<CategoríasSuperiore> findAll() {// recupera todas las categorías superiores
		List<CategoríasSuperiore> resultado;
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createNamedQuery("CategoríasSuperiore.findAll",CategoríasSuperiore.class);
			resultado = query.getResultList();
		} finally {
			em.close();
		}
		return resultado;

	}
	
	public CategoríasSuperiore findcategoríaSuperiorById(String id) {
		CategoríasSuperiore categoríaSuperior = null;
		EntityManager em = getEntityManager();
		try {
			categoríaSuperior = (CategoríasSuperiore) em.find(CategoríasSuperiore.class, id);
		} finally {
			em.close();
		}
		return categoríaSuperior;
	}

	public CategoríasSuperiore getNewcategoríaSuperior() {

		CategoríasSuperiore categoríaSuperior = new CategoríasSuperiore();

		return categoríaSuperior;
	}
}
