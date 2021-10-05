package com.banqueMisr.champion.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan("com.banqueMisr.champion")
@EnableTransactionManagement
@Configuration
public class DataBaseConfiguration {
	@Bean(destroyMethod = "")
	public DataSource getLocalDataSource() throws NamingException {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		//dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/paticipant");
		dataSource.setUsername("root");
		dataSource.setPassword("welcome1");
		return dataSource;

	}
}
