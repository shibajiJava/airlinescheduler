package com.ibm.airlineScheduler.masterapiServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.airlineScheduler.masterapimodel.Airlines;
import com.ibm.airlineScheduler.masterapimodel.Airport;
import com.ibm.airlineScheduler.masterapimodel.DepartureInfo;
import com.ibm.airlineScheduler.masterapimodel.Employee;
import com.ibm.airlineScheduler.masterapimodel.OperatingFlight;
import com.ibm.airlineScheduler.masterapimodel.OperatingFlightsForScheduler;
import com.ibm.airlineScheduler.masterapimodel.Passenger;
import com.ibm.airlineScheduler.masterapimodel.ScheduleMaster;
import com.ibm.airlineScheduler.masterapimodel.dao.AirlineResponse;
import com.ibm.airlineScheduler.masterapimodel.dao.AirportResponse;
import com.ibm.airlineScheduler.masterapimodel.dao.ScheduleMasterRequest;
import com.ibm.airlineScheduler.masterapirepository.AirLineRepository;
import com.ibm.airlineScheduler.masterapirepository.AirportDAL;
import com.ibm.airlineScheduler.masterapirepository.AirportRepository;
import com.ibm.airlineScheduler.masterapirepository.EmployeeRepository;
import com.ibm.airlineScheduler.masterapirepository.OperatingFlightsForSchedulerRepository;
import com.ibm.airlineScheduler.masterapirepository.PassengerRepository;
import com.ibm.airlineScheduler.masterapirepository.ScheduleMasterRepository;
import com.sun.jdi.InternalException;


@Service
public class MasterAPIServicesImpl implements MasterAPIServices {

	private  AirLineRepository airLineRepository;
	
	private  AirportRepository airportRepository;
	
	@Autowired
	private AirportDAL AirportDAL;
	
	@Autowired
	private ScheduleMasterRepository scheduleMasterRepository;
	
	@Autowired
	private PassengerRepository passengerRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private OperatingFlightsForSchedulerRepository operatingFlightsForSchedulerRepository;
	
	public MasterAPIServicesImpl(AirLineRepository airLineRepository,AirportRepository airportRepository) {
		this.airLineRepository=airLineRepository;
		this.airportRepository=airportRepository;

	}
	
	@Override
	public List<AirlineResponse> getAllAirlines() {
		
		List<Airlines> allAirlines = airLineRepository.findAll();
		List<AirlineResponse> allAirlineResponses=  new ArrayList<>();
		for(Airlines airline:allAirlines) {
			AirlineResponse airlineResponse =  new AirlineResponse();
			airlineResponse.setAirline(airline.getAirline());
			airlineResponse.setiATACode(airline.getiATACode());
			airlineResponse.setRegion(airline.getRegion());
			allAirlineResponses.add(airlineResponse);
		}
		return allAirlineResponses;
	}

	@Override
	public List<AirportResponse> getAirport() {
		// TODO Auto-generated method stub
		List<Airport> allAirportList = airportRepository.findAll();
		List<AirportResponse> allAirportResponse  = new ArrayList<>();
		for(Airport airport:allAirportList) {
			AirportResponse airportResponse = new AirportResponse();
			airportResponse.setAirportId(airport.getAirportId());
			airportResponse.setAirportName(airport.getAirportName());
			airportResponse.setCountry(airport.getCountry());
			airportResponse.setIataCode(airport.getIataCode());
			allAirportResponse.add(airportResponse);
		}
		return allAirportResponse;
	}

	@Override
	public List<AirportResponse> getAirportBycountry(String country) {
		// TODO Auto-generated method stub
		List<Airport> listOfAirport = AirportDAL.getAirportByCountry(country);
		List<AirportResponse> allAirportResponse  = new ArrayList<>();
		for(Airport airport:listOfAirport) {
			AirportResponse airportResponse = new AirportResponse();
			airportResponse.setAirportId(airport.getAirportId());
			airportResponse.setAirportName(airport.getAirportName());
			airportResponse.setCountry(airport.getCountry());
			airportResponse.setIataCode(airport.getIataCode());
			allAirportResponse.add(airportResponse);
		}
		return allAirportResponse;
	}

