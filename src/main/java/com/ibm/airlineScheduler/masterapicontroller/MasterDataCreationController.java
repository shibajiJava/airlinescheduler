package com.ibm.airlineScheduler.masterapicontroller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ibm.airlineScheduler.masterapiServices.MasterAPIServices;
import com.ibm.airlineScheduler.masterapimodel.dao.AirlineResponse;
import com.ibm.airlineScheduler.masterapimodel.dao.AirportResponse;
import com.ibm.airlineScheduler.masterapimodel.dao.ScheduleMasterRequest;

@RestController
public class MasterDataCreationController {

	private  MasterAPIServices masterAPIServices;
	
	public ScheduleMasterRequest ss =  new ScheduleMasterRequest();
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTempletae;
	
	private static final String TOPIC="AIRLINE_SCHEDULER";
	
	public MasterDataCreationController(MasterAPIServices masterAPIServices) {
		this.masterAPIServices=masterAPIServices;
	}
	
	@CrossOrigin(origins = "http://localhost:9000")
	@GetMapping("/getAllAirlines")
	public ResponseEntity<List<AirlineResponse>> getAllAirlines(){
		System.out.println("===================GOT THE HIT=============");
		//List<AirlineResponse> allAirline  = masterAPIServices.getAllAirlines();
		
		List<AirlineResponse> allAirline = new ArrayList<>();
		AirlineResponse a1 = new AirlineResponse();
		a1.setAirline("Amadeus IT Group S.A. ");
		a1.setiATACode("1A");
		a1.setRegion("United States");
		allAirline.add(a1);
		
		AirlineResponse a2 = new AirlineResponse();
		a2.setAirline("Air India Express");
		a2.setiATACode("IX");
		a2.setRegion("India");
		allAirline.add(a2);
		
		AirlineResponse a3 = new AirlineResponse();
		a3.setAirline("Air Nippon");
		a3.setiATACode("EL");
		a3.setRegion("Japan");
		allAirline.add(a3);
		
		AirlineResponse a4 = new AirlineResponse();
		a4.setAirline("Air Canada");
		a4.setiATACode("AC");
		a4.setRegion("Canada");
		allAirline.add(a4);
		
		AirlineResponse a5 = new AirlineResponse();
		a5.setAirline("Air Sunshine");
		a5.setiATACode("YI");
		a5.setRegion("United States");
		allAirline.add(a5);
		
		AirlineResponse a6 = new AirlineResponse();
		a6.setAirline("Air India Limited");
		a6.setiATACode("AI");
		a6.setRegion("India");
		allAirline.add(a6);
		
		return new ResponseEntity<>(allAirline,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:9000")
	@GetMapping("/getAllAirports")
	public ResponseEntity<List<AirportResponse>> getAllAirport(){
		System.out.println("===================GOT THE HIT=============");
		//List<AirportResponse> allAirports  = masterAPIServices.getAirport();
		
		
		List<AirportResponse> allAirports =  new ArrayList<>();
		AirportResponse a1 = new AirportResponse();
		a1.setAirportId("AIRPORT_YVR");
		a1.setAirportName("Vancouver International Airport");
		a1.setCountry("Canada");
		a1.setIataCode("YVR");
		allAirports.add(a1);
		
		AirportResponse a2 = new AirportResponse();
		a2.setAirportId("AIRPORT_CCU");
		a2.setAirportName("Kolkata Airport");
		a2.setCountry("India");
		a2.setIataCode("CCU");
		allAirports.add(a2);
		return new ResponseEntity<>(allAirports,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:9000")
	@GetMapping("/getAllAirports/country/{country}")
	public ResponseEntity<List<AirportResponse>> getAllAirport(@PathVariable(value = "country")  String country){
		List<AirportResponse> allAirports  = masterAPIServices.getAirportBycountry(country);
		return new ResponseEntity<>(allAirports,HttpStatus.OK);
		
	}
	
	@CrossOrigin(origins = "http://localhost:9000")
	@PostMapping("/createSchedule")
	public ResponseEntity<ScheduleMasterRequest> getAllAirport(@RequestBody ScheduleMasterRequest scheduleMasterRequest){
		ss=scheduleMasterRequest;
		String schedularID  = masterAPIServices.createScheduler(scheduleMasterRequest);
		//kafkaTempletae.send(TOPIC,schedularID);
		return new ResponseEntity<>(getServerdata(),HttpStatus.OK);
	}
	
	
	@CrossOrigin(origins = "http://localhost:9000")
	@GetMapping("/getAllSchedule")
	public ResponseEntity<List<ScheduleMasterRequest>> getAllSchedule(){
		ScheduleMasterRequest s1 = new ScheduleMasterRequest();
		s1.setCarrierCode("111");
		s1.setInterval("10");
		s1.setNoOfCrew("10");
		s1.setNoOfPasenger("100");
		s1.setNoOfPilot("2");
		s1.setScheduleSource("CCU");
		s1.setScheduleMasterId("SCH001");
		
		
		ScheduleMasterRequest s2 = new ScheduleMasterRequest();
		s2.setScheduleMasterId("SCH001");
		s2.setCarrierCode("222");
		s2.setInterval("1");
		s2.setNoOfCrew("10");
		s2.setNoOfPasenger("100");
		s2.setNoOfPilot("2");
		s2.setScheduleSource("CCU");
		List ss = new ArrayList<>();
		ss.add(s1);
		ss.add(s2);
		//String schedularID  = masterAPIServices.createScheduler(scheduleMasterRequest);
		//kafkaTempletae.send(TOPIC,schedularID);
		return new ResponseEntity<>(ss,HttpStatus.OK);
	}
	
	
	/*
	@CrossOrigin(origins = "http://localhost:9000")
	@PostMapping("/createSchedule")
	public ResponseEntity<String> getAllAirport(@RequestBody ScheduleMasterRequest scheduleMasterRequest){
		
		String schedularID  = masterAPIServices.createScheduler(scheduleMasterRequest);
		//kafkaTempletae.send(TOPIC,schedularID);
		return new ResponseEntity<>("success : schedularID : 12346DUMMY",HttpStatus.OK);
	}		*/
	
	
	@CrossOrigin(origins = "http://localhost:9000")
	@PostMapping("/createScheduleaws")
	public ResponseEntity<ScheduleMasterRequest> getAllAirportServer(@RequestBody ScheduleMasterRequest scheduleMasterRequest){
		ss=scheduleMasterRequest;
		String schedularID  = masterAPIServices.createScheduler(scheduleMasterRequest);
		//kafkaTempletae.send(TOPIC,schedularID);
		return new ResponseEntity<>(getServerdata(),HttpStatus.OK);
	}

	private ScheduleMasterRequest getServerdata() {
		
		ss.setScheduleMasterId("SCHE0001");
		return ss;
	}		
	
	
	
	
	
}
