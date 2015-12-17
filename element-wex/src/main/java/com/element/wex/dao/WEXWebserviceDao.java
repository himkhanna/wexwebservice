package com.element.wex.dao;

import com.wrightexpress.driver.drivermanagement.DriverSearchRequestType;

public interface WEXWebserviceDao{
	

	String searchDriverPin(DriverSearchRequestType driverSearchRequestType);
	
}
