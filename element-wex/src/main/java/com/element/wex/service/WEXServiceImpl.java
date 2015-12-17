package com.element.wex.service;

import com.element.wex.dao.WEXWebserviceDaoImpl;
import com.wrightexpress.driver.drivermanagement.DriverSearchRequestType;

public class WEXServiceImpl implements WEXService {
	/*@Autowired
	WEXWebserviceDao WEXWebserviceDao;*/
	
	public String searchDriverPin(DriverSearchRequestType driverSearchRequestType){
		WEXWebserviceDaoImpl  WEXWebserviceDao=new WEXWebserviceDaoImpl();
		return WEXWebserviceDao.searchDriverPin(driverSearchRequestType);
		
	} 
}
