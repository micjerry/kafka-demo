package com.kama.mysqlsinkconnector;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kama.mysqlsinkconnector.db.KamaMysqlService;
import com.kama.mysqlsinkconnector.db.KamaMysqlServiceFactory;

public class KamaMysqlSinkTask extends SinkTask {
	
	private static Logger logger = LoggerFactory.getLogger(KamaMysqlSinkTask.class);
	
	private KamaMysqlService service = null;;
	
	private KamaMysqlSinkConnectorConfig config = null;

	@Override
	public String version() {
		return KamaMysqlSinkConnector.VERSION;
	}

	@Override
	public void start(Map<String, String> props) {
		config = new KamaMysqlSinkConnectorConfig(props);
		service = KamaMysqlServiceFactory.getService(config.getMysqlUrl(), config.getMysqlUser(), config.getMysqlPassword());
		try {
			service.start();
		} catch (Exception e) {
			logger.error("Can not start kamamysqlservice", e);
			throw new ConnectException("Start failed");
		}
		
	}

	@Override
	public void put(Collection<SinkRecord> records) {
		try {
            Collection<String> recordsAsString = records.stream().map(r -> String.valueOf(r.value())).collect(Collectors.toList());
            if (!service.process(recordsAsString)) {
            	throw new ConnectException("process failed");
            } 
        }
        catch (Exception e) {
        	logger.error("Error while processing records", e);
        	throw new ConnectException(e);
        }
	}

	@Override
	public void stop() {
		 service.stop();	
	}

}
