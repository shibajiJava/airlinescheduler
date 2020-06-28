package com.ibm.airlineScheduler.masterapirepository;

import java.util.List;

import com.ibm.airlineScheduler.masterapimodel.Airport;

public interface AirportDAL {

	List<Airport> getAirportByCountry(String country);
	
	Airport getAirportByCode(String code);
}
