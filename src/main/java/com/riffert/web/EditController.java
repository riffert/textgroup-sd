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
import com.riffert.textgroup.handler.DatabaseHandler;
import com.riffert.textgroup.service.DatabaseRequestService;

@Controller
public class EditController
{
		@Autowired
		private DatabaseRequestService databaseRequestService;
		
		@Autowired
		private DatabaseHandler domainHandler;

		@RequestMapping(value="/add")
		public String add(Model model,
				@RequestParam(defaultValue="1")Domain domain,
				@RequestParam(defaultValue="1")Group group,
				@RequestParam(defaultValue="0")int currentpage)
		{
				List<Group> groups = databaseRequestService.getGroups(domain,group);
				
				model.addAttribute("domain", domain);
				model.addAttribute("group", group);
				model.addAttribute("currentpage", currentpage);
				model.addAttribute("groups", groups);
				
				return "add";
		}

		
		@RequestMapping(value="/remove")
		public String delete(Model model,
				@RequestParam(defaultValue="0",name="equivalenceId")Long equivalenceId)
		{
				if (equivalenceId != 0)
				{
					System.out.println("equivalence different de 0 : "+equivalenceId);
					domainHandler.removeEquivalence(equivalenceId);
				}
				
				return "/";
		}
		
		@RequestMapping(value="/edit")
		public String edit(Model model,@RequestParam(defaultValue="1")Equivalence equivalence,
				@RequestParam(defaultValue="0")int currentpage,
				@RequestParam(defaultValue="1")Domain domain)
		{
				List<Group> groups = databaseRequestService.getGroups(domain,new Group());
				List<Text> texts = equivalence.getTexts();
			
				for(Group grp:groups)
				{
						if (!isGroupPresent(grp,texts))
						{
							Text text = new Text("");
							text.setEquivalence(equivalence);
							text.setGroup(grp);
							text.setDomain(domain);
							grp.add(text);
							
							domainHandler.addText(text, equivalence, grp);
						}
				}
				
				texts = equivalence.getTexts();
				
				model.addAttribute("texts", texts);
				model.addAttribute("currentpage", currentpage);
				model.addAttribute("equivalence", equivalence);
				model.addAttribute("domain",domain);
				
				return "edit";
		}
		
		private boolean isGroupPresent(Group group,List<Text> texts)
		{
				for(Text text:texts)
					if (group == text.getGroup())
						return true;

				return false;
		}
		
	
		@RequestMapping(value="/save",method=RequestMethod.POST)
		public String save(Model model,@RequestParam Map<String, String> params,
						@RequestParam(defaultValue="1")Domain domain,
						@RequestParam(defaultValue="1")Group group,
						@RequestParam(defaultValue="0")int currentpage)
		{		
				Equivalence equivalence = databaseRequestService.getNewEquivalence(group);
				List<Group> groups = databaseRequestService.getGroups(domain,group);
				
				for (Group groop:groups)
				{
						//String name = groop.getName();
						String text = params.get(groop.getName());
						databaseRequestService.addText(groop, new Text(text),equivalence);
				}
				
				Long nextEquivalenceId = domain.getNextEquivalenceId();
				domain.incrementNextEquivalenceId();
				equivalence.setUserId(nextEquivalenceId);
				
				domainHandler.saveEquivalence(equivalence);
				domainHandler.saveDomain(domain);
			
				return "redirect:/?domain="+domain.getId()+"&currentpage="+currentpage;
		}

		@RequestMapping(value="/update",method=RequestMethod.POST)
		public String update(@RequestParam Map<String, String> params)
		{		
				System.out.println("EditController.update :");
			
				for (String key : params.keySet())
				{
						System.out.println("key : "+key+", value : "+params.get(key) );
					
						if ( !(key.equals("domain") || key.equals("currentpage")))
							databaseRequestService.updateText(Long.parseLong(key), params.get(key));
				}
				
				
				
				String currentpage = params.get("currentpage");
				String domain = params.get("domain");
				
				if (currentpage != null && domain != null)
					return "redirect:/?domain="+domain+"&currentpage="+currentpage;
				else
					return "redirect:/";
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
