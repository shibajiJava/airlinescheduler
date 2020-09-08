package com.ibm.airlineScheduler.masterapiServices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.airlineScheduler.CustomRestTemplate;
import com.ibm.airlineScheduler.kafkaModel.KakfaModelPnr;
import com.ibm.airlineScheduler.kafkaModel.PnrDetailsKafka;
import com.ibm.airlineScheduler.kafkaModel.ScdedularDetails;
import com.ibm.airlineScheduler.masterapimodel.Airlines;
import com.ibm.airlineScheduler.masterapimodel.Airport;
import com.ibm.airlineScheduler.masterapimodel.DepartureInfo;
import com.ibm.airlineScheduler.masterapimodel.Employee;
import com.ibm.airlineScheduler.masterapimodel.OperatingFlight;
import com.ibm.airlineScheduler.masterapimodel.Passenger;
import com.ibm.airlineScheduler.masterapimodel.PnrDetails;
import com.ibm.airlineScheduler.masterapimodel.ScheduleMaster;
import com.ibm.airlineScheduler.masterapimodel.UserAuth;
import com.ibm.airlineScheduler.masterapimodel.UserAuthRequest;
import com.ibm.airlineScheduler.masterapimodel.UserAuthResponse;
import com.ibm.airlineScheduler.masterapimodel.dao.AirlineResponse;
import com.ibm.airlineScheduler.masterapimodel.dao.AirportResponse;
import com.ibm.airlineScheduler.masterapimodel.dao.ScheduleMasterRequest;
import com.ibm.airlineScheduler.masterapirepository.AirLineRepository;
import com.ibm.airlineScheduler.masterapirepository.AirportDAL;
import com.ibm.airlineScheduler.masterapirepository.AirportRepository;
import com.ibm.airlineScheduler.masterapirepository.EmployeeRepository;
import com.ibm.airlineScheduler.masterapirepository.OperatingFlightDAL;
import com.ibm.airlineScheduler.masterapirepository.OperatingFlightRepositort;
import com.ibm.airlineScheduler.masterapirepository.OperatingFlightsForSchedulerRepository;
import com.ibm.airlineScheduler.masterapirepository.PassengerRepository;
import com.ibm.airlineScheduler.masterapirepository.ScheduleMasterRepository;
import com.ibm.airlineScheduler.masterapirepository.ScheduleMasterRepositoryDAL;
import com.ibm.airlineScheduler.masterapirepository.UserAuthRepository;
import com.sun.jdi.InternalException;


@Service
public class MasterAPIServicesImpl implements MasterAPIServices {

	private  AirLineRepository airLineRepository;
	
	private  AirportRepository airportRepository;
	
	
	private CustomRestTemplate customRestTemplate;
	
	@Autowired
	private OperatingFlightRepositort operatingFlightRepositort ;

	@Autowired
	private UserAuthRepository userAuthRepository;
	
	@Autowired
	private AirportDAL AirportDAL;
	
	@Autowired
	private ScheduleMasterRepositoryDAL scheduleMasterRepositoryDAL ;
	
	@Autowired
	private OperatingFlightDAL OperatingFlightDAL ;
	
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
		this.customRestTemplate= new CustomRestTemplate();

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

	private void deleteScheduleById(String id) {
		scheduleMasterRepository.deleteById(id);
	}
	
