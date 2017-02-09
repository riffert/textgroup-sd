package com.riffert.textgroup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riffert.textgroup.dao.LanguageRepository;
import com.riffert.textgroup.entity.Domain;
import com.riffert.textgroup.entity.Equivalence;
import com.riffert.textgroup.entity.Group;
import com.riffert.textgroup.entity.Language;
import com.riffert.textgroup.entity.Text;
import com.riffert.textgroup.handler.DomainHandler;
import com.riffert.textgroup.handler.TextHandler;


// conversion des string -> reference dans la présente couche

@Service
public class DatabaseRequestService
{
		@Autowired
		private TextHandler textHandler;
		
		@Autowired
		private DomainHandler domainHandler;
		
		@Autowired
		private LanguageRepository languageRepository; // TODO uniquement des handler ici
		
		public void deleteText(Text text)
		{
				textHandler.deleteText(text);
		}
		
		public void createDomain(String domainName)
		{
				domainHandler.createDomain(new Domain(domainName));
		}

		public List<Equivalence> getList(Equivalence equivalence)
		{
				List<Equivalence> listTexts = textHandler.getTextsEquivalence(equivalence);
				return listTexts;
		}
		
		public Page<Text> getPage(Group group,String keyword,int currentpage,int pagesize)
		{
				Page<Text> pageTexts = textHandler.search(keyword, group,currentpage, pagesize);
				return pageTexts;
		}
		
		public List<Domain> getDomains(Domain domain)
		{
				List<Domain> domains = domainHandler.getDomains();
				
				if ( domains != null )
				{
						boolean  bFound = false;
						
						for (int i=0;i<domains.size();i++)
						{
							Domain dom = domains.get(i);
							
							if ( dom == domain )
							{
								bFound = true;
								dom.setSelected("selected=\"selected\"");
							}
							else
								dom.setSelected("");
						}
						
						if ( !bFound && domains.size() > 0 )
							domains.get(0).setSelected("selected=\"selected\"");
						
				}
				
				
				return domains;
		}
		
		// TODO java 8 compliant
		public List<Group> getGroups(Domain domain, Group group)
		{
				if ( domain == null) return null;
			
				List<Group> groups = domainHandler.getDomain(domain).getGroups();

				if ( groups != null )
				{
						boolean  bFound = false;
						
						for (int i=0;i<groups.size();i++)
						{
							Group grp = groups.get(i);
							
							if ( grp == group )
							{
								bFound = true;
								grp.setSelected("selected=\"selected\"");
							}
							else
								grp.setSelected("");
						}
						
						if ( !bFound && groups.size() > 0 )
							groups.get(0).setSelected("selected=\"selected\"");
						
				}
				
				return groups;
		}
		
		
		public Equivalence getNewEquivalence(Group group)
		{
				return domainHandler.createEquivalence(group.getDomain());
		}
		
		public boolean addText(Group group,Text text)
		{
				Equivalence equivalence = domainHandler.createEquivalence(group.getDomain());
				domainHandler.addText(text, equivalence, group);
				return true;
		}

		public boolean addText(Group group,Text text,Equivalence equivalence)
		{
				domainHandler.addText(text, equivalence, group);
				return true;
		}

		
		public Page<Language> getLanguages(int currentpage,int pagesize)
		{
				return languageRepository.findAll(new PageRequest(currentpage, pagesize));
		}
		
		public Page<Language> getLanguagesByEnglishKeyword(String keyword,int currentpage,int pagesize)
		{
				return languageRepository.searchByEnglishKeyword("%"+keyword+"%",new PageRequest(currentpage, pagesize));
		}
		
		public void addGroup(Domain domain,String groupName, String userGroupName)
		{
				
				domainHandler.addGroup(domain, new Group(groupName,userGroupName));
		}
		
}
