package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.riffert.textgroup.dao.CustomRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories (repositoryBaseClass = CustomRepositoryImpl.class)
public class TextgroupApplication
{
		public static void main(String[] args)
		{
				SpringApplication.run(TextgroupApplication.class, args);
		}
}
