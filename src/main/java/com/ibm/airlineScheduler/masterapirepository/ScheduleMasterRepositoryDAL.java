package com.ibm.airlineScheduler.masterapirepository;

import com.ibm.airlineScheduler.masterapimodel.ScheduleMaster;

public interface ScheduleMasterRepositoryDAL {
	ScheduleMaster getSchedulerById(String schedulerId);
}
