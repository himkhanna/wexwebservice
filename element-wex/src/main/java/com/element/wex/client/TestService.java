package com.element.wex.client;



import com.element.wex.service.WEXServiceImpl;
import com.wrightexpress.driver.drivermanagement.DriverSearchRequestType;
import com.wrightexpress.driver.drivermanagement.DriverSearchRequestType.Driver;

public class TestService {
	
	
	public static void main(String[] args) throws Exception {
		
		WEXServiceImpl wexServiceImpl=new WEXServiceImpl();
		DriverSearchRequestType driverSearchRequestType=new DriverSearchRequestType();
		Driver driverInfo=new Driver();
		driverInfo.setAccountNumber("6900460473000009746");
		driverSearchRequestType.setStartPage(1);
		driverSearchRequestType.setDriver(driverInfo);
		wexServiceImpl.searchDriverPin(driverSearchRequestType);
		
	}
}
