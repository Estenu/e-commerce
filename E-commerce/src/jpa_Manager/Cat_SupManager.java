package jpa_Manager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import servlet_ecommerce.Categor�asInferiore;
import servlet_ecommerce.Categor�asSuperiore;
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

	public String createCategor�asSuperiores(Categor�asSuperiore categor�aSuperior) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(categor�aSuperior);
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

	public String deletecategor�aSuperior(Categor�asSuperiore categor�aSuperior) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			categor�aSuperior = em.merge(categor�aSuperior);
			em.remove(categor�aSuperior);
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

	public String updatecategor�aSuperior(Categor�asSuperiore categor�aSuperior) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			categor�aSuperior = em.merge(categor�aSuperior);
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
	public List<Categor�asSuperiore> findAll() {// recupera todas las categor�as superiores
		List<Categor�asSuperiore> resultado;
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createNamedQuery("Categor�asSuperiore.findAll",Categor�asSuperiore.class);
			resultado = query.getResultList();
		} finally {
			em.close();
		}
		return resultado;

	}
	
	public Categor�asSuperiore findcategor�aSuperiorById(String id) {
		Categor�asSuperiore categor�aSuperior = null;
		EntityManager em = getEntityManager();
		try {
			categor�aSuperior = (Categor�asSuperiore) em.find(Categor�asSuperiore.class, id);
		} finally {
			em.close();
		}
		return categor�aSuperior;
	}

	public Categor�asSuperiore getNewcategor�aSuperior() {

		Categor�asSuperiore categor�aSuperior = new Categor�asSuperiore();

		return categor�aSuperior;
	}
}
