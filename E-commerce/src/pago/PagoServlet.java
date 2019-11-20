package pago;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import request_Manager.Request_Manager;

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
    
    		String emailPago = req.getParameter("email");
    		String tarjeta = req.getParameter("tarjeta");
    		double Precio = Double.parseDouble(req.getParameter("Precio"));
    		System.out.println("Precio:"+Precio);
    		
    		Request_Manager manager=new Request_Manager();
    		
    		
    		System.out.println("EmailPago:"+emailPago);
    		int i=0;
    		String id="idP"+Integer.toString(i); 
    		String idC="idC"+Integer.toString(i); 
    	
    		
    		while(req.getParameter(id)!=null) {
    			
    		String idProducto = req.getParameter(id);
    		System.out.println("IdProducto:"+idProducto);
    		int idCantidad = Integer.parseInt(req.getParameter(idC));
    		System.out.println("IdCantidad:"+idCantidad);
    		
    		manager.crearPedido(2, emailPago, idCantidad, idProducto);
    		i=i+1;
    		id="idP"+Integer.toString(i);
    		idC="idC"+Integer.toString(i); 
    		/*ENVIAR MENSAJE DE CONFIRMACION DE PAGO*/
    		String confimado="PAGO REALIZADO del producto:"+idProducto;
    		//email de destino es emailPago
    		
    		
    		}
    		
    	   			
    			RequestDispatcher miR=req.getRequestDispatcher("index.jsp");
    			miR.forward(req, resp);

    		
    	}


}
