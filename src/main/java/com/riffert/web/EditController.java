package com.riffert.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.riffert.textgroup.entity.Domain;
import com.riffert.textgroup.entity.Equivalence;
import com.riffert.textgroup.entity.Group;
import com.riffert.textgroup.entity.Text;
import com.riffert.textgroup.service.DatabaseRequestService;

@Controller
public class EditController
{
		@Autowired
		private DatabaseRequestService databaseRequestService;

		@RequestMapping(value="/add")
		public String add(Model model,
				@RequestParam(defaultValue="1")Domain domain,
				@RequestParam(defaultValue="1")Group group)
		{
				List<Group> groups = databaseRequestService.getGroups(domain,group);
				
				model.addAttribute("domainid", domain.getId());
				model.addAttribute("groupid", group.getId());
				
				
				model.addAttribute("groups", groups);
				
				return "add";
		}
		
		@RequestMapping(value="/edit")
		public String edit(Model model,@RequestParam(defaultValue="1")Equivalence equivalence)
		{
				List<Text> texts = equivalence.getTexts();
				model.addAttribute("texts", texts);
				
				return "edit";
		}
		
	
		@RequestMapping(value="/save",method=RequestMethod.POST)
		public String save(Model model,@RequestParam Map<String, String> params,
						@RequestParam(defaultValue="1")Domain domain,
						@RequestParam(defaultValue="1")Group group)
		{		
			
				Equivalence equivalence = databaseRequestService.getNewEquivalence(group);
				List<Group> groups = databaseRequestService.getGroups(domain,group);
				
				for (Group groop:groups)
				{
						String name = groop.getName();
						String text = params.get(groop.getName());
						databaseRequestService.addText(groop, new Text(text),equivalence);
				}
			
				return "/";
		}

		@RequestMapping(value="/update",method=RequestMethod.POST)
		public String update(@RequestParam Map<String, String> params)
		{		
				for (String key : params.keySet())
				{
						//System.out.println("key : "+key+", value : "+params.get(key) );
						databaseRequestService.updateText(Long.parseLong(key), params.get(key));
				}
				
				return "/";
		}
		
		
		
//		@RequestMapping(value="/update",method=RequestMethod.POST)
//		public String update(@RequestParam Map<String, String> params)
//		{		
//				System.out.println("[update]");
//				
//				for (String key : params.keySet()) {
//				    System.out.println("key : "+key+", value : "+params.get(key) );
//				}
//				
//				
//				return "/";
//		}		
		
		/*
		@RequestMapping("/listItems")
		public @ResponseBody GridModel getUsersForGrid(@RequestParam Map<String, String> params) {
		    params.get("parametername");
		    // ...
		}
		*/		

}
