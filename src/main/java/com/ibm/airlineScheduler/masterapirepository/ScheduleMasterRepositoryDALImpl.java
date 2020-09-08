package com.ibm.airlineScheduler.masterapirepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ibm.airlineScheduler.masterapimodel.ScheduleMaster;
@Repository
public class ScheduleMasterRepositoryDALImpl implements ScheduleMasterRepositoryDAL {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public ScheduleMaster getSchedulerById(String schedulerId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(schedulerId));
		return mongoTemplate.findOne(query, ScheduleMaster.class);
	}
	
	

}
