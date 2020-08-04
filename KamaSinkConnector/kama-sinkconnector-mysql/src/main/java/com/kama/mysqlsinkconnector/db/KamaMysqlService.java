package com.kama.mysqlsinkconnector.db;

import java.util.Collection;

public interface KamaMysqlService {
	
	public boolean process(Collection<String> recordsAsString) throws Exception;
	
	public void start() throws Exception;
	
	public void stop();

}
