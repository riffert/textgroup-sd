package com.riffert.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.dialect.Dialect;
//import org.seasar.doma.jdbc.DomaAbstractConfig;
//import org.seasar.doma.jdbc.dialect.MysqlDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DatabaseConfig
{
	
		@Bean
		public JpaVendorAdapter jpaVendorAdapter()
		{
				HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
				jpaVendorAdapter.setGenerateDdl(true);
				jpaVendorAdapter.setShowSql(true);
				
				return jpaVendorAdapter;
		}
	

		public DataSource dataSource1()
		{
				System.out.println("Dbaccess::dataSource1()");
				
				org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
				ds.setDriverClassName("com.mysql.jdbc.Driver");
				ds.setUrl("jdbc:mysql://localhost:3306/textgroup_test3");
				ds.setUsername("user1");
				ds.setPassword("pw1");
				ds.setMaxActive(2); // max connections
				ds.setMaxWait(2);
				ds.setTimeBetweenEvictionRunsMillis(100);
				ds.setMinEvictableIdleTimeMillis(100);
				ds.setRemoveAbandonedTimeout(1);
				ds.setRemoveAbandoned(true);
				
				Properties props = new Properties();
				props.setProperty("characterEncoding", "UTF-8");
				props.setProperty("useUnicode", "true");
				props.setProperty("autoReconnect", "true");
				props.setProperty("synchronizeOnSession", "true");
				ds.setDbProperties(props);
				
				return ds;
		}

		public DataSource dataSource2()
		{
				System.out.println("Dbaccess::dataSource2()");
				
				org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
				ds.setDriverClassName("com.mysql.jdbc.Driver");
				ds.setUrl("jdbc:mysql://localhost:3306/textgroup_test2");
				ds.setUsername("user2");
				ds.setPassword("pw2");
				ds.setDefaultAutoCommit(true);
				ds.setMaxActive(2);
				ds.setMaxWait(2);
				ds.setTimeBetweenEvictionRunsMillis(100);
				ds.setMinEvictableIdleTimeMillis(100);
				ds.setRemoveAbandonedTimeout(1);
				ds.setRemoveAbandoned(true);
				
				Properties props = new Properties();
				props.setProperty("characterEncoding", "UTF-8");
				props.setProperty("useUnicode", "true");
				props.setProperty("autoReconnect", "true");
				ds.setDbProperties(props);	        
				
				return ds;
		}
	
		
	    @Bean
	    public DynamicRoutingDataSourceResolver dataSource()
	    {
				DynamicRoutingDataSourceResolver resolver = new DynamicRoutingDataSourceResolver();
				
				Map<Object, Object> dataSources = new HashMap<Object, Object>();
				dataSources.put("dataSource1", dataSource1());
				dataSources.put("dataSource2", dataSource2());
				
				resolver.setTargetDataSources(dataSources);
				
				return resolver;
	    }

	    @Bean
	    public PlatformTransactionManager transactionManager() {
		        return new DataSourceTransactionManager(dataSource());
	    }

}
