package com.my.todo;



import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import org.springframework.core.env.Environment;


@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class DBConfig {
/*
	@Value("${db.driver}")
	private String DRIVER;
	
	@Value("{db.URL}")
	private String URL;
	
	@Value("{db.USER}")
	private String USER;
	
	@Value("{db.PW}")
	private String PW;
	
		@Bean
	public DataSource DataSource() {
		BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PW);
        return dataSource;
	}	
*/
	@Autowired
	Environment env;
	
	@Bean
	public DataSource DataSource() {
		BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("db.DRIVER"));
        dataSource.setUrl(env.getProperty("db.URL"));
        dataSource.setUsername(env.getProperty("db.USER"));
        dataSource.setPassword(env.getProperty("db.PW"));
        return dataSource;
	}	
}
