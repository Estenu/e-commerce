package jms_manager;

import java.io.IOException;
import java.util.ArrayList;

import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import servlet_ecommerce.Usuario;


/**
 * Servlet implementation class jms_servlet
 */

public class jms_servlet extends HttpServlet {
	/**
	 *
	 */
	private static final long serialVersionUID = 2920899322727130776L;
	
    public jms_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	* @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Usuario user = (Usuario) session.getAttribute("user");
		
		String mode=request.getParameter("mode");
		InteraccionJMS mq=new InteraccionJMS();
		
		
		int status = user.getEstatus(); //0-comprador , 1-vendedor
		int intMetodo; // 0- escribir en cola para Vendedores, 1- escribir en cola para compradores
		
		/*
		intOperacion = 1  //OPERACION_ESCRIBIR_MENSAJE_COLA

		intOperacion = 2  //OPERACION_LECTURA_NORMAL

		intOperacion = 3  //OPERACION_LECTURA_BROWSER
		*/
		

		
		if("sendAll".equalsIgnoreCase(mode)) {
			
			
			String selector =request.getParameter("corrId");
			System.out.println("En El sendAll corrId:"+selector);
			String email =user.getEmail();
			int intOperacion=1;
			
			switch(status) {
			case 0:
				intMetodo=0;  //comprador envia a vendedor/vendedores
				break;
				
			case 1:
			default:
				intMetodo=1; //vendedor envia a comprador / compradores
				
				break;
			}
				
			if(selector==null||selector=="") { //Envia notificación
				mq.escrituraJMS(email,request.getParameter("mensaje"),intMetodo,intOperacion);
			}else { //Envía por correlationID
				mq.escrituraJMS(email,request.getParameter("mensaje"),intMetodo,intOperacion,selector); 
			}
			
			RequestDispatcher miR=request.getRequestDispatcher("index.jsp");
			miR.forward(request, response);
		
			
		
		}else if("clearInbox".equalsIgnoreCase(mode)) {  //Borra los mensajes de la bandeja de entrada consumiendolos
			String strAux="";
			int intOperacion=2;
			
			switch(status) {
			case 0:
				intMetodo=1;  //limpia bandeja de los vendedores
				break;
				
			case 1:
			default:
				intMetodo=0; //limpia bandeja de los compradores
				
				break;
			}
			
			strAux=mq.lecturaJMS(intMetodo,intOperacion);
			request.setAttribute("mensajes",null);
			
			RequestDispatcher miR=request.getRequestDispatcher("mensajes-read.jsp");
			miR.forward(request, response);
			
			
			
		}else if("read".equalsIgnoreCase(mode)) { //leemos por browser
			
			ArrayList<TextMessage> mensajes = null;
			ArrayList<TextMessage> mensajesCorr = null;
			String selector = user.getEmail();
			
			String strAux;
			
			
			switch(status) {
			case 0:
				intMetodo=1;  //comprador lee de cola para compradores
				break;
				
			case 1:
			default:
				intMetodo=0; //vendedor lee de cola para vendedores
				
				break;
			}
			
			
			
			mensajesCorr=mq.lecturaJMS(intMetodo,2,selector);  //leemos y consumimos los mensajes personales (corrId)
			mensajes=mq.lecturaBrowser(intMetodo,3); //leemos las notificaciones
			request.setAttribute("mensajes",mensajes);
			request.setAttribute("personal-mensajes",mensajesCorr);
			RequestDispatcher miR=request.getRequestDispatcher("mensajes-read.jsp");
			miR.forward(request, response);
			
			
	
		}else if("toSend".equalsIgnoreCase(mode)) { //Redirecciona a pagina de escribir mensaje
				System.out.println("En El toSend corrId:"+request.getParameter("corrId"));
				RequestDispatcher rd=request.getRequestDispatcher("messages-index.jsp");
				rd.forward(request, response);
		}
	
		
			
			
			
	}

	/**
	* @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		doGet(request, response);

	}
	
	



}
