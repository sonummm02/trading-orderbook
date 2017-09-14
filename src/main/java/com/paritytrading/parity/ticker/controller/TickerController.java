package com.paritytrading.parity.ticker.controller;
import com.paritytrading.parity.ticker.DisplayFormat;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TickerController {

	/*	@Autowired
	private DisplayFormat displayFormat;
	 */
	//	@Autowired
	// private	OrderBook book= new OrderBook() ;

	//long instrument = book.getInstrument();

	/*@PostMapping(value="/getInstrument",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public long getInstrument(@RequestBody Map<String,Object> data) {
		
		System.out.println("********************************inside get Instrument");
		System.out.println("data recieve from postman : "+data);
		DisplayFormat displayFormat = new DisplayFormat();
		System.out.println("counter value of DisplayFormat : "+displayFormat.getCounter());
		displayFormat.updateCounter();
		System.out.println("counter value of DisplayFormat : "+displayFormat.getCounter());
		return displayFormat.getCounter();
	}
@PostMapping(value="/h")
public int getCounter()
{
	System.out.println("inside get counter");
return 1;
}*/

	@RequestMapping(value="/hello", method=RequestMethod.GET)
	String show() {
		System.out.println("params ");
		return "hiiii sonu ";
	}
	/*String update() {

		return "";
	}*/



	/*
	@RequestMapping(value = "/process", 
		    method = RequestMethod.GET)
		public void process(@RequestBody Map<String, Object> payload) 
		    throws Exception {

		  System.out.println(payload);


		}*/


}
