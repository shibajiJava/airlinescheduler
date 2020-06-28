package com.ibm.airlineScheduler.masterapirepository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ibm.airlineScheduler.masterapimodel.OperatingFlight;
@Repository
public interface OperatingFlightRepositort extends MongoRepository<OperatingFlight, String>{

}
