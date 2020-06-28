package com.ibm.airlineScheduler.masterapiServices;

import java.util.List;

import com.ibm.airlineScheduler.masterapimodel.dao.AirlineResponse;
import com.ibm.airlineScheduler.masterapimodel.dao.AirportResponse;
import com.ibm.airlineScheduler.masterapimodel.dao.ScheduleMasterRequest;

public interface MasterAPIServices {

	List<AirlineResponse> getAllAirlines();
	
	List<AirportResponse> getAirport();
	
	
	List<AirportResponse> getAirportBycountry(String country);
	
	
	String createScheduler(ScheduleMasterRequest scheduleMasterRequest);
}
