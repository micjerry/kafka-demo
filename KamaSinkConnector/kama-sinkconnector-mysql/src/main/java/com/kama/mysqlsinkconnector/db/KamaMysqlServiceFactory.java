package com.kama.mysqlsinkconnector.db;

import com.kama.mysqlsinkconnector.KamaMysqlSinkConnectorConfig;

public class KamaMysqlServiceFactory {
	
	private static KamaMysqlServiceImpl service;
	
	public static KamaMysqlService getService(KamaMysqlSinkConnectorConfig config) {
		if (null == service) {
			synchronized(KamaMysqlServiceFactory.class) {
				if (null == service) {
					service = new KamaMysqlServiceImpl(config);
				}
			}
		}
		
		return service;
	}

}
