package com.element.wex.dao;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.security.wss4j.Wss4jSecurityInterceptor;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import com.wrightexpress.driver.drivermanagement.DriverSearchRequestType;
import com.wrightexpress.driver.drivermanagement.SearchRequest;
import com.wrightexpress.driver.drivermanagement.SearchResponse;

public class WEXWebserviceDaoImpl extends WebServiceGatewaySupport implements WEXWebserviceDao {

	public String searchDriverPin(DriverSearchRequestType driverSearchRequestType) {
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.setDriver(driverSearchRequestType);
		System.setProperty("javax.net.ssl.trustStore", "C:\\opt\\Java\\jdk1.8.0_65\\jre\\lib\\security\\cacerts");

		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.wrightexpress.driver.drivermanagement");

		// HttpClient client = new DefaultHttpClient();
		// client.getParams().setAuthenticationPreemptive(true);

		WebServiceTemplate template = getWebServiceTemplate();
		template.setMarshaller(marshaller);
		HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();
		UsernamePasswordCredentials usernamePasswordCredentials = new UsernamePasswordCredentials("PHHWEBSERV_XC",
				"ch4rt_h4nd");
		httpComponentsMessageSender.setCredentials(usernamePasswordCredentials);

		/*
		 * XwsSecurityInterceptor wsSecurityInterceptor=new
		 * XwsSecurityInterceptor(); wsSecurityInterceptor.set;
		 */
		 Wss4jSecurityInterceptor wss4jSecurityInterceptor = new
		 Wss4jSecurityInterceptor();
		 wss4jSecurityInterceptor.setSecurementActions("UsernameToken");
		 wss4jSecurityInterceptor.setSecurementUsername("PHHWEBSERV_XC");
		 wss4jSecurityInterceptor.setSecurementPassword("ch4rt_h4nd");
		 wss4jSecurityInterceptor.setSecurementPasswordType("PasswordText");
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
		SearchResponse searchResponse = (SearchResponse) template.marshalSendAndReceive(
				"https://webservices-xc.wexinc.com/EWSDriverProject/ProxyServices/DriverProxyv1", searchRequest);
		return searchResponse.toString();
	}

}
