package com.riffert.web;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.riffert.textgroup.entity.Domain;
import com.riffert.textgroup.entity.Group;
import com.riffert.textgroup.service.DatabaseRequestService;

@Controller
public class GroupController
{
		@Autowired
		private DatabaseRequestService databaseRequestService;

		@RequestMapping(value="/addGroup")
		public String addGroup(Model model,
				@RequestParam(defaultValue="1")Domain domain,
				@RequestParam(defaultValue="")String groupname,
				@RequestParam(defaultValue="")String usergroupname)
		{		
				// checking if groupname already present
				// (user has added 2 or more times the same language)
				for(Group group:domain.getGroups())
				{
					if (group.getName().equals(groupname))
					{
						groupname = groupname + randomString(4);
					}
				}
			
				databaseRequestService.addGroup(domain, groupname, usergroupname );
			
				return "/";
		}
		
		private String randomString(int nlen)
		{
			char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
			StringBuilder sb = new StringBuilder();
			Random random = new Random();
			for (int i = 0; i < nlen; i++) {
			    char c = chars[random.nextInt(chars.length)];
			    sb.append(c);
			}
			return sb.toString();

		}
}
