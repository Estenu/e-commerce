/*
 * Creado el 11-nov-14
 * https://docs.oracle.com/javaee/5/tutorial/doc/bncfa.html#bncfl
 */
package jms_manager;

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

		switch (metodo) {

		case 1: //METODO_USO_QUEUE_SIN_REFERENCIA
			factory = (javax.jms.ConnectionFactory) contextoInicial.lookup(InformacionProperties.getQCF());
			cola = (javax.jms.Destination) contextoInicial.lookup(InformacionProperties.getQueue());
			break;
		case 2: //METODO_USO_QUEUE_ASINCRONA
			factory = (javax.jms.ConnectionFactory) contextoInicial.lookup(InformacionProperties.getQCF());
			cola = (javax.jms.Destination) contextoInicial.lookup(InformacionProperties.getQueueAsincrona());
			break;
		case 3: //METODO_USO_QUEUE_REFERENCIA
			factory = (javax.jms.ConnectionFactory) contextoInicial
					.lookup("java:comp/" + InformacionProperties.getQCF() + "Ref");
			cola = (javax.jms.Destination) contextoInicial
					.lookup("java:comp/" + InformacionProperties.getQueue() + "Ref");
			break;
		default:
			factory = (javax.jms.ConnectionFactory) contextoInicial.lookup(InformacionProperties.getQCF());
			cola = (javax.jms.Destination) contextoInicial.lookup(InformacionProperties.getQueue());
			break;
		}

		Qcon = factory.createConnection();

		QSes = Qcon.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);

		if (operacion == 1) {  //OPERACION_ESCRIBIR_MENSAJE_COLA

			Mpro = QSes.createProducer(cola);

		} else if (operacion == 2) { //OPERACION_LECTURA_NORMAL_POR_JMSCorrelationID

			String sSelector = "JMSCorrelationID = '" + strSelectorPasado.trim() + "'";

			if (strSelectorPasado.equals("")) {
				Mcon = QSes.createConsumer(cola);
			} else {
				Mcon = QSes.createConsumer(cola, sSelector);
			}

		} else {   
			// OPERACION_LECTURA_BROWSER_POR_JMSCorrelationID
			if (strSelectorPasado.equals("")) {
				browser = QSes.createBrowser((Queue) cola);
				
			} else {
				browser = QSes.createBrowser((Queue) cola);//, strSelectorPasado);
			}
			
		}
	}

	

	public void escrituraJMS(String mensaje, int metodo, int operacion, String selector) {

		try {

			cargaViaJNDI(metodo, operacion, selector);

			javax.jms.TextMessage men = QSes.createTextMessage();

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
	
	
	public void escrituraJMS(String mensaje, int metodo, int operacion) {

		try {

			cargaViaJNDI(metodo, operacion, "");

			javax.jms.TextMessage men = QSes.createTextMessage();

			men.setText(mensaje);
			
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

	public String lecturaJMS(int metodo, int operacion, String strSelectorPasado) {

		StringBuffer mSB = new StringBuffer(64);
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
	
	
	public String lecturaJMS(int metodo, int operacion) {

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
	public String lecturaBrowser(int metodo, int operacion, String strSelectorPasado) {
			
		StringBuffer _sB = new StringBuffer(32);
		_sB.append("<br>Lectura en Broswer</br>");

		try {
			
			cargaViaJNDI(metodo, operacion, strSelectorPasado);
			
			Qcon.start();
			
			Enumeration messageEnum = browser.getEnumeration();
			
			if ( !messageEnum.hasMoreElements() ) { 
			    _sB.append("No messages in queue");
			} else { 
				while (messageEnum.hasMoreElements()) {
					TextMessage message = (TextMessage) messageEnum.nextElement();
					_sB.append("Browse [" + message.getText() + "]</br>");
				}
			}
			
			
			while (messageEnum.hasMoreElements()) {
				TextMessage message = (TextMessage) messageEnum.nextElement();
				_sB.append(message.getText());
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

		return _sB.toString();

	}
	
	@SuppressWarnings("rawtypes")
	public String lecturaBrowser(int metodo, int operacion) {
			
		StringBuffer _sB = new StringBuffer(32);
		_sB.append("<br>Lectura en Broswer</br>");

		try {
			
			cargaViaJNDI(metodo, operacion, "");
			
			Qcon.start();
			
			Enumeration messageEnum = browser.getEnumeration();
			
			if ( !messageEnum.hasMoreElements() ) { 
			    _sB.append("No messages in queue");
			} else { 
				while (messageEnum.hasMoreElements()) {
					TextMessage message = (TextMessage) messageEnum.nextElement();
					_sB.append("Browse [" + message.getText() + "]</br>");
				}
			}
			
			
			while (messageEnum.hasMoreElements()) {
				TextMessage message = (TextMessage) messageEnum.nextElement();
				_sB.append(message.getText());
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

		return _sB.toString();

	}
	
}
