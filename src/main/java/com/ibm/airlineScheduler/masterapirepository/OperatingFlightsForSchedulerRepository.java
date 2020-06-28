package com.ibm.airlineScheduler.masterapirepository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ibm.airlineScheduler.masterapimodel.OperatingFlightsForScheduler;
@Repository
public interface OperatingFlightsForSchedulerRepository extends MongoRepository<OperatingFlightsForScheduler, String> {

}
