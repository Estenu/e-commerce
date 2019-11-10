package jpa_Manager;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


import servlet_ecommerce.Mensaje;
public class MensajesManager {
	private EntityManagerFactory emf;

	public MensajesManager() {

	}

	public MensajesManager(EntityManagerFactory emf) {
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

	public String createMensaje(Mensaje mensaje) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(mensaje);
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

	public String deletemensaje(Mensaje mensaje) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			mensaje = em.merge(mensaje);
			em.remove(mensaje);
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

	public String updatemensaje(Mensaje mensaje) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			mensaje = em.merge(mensaje);
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

	public Mensaje findmensajeById(int id) {
		Mensaje mensaje = null;
		EntityManager em = getEntityManager();
		try {
			mensaje = (Mensaje) em.find(Mensaje.class, id);
		} finally {
			em.close();
		}
		return mensaje;
	}

	public Mensaje getNewmensaje() {

		Mensaje mensaje = new Mensaje();

		return mensaje;
	}

}



