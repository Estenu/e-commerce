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
		
		
		String mode=request.getParameter("mode");
		InteraccionJMS mq=new InteraccionJMS();
		
		int intMetodo; //Escribir en la Cola usando JNDI || Escribir para lectura asícrona || Escritura usando referencias
		
		if(request.getParameter("metodo")==null) {
			intMetodo = 1;
		}else {
			intMetodo = Integer.parseInt(request.getParameter("metodo"));
		}
		
		
		if("sendAll".equalsIgnoreCase(mode)) {
			
			int intOperacion=1;
			mq.escrituraJMS(request.getParameter("mensaje"),intMetodo,intOperacion);
			RequestDispatcher miR=request.getRequestDispatcher("index.jsp");
			miR.forward(request, response);
			
			
		}else if("read".equalsIgnoreCase(mode)) {
			
			String strAux="";
			//strAux=mq.lecturaJMS(intMetodo);
			int intOperacion=3;
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
