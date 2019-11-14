package jms_manager;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
		

		
		//Escribir en la Cola usando JNDI || Escribir para lectura asícrona || Escritura usando referencias
		int intMetodo = Integer.parseInt(request.getParameter("metodo"));  
		
		//Mandar Mensaje: Escribir en la Cola || Leer en Browser || Leer Mensaje por JMSCorrelationID
		int intOperacion = Integer.parseInt(request.getParameter("operacion"));
		
		
		
		
		InteraccionJMS mq=new InteraccionJMS();


		if (intOperacion==1){
			mq.escrituraJMS(request.getParameter("mensaje"),intMetodo);
			RequestDispatcher miR=request.getRequestDispatcher("index.jsp");
			miR.forward(request, response);

		}else{
			String strAux="";
			strAux=mq.lecturaJMS(intMetodo);
			request.setAttribute("mensajes",strAux);
			RequestDispatcher miR=request.getRequestDispatcher("mensajes-read.jsp");
			miR.forward(request, response);
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
