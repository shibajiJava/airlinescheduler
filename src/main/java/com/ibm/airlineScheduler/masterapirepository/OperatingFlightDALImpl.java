package com.ibm.airlineScheduler.masterapirepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ibm.airlineScheduler.masterapimodel.OperatingFlight;
@Repository
public class OperatingFlightDALImpl implements OperatingFlightDAL {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public OperatingFlight getoperatingFlight(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		return mongoTemplate.findOne(query, OperatingFlight.class);
	}
}
