package com.element.wex.dao;

import org.apache.ws.security.components.crypto.Crypto;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.security.wss4j.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j.support.CryptoFactoryBean;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import com.wrightexpress.driver.drivermanagement.DriverResponseType;
import com.wrightexpress.driver.drivermanagement.DriverSearchRequestType;

public class WEXWebserviceDaoImpl extends WebServiceGatewaySupport implements WEXWebserviceDao {

	public String searchDriverPin(DriverSearchRequestType driverSearchRequestType) {
	//	SearchReques searchRequest = new SearchRequest();
		//searchRequest.setDriver(driverSearchRequestType);
		//System.setProperty("javax.net.ssl.trustStore", "C:\\Program Files\\Java\\jdk1.8.0_65\\jre\\lib\\security\\cacerts");
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.wrightexpress.driver.drivermanagement");

		// HttpClient client = new DefaultHttpClient();
		// client.getParams().setAuthenticationPreemptive(true);

		WebServiceTemplate template = getWebServiceTemplate();
		template.setMarshaller(marshaller);
		HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();
		/*UsernamePasswordCredentials usernamePasswordCredentials = new UsernamePasswordCredentials("PHHWEBSERV_XC1",
				"ch4rt_h4nd1");
		httpComponentsMessageSender.setCredentials(usernamePasswordCredentials);*/

		/*
		 * XwsSecurityInterceptor wsSecurityInterceptor=new
		 * XwsSecurityInterceptor(); wsSecurityInterceptor.set;
		 */
		 Wss4jSecurityInterceptor wss4jSecurityInterceptor = new Wss4jSecurityInterceptor();
		 wss4jSecurityInterceptor.setSecurementActions("UsernameToken");
		 wss4jSecurityInterceptor.setSecurementUsername("PHHWEBSERV_XC");
		 //wss4jSecurityInterceptor.setSecurementPassword("ch4rt_h4nd");
		 wss4jSecurityInterceptor.setSecurementPasswordType("PasswordText");
		 wss4jSecurityInterceptor.setSecurementCallbackHandler(new PasswordCallbackHandler());
		// wss4jSecurityInterceptor.setSecurementUsernameTokenElements("Nonce Created");
		 //wss4jSecurityInterceptor.setValidationActions("Signature");
		 
//		 try {
//			wss4jSecurityInterceptor.setValidationSignatureCrypto( myCrypto() );
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		 try {
				wss4jSecurityInterceptor.afterPropertiesSet();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		 ClientInterceptor[] interceptors = { wss4jSecurityInterceptor };
		 template.setInterceptors(interceptors);
		httpComponentsMessageSender.setConnectionTimeout(4000000);

		try {
			httpComponentsMessageSender.afterPropertiesSet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		template.setMessageSender(httpComponentsMessageSender);
		try {
			afterPropertiesSet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		String requestXML="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:driv=\"http://driver.wrightexpress.com/DriverManagement/\">"
//				+ "<soapenv:Header/><soapenv:Body><driv:SearchRequest><driver><AccountNumber>6900460473000009746</AccountNumber>"
//				+ "</driver><StartPage>1</StartPage></driv:SearchRequest></soapenv:Body></soapenv:Envelope>";
//		StringResult result = new StringResult();
//		Source payload = new StreamSource(new StringReader(requestXML));
	//	template.sendSourceAndReceiveToResult("https://webservices-xc.wexinc.com/EWSDriverProject/ProxyServices/DriverProxyv1", payload, result);
		DriverResponseType searchResponse = (DriverResponseType) template.marshalSendAndReceive(
				"https://webservices-xc.wexinc.com/EWSDriverProject/ProxyServices/DriverProxyv1", driverSearchRequestType);
		return searchResponse.toString();
	}

	public Crypto myCrypto() throws Exception{
	    CryptoFactoryBean factory = new CryptoFactoryBean();
	    factory.setTrustStorePassword( "changeit" );
	    factory.setKeyStorePassword( "changeit" );
	    factory.setKeyStoreLocation( new ClassPathResource("keystore.jks") );
	    factory.afterPropertiesSet();
	    return (Crypto) factory.getObject();
	}
	
}
