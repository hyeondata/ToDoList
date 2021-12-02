package com.my.todo;



import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DBConfig {

	private static  String DRIVER = "com.mysql.jdbc.Driver"; //Connection�� ������ Ŭ������ �̸�
	private static  String URL = "JDBC:MYSQL://localhost:3306/todolist?useSSL=false"; //mysql ���� �ּ�
	private static  String USER = "root"; //����
	private static  String PW = "1234567890"; // ��й�ȣ

	
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
