package com.ibm.airlineScheduler.masterapirepository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ibm.airlineScheduler.masterapimodel.ScheduleMaster;
@Repository
public interface ScheduleMasterRepository extends MongoRepository<ScheduleMaster, String> {

}
