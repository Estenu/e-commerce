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



public class InteraccionJMS {

	private javax.jms.ConnectionFactory factory = null;
	private javax.naming.InitialContext contextoInicial = null;
	private javax.jms.Destination cola = null;
	private javax.jms.Connection Qcon = null;
	private javax.jms.Session QSes = null;
	private javax.jms.MessageProducer Mpro = null;
	private javax.jms.MessageConsumer Mcon = null;
	
	private javax.jms.TopicConnectionFactory topicfactory = null;
	private javax.naming.InitialContext topiccontextoInicial = null;
	private javax.jms.Topic topic = null;
	private javax.jms.TopicConnection topicCon = null;
	private javax.jms.TopicSession topicSes = null;
	private javax.jms.TopicPublisher publisher = null;
	private javax.jms.TopicRequestor subscriber = null;

	public void escrituraJMS(String mensaje, int opcion) {

		try {

			contextoInicial = new javax.naming.InitialContext();

			switch (opcion) {
			case 1:
				factory = (javax.jms.ConnectionFactory) contextoInicial
						.lookup(InformacionProperties.getQCF());
				cola = (javax.jms.Destination) contextoInicial
						.lookup(InformacionProperties.getQueue());
				break;
			case 2:
				factory = (javax.jms.ConnectionFactory) contextoInicial.lookup(InformacionProperties.getQCF());
				cola = (javax.jms.Destination) contextoInicial.lookup(InformacionProperties.getQueueAsincrona());
				break;
			case 3:
				factory = (javax.jms.ConnectionFactory) contextoInicial
						.lookup("java:comp/env/"
								+ InformacionProperties.getQCF() + "Ref");
				cola = (javax.jms.Destination) contextoInicial
						.lookup("java:comp/env/"
								+ InformacionProperties.getQueue() + "Ref");
				break;
			default:
				factory = (javax.jms.ConnectionFactory) contextoInicial
						.lookup(InformacionProperties.getQCF());
				cola = (javax.jms.Destination) contextoInicial
						.lookup(InformacionProperties.getQueue());
				break;
			}
			Qcon = factory.createConnection();
			QSes = Qcon
					.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);

			Mpro = QSes.createProducer(cola);

			javax.jms.TextMessage men = QSes.createTextMessage();

			men.setText(mensaje);
			
			Qcon.start();
			Mpro.send(men);

			this.Mpro.close();
			this.QSes.close();
			this.Qcon.close();

		} catch (javax.jms.JMSException e) {
			System.out
					.println(".....JHC *************************************** Error de JMS: "
							+ e.getLinkedException().getMessage());
			System.out
					.println(".....JHC *************************************** Error de JMS: "
							+ e.getLinkedException().toString());
		} catch (Exception e) {
			System.out
					.println("JHC *************************************** Error Exception: "
							+ e.getMessage());
		}

	}
	
	
	
	
	public String lecturaJMS(int opcion) {

		StringBuffer mSB = new StringBuffer(64);
		try {
			contextoInicial = new javax.naming.InitialContext();

			switch (opcion) {
			case 1:
				factory = (javax.jms.ConnectionFactory) contextoInicial
						.lookup(InformacionProperties.getQCF());
				cola = (javax.jms.Destination) contextoInicial
						.lookup(InformacionProperties.getQueue());
				break;
			case 2:
				factory = (javax.jms.ConnectionFactory) contextoInicial
						.lookup("java:comp/env/"
								+ InformacionProperties.getQCF() + "Ref");
				cola = (javax.jms.Destination) contextoInicial
						.lookup("java:comp/env/"
								+ InformacionProperties.getQueue() + "Ref");
				break;
			default:
				factory = (javax.jms.ConnectionFactory) contextoInicial
						.lookup(InformacionProperties.getQCF());
				cola = (javax.jms.Destination) contextoInicial
						.lookup(InformacionProperties.getQueue());
				break;
			}

			Qcon = factory.createConnection();

			QSes = Qcon
					.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);

			

			
			Mcon = QSes.createConsumer(cola);
			
				

			
			Qcon.start();
			Message mensaje = null;
			mSB.append("</br>Estos son los mensajes leidos con el selector </br>");
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
			System.out
					.println(".....JHC *************************************** Error de JMS: "
							+ e.getLinkedException().getMessage());
			System.out
					.println(".....JHC *************************************** Error de JMS: "
							+ e.getLinkedException().toString());
		} catch (Exception e) {
			System.out
					.println("JHC *************************************** Error Exception: "
							+ e.getMessage());
		}

		return mSB.toString();

	}

	public String lecturaJMSTopic(int opcion) {

		StringBuffer mSB = new StringBuffer(64);
		try {
			topiccontextoInicial = new javax.naming.InitialContext();

			switch (opcion) {
			case 1:
				topicfactory = (javax.jms.TopicConnectionFactory) contextoInicial
						.lookup(InformacionProperties.getQCF());
				topic = (javax.jms.Topic) contextoInicial
						.lookup(InformacionProperties.getQueue());
				break;
			case 2:
				topicfactory = (javax.jms.TopicConnectionFactory) contextoInicial
						.lookup(InformacionProperties.getQCF());
				topic = (javax.jms.Topic) contextoInicial
						.lookup(InformacionProperties.getQueue());
				break;
			default:
				topicfactory = (javax.jms.TopicConnectionFactory) contextoInicial
						.lookup(InformacionProperties.getQCF());
				topic = (javax.jms.Topic) contextoInicial
					.lookup(InformacionProperties.getQueue());
		break;
			}

			topicCon = topicfactory.createTopicConnection();

			topicSes = topicCon.createTopicSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);

			topic = topicSes.createTopic("compradoresTopic");

			
			
			
				

			
			Qcon.start();
			Message mensaje = null;
			mSB.append("</br>Mensajes Nuevos </br>");
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
					mSB.append(" No hay Mensajes en la Queue</br>");
					break;
				}

			}
			this.Mcon.close();
			this.QSes.close();
			this.Qcon.close();

		} catch (javax.jms.JMSException e) {
			System.out
					.println(".....JHC *************************************** Error de JMS: "
							+ e.getLinkedException().getMessage());
			System.out
					.println(".....JHC *************************************** Error de JMS: "
							+ e.getLinkedException().toString());
		} catch (Exception e) {
			System.out
					.println("JHC *************************************** Error Exception: "
							+ e.getMessage());
		}

		return mSB.toString();

	}
	
	public void escrituraJMSTopic(String mensaje, int opcion) {

		try {

			contextoInicial = new javax.naming.InitialContext();

			switch (opcion) {
			case 1:
				factory = (javax.jms.ConnectionFactory) contextoInicial
						.lookup(InformacionProperties.getQCF());
				cola = (javax.jms.Destination) contextoInicial
						.lookup(InformacionProperties.getQueue());
				break;
			case 2:
				factory = (javax.jms.ConnectionFactory) contextoInicial.lookup(InformacionProperties.getQCF());
				cola = (javax.jms.Destination) contextoInicial.lookup(InformacionProperties.getQueueAsincrona());
				break;
			case 3:
				factory = (javax.jms.ConnectionFactory) contextoInicial
						.lookup("java:comp/env/"
								+ InformacionProperties.getQCF() + "Ref");
				cola = (javax.jms.Destination) contextoInicial
						.lookup("java:comp/env/"
								+ InformacionProperties.getQueue() + "Ref");
				break;
			default:
				factory = (javax.jms.ConnectionFactory) contextoInicial
						.lookup(InformacionProperties.getQCF());
				cola = (javax.jms.Destination) contextoInicial
						.lookup(InformacionProperties.getQueue());
				break;
			}
			Qcon = factory.createConnection();
			QSes = Qcon
					.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);

			Mpro = QSes.createProducer(cola);

			javax.jms.TextMessage men = QSes.createTextMessage();

			men.setText(mensaje);
			
			Qcon.start();
			Mpro.send(men);

			this.Mpro.close();
			this.QSes.close();
			this.Qcon.close();

		} catch (javax.jms.JMSException e) {
			System.out
					.println(".....JHC *************************************** Error de JMS: "
							+ e.getLinkedException().getMessage());
			System.out
					.println(".....JHC *************************************** Error de JMS: "
							+ e.getLinkedException().toString());
		} catch (Exception e) {
			System.out
					.println("JHC *************************************** Error Exception: "
							+ e.getMessage());
		}

	}
	
}
