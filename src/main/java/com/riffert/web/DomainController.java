package com.riffert.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.riffert.textgroup.service.DatabaseRequestService;


@Controller
public class DomainController
{
		@Autowired
		private DatabaseRequestService databaseRequestService;

		@RequestMapping(value="/inputDomain")
		public String inputDomain()
		{		
				return "inputDomain";
		}
		

		@RequestMapping(value="/createDomain")
		public String createDomain(@RequestParam(defaultValue="")String domain)
		{		
				if ( !domain.equals("") ) {
					databaseRequestService.createDomain(domain);
				}
				
				return "redirect:/";
		}

}
