package jpa_Manager;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


import servlet_ecommerce.Usuario;
public class UsuariosManager {
	private EntityManagerFactory emf;

	public UsuariosManager() {

	}

	public UsuariosManager(EntityManagerFactory emf) {
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

	public String createUsuario(Usuario usuario) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(usuario);
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

	public void deleteusuario(Usuario usuario) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			usuario = em.merge(usuario);
			em.remove(usuario);
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
	
	}

	public Usuario updateusuario(Usuario usuario) throws Exception {
		EntityManager em = getEntityManager();
		Usuario updatedUser;
		try {
			em.getTransaction().begin();
			updatedUser = em.merge(usuario);
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
		return updatedUser;
	}

	public Usuario findusuarioById(String id) {
		Usuario usuario = null;
		EntityManager em = getEntityManager();
		try {
			usuario = (Usuario) em.find(Usuario.class, id);
		} finally {
			em.close();
		}
		return usuario;
	}

	public Usuario getNewusuario() {

		Usuario usuario = new Usuario();

		return usuario;
	}

}



