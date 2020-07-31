package com.kama.producer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class KamaProducer {
	private static final Logger LOGGER =
		      LoggerFactory.getLogger(KamaProducer.class);
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public void send(String filename) {
		LOGGER.info("sending file='{}'", filename);
		
		try {
			File kamaFile = new File(filename);
			Scanner kamaReader = new Scanner(kamaFile);
			while (kamaReader.hasNextLine()) {
				kafkaTemplate.send("kama-record", kamaReader.nextLine());
			}
			
			kamaReader.close();
		} catch (FileNotFoundException e) {
			LOGGER.error("file='{}' not found", filename);
		}
		
	}
}
