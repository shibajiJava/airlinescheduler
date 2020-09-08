package com.ibm.airlineScheduler.masterapirepository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ibm.airlineScheduler.masterapimodel.PnrDetails;
@Repository
public interface PnrDetailsRepository extends MongoRepository<PnrDetails, String> {

}
