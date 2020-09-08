package com.ibm.airlineScheduler.masterapirepository;

import com.ibm.airlineScheduler.masterapimodel.OperatingFlight;

public interface OperatingFlightDAL {
	
	OperatingFlight getoperatingFlight(String id);

}
