package com.ibm.airlineScheduler.kafkaModel;

import java.util.List;

public class ScdedularDetails {

	private String schedularId;
	private List<PnrDetailsKafka> pnrDetails;
	
	public String getSchedularId() {
		return schedularId;
	}
	public void setSchedularId(String schedularId) {
		this.schedularId = schedularId;
	}
	
	public List<PnrDetailsKafka> getPnrDetails() {
		return pnrDetails;
	}
	public void setPnrDetails(List<PnrDetailsKafka> pnrDetails) {
		this.pnrDetails = pnrDetails;
	}
	
	
}
