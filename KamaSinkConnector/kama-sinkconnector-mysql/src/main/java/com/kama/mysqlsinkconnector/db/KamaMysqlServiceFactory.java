package com.kama.mysqlsinkconnector.db;

public class KamaMysqlServiceFactory {
	public static KamaMysqlService getService(String url, String user, String pass) {
		return new KamaMysqlServiceImpl(url, user, pass);
	}
}
