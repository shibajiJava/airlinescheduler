package com.ibm.airlineScheduler.kafkaModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KakfaModelPnr {

	@JsonProperty("scdedularPnrDetails")
	private ScdedularDetails scdedularPnrDetails;

	public ScdedularDetails getScdedularPnrDetails() {
		return scdedularPnrDetails;
	}

	public void setScdedularPnrDetails(ScdedularDetails scdedularPnrDetails) {
		this.scdedularPnrDetails = scdedularPnrDetails;
	}
	
	
}
