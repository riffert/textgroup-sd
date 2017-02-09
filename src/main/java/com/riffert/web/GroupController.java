package com.riffert.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.riffert.textgroup.entity.Domain;
import com.riffert.textgroup.service.DatabaseRequestService;

@Controller
public class GroupController
{
		@Autowired
		private DatabaseRequestService databaseRequestService;

		@RequestMapping(value="/addGroup")
		public String addGroup(Model model,@RequestParam(defaultValue="")String groupname,
				@RequestParam(defaultValue="")String usergroupname,
				@RequestParam(defaultValue="")String domain)
		{		
				System.out.println("adding groupName : "+groupname);
				
				if ( groupname != "" )
				{
						databaseRequestService.addGroup(domain, groupname, usergroupname );
				}
			
				return "redirect:/?groupName="+groupname;
		}
}
