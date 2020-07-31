package com.kama.file;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

public class KamaConsumer {
	private final Logger logger = LoggerFactory.getLogger(KamaConsumer.class);

	
	@KafkaListener(topics = "kama-record")
	public void receive(String message) throws IOException {
		logger.info(String.format("#### -> Consumed message -> %s", message));
	}

}
