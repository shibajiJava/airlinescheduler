package com.ibm.airlineScheduler.masterapicontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.airlineScheduler.masterapiServices.MasterAPIServices;
import com.ibm.airlineScheduler.masterapimodel.Airport;
import com.ibm.airlineScheduler.masterapimodel.OperatingFlight;
import com.ibm.airlineScheduler.masterapimodel.ScheduleMaster;
import com.ibm.airlineScheduler.masterapimodel.UserAuthRequest;
import com.ibm.airlineScheduler.masterapimodel.UserAuthResponse;
import com.ibm.airlineScheduler.masterapimodel.dao.AirlineResponse;
import com.ibm.airlineScheduler.masterapimodel.dao.AirportResponse;
import com.ibm.airlineScheduler.masterapimodel.dao.ScheduleMasterRequest;
import com.ibm.airlineScheduler.masterapirepository.AirportDAL;
import com.ibm.airlineScheduler.masterapirepository.ScheduleMasterRepository;

@RestController
public class MasterDataCreationController {

	private  MasterAPIServices masterAPIServices;
	
	@Autowired
	private ScheduleMasterRepository scheduleMasterRepository;
	
	
	@Autowired
	private AirportDAL AirportDAL;
	
	public ScheduleMasterRequest ss =  new ScheduleMasterRequest();
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTempletae;
	
	private static final String TOPIC="dynamicflightevents";
	
