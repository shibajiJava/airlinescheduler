package com.ibm.airlineScheduler.masterapirepository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ibm.airlineScheduler.masterapimodel.Passenger;
@Repository
public interface PassengerRepository extends MongoRepository<Passenger, String> {

}
