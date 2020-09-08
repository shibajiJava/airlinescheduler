package com.ibm.airlineScheduler.masterapirepository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ibm.airlineScheduler.masterapimodel.UserAuth;
@Repository
public interface UserAuthRepository extends MongoRepository<UserAuth, String> {

}
