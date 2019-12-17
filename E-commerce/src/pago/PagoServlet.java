package pago;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jms_manager.InteraccionJMS;
import java.util.List;
import java.util.ArrayList;
import request_Manager.Request_Manager;
import servlet_ecommerce.Pedido;
import servlet_ecommerce.Producto;
import servlet_ecommerce.Usuario;
import tidw.domains.Mensaje;
import es.uc3m.tiw.banco.domains.Result2;
/**
 * Servlet implementation class pago
 */
@WebServlet("/PagoServlet")
public class PagoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PagoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    		doPost(req, resp);
    	}

    	/**
    	* @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
    	*/
    	public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    		
    		HttpSession session = req.getSession();
    		Usuario user = (Usuario) session.getAttribute("user");
    		String strAux =  user.getEmail();
    		
    		String emailPago = req.getParameter("email");
    		System.out.println(emailPago);
    		String tarjeta = req.getParameter("tarjeta");
    		System.out.println(tarjeta);
    		String cv2 = req.getParameter("CV2");
    		System.out.println(cv2);
    		String fecha = req.getParameter("fecha");
    		System.out.println(fecha);
    		double Precio = Double.parseDouble(req.getParameter("Precio"));
    		System.out.println("Precio:"+Precio);
    		
    		Client client = ClientBuilder.newClient();
			WebTarget webResource = client.target("http://localhost:12610").path("/banco").queryParam("tarjeta",tarjeta).queryParam("cv",cv2).queryParam("fecha",fecha);
			Invocation.Builder invocationBuilder= webResource.request(MediaType.APPLICATION_JSON);
			Response result=invocationBuilder.get();
			System.out.println("RESULT:"+result.getStatus());
    		if(result.getStatus()==200) {
    			
    			//PARA ESCRIBIR MENSAJES
				Mensaje mensaje = new Mensaje();
				mensaje.setEmaildest("rabobank@g.com");
				mensaje.setEmailori(emailPago);
				mensaje.setMensaje("Ha recibido "+Precio+"$ del comprador: "+emailPago+
						" (Numero de cuenta bancaria: "+tarjeta+")");
				Client client2 = ClientBuilder.newClient();
				WebTarget webResource2 = client2.target("http://localhost:12611").path("mensaje");
				Response result2=	webResource2.request("application/json").accept("application/json").post(Entity.entity(mensaje,MediaType.APPLICATION_JSON),Response.class);	
				 System.out.println("Resultado de crear el primero"+result2.getStatus());
			if(result2.getStatus()==201) {
				//PARA ESCRIBIR MENSAJES
				Mensaje mensaje2 = new Mensaje();
				mensaje2.setEmaildest(strAux);
				mensaje2.setEmailori("rabobank@g.com");
				mensaje2.setMensaje("Pago realizado");
				Client client3 = ClientBuilder.newClient();
				WebTarget webResource3 = client3.target("http://localhost:12611").path("mensaje");
				Response result3=	webResource3.request("application/json").accept("application/json").post(Entity.entity(mensaje2,MediaType.APPLICATION_JSON),Response.class);	
				
			if(result3.getStatus()==201) {
				RequestDispatcher miR=req.getRequestDispatcher("index.jsp");
				miR.forward(req, resp);
			}else {
				RequestDispatcher miR=req.getRequestDispatcher("index.jsp");
				miR.forward(req, resp);
			}
			}else {
				RequestDispatcher miR=req.getRequestDispatcher("index.jsp");
				miR.forward(req, resp);
			}
    			
    			System.out.println("codigo de identificación de la transacción de compra"+result.readEntity(Result2.class).getResult());
    			System.out.println("SI 200");
    		}else {
    			System.out.println("NO 200");
    		}
    	/*	Request_Manager manager=new Request_Manager();

			//	mq.escrituraJMS("Banco",confirmado,intMetodo,intOperacion,selector);
				

				
    		
    		}
    	/*	
    		List<Pedido> carrito=manager.getCarrito(usuario);
    		int size=carrito.size();
    		for(int w=0;w<size;w++) {
    			manager.eliminarPedido(carrito.get(w).getNºPedido());
    		}
    		carrito=manager.getCarrito(usuario);
    		HttpSession session = req.getSession();
    		session.setAttribute("carrito", carrito);
    		List<Producto>productoscarrito=manager.getProductos(carrito);
    		session.setAttribute("productoscarrito", productoscarrito);
			String msg = "Ha recibido "+Precio+"$ del comprador: "+emailPago+
					" (Numero de cuenta bancaria: "+tarjeta+")";
			String selector = "rabobank@g.com";
			int intMetodo=0;
			int intOperacion=1;
			//mq.escrituraJMS("E-commerce.com",msg,intMetodo,intOperacion,selector);
    		
    		
    	   	*/		
    			RequestDispatcher miR=req.getRequestDispatcher("index.jsp");
    			miR.forward(req, resp);

    		
    	}


}
