package com.ibm.airlineScheduler.masterapirepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ibm.airlineScheduler.masterapimodel.Employee;

@Repository
public class EmployeeDALImpl implements EmployeeDAL {

	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public List<Employee> getAirportByCountry(String empType) {
		Query query = new Query();
		query.addCriteria(Criteria.where("typeCode").is(empType));
		return mongoTemplate.find(query, Employee.class);
	}

	
	

	
}
