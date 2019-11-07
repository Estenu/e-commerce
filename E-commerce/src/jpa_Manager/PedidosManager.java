package jpa_Manager;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


import servlet_ecommerce.Pedido;
public class PedidosManager {
	private EntityManagerFactory emf;

	public PedidosManager() {

	}

	public PedidosManager(EntityManagerFactory emf) {
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

	public String createPedido(Pedido pedido) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(pedido);
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

	public String deletepedido(Pedido pedido) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			pedido = em.merge(pedido);
			em.remove(pedido);
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

	public String updatepedido(Pedido pedido) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			pedido = em.merge(pedido);
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

	public Pedido findpedidoById(String id) {
		Pedido pedido = null;
		EntityManager em = getEntityManager();
		try {
			pedido = (Pedido) em.find(Pedido.class, id);
		} finally {
			em.close();
		}
		return pedido;
	}

	public Pedido getNewpedido() {

		Pedido pedido = new Pedido();

		return pedido;
	}

}



