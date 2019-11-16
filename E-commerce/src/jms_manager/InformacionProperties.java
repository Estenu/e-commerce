package jms_manager;

import java.util.*;

public class InformacionProperties {

	private static String strQCF;

	private static String strQueueSendBuyers;

	private static String strQueueSendSellers;

	private static final String nombreProperties = "InfoAplicacion";

	// **************************************************
	public InformacionProperties() {
		super();
	}

	// **************************************************
	public static String getQCF() {

		if (strQCF == null)
			cagarProperties();

		return strQCF;
	}

	// **************************************************
	public static String getQueueSendBuyers() {

		if (strQueueSendBuyers == null)
			cagarProperties();

		return strQueueSendBuyers;
	}

	// **************************************************
		public static String getQueueSendSellers() {

			if (strQueueSendSellers == null)
				cagarProperties();

			return strQueueSendSellers;
		}

	// **************************************************
	private static void cagarProperties() throws MissingResourceException {

		PropertyResourceBundle appProperties = null;

		try {

			appProperties = (PropertyResourceBundle) PropertyResourceBundle
					.getBundle(nombreProperties);

			strQCF = appProperties.getString("Info.strQCF");
			strQueueSendBuyers = appProperties.getString("Info.strQueueSendBuyers");
			strQueueSendSellers = appProperties.getString("Info.strQueueSendSellers");

		} catch (MissingResourceException e) {

			throw e;
		}

	}
}