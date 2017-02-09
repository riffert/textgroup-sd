package com.riffert.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.riffert.bootstrap.Nav;
import com.riffert.textgroup.entity.Domain;
import com.riffert.textgroup.entity.Language;
import com.riffert.textgroup.service.DatabaseRequestService;

@Controller
public class LanguageController
{
		@Autowired
		private DatabaseRequestService databaseRequestService;
		
		@RequestMapping(value="/chooseLang")
		public String chooseLanguage(Model model,
				@RequestParam(defaultValue="1")Domain domain,
				@RequestParam(defaultValue="0")int currentpage,
				@RequestParam(defaultValue="eng")String groupname,
				@RequestParam(defaultValue="")String keyword,
				@RequestParam(defaultValue="")String dest,
				@RequestParam(defaultValue="10")int pagesize)
				
		{		
				//Page<Language> languages = databaseRequestService.getLanguages(currentpage, pagesize);
			
				Page<Language> languages = databaseRequestService.getLanguagesByEnglishKeyword(keyword,currentpage, pagesize);
				
				Nav[] navs = null;
				
				if ( languages != null )
				{
						int pagesCount = languages.getTotalPages();
						
						navs = new Nav[pagesCount];
						for (int i=0;i<pagesCount;i++)
							navs[i] = new Nav(i,currentpage);
				}
				
				model.addAttribute("languages", languages);
	
				model.addAttribute("domain",domain.getId());
				model.addAttribute("currentpage", currentpage);
				model.addAttribute("keyword",keyword);
				model.addAttribute("pagesize",pagesize);
				model.addAttribute("groupname",groupname);
				model.addAttribute("dest",dest);
				
				model.addAttribute("navs", navs);			
				
				return "chooseLang";
		}			

}