	@Override
	public String createScheduler(ScheduleMasterRequest scheduleMasterRequest) {

	
		
		
		ScheduleMaster sMaster = new ScheduleMaster();
		sMaster.setCarrierCode(scheduleMasterRequest.getCarrierCode());
		sMaster.setScheduleActiveEndDate(scheduleMasterRequest.getScheduleActiveEndDate());
		
		List<Airport> destAirportList = new ArrayList<>();
		for(String code:scheduleMasterRequest.getScheduleDestination())
		{
			Airport airport  = AirportDAL.getAirportByCode(code);
			if(airport==null) {
				throw new InternalException("NoData Found");
			}
			System.out.println("Airport Data Found");
			destAirportList.add(airport);
		}
		
		
		sMaster.setScheduleDestination(destAirportList);
		sMaster.setScheduleForFlight(scheduleMasterRequest.getScheduleForFlight());
		sMaster.setScheduleSource(scheduleMasterRequest.getScheduleSource());
		
		sMaster.setScheduleActiveStartDate(scheduleMasterRequest.getScheduleActiveStartDate());
		
		//04/02/2020 20:27:05
		DateTime enddate = DateTime.parse(scheduleMasterRequest.getScheduleActiveEndDate(), 
                DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss"));
		//05/02/2020 20:27:05
		DateTime startdate = DateTime.parse(scheduleMasterRequest.getScheduleActiveStartDate(), 
                DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss"));
		
		long min = getTimeDiffInMinutes(scheduleMasterRequest.getScheduleActiveStartDate(),scheduleMasterRequest.getScheduleActiveEndDate());
		long tripCount  = min/Long.valueOf(scheduleMasterRequest.getInterval());
		int noOfCrew = Integer.valueOf(scheduleMasterRequest.getNoOfCrew());
		
		int noOfPilot = Integer.valueOf(scheduleMasterRequest.getNoOfPilot());
		
		int noOfPassenger = Integer.valueOf(scheduleMasterRequest.getNoOfPasenger());
		
		List<OperatingFlight> allScheduled = new ArrayList<>();
		 
		DateTime estimatedArrivalTime = DateTime.parse(scheduleMasterRequest.getScheduleActiveStartDate(), 
                DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss"));
		for(int i=0;i<=tripCount;i++) {
			
			
			OperatingFlight operatingFlight =  new OperatingFlight();
			List<Employee> empList = new ArrayList<>();
			List<Passenger> passngerList = new ArrayList<>();
			
			List<Passenger> passengetList =  passengerRepository.findAll();
			List<Employee> emplyeeList =  employeeRepository.findAll();
			
			
			List<Integer> crewListElemet =  getRandomNumber(noOfCrew,emplyeeList.size());
			
			for(Integer item:crewListElemet) {
				empList.add(emplyeeList.get(item));
			}
			
			List<Integer> empListElement =  getRandomNumber(noOfPassenger,passengetList.size());
			
			for(Integer item:empListElement) {
				passngerList.add(passengetList.get(item));
			}
			
			
			operatingFlight.setAssignedEmployees(empList);
			operatingFlight.setAssignedPassengers(passngerList);
			operatingFlight.setFlightNumber("123");
			DepartureInfo departureInfo =  new DepartureInfo();
			departureInfo.setEstimatedDateTime(estimatedArrivalTime.toString());
			estimatedArrivalTime = estimatedArrivalTime.plusMinutes(Integer.valueOf(scheduleMasterRequest.getInterval()));
			departureInfo.setGateStatus("Open");
			departureInfo.setGate("23A");
			operatingFlight.setCarrierCode(scheduleMasterRequest.getCarrierCode());
			operatingFlight.setDepartureInfo(departureInfo);
			System.out.println("DEST : "+getRandomNumber(1,scheduleMasterRequest.getScheduleDestination().size()));
			operatingFlight.setDestination(scheduleMasterRequest.getScheduleDestination().get(getRandomNumber(1,scheduleMasterRequest.getScheduleDestination().size()).get(0)));
			allScheduled.add(operatingFlight);
			
			
		}
		sMaster.setOperatingFlight(allScheduled);
		sMaster.setNoOfSchduler(String.valueOf(allScheduled.size()) );
		sMaster.setScheduleId("SCHE-"+scheduleMasterRequest.getCarrierCode()+getRandomNumber(1,1000).get(0));
		
		//sMaster.setScheduleDestination(scheduleMasterRequest.getScheduleDestination());
		scheduleMasterRepository.save(sMaster);
		return "ID";
	}
	
	
	private static ArrayList<Integer> getRandomNumber(int requiredNumber,int maxNumber){
		ArrayList<Integer> numbers = new ArrayList<Integer>();   
		Random randomGenerator = new Random();
		while (numbers.size() < requiredNumber) {

		    int random = randomGenerator.nextInt(maxNumber);
		    if (!numbers.contains(random)) {
		        numbers.add(random);
		    }
		}
		return numbers;
	}

	private static long getTimeDiffInMinutes(String startDateTime,String endDateTime)
	{
		
		DateTime enddate = DateTime.parse(endDateTime, 
                DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss"));
		//05/02/2020 20:27:05
		DateTime startdate = DateTime.parse(startDateTime, 
                DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss"));
		
		Interval interval = new Interval(startdate,enddate);
		
		return (interval.toDurationMillis()/60000);
	}

	
	
	
	

}
