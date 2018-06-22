package com.riffert.textgroup.database;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.DomaAbstractConfig;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.MysqlDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
public class DatabaseConfig
{

		public DataSource dataSource1()
		{
			System.out.println("Dbaccess::dataSource1()");

	        org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
	        ds.setDriverClassName("com.mysql.jdbc.Driver");
	        ds.setUrl("jdbc:mysql://localhost:3306/textgroup_test1");
	        ds.setUsername("root");
	        ds.setPassword("783183");
	        ds.setMaxActive(2); // max connections
	        ds.setMaxWait(2);
	        ds.setTimeBetweenEvictionRunsMillis(100);
	        ds.setMinEvictableIdleTimeMillis(100);
	        ds.setRemoveAbandonedTimeout(1);
	        ds.setRemoveAbandoned(true);
	        
	        Properties props = new Properties();
	        props.setProperty("characterEncoding", "UTF-8");
	        props.setProperty("useUnicode", "true");
	        ds.setDbProperties(props);
	        
	        return ds;
		    
		}

		public DataSource dataSource2()
		{
			System.out.println("Dbaccess::dataSource2()");
			
	        org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
	        ds.setDriverClassName("com.mysql.jdbc.Driver");
	        ds.setUrl("jdbc:mysql://localhost:3306/textgroup_test2");
	        ds.setUsername("root");
	        ds.setPassword("783183");
	        ds.setDefaultAutoCommit(true);
	        //ds.setUseDisposableConnectionFacade(true);// test
	        ds.setMaxActive(2);
	        ds.setMaxWait(2);
	        ds.setTimeBetweenEvictionRunsMillis(100);
	        ds.setMinEvictableIdleTimeMillis(100);
	        ds.setRemoveAbandonedTimeout(1);
	        ds.setRemoveAbandoned(true);
	        
	        Properties props = new Properties();
	        props.setProperty("characterEncoding", "UTF-8");
	        props.setProperty("useUnicode", "true");
	        ds.setDbProperties(props);	        
	        
	        
	        return ds;
		}
	
		
	    @Bean
	    public DynamicRoutingDataSourceResolver dataSource()
	    {
	        DynamicRoutingDataSourceResolver resolver = new DynamicRoutingDataSourceResolver();

	        Map<Object, Object> dataSources = new HashMap(); //Maps.newHashMap();
	        dataSources.put("dataSource1", dataSource1());
	        dataSources.put("dataSource2", dataSource2());

	        resolver.setTargetDataSources(dataSources);

	        return resolver;
	    }

	    @Bean
	    public PlatformTransactionManager transactionManager() {
	        return new DataSourceTransactionManager(dataSource());
	    }

	    //@Bean
	    public Dialect dialect() {
	        return new MysqlDialect();
	    }

	    @Bean
	    public Config domaConfig() {
	        return new DomaAbstractConfig() {
	            @Override
	            public Dialect getDialect() {
	                return dialect();
	            }

	            @Override
	            public DataSource getDataSource() {
	                return new TransactionAwareDataSourceProxy(dataSource());
	            }
	        };
	    }

}
