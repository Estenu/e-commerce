/*
 * Creado el 11-nov-14
 * https://docs.oracle.com/javaee/5/tutorial/doc/bncfa.html#bncfl
 */
package jms_manager;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import servlet_ecommerce.Usuario;




public class InteraccionJMS {

	private javax.jms.ConnectionFactory factory = null;
	private javax.naming.InitialContext contextoInicial = null;
	private javax.jms.Destination cola = null;
	private javax.jms.Connection Qcon = null;
	private javax.jms.Session QSes = null;
	private javax.jms.MessageProducer Mpro = null;
	private javax.jms.MessageConsumer Mcon = null;
	private javax.jms.QueueBrowser browser = null;
	
	private void cargaViaJNDI(int metodo, int operacion, String strSelectorPasado)
			throws NamingException, JMSException {

		contextoInicial = new javax.naming.InitialContext();
		
		try {

			switch (metodo) {
	
			case 0: //Cola para enviar a vendedores
				factory = (javax.jms.ConnectionFactory) contextoInicial.lookup(InformacionProperties.getQCF());
				cola = (javax.jms.Destination) contextoInicial.lookup(InformacionProperties.getQueueSendSellers());
				break;
			case 1: //Cola para enviar a compradores
				factory = (javax.jms.ConnectionFactory) contextoInicial.lookup(InformacionProperties.getQCF());
				cola = (javax.jms.Destination) contextoInicial.lookup(InformacionProperties.getQueueSendBuyers());
				break;
	
			default:
				factory = (javax.jms.ConnectionFactory) contextoInicial.lookup(InformacionProperties.getQCF());
				cola = (javax.jms.Destination) contextoInicial.lookup(InformacionProperties.getQueueSendSellers());
				break;
			}
	
			Qcon = factory.createConnection();
	
			QSes = Qcon.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
	
			if (operacion == 1) {  //OPERACION_ESCRIBIR_MENSAJE_COLA
	
				Mpro = QSes.createProducer(cola);
	
			} else if (operacion == 2) { //OPERACION_LECTURA_NORMAL
	
				String sSelector = "JMSCorrelationID = '" + strSelectorPasado.trim() + "'";
	
				if (strSelectorPasado.equals("")) {
					Mcon = QSes.createConsumer(cola);
				} else {
					Mcon = QSes.createConsumer(cola, sSelector);
				}
	
			} else {   
				// OPERACION_LECTURA_BROWSER
				
				
				if (strSelectorPasado.equals("")) {
					browser = QSes.createBrowser((Queue) cola);
					
				} else {
					browser = QSes.createBrowser((Queue) cola, strSelectorPasado);
				}
				
			}
		
		} catch (JMSException e) {
			System.out.println(".....JHC *************************************** Error de JMS: "
					+ e.getLinkedException().getMessage());
			System.out.println(".....JHC *************************************** Error de JMS: "
					+ e.getLinkedException().toString());
		} catch (NamingException e) {
			System.out.println("JHC *************************************** Error Exception: " + e.getMessage());
		}
	}

	
	//Escribimos un mensaje para una persona concreta por correlationID
	public void escrituraJMS(String email,String mensaje, int metodo, int operacion, String selector) {

		try {

			cargaViaJNDI(metodo, operacion, selector);

			javax.jms.TextMessage men = QSes.createTextMessage();
			
			
			men.setStringProperty("JMSXUserID", email);
			men.setText(mensaje);
			men.setJMSCorrelationID(selector);
			Qcon.start();
			Mpro.send(men);

			this.Mpro.close();
			this.QSes.close();
			this.Qcon.close();

		} catch (javax.jms.JMSException e) {
			System.out.println(".....JHC *************************************** Error de JMS: "
					+ e.getLinkedException().getMessage());
			System.out.println(".....JHC *************************************** Error de JMS: "
					+ e.getLinkedException().toString());
		} catch (NamingException e) {
			System.out.println("JHC *************************************** Error Exception: " + e.getMessage());
		}

	}
	
	//Escribimos una notificacion
	public void escrituraJMS(String email,String mensaje, int metodo, int operacion) {

		try {

			cargaViaJNDI(metodo, operacion, "");

			javax.jms.TextMessage men = QSes.createTextMessage();

			men.setText(mensaje);
			men.setStringProperty("JMSXUserID", email);
			
			Qcon.start();
			Mpro.send(men);

			this.Mpro.close();
			this.QSes.close();
			this.Qcon.close();

		} catch (javax.jms.JMSException e) {
			System.out.println(".....JHC *************************************** Error de JMS: "
					+ e.getLinkedException().getMessage());
			System.out.println(".....JHC *************************************** Error de JMS: "
					+ e.getLinkedException().toString());
		} catch (NamingException e) {
			System.out.println("JHC *************************************** Error Exception: " + e.getMessage());
		}

	}

