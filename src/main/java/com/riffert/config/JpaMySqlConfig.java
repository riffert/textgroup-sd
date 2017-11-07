package com.riffert.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages={"com.riffert.textgroup"})
@EnableTransactionManagement
public class JpaMySqlConfig
{
		@Bean(name="entityManagerFactory")
		public LocalContainerEntityManagerFactoryBean getEntityManagerFactory()
		{
			LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
			emf.setDataSource(getDataSource());
			emf.setPackagesToScan("com.riffert.textgroup.dao","com.riffert.textgroup.entity");
			
			JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
			emf.setJpaVendorAdapter(adapter);
			emf.setJpaProperties(getProperties());
			
			return emf;
		}
		
		@Bean(name="transactionManager")
		public PlatformTransactionManager getTransactionManager(EntityManagerFactory emf)
		{
			JpaTransactionManager manager = new JpaTransactionManager();
			manager.setEntityManagerFactory(emf);
			
			return manager;
		}
		
		@Bean
		public PersistenceExceptionTranslationPostProcessor getPostProcessor()
		{	
			return new PersistenceExceptionTranslationPostProcessor();
		}
	
		public DataSource getDataSource()
		{
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
			dataSource.setUrl("jdbc:mysql://localhost:3306/thedatabase");
			dataSource.setUsername("root");
			dataSource.setPassword("xd300%783");
			
			return dataSource;
		}
	
		private Properties getProperties()
		{
			Properties props = new Properties();
			props.setProperty("hibernate.hbm2ddl.auto", "validate");
			props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
			props.setProperty("hibernate.show_sql", "true");
			props.setProperty("hibernate.enable_lazy_load_no_trans", "true");
			
			return props;
		}

}
