package com.riffert.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.riffert.option.FreeId;
import com.riffert.textgroup.entity.Domain;
import com.riffert.textgroup.entity.Group;
import com.riffert.textgroup.service.DatabaseRequestService;



@Controller
public class OptionsController
{
		@Autowired
		private DatabaseRequestService databaseRequestService;
	
		@RequestMapping(value="/options")
		public String options(Model model,
				@RequestParam(defaultValue="1")Domain domain,
				@RequestParam(defaultValue="1")Group group,
				@RequestParam(defaultValue="0")int currentpage)
		{
				List<FreeId> freeIds = new ArrayList<FreeId>();;
				
				System.out.println("domain -> "+domain.getId());
				System.out.println("domain -> "+domain.getName());
				
				List<Long> userIds = databaseRequestService.getUserIds(domain);
				
				for (Long nlong:userIds)
				{
					System.out.println("-> "+nlong);
				}
				
				Long nextEquivalenceId = domain.getNextEquivalenceId();

				boolean[] aPresent = new boolean[nextEquivalenceId.intValue()+1]; // TODO long
				
				for (int i=0;i<aPresent.length;i++)
					aPresent[i] = false;

				for (int i=0;i<userIds.size();i++)
				{
					Long userId = userIds.get(i);
					aPresent[userId.intValue()] = true;
				}
				
				for (int i=1;i<aPresent.length;i++)
					if (!aPresent[i])
							freeIds.add(new FreeId((long)i));

				model.addAttribute("freeIds",freeIds);
				model.addAttribute("domain", domain);
				model.addAttribute("group", group);
				model.addAttribute("currentpage", currentpage);
				return "options";
		}
}
