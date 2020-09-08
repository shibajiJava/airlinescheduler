package com.ibm.airlineScheduler.masterapirepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ibm.airlineScheduler.masterapimodel.UserAuth;
@Repository
public class UserAuthDALImpl implements UserAuthDAL{

	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public UserAuth getUserAuthByName(String userName) {
		Query query = new Query();
		query.addCriteria(Criteria.where("userName").is(userName));
		return mongoTemplate.findOne(query, UserAuth.class);
	}
}
