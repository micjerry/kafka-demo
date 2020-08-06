package com.kama.mysqlsinkconnector.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KamaMysqlServiceImpl implements KamaMysqlService {
	private static final int FIELDS_NUMBER = 11;

	private static final String KAMA_SQL = "INSERT INTO kama_record(dev_number, imsi, imei, tmsi, longitude, latitude, rtime, operid, phone, provinceid, cityid) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

	private static Logger logger = LoggerFactory.getLogger(KamaMysqlServiceImpl.class);

	private KamaMysqlConnectionPool connectionPool = null;
	
	private String mysql_url = null;
	
	private String mysql_user = null;
	
	private String mysql_password = null;
	
	public KamaMysqlServiceImpl(String url, String user, String pass) {
		this.mysql_url = url;
		this.mysql_user = user;
		this.mysql_password = pass;
	}

	@Override
	public boolean process(Collection<String> recordsAsString) throws Exception {
		Connection conn = null;
		boolean result = true;

		try {
			conn = connectionPool.getDataSource().getConnection();
			conn.setAutoCommit(false);

			Iterator<String> iterator = recordsAsString.iterator();
			while (iterator.hasNext()) {
				String record = iterator.next();
				String[] fields = record.split(",");
				if (fields.length != FIELDS_NUMBER) {
					logger.error("invalid record: {} ", record);
					continue;
				}

				PreparedStatement preparedStatement = conn.prepareStatement(KAMA_SQL);
				for (int i = 1; i <= FIELDS_NUMBER; i++) {
					if (i == 7) {
						preparedStatement.setLong(i, Long.parseLong(fields[i - 1]));
					} else {
						preparedStatement.setString(i, fields[i - 1]);
					}
				}

				preparedStatement.execute();

			}
			
			conn.commit();
			conn.setAutoCommit(true);

		} catch (SQLException e) {
			if (conn != null) {
				conn.rollback();
				
			}
			logger.error("Can insert data", e);
			result = false;
		}
		
		return result;
	}

	@Override
	public void stop() {

	}

	@Override
	public void start() throws Exception {
		connectionPool = new KamaMysqlConnectionPool(mysql_url, mysql_user,
				mysql_password);
		connectionPool.setupPool();
	}

}
