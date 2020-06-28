package com.ibm.airlineScheduler.masterapirepository;

import java.util.List;

import com.ibm.airlineScheduler.masterapimodel.Employee;

public interface EmployeeDAL {

	List<Employee> getAirportByCountry(String empType);
}
