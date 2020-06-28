package com.ibm.airlineScheduler.masterapirepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ibm.airlineScheduler.masterapimodel.Airport;
@Repository
public class AirportDALImpl implements AirportDAL {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Airport> getAirportByCountry(String country) {
		Query query = new Query();
		query.addCriteria(Criteria.where("country").is(country));
		return mongoTemplate.find(query, Airport.class);
	}

	@Override
	public Airport getAirportByCode(String code) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("iataCode").is(code));
		return mongoTemplate.findOne(query, Airport.class);
	}
}
