package com.my.todo;



import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DBConfig {

	private static  String DRIVER = "com.mysql.jdbc.Driver"; //Connection을 구현한 클래스의 이름
	private static  String URL = "JDBC:MYSQL://localhost:3306/todolist?useSSL=false"; //mysql 서버 주소
	private static  String USER = "root"; //계정
	private static  String PW = "1234567890"; // 비밀번호

	
	@Bean
	public DataSource DataSource() {
		BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PW);
        return dataSource;
	}	
}
