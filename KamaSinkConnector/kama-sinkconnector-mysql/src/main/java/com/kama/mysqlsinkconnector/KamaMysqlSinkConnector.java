package com.kama.mysqlsinkconnector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigException;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.sink.SinkConnector;

public class KamaMysqlSinkConnector extends SinkConnector {
	
	public static final String VERSION = "1.0.0";

	private KamaMysqlSinkConnectorConfig connectorConfig;

	@Override
	public String version() {
		return VERSION;
	}

	@Override
	public void start(Map<String, String> props) {
		try {
			connectorConfig = new KamaMysqlSinkConnectorConfig(props);
		} catch (ConfigException e) {
			throw new ConnectException("Couldn't start KamaMysqlSinkConnector due to configuration error", e);
		}
	}

	@Override
	public Class<? extends Task> taskClass() {
		return KamaMysqlSinkTask.class;
	}

	@Override
	public List<Map<String, String>> taskConfigs(int maxTasks) {
		List<Map<String, String>> taskConfigs = new ArrayList<>(maxTasks);
		for (int i = 0; i < maxTasks; i++) {
			Map<String, String> taskProps = new HashMap<>(4);
			taskProps.put(KamaMysqlSinkConnectorConfig.MYSQL_URL, connectorConfig.getMysqlUrl());
			taskProps.put(KamaMysqlSinkConnectorConfig.MYSQL_USER, connectorConfig.getMysqlUser());
			taskProps.put(KamaMysqlSinkConnectorConfig.MYSQL_PASSWORD, connectorConfig.getMysqlPassword());
			taskProps.put(KamaMysqlSinkConnectorConfig.TABLE_NAME, connectorConfig.getTableName());
			taskConfigs.add(taskProps);
		}
		return taskConfigs;
	}

	@Override
	public void stop() {
		return;
	}

	@Override
	public ConfigDef config() {
		return KamaMysqlSinkConnectorConfig.config();
	}

}
