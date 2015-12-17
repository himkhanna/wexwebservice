package com.element.wex.dao;

import java.io.StringWriter;
import java.net.URLDecoder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;
import org.springframework.xml.transform.StringResult;

import com.wrightexpress.driver.drivermanagement.DriverSearchRequestType;
import com.wrightexpress.driver.drivermanagement.SearchRequest;
import com.wrightexpress.driver.drivermanagement.SearchResponse;

public class WEXWebserviceDaoImpl  extends WebServiceGatewaySupport implements WEXWebserviceDao {

	public String searchDriverPin(DriverSearchRequestType driverSearchRequestType) {
		StringWriter stringWriter = new StringWriter();
		JAXBContext jaxbContext;
		SearchRequest searchRequest=new SearchRequest();
		searchRequest.setDriver(driverSearchRequestType);
		
		//String path = URLDecoder.decode(getClass().getResource("Wex.cer").getPath());
		//System.setProperty("javax.net.ssl.trustStore", "D:\\Element\\wex\\element-wex\\src\\main\\java\\com\\element\\wex\\dao\\Wex.cer"); 
		System.setProperty("javax.net.ssl.trustStore","C:\\Program Files\\Java\\jdk1.8.0_65\\jre\\lib\\security\\cacerts"); 
		
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.wrightexpress.driver.drivermanagement");
		/*try {
			jaxbContext = JAXBContext.newInstance(SearchRequest.class);
			jaxbMarshaller = jaxbContext.createMarshaller();

			// format the XML output
			try {
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			} catch (PropertyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			jaxbMarshaller.marshal(searchRequest, stringWriter);
		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		
		//HttpClient client = new DefaultHttpClient();
		//client.getParams().setAuthenticationPreemptive(true);

		/*CloseableHttpClient httpclient = HttpClients.createDefault();
		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("PHHWEBSERV_XC","ch4rt_h4nd"));
		HttpClientContext localContext = HttpClientContext.create();
		localContext.setCredentialsProvider(credentialsProvider);
		HttpComponentsMessageSender httpComponentsMessageSender=new HttpComponentsMessageSender(httpclient);*/
		//httpComponentsMessageSender.setHttpClient(httpclient);
		
		String result1 = stringWriter.toString();
		WebServiceTemplate template = getWebServiceTemplate();
	//	template.setMessageSender(httpComponentsMessageSender);
		template.setMarshaller(marshaller);
		HttpComponentsMessageSender httpComponentsMessageSender=new HttpComponentsMessageSender();
		//httpComponentsMessageSender.setConnectionTimeout(4000000);
		template.setMessageSender(httpComponentsMessageSender);
		StringResult result = new StringResult();
			/*SearchResponse searchResponse=(SearchResponse)template.marshalSendAndReceive(
					"http://www.webservicex.net/globalweather.asmx",searchRequest);*/
		SearchResponse searchResponse=(SearchResponse)template.marshalSendAndReceive(
				"https://webservices-xc.wexinc.com/EWSDriverProject/ProxyServices/DriverProxyv1",searchRequest);
		/*template.sendSourceAndReceiveToResult(
				"https://webservices-xc.wexinc.com/EWSDriverProject/ProxyServices/DriverProxyv1", new StreamSource(new StringReader(result1)),result );*/
		return searchResponse.toString();
	}
	
	
	
}
