package com.shiva.config;

import java.util.Properties;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.shiva" })
@PropertySource(value = { "classpath:application.properties" })
public class HibernateConfig {
	@Autowired
	private Environment env;

	/*public BankAppDataSource bankAppDataSource() {
		BankAppDataSource bankAppDataSource = new BankAppDataSource();
		bankAppDataSource.setDriverClassName("xzvc");
		bankAppDataSource.setUrl("dvczx");
		bankAppDataSource.setUsername("xczv");
		bankAppDataSource.setPassword("cxvzx");
		// Query validation should not take place while connection is idle.
		bankAppDataSource.setTestWhileIdle(false);
		// To validate connection before it is handed out to the application.
		bankAppDataSource.setTestOnBorrow(true);
		// for connection validation it should not be null.
		bankAppDataSource.setValidationQuery("SELECT 1");
		// validation should not take place after a connection is returned to the pool.
		bankAppDataSource.setTestOnReturn(false);
		bankAppDataSource.setValidationInterval(30000);
		// sleep time in between validations in milliseconds
		bankAppDataSource.setTimeBetweenEvictionRunsMillis(30000);
		// The maximum number of active connections that can be allocated from this pool
		// at the same time.
		bankAppDataSource.setMaxActive(40);
		bankAppDataSource.setMaxIdle(40);
		// Set the number of connections that will be established when the connection
		// pool is started
		bankAppDataSource.setInitialSize(10);
		// The maximum number of milliseconds that the pool will wait (when there are no
		// available
		// connections and the getMaxActive() has been reached) for a connection to be
		// returned before
		// throwing an exception.
		bankAppDataSource.setMaxWait(30000);
		// The time in seconds before a connection can be considered abandoned.
		bankAppDataSource.setRemoveAbandonedTimeout(60);
		// The minimum amount of time an object must sit idle in the pool before it is
		// eligible for eviction.
		bankAppDataSource.setMinEvictableIdleTimeMillis(30000);
		// The minimum number of established connections that should be kept in the pool
		// at all times.
		bankAppDataSource.setMinIdle(10);
		// flag to set if stack traces should be logged for application code which
		// abandoned a Connection
		bankAppDataSource.setLogAbandoned(true);
		// remove abandoned connections if they exceed the removeAbandonedTimout.
		bankAppDataSource.setRemoveAbandoned(true);
		bankAppDataSource.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
				+ "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer;"
				+ "org.apache.tomcat.jdbc.pool.interceptor.ResetAbandonedTimer");

		return bankAppDataSource;
	}
*/
	@Bean
	public DataSource securityDataSource() {
		 
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		  dataSource.setDriverClassName(env.getProperty("mysql.driverClassName"));
		  dataSource.setUrl(env.getProperty("mysql.url"));
		  dataSource.setUsername(env.getProperty("mysql.username"));
		  dataSource.setPassword(env.getProperty("mysql.password")); 
		  return dataSource;
		
		
		/*
		 * EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		 * EmbeddedDatabase db = builder .setType(EmbeddedDatabaseType.H2) //.H2 or
		 * .DERBY .build(); return db;
		 */

	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(securityDataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.bank.model" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	public Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
		properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
		return properties;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

}
