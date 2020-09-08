package com.ibm.airlineScheduler.masterapiServices;

import java.util.List;

import com.ibm.airlineScheduler.masterapimodel.OperatingFlight;
import com.ibm.airlineScheduler.masterapimodel.ScheduleMaster;
import com.ibm.airlineScheduler.masterapimodel.UserAuthRequest;
import com.ibm.airlineScheduler.masterapimodel.UserAuthResponse;
import com.ibm.airlineScheduler.masterapimodel.dao.AirlineResponse;
import com.ibm.airlineScheduler.masterapimodel.dao.AirportResponse;
import com.ibm.airlineScheduler.masterapimodel.dao.ScheduleMasterRequest;

public interface MasterAPIServices {

	List<AirlineResponse> getAllAirlines();
	
	List<AirportResponse> getAirport();
	
	
	List<AirportResponse> getAirportBycountry(String country);
	
	
	String createScheduler(ScheduleMaster sMaster,ScheduleMasterRequest scheduleMasterRequest,boolean updateFlg);
	
	List<ScheduleMasterRequest> AllScheduleMaster();
	
	List<OperatingFlight> AllScheduleMasterById(String schedulerId);
	
	
	public String asyncC();
	
	UserAuthResponse Signup(UserAuthRequest userAuth);
	
	UserAuthResponse Signin(UserAuthRequest userAuth);
	
	OperatingFlight updateOpflight(String operatingFlightId,OperatingFlight operatingFlight );
	
	
}
