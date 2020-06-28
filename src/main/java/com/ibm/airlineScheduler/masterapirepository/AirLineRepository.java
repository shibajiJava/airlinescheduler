package com.ibm.airlineScheduler.masterapirepository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ibm.airlineScheduler.masterapimodel.Airlines;

@Repository
public interface AirLineRepository extends MongoRepository<Airlines, String> {

}
