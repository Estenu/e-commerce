package jpa_Manager;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import servlet_ecommerce.Categor�asInferiore;
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

	public String createCategor�asInferiores(Categor�asInferiore categor�aInferior) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(categor�aInferior);
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

	public String deletecategor�aInferior(Categor�asInferiore categor�aInferior) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			categor�aInferior = em.merge(categor�aInferior);
			em.remove(categor�aInferior);
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

	public String updatecategor�aInferior(Categor�asInferiore categor�aInferior) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			categor�aInferior = em.merge(categor�aInferior);
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
	public List<Categor�asInferiore> findAll() {// recupera todas las categor�as inferiores
		List<Categor�asInferiore> resultado;
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createNamedQuery("Categor�asInferiore.findAll",Categor�asInferiore.class);
			resultado = query.getResultList();
		} finally {
			em.close();
		}
		return resultado;

	}

	public Categor�asInferiore findcategor�aInferiorById(String id) {
		Categor�asInferiore categor�aInferior = null;
		EntityManager em = getEntityManager();
		try {
			categor�aInferior = (Categor�asInferiore) em.find(Categor�asInferiore.class, id);
		} finally {
			em.close();
		}
		return categor�aInferior;
	}

	public Categor�asInferiore getNewcategor�aInferior() {

		Categor�asInferiore categor�aInferior = new Categor�asInferiore();

		return categor�aInferior;
	}
}
