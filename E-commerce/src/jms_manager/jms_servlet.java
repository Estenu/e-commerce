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
@WebServlet("/jms_servlet")
public class jms_servlet extends HttpServlet {
	/**
	 *
	 */
	private static final long serialVersionUID = 2920899322727130776L;

	/**
	* @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
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
		
		////AAAAAAAA || BBBBBBBB
		/**int intSelector=Integer.parseInt(req.getParameter("selector")); */
		
		//Escribir en la Cola usando JNDI || Escribir para lectura asícrona || Escritura usando referencias
		int intMetodo = Integer.parseInt(req.getParameter("metodo"));  
		
		//Mandar Mensaje: Escribir en la Cola || Leer en Browser || Leer Mensaje por JMSCorrelationID
		int intOperacion = Integer.parseInt(req.getParameter("operacion"));

		InteraccionJMS mq=new InteraccionJMS();
		/**String strAux="";
		switch (intSelector) {
		case 1:
			strAux=Selectores.SelecrtorA;
			break;
		case 2:
			strAux=Selectores.SelecrtorB;
			break;
		default:
			strAux=Selectores.SelecrtorA;
			break;
		}*/

		if (intOperacion==1){
			mq.escrituraJMS(req.getParameter("mensaje"),intMetodo);
			RequestDispatcher miR=req.getRequestDispatcher("index.html");
			miR.forward(req, resp);

		}else{
			String strAux="";
			strAux=mq.lecturaJMS(intMetodo);
			req.setAttribute("mensajes",strAux);
			RequestDispatcher miR=req.getRequestDispatcher("mensajesLeidos.jsp");
			miR.forward(req, resp);
		}

	}



}