	public ArrayList<TextMessage> lecturaJMS(int metodo, int operacion, String strSelectorPasado) { //lectura por CorrelationID

		StringBuffer mSB = new StringBuffer(64);
		ArrayList<TextMessage> mensajes = new ArrayList<TextMessage>();
		
		try {

			cargaViaJNDI(metodo, operacion, strSelectorPasado);

			Qcon.start();

			Message mensaje = null;

			mSB.append("</br>Estos son los mensajes leidos con el selector " + strSelectorPasado + " </br>");

			while (true) {
				mensaje = Mcon.receive(100);
				if (mensaje != null) {
					if (mensaje instanceof TextMessage) {
						TextMessage m = (TextMessage) mensaje;
						mensajes.add(m);
						mSB.append("       Mensaje: " + m.getText() + " </br>");
					} else {
						// JHC ************ No es del tipo correcto
						break;
					}
				} else // NO existe mensaje, mensaje es null
				{
					mSB.append("TIdW 2013-14: No hay Mensajes en la Queue</br>");
					break;
				}

			}
			this.Mcon.close();
			this.QSes.close();
			this.Qcon.close();

		} catch (javax.jms.JMSException e) {
			System.out.println(".....JHC *************************************** Error de JMS: "
					+ e.getLinkedException().getMessage());
			System.out.println(".....JHC *************************************** Error de JMS: "
					+ e.getLinkedException().toString());
		} catch (Exception e) {
			System.out.println("JHC *************************************** Error Exception: " + e.getMessage());
		}

		if(mensajes.size()==0) {
			return null;
		}else {
			return mensajes;
		}

	}
	
	
	public String lecturaJMS(int metodo, int operacion) { //lectura que consume los mensajes (usada para limpiar bandeja de entrada)

		StringBuffer mSB = new StringBuffer(64);
		try {

			cargaViaJNDI(metodo, operacion, "");

			Qcon.start();

			Message mensaje = null;

			mSB.append("</br>Estos son los mensajes leidos  </br>");

			while (true) {
				mensaje = Mcon.receive(100);
				if (mensaje != null) {
					if (mensaje instanceof TextMessage) {
						TextMessage m = (TextMessage) mensaje;
						mSB.append("       Mensaje: " + m.getText() + " </br>");
					} else {
						// JHC ************ No es del tipo correcto
						break;
					}
				} else // NO existe mensaje, mensaje es null
				{
					mSB.append("TIdW 2013-14: No hay Mensajes en la Queue</br>");
					break;
				}

			}
			this.Mcon.close();
			this.QSes.close();
			this.Qcon.close();

		} catch (javax.jms.JMSException e) {
			System.out.println(".....JHC *************************************** Error de JMS: "
					+ e.getLinkedException().getMessage());
			System.out.println(".....JHC *************************************** Error de JMS: "
					+ e.getLinkedException().toString());
		} catch (Exception e) {
			System.out.println("JHC *************************************** Error Exception: " + e.getMessage());
		}

		return mSB.toString();

	}

	
	@SuppressWarnings("rawtypes")
	public ArrayList lecturaBrowser(int metodo, int operacion) { //lee notificicaciones pero las consume			
		StringBuffer _sB = new StringBuffer(32);
		//_sB.append("<br>Lectura en Broswer</br>");
		System.out.println("Lectura en Broswer\n");
		
		ArrayList<TextMessage> mensajes = new ArrayList<TextMessage>();

		try {
			
			cargaViaJNDI(metodo, operacion, "");
			
			Qcon.start();
			
			Enumeration messageEnum = browser.getEnumeration();
			
			
			
			if ( !messageEnum.hasMoreElements() ) { 
			    System.out.println("No hay mensajes en browser");
			    
			} else { 
				while (messageEnum.hasMoreElements()) {
				
					TextMessage message = (TextMessage) messageEnum.nextElement();
					mensajes.add(message);
					_sB.append(message.getText() + "</br>");
				}

			}
			

			
			browser.close();

		} catch (JMSException e) {
			System.out.println(".....JHC *************************************** Error de JMS: "
					+ e.getLinkedException().getMessage());
			System.out.println(".....JHC *************************************** Error de JMS: "
					+ e.getLinkedException().toString());
		} catch (NamingException e) {
			System.out.println("JHC *************************************** Error Exception: " + e.getMessage());
		} finally {
			

			if (Qcon != null) {
				try {
					Qcon.close();
				} catch (JMSException e) {
					System.out.println(".....JHC *************************************** Error de JMS: "
							+ e.getLinkedException().getMessage());
					System.out.println(".....JHC *************************************** Error de JMS: "
							+ e.getLinkedException().toString());
				}
			}
		}
		if(mensajes.size()==0) {
			return null;
		}else {
			return mensajes;
		}
		
	}
	
}
