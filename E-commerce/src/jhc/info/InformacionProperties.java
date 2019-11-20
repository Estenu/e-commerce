package jhc.info;

import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;

public class InformacionProperties {

	private static String strServer;
	
	private static String strPort;

	private static String strUser;
	
	private static String strPassword;
	
	private static String strDataSource;
	
	private static String strClassDriver;
	
	private static String strDatabaseName;

	private static final String nombreProperties = "InfoAplicacion";



	public static String getStrServer() {
		
		if (strServer == null)
			cagarProperties();
		return strServer;
	}

	public static String getStrPort() {
		if (strPort == null)
			cagarProperties();
		return strPort;
	}

	public static String getStrDatabaseName() {
		if (strDatabaseName == null)
			cagarProperties();
		return strDatabaseName;
	}
	
	public static String getStrUser() {
		if (strUser == null)
			cagarProperties();
		return strUser;
	}

	public static String getStrPassword() {
		if (strPassword == null)
			cagarProperties();
		return strPassword;
	}

	public static String getStrDataSource() {
		if (strDataSource == null)
			cagarProperties();
		return strDataSource;
	}

	public static String getStrClassDriver() {
		if (strClassDriver == null)
			cagarProperties();
		return strClassDriver;
	}

	// **************************************************
	private static void cagarProperties() throws MissingResourceException {

		PropertyResourceBundle appProperties = null;

		try {

			appProperties = (PropertyResourceBundle) PropertyResourceBundle
					.getBundle(nombreProperties);

			strServer = appProperties.getString("Info.strServer");
			strPort = appProperties.getString("Info.strPort");
			strUser = appProperties.getString("Info.strUser");
			strPassword = appProperties.getString("Info.strPassword");
			strDataSource = appProperties.getString("Info.strDataSource");
			strClassDriver = appProperties.getString("Info.strClassDriver");
			strDatabaseName = appProperties.getString("Info.strDatabaseName");
			
			
					
		} catch (MissingResourceException e) {

			throw e;
		}

	}
}