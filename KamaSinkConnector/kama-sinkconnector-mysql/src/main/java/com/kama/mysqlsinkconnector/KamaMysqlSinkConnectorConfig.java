package com.kama.mysqlsinkconnector;

import java.util.Map;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Type;

public class KamaMysqlSinkConnectorConfig extends AbstractConfig {
	public static final String MYSQL_URL = "mysql.url";
	private static final String MYSQL_URL_DOC = "Mysql URL to connect.";
	
	public static final String MYSQL_USER = "mysql.user";
	private static final String MYSQL_USER_DOC = "Mysql User to connect.";
	
	public static final String MYSQL_PASSWORD = "mysql.password";
	private static final String MYSQL_PASSWORD_DOC = "Mysql Password to connect.";
	
	public static final String TABLE_NAME = "mysql.table";
	private static final String TABLE_NAME_DOC = "Mysql table name.";
	
	public KamaMysqlSinkConnectorConfig(ConfigDef config, Map<String, String> parsedConfig) {
		super(config, parsedConfig);
	}
	
	public KamaMysqlSinkConnectorConfig(Map<String, String> parsedConfig) {
		this(config(), parsedConfig);
	}
	
	public static ConfigDef config() {
		return new ConfigDef()
				.define(MYSQL_URL, Type.STRING, Importance.HIGH, MYSQL_URL_DOC)
				.define(MYSQL_USER, Type.STRING, Importance.HIGH, MYSQL_USER_DOC)
				.define(MYSQL_PASSWORD, Type.STRING, Importance.HIGH, MYSQL_PASSWORD_DOC)
				.define(TABLE_NAME, Type.STRING, Importance.HIGH, TABLE_NAME_DOC);
	}
	
	public String getMysqlUrl() {
		return this.getString(MYSQL_URL);
	}
	
	public String getMysqlUser() {
		return this.getString(MYSQL_USER);
	}
	
	public String getMysqlPassword() {
		return this.getString(MYSQL_PASSWORD);
	}
	
	public String getTableName() {
		return this.getString(TABLE_NAME);
	}
	
}