	@Override
	@Async("asyncExecutor")
	@Transactional
	public String createScheduler(ScheduleMaster sMaster,ScheduleMasterRequest scheduleMasterRequest,boolean updateFlg) {

		
		createSchedular(sMaster,scheduleMasterRequest,updateFlg);
		
		return sMaster.getScheduleId();
	}
	
	
	private void pushDataInkafka(String kafkaData) {
		try {

			
			HttpEntity<Object> requestEntity = new HttpEntity<>(kafkaData);
	        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
	        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
	        customRestTemplate.airlineRestTemplate().getMessageConverters().add(mappingJackson2HttpMessageConverter);

	        ResponseEntity<String> response = customRestTemplate.airlineRestTemplate().exchange("https://159.122.174.61:32336/sendpost", HttpMethod.POST, requestEntity,String.class);
	        
	       
			
			/*
			URL url = new URL(
					"http://173.193.85.145:31978/sendpost");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			String input = kafkaData;

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				System.out.println("Failed to publish in kafka. Response code : "+conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			
			

			conn.disconnect();*/

 
		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	@Transactional
	private String createSchedular(ScheduleMaster sMaster,ScheduleMasterRequest scheduleMasterRequest,boolean updateFlg)
	{
		
		ScheduleMaster sMasterUpdate = null;
		if((scheduleMasterRequest.getScheduleMasterId()!=null && !scheduleMasterRequest.getScheduleMasterId().isEmpty()) && scheduleMasterRequest.getDatauploadstatus().equalsIgnoreCase("Available") && updateFlg) {
			
			deleteScheduleById(scheduleMasterRequest.getScheduleMasterId());
			sMasterUpdate=sMaster;
		}
		else
		{
			Optional<ScheduleMaster> listScheduleMaster = scheduleMasterRepository.findById(sMaster.getId());
			if(listScheduleMaster.isPresent()) {
			
				sMasterUpdate = listScheduleMaster.get();
			}
			
		}
		
		
		
		sMasterUpdate.setCarrierCode(scheduleMasterRequest.getCarrierCode());
		sMasterUpdate.setScheduleActiveEndDate(scheduleMasterRequest.getScheduleActiveEndDate());
		
		List<Airport> destAirportList = new ArrayList<>();
		for(String code:scheduleMasterRequest.getScheduleDestination())
		{
			Airport airport  = AirportDAL.getAirportByCode(code);
			if(airport==null) {
				sMasterUpdate.setDatauploadstatus("Failed");
				sMasterUpdate.setDatauploadstatus("Unable to create Schedular. Probable reson for fail Airport Data Found");
				scheduleMasterRepository.save(sMasterUpdate);
				return null;
				//throw new InternalException("NoData Found");
			}
			
			
			destAirportList.add(airport);
		}
		
		
		sMasterUpdate.setScheduleDestination(destAirportList);
		sMasterUpdate.setScheduleForFlight(scheduleMasterRequest.getScheduleForFlight());
		sMasterUpdate.setScheduleSource(scheduleMasterRequest.getScheduleSource());
		
		sMasterUpdate.setScheduleActiveStartDate(scheduleMasterRequest.getScheduleActiveStartDate());
		
		//04/02/2020 20:27:05
		DateTime enddate = DateTime.parse(scheduleMasterRequest.getScheduleActiveEndDate(), 
                DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss"));
		//05/02/2020 20:27:05
		DateTime startdate = DateTime.parse(scheduleMasterRequest.getScheduleActiveStartDate(), 
                DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss"));
		
		if(enddate.isBefore(startdate)) {
			sMasterUpdate.setDatauploadstatus("Failed");
			sMasterUpdate.setDatauploadstatus("End data can not be before of start date");
			scheduleMasterRepository.save(sMasterUpdate);
			return null;
		}
		
		long min = getTimeDiffInMinutes(scheduleMasterRequest.getScheduleActiveStartDate(),scheduleMasterRequest.getScheduleActiveEndDate());
		long tripCount  = min/Long.valueOf(scheduleMasterRequest.getInterval());
		int noOfCrew = Integer.valueOf(scheduleMasterRequest.getNoOfCrew());
		
		int noOfPilot = Integer.valueOf(scheduleMasterRequest.getNoOfPilot());
		
		int noOfPassenger = Integer.valueOf(scheduleMasterRequest.getNoOfPasenger());
		
		List<OperatingFlight> allScheduled = new ArrayList<>();
		 
		DateTime estimatedArrivalTime = DateTime.parse(scheduleMasterRequest.getScheduleActiveStartDate(), 
                DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss"));
		for(int i=0;i<=tripCount;i++) {
			
			
			OperatingFlight operatingFlight =  new OperatingFlight();
			List<Employee> empList = new ArrayList<>();
			List<Passenger> passngerList = new ArrayList<>();
			
			List<Passenger> passengetList =  passengerRepository.findAll();
			List<Employee> emplyeeList =  employeeRepository.findAll();
			
			if(noOfCrew>emplyeeList.size()) {
				sMasterUpdate.setDatauploadstatus("Failed");
				sMasterUpdate.setDatauploadstatus("Unable to create Schedular. Probable reson for fail noOfCrew can be max at :"+emplyeeList.size());
				scheduleMasterRepository.save(sMasterUpdate);
				return null;
				//throw new InternalException("noOfCrew can be max at :"+emplyeeList.size());
				
			}
			
			List<Integer> crewListElemet =  getRandomNumber(noOfCrew,emplyeeList.size());
			
			for(Integer item:crewListElemet) {
				
				Employee emp = emplyeeList.get(item);
				emp.setRole(getRandomString());
				empList.add(emp);
			}
			
			if(noOfPassenger>passengetList.size()) {
				sMasterUpdate.setDatauploadstatus("Failed");
				sMaster.setDatauploadstatus("Unable to create Schedular.Probable reson for fail noOfPassenger can be max at :"+passengetList.size());
				scheduleMasterRepository.save(sMaster);
				throw new InternalException("noOfPassenger can be max at :"+passengetList.size());
			}
			List<Integer> passListElement =  getRandomNumber(noOfPassenger,passengetList.size());
			
			List<PnrDetails> pnrDtlList = new ArrayList<>() ;
			for(Integer item:passListElement) {
				
				PnrDetails pnrDtl = new PnrDetails();
				pnrDtl.setPassId(passengetList.get(item).getPassengerId());
				pnrDtl.setPnrNumber("SCHE"+scheduleMasterRequest.getCarrierCode()+getRandomNumber(4,1000).get(0)+passengetList.get(item).getPassengerId()+scheduleMasterRequest.getCarrierCode());
				Passenger passenger  =  passengetList.get(item);
				passenger.setPnr(pnrDtl.getPnrNumber());
				
				passenger.setSeatNumber(getRandomStringSeatNumber()+item);
				passenger.setPclass(getRandomStringClass());
				
				passngerList.add(passengetList.get(item));
				
				
				pnrDtlList.add(pnrDtl);
				//pushDataInkafka(kafkaPnrString);
			}
			
			operatingFlight.setPnrDetails(pnrDtlList);
			operatingFlight.setEmployee(empList);
			operatingFlight.setPassenger(passngerList);
			operatingFlight.setFlightNumber(scheduleMasterRequest.getScheduleForFlight()+"_"+Integer.toString(i));
			DepartureInfo departureInfo =  new DepartureInfo();
			departureInfo.setEstimatedDateTime(estimatedArrivalTime.toString());
			estimatedArrivalTime = estimatedArrivalTime.plusMinutes(Integer.valueOf(scheduleMasterRequest.getInterval()));
			departureInfo.setGateStatus("Open");
			departureInfo.setGate(i*10+"A");
			operatingFlight.setCarrierCode(scheduleMasterRequest.getCarrierCode());
			operatingFlight.setDepartureInfo(departureInfo);
			operatingFlight.setSource(scheduleMasterRequest.getScheduleSource());			
			operatingFlight.setDestination(scheduleMasterRequest.getScheduleDestination().get(getRandomNumber(1,scheduleMasterRequest.getScheduleDestination().size()).get(0)));
			operatingFlight.setBoardingStatus(scheduleMasterRequest.getBoardingStatus());
			allScheduled.add(operatingFlight);			
		}
		
		sMasterUpdate.setInterval(scheduleMasterRequest.getInterval());
		sMasterUpdate.setScheduleInterval(scheduleMasterRequest.getScheduleInterval());
		sMasterUpdate.setNoOfPasenger(scheduleMasterRequest.getNoOfPasenger());
		sMasterUpdate.setNoOfPilot(scheduleMasterRequest.getNoOfPilot());
		sMasterUpdate.setOperatingFlight(allScheduled);
		sMasterUpdate.setNoOfCrew(scheduleMasterRequest.getNoOfCrew());
		sMasterUpdate.setNoOfSchduler(String.valueOf(allScheduled.size()) );
		sMasterUpdate.setScheduleId("SCHE-"+scheduleMasterRequest.getCarrierCode()+getRandomNumber(1,1000).get(0));
		sMasterUpdate.setDatauploadstatus("Available");
		//sMaster.setScheduleDestination(scheduleMasterRequest.getScheduleDestination());
		scheduleMasterRepository.save(sMasterUpdate);
		
		//return sMaster.getScheduleId();
		
		//List<PnrDetailsKafka> PnrDetailsList = new ArrayList<>();
		
		for(OperatingFlight operatingFlight : sMasterUpdate.getOperatingFlight()) {
			ScdedularDetails scdedularDetails = new ScdedularDetails();
			
			scdedularDetails.setSchedularId(operatingFlight.getId());
			
			List<PnrDetails> pnrDtl  = operatingFlight.getPnrDetails();
			List<PnrDetailsKafka> pnrkafkaList =  new ArrayList<>();
			for(int i=0;i<pnrDtl.size();i++) {
				PnrDetails pnrDetails = pnrDtl.get(i);
				PnrDetailsKafka pnrDetailskfk = new PnrDetailsKafka();
				pnrDetailskfk.setId(pnrDetails.getId());
				pnrDetailskfk.setPassengerId(pnrDetails.getPassId());
				pnrDetailskfk.setAssignedPNR(pnrDetails.getPnrNumber());
				pnrkafkaList.add(pnrDetailskfk);
			}
			scdedularDetails.setPnrDetails(pnrkafkaList);
			KakfaModelPnr kakfaModelPnr = new KakfaModelPnr();			
			kakfaModelPnr.setScdedularPnrDetails(scdedularDetails);
			ObjectMapper Obj = new ObjectMapper(); 
			
			try {
			String jsonStr = Obj.writeValueAsString(kakfaModelPnr); 
		
			pushDataInkafka(jsonStr);
			}
			catch(Exception exx)
			{
				exx.printStackTrace();
			}
		}
			
		
		
		
		
		String kakfaPushData = "{\"id\":\""+sMasterUpdate.getId()+"\",\"noOfScheduler\":\""+String.valueOf(sMasterUpdate.getOperatingFlight().size())+"\"}";
		//pushDataInkafka(kakfaPushData);
		
		
		
		
		return null;
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
                DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss"));
		//05/02/2020 20:27:05
		DateTime startdate = DateTime.parse(startDateTime, 
                DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss"));
		
		Interval interval = new Interval(startdate,enddate);
		
		return (interval.toDurationMillis()/60000);
	}

	@Override
	public List<ScheduleMasterRequest> AllScheduleMaster() {
		List<ScheduleMaster> allScheduler  = scheduleMasterRepository.findAll();
		List<ScheduleMasterRequest> returnList = new ArrayList<>();
		for(ScheduleMaster scheduleMaster : allScheduler) {
			ScheduleMasterRequest sMaster = new ScheduleMasterRequest();
			sMaster.setCarrierCode(scheduleMaster.getCarrierCode());
			sMaster.setScheduleActiveEndDate(scheduleMaster.getScheduleActiveEndDate());
			sMaster.setScheduleActiveStartDate(scheduleMaster.getScheduleActiveStartDate());
			sMaster.setNoOfCrew(scheduleMaster.getNoOfCrew());
			sMaster.setNoOfSchedular(scheduleMaster.getNoOfSchduler());
			List<String> allAirport =  new ArrayList<>();
			if(scheduleMaster.getScheduleDestination()!=null) {
				for(Airport airport:scheduleMaster.getScheduleDestination()) {
					allAirport.add(airport.getIataCode());
				}
			}
			
			sMaster.setScheduleSource(scheduleMaster.getScheduleSource());
			sMaster.setScheduleDestination(allAirport);
			sMaster.setNoOfPilot(scheduleMaster.getNoOfPilot());
			sMaster.setNoOfCrew(scheduleMaster.getNoOfCrew());
			sMaster.setInterval(scheduleMaster.getInterval());
			sMaster.setScheduleInterval(scheduleMaster.getScheduleInterval());
			sMaster.setCarrierCode(scheduleMaster.getCarrierCode());
			sMaster.setScheduleForFlight(scheduleMaster.getScheduleForFlight());
			sMaster.setScheduleMasterId(scheduleMaster.getId());
			sMaster.setNoOfPasenger(scheduleMaster.getNoOfPasenger());
			sMaster.setInterval(scheduleMaster.getInterval());
			sMaster.setScheduleInterval(scheduleMaster.getScheduleInterval());
			sMaster.setDatauploadstatus(scheduleMaster.getDatauploadstatus());
			returnList.add(sMaster);
		}
		return returnList;
	}

	public static void main(String[] args) {
		System.out.println("Started ");
		System.out.println(getRandomNumber(2,5));
		System.out.println("ended");
	}

	@Override
	public UserAuthResponse Signup(UserAuthRequest userAuthres) {
		
		List<UserAuth> allusers  = userAuthRepository.findAll();
		
		for(int i=0;i<allusers.size();i++) {
			if(userAuthres.getUsername().equalsIgnoreCase(allusers.get(i).getName())) {
				
				UserAuthResponse userAuthResponse= new UserAuthResponse();
				userAuthResponse.setStatus("1");
				userAuthResponse.setMessage("User name already exist");
				return userAuthResponse;
				//throw new InternalException("User name already exist");
			}
			
			if(userAuthres.getEmailAddress().equalsIgnoreCase(allusers.get(i).getEmail())) {
				
				UserAuthResponse userAuthResponse= new UserAuthResponse();
				userAuthResponse.setStatus("1");
				userAuthResponse.setMessage("EmailAddress name already exist");
				return userAuthResponse;
				//throw new InternalException("Email address already exist");
			}
		}
		UserAuth userAuth =  new UserAuth();
		userAuth.setAirline(userAuthres.getAirlineId());
		userAuth.setEmail(userAuthres.getEmailAddress());
		userAuth.setName(userAuthres.getUsername());
		userAuth.setPassword(userAuthres.getPassword());
		
		userAuthRepository.save(userAuth);
		UserAuthResponse userAuthResponse= new UserAuthResponse();
		userAuthResponse.setMessage("User created");
		return userAuthResponse;
	}

	@Override
	public UserAuthResponse Signin(UserAuthRequest userAuth) {
		// TODO Auto-generated method stub
		boolean notMatched=false;
		List<UserAuth> allusers  = userAuthRepository.findAll();
		for(int i=0;i<allusers.size();i++) {
			if(allusers.get(i).getEmail().equalsIgnoreCase(userAuth.getEmailAddress())) {
				if(allusers.get(i).getPassword().equals(userAuth.getPassword())) {
					notMatched=true;
					UserAuthResponse userAuthResponse=new UserAuthResponse();
					userAuthResponse.setAirlineId(allusers.get(i).getAirline());
					userAuthResponse.setEmailAddress(allusers.get(i).getEmail());
					userAuthResponse.setUsername(allusers.get(i).getName());
					return userAuthResponse;
				}
			}
		}
		if(!notMatched) {
			UserAuthResponse userAuthResponse= new UserAuthResponse();
			userAuthResponse.setStatus("1");
			userAuthResponse.setMessage("Wrong user name or password");
			return userAuthResponse;
		}
		return null;
	}
	@Async("asyncExecutor")
	public String asyncC() {
		
		asyncMethodWithReturnType();
		
		return "all good";
		
	}
	
	
	
	@Async("asyncExecutor")
	public CompletableFuture<String> asyncMethodWithReturnType() {
	    System.out.println("Execute method asynchronously - "
	      + Thread.currentThread().getName());
	    try {
	        Thread.sleep(20000);
	       System.out.println("ALL DONE GOOD TO GO========================");
	    } catch (InterruptedException e) {
	        //
	    }
	 
	    return null;
	}

	@Override
	public List<OperatingFlight> AllScheduleMasterById(String schedulerId) {
		
		//List<Employee> employeeList= new ArrayList<>();
		//List<Passenger> passengerList= new ArrayList<>();
		// TODO Auto-generated method stub
		ScheduleMaster scheduleMaster = scheduleMasterRepositoryDAL.getSchedulerById(schedulerId);
		
		List<OperatingFlight> OperatingFlightList = scheduleMaster.getOperatingFlight();
		
		List<OperatingFlight> retrunOperatingFlightList = new ArrayList<>();
		
		for(int i=0;i<OperatingFlightList.size();i++) {

			OperatingFlight returnOperatingFlight = new OperatingFlight();
			
			returnOperatingFlight.setArrivalInfo(OperatingFlightList.get(i).getArrivalInfo());
			returnOperatingFlight.setBoardingStatus(OperatingFlightList.get(i).getBoardingStatus());
			returnOperatingFlight.setCarrierCode(OperatingFlightList.get(i).getCarrierCode());
			returnOperatingFlight.setDepartureInfo(OperatingFlightList.get(i).getDepartureInfo());
			returnOperatingFlight.setDestination(OperatingFlightList.get(i).getDestination());
			returnOperatingFlight.setId(OperatingFlightList.get(i).getId());
			returnOperatingFlight.setSource(OperatingFlightList.get(i).getSource());
			returnOperatingFlight.setMfClassName(OperatingFlightList.get(i).getMfClassName());
			returnOperatingFlight.setSource(OperatingFlightList.get(i).getSource());
			returnOperatingFlight.setBoardingStatus(OperatingFlightList.get(i).getBoardingStatus());
			returnOperatingFlight.setFlightNumber(OperatingFlightList.get(i).getFlightNumber());
			returnOperatingFlight.setMfClassName(OperatingFlightList.get(i).getMfClassName());
			
			OperatingFlight operatingFlight = OperatingFlightDAL.getoperatingFlight(OperatingFlightList.get(i).getId());
			//System.out.println("operatingFlight :"+operatingFlight.getEmployee().size());
			List<Employee> employeeList= new ArrayList<>();
			for(int j=0;j<operatingFlight.getEmployee().size();j++) {
				
				Optional<Employee> employee = employeeRepository.findById(operatingFlight.getEmployee().get(j).getId());
				employeeList.add(employee.get());
			}
			returnOperatingFlight.setEmployee(employeeList);
			List<Passenger> passengerList= new ArrayList<>();
			for(int j=0;j<operatingFlight.getPassenger().size();j++) {
				
				Optional<Passenger> passenger = passengerRepository.findById(operatingFlight.getPassenger().get(j).getId());
				passengerList.add(passenger.get());
			}
			returnOperatingFlight.setPassenger(passengerList);
			
			retrunOperatingFlightList.add(returnOperatingFlight);
		}
		

		return retrunOperatingFlightList;
		
	}

	@Override
	public OperatingFlight updateOpflight(String operatingFlightId, OperatingFlight operatingFlightReq) {
		
		try {
		operatingFlightRepositort.save(operatingFlightReq);
		}
		catch(Exception exx) {
			exx.printStackTrace();
		}
		return operatingFlightReq;
	}

	

static String getRandomString(){
        int r = (int) (Math.random()*5);
        String name = new String [] {"Pilot","Co-Pilot","Ground staff","Cabin crew","Air Hostess","Lead Air Hostess"}[r];
        return name;
    }

static String getRandomStringClass(){
    int r = (int) (Math.random()*3);
    String name = new String [] {"First Class","Business Class","Economy Class"}[r];
    return name;
}

static String getRandomStringSeatNumber(){
    int r = (int) (Math.random()*10);
    String name = new String [] {"A","B","C","D","E","F","G","H","I","J"}[r];
    return name;
}
}
