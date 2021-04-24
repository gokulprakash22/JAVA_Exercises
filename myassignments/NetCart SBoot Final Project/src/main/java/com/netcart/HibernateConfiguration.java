package com.netcart;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({"com"})
@EnableTransactionManagement
@PropertySource(value = {"classpath:application.properties"})
public class HibernateConfiguration {

	@Autowired
	private Environment environment;
	
	private Properties getProperties() {
		Properties prop=new Properties();
		prop.put("hibernate.connection.username", environment.getRequiredProperty("spring.datasource.username"));
		prop.put("hibernate.connection.password", environment.getRequiredProperty("spring.datasource.password"));
		prop.put("hibernate.dialect", environment.getRequiredProperty("spring.jpa.properties.hibernate.dialect"));
		prop.put("hibernate.show_sql", environment.getRequiredProperty("spring.jpa.show-sql"));
		prop.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("spring.jpa.hibernate.ddl-auto"));
		return prop;
	}
	
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(environment.getRequiredProperty("spring.datasource.url"));
		return dataSource;
	}
	@Bean
	public LocalSessionFactoryBean  sessionFactory() {
		LocalSessionFactoryBean lsf=new LocalSessionFactoryBean();
		lsf.setHibernateProperties(getProperties());
		lsf.setPackagesToScan(new String[] {"com.model"});
		lsf.setDataSource(getDataSource());
		return lsf;
	}
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory factory) {
		HibernateTransactionManager txManager=new HibernateTransactionManager();
		txManager.setSessionFactory(factory);
		return txManager;
	}
}
