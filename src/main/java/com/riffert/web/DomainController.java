package com.riffert.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.riffert.textgroup.entity.Domain;
import com.riffert.textgroup.service.DatabaseRequestService;


@Controller
public class DomainController
{
		@Autowired
		private DatabaseRequestService databaseRequestService;

		@RequestMapping(value="/addDomain")
		public String inputDomain()
		{		
				return "addDomain";
		}
		
		@RequestMapping(value="/createDomain")
		public String createDomain(@RequestParam(defaultValue="")String domain)
		{		
				if ( !domain.equals("") ) {
					databaseRequestService.createDomain(domain);
				}
				
				return "redirect:/";
		}
		
		@RequestMapping(value="/modifyDomain")
		public String modifyDomain(Model model,@RequestParam(defaultValue="")Domain domain)
		{		
				model.addAttribute("domain", domain);
				
				return "modifyDomain";
		}
		
		@RequestMapping(value="/updateDomain")
		public String updateDomain(@RequestParam(defaultValue="")Domain domain,
								   @RequestParam(defaultValue="")String newName)
		{		
				if ( !newName.equals("") )
				{
					System.out.println("update : "+domain.getName()+" to "+newName);
					
					domain.setName(newName);
					databaseRequestService.updateDomain(domain);
				}
				
				return "redirect:/?domain="+domain.getId();
		}
		

}
