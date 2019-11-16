package jms_manager;

import java.io.IOException;

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
		int intMetodo;
		
		/*if(request.getParameter("metodo")==null) {
			intMetodo = 1;
		}else {
			intMetodo = Integer.parseInt(request.getParameter("metodo"));
		}
		*/
		
		if("sendAll".equalsIgnoreCase(mode)) {
			
			int intOperacion=1;
			
			switch(status) {
			case 0:
				intMetodo=0;  //comprador envia a todos los vendedores
				break;
				
			case 1:
			default:
				intMetodo=1; //vendedore envia a todos los compradores
				
				break;
			}
				
				
			mq.escrituraJMS(request.getParameter("mensaje"),intMetodo,intOperacion);
			RequestDispatcher miR=request.getRequestDispatcher("index.jsp");
			miR.forward(request, response);
			
		
		}else if("clearInbox".equalsIgnoreCase(mode)) { 
			String strAux="";
			int intOperacion=2;
			
			switch(status) {
			case 0:
				intMetodo=1;  //comprador envia a todos los vendedores
				break;
				
			case 1:
			default:
				intMetodo=0; //vendedore envia a todos los compradores
				
				break;
			}
			
			strAux=mq.lecturaJMS(intMetodo,intOperacion);
			request.setAttribute("mensajes",null);
			
			RequestDispatcher miR=request.getRequestDispatcher("mensajes-read.jsp");
			miR.forward(request, response);
			
			
			
		}else if("read".equalsIgnoreCase(mode)) { //leemos por browser
			
			String strAux;
			int intOperacion=3;
			
			switch(status) {
			case 0:
				intMetodo=1;  //comprador lee de cola para compradores
				break;
				
			case 1:
			default:
				intMetodo=0; //vendedore envia a todos los compradores
				
				break;
			}
			
			
			strAux=mq.lecturaBrowser(intMetodo,intOperacion);
			request.setAttribute("mensajes",strAux);
			RequestDispatcher miR=request.getRequestDispatcher("mensajes-read.jsp");
			miR.forward(request, response);
			
			
	
		}else if("toSend".equalsIgnoreCase(mode)) {
				response.setContentType("text/html");
				RequestDispatcher rd=request.getRequestDispatcher("/messages-index.jsp");
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
