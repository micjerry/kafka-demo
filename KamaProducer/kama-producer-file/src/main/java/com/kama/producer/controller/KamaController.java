package com.kama.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kama.producer.KamaProducer;

@RestController
public class KamaController {
	
	@Autowired
	private KamaProducer producer;
	
	@RequestMapping(value = "/static/sendfile", method = RequestMethod.POST)
	public String sendfile(@RequestBody SendFileMessage message) {
		producer.send(message.getFilename());
		return "OK";
	}

}