	public MasterDataCreationController(MasterAPIServices masterAPIServices) {
		this.masterAPIServices=masterAPIServices;
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/getAllAirlines")
	public ResponseEntity<Object> getAllAirlines(){
		
		List<AirlineResponse> allAirline  = masterAPIServices.getAllAirlines();
		
			
		return new ResponseEntity<>(allAirline,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/getAllAirports")
	public ResponseEntity<Object> getAllAirport(){
		
		List<AirportResponse> allAirports  = masterAPIServices.getAirport();
		return new ResponseEntity<>(allAirports,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/getAllAirports/country/{country}")
	public ResponseEntity<List<AirportResponse>> getAllAirport(@PathVariable(value = "country")  String country){
		List<AirportResponse> allAirports  = masterAPIServices.getAirportBycountry(country);
		return new ResponseEntity<>(allAirports,HttpStatus.OK);
		
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/createSchedule")

	public ResponseEntity<Object> getAllAirport(@RequestBody ScheduleMasterRequest scheduleMasterRequest){
		ObjectMapper Obj = new ObjectMapper(); 
		
		try {
		String jsonStr = Obj.writeValueAsString(scheduleMasterRequest); 
		  
        // Displaying JSON String 
        System.out.println(jsonStr);
		}
		catch(Exception exx)
		{
			exx.printStackTrace();
		}
		boolean updateFlg = false;
		ScheduleMaster sMaster = new ScheduleMaster();
		sMaster.setCarrierCode(scheduleMasterRequest.getCarrierCode());
		sMaster.setScheduleActiveEndDate(scheduleMasterRequest.getScheduleActiveEndDate());
		sMaster.setScheduleForFlight(scheduleMasterRequest.getScheduleForFlight());
		sMaster.setScheduleSource(scheduleMasterRequest.getScheduleSource());
		sMaster.setScheduleActiveStartDate(scheduleMasterRequest.getScheduleActiveStartDate());
		sMaster.setInterval(scheduleMasterRequest.getInterval());
		sMaster.setScheduleInterval(scheduleMasterRequest.getScheduleInterval());
		sMaster.setNoOfPasenger(scheduleMasterRequest.getNoOfPasenger());
		sMaster.setNoOfPilot(scheduleMasterRequest.getNoOfPilot());
		sMaster.setDatauploadstatus("In Progress");
		scheduleMasterRequest.setDatauploadstatus("In Progress");
		sMaster.setScheduleId("SCHE-"+scheduleMasterRequest.getCarrierCode()+getRandomNumber(1,1000).get(0));
		sMaster.setNoOfCrew(scheduleMasterRequest.getNoOfCrew());
		sMaster.setBoardingStatus(scheduleMasterRequest.getBoardingStatus());
		
		List<Airport> destAirportList = new ArrayList<>();
		for(String code:scheduleMasterRequest.getScheduleDestination())
		{
			Airport airport  = AirportDAL.getAirportByCode(code);
			
			destAirportList.add(airport);
		}
		if(scheduleMasterRequest.getScheduleMasterId()!=null && !scheduleMasterRequest.getScheduleMasterId().isEmpty()) {
		sMaster.setId(scheduleMasterRequest.getScheduleMasterId());
		updateFlg=true;
		}
		sMaster.setScheduleDestination(destAirportList);
		scheduleMasterRepository.save(sMaster);
		
		String schedularID  = masterAPIServices.createScheduler(sMaster,scheduleMasterRequest,updateFlg);
		
		//scheduleMasterRscheduleMasterRequestequest.setScheduleMasterId(schedularID);
		scheduleMasterRequest.setScheduleMasterId(sMaster.getId());
		//kafkaTempletae.send(TOPIC,schedularID);
		return new ResponseEntity<>(scheduleMasterRequest,HttpStatus.OK);
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
	
	@CrossOrigin(origins = "*")
	@GetMapping("/getAllSchedule")
	public ResponseEntity<Object> getAllSchedule(){
		
		List<ScheduleMasterRequest> returnList  = masterAPIServices.AllScheduleMaster();
		return new ResponseEntity<>(returnList,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/getAllSchedule/{scheduleId}")
	public ResponseEntity<Object> getAllScheduleByScheduleId(@PathVariable(value = "scheduleId")  String scheduleId){
		
		List<OperatingFlight> operatingFlightList  = masterAPIServices.AllScheduleMasterById(scheduleId);
		return new ResponseEntity<>(operatingFlightList,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@PutMapping("/updateOperatingFligt/{operatingFlightId}")
	public ResponseEntity<Object> updateOperatingFlightById(@RequestBody OperatingFlight operatingFlight, @PathVariable(value = "operatingFlightId")  String operatingFlightId){
		
		OperatingFlight operatingFlightList  = masterAPIServices.updateOpflight(operatingFlightId,operatingFlight);
		return new ResponseEntity<>(operatingFlightList,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/signin")
	public ResponseEntity<Object> getSignin(@RequestBody UserAuthRequest userAuthRequest){
		
		UserAuthResponse userAuthResponse  = masterAPIServices.Signin(userAuthRequest);
		return new ResponseEntity<>(userAuthResponse,HttpStatus.OK);
	}
	
	
	@CrossOrigin(origins = "*")
	@PostMapping("/signup")
	public ResponseEntity<Object> getSignup(@RequestBody UserAuthRequest userAuthRequest){
		
		UserAuthResponse userAuthResponse  = masterAPIServices.Signup(userAuthRequest);
		return new ResponseEntity<>(userAuthResponse,HttpStatus.OK);
	}
	
	
	
	/*
	@CrossOrigin(origins = "http://localhost:9000")
	@PostMapping("/createSchedule")
	public ResponseEntity<String> getAllAirport(@RequestBody ScheduleMasterRequest scheduleMasterRequest){
		
		String schedularID  = masterAPIServices.createScheduler(scheduleMasterRequest);
		//kafkaTempletae.send(TOPIC,schedularID);
		return new ResponseEntity<>("success : schedularID : 12346DUMMY",HttpStatus.OK);
	}		*/
	
	@CrossOrigin(origins = "*")
	@PostMapping("/test")

	public ResponseEntity<Object> getTest(@RequestBody UserAuthRequest userAuthRequest){
		
		String userAuthResponse  = masterAPIServices.asyncC();
		return new ResponseEntity<>("All Good",HttpStatus.OK);
	}
	
	
	
	
	

	private ScheduleMasterRequest getServerdata() {
		
		ss.setScheduleMasterId("SCHE0001");
		return ss;
	}		
	
	
	
	
	
}
