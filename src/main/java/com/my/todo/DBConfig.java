package com.my.todo;



import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;


@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class DBConfig {

	@Autowired
	Environment env;
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("db.DRIVER"));
        dataSource.setUrl(env.getProperty("db.URL"));
        dataSource.setUsername(env.getProperty("db.USER"));
        dataSource.setPassword(env.getProperty("db.PW"));
        return dataSource;
	}	
	
	@Bean
	public JdbcTemplate db(DataSource dataSource) {
		JdbcTemplate db = new JdbcTemplate(dataSource());
		return db;
	}
}
