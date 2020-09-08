package com.ibm.airlineScheduler.masterapirepository;

import com.ibm.airlineScheduler.masterapimodel.UserAuth;

public interface UserAuthDAL {
	public UserAuth getUserAuthByName(String userName);
}
