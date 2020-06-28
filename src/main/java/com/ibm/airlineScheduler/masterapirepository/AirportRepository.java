package com.ibm.airlineScheduler.masterapirepository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ibm.airlineScheduler.masterapimodel.Airport;
@Repository
public interface AirportRepository extends MongoRepository<Airport, String> {

}
