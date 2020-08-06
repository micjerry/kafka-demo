package com.kama.mysqlsinkconnector.db;

import javax.sql.DataSource;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

public class KamaMysqlConnectionPool {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	private GenericObjectPool gPool = null;

	private PoolingDataSource datasource = null;

	private String mysql_url;

	private String mysql_user;

	private String mysql_pass;

	public KamaMysqlConnectionPool(String url, String user, String pass) {
		this.mysql_url = url;
		this.mysql_user = user;
		this.mysql_pass = pass;
	}

	@SuppressWarnings("unused")
	public void setupPool() throws Exception{
		Class.forName(JDBC_DRIVER);

		gPool = new GenericObjectPool();
		gPool.setMaxActive(5);

		ConnectionFactory cf = new DriverManagerConnectionFactory(mysql_url, mysql_user, mysql_pass);

		PoolableConnectionFactory pcf = new PoolableConnectionFactory(cf, gPool, null, null, false, true);

		datasource = new PoolingDataSource(gPool);
	}

	public DataSource getDataSource() {
		return datasource;
	}

	public GenericObjectPool getConnectionPool() {
		return gPool;
	}
}
