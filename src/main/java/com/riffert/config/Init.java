package com.riffert.config;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.riffert.textgroup.entity.Text;
import com.riffert.textgroup.handler.DomainHandler;


@ComponentScan(value="com.riffert.textgroup.entity,com.riffert.textgroup.dao,com.riffert.textgroup.handler")
public class Init implements ServletContextListener
{
		private static Map<String, List<Text>> hm ;
	
		public void contextInitialized(ServletContextEvent sce)
		{
				System.out.println("Init::contextInitialized() [start running]");
				
				AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JpaMySqlConfig.class);
				DomainHandler domainHandler = new DomainHandler(ctx);
				hm = domainHandler.getMap(1); 
				
				System.out.println("size of map is "+hm.size());

				//ctx.close();
				
		}

		public void contextDestroyed(ServletContextEvent sce) {	}
		
		
		public static Map<String, List<Text>> getLanguagesMap() {
			return hm;
		}

		public static void setLanguagesMap(Map<String, List<Text>> hm) {
			Init.hm = hm;
		}
		
}


