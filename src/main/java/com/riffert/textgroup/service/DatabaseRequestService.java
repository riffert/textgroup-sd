package com.riffert.textgroup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.riffert.textgroup.entity.Domain;
import com.riffert.textgroup.entity.Equivalence;
import com.riffert.textgroup.entity.Group;
import com.riffert.textgroup.entity.Language;
import com.riffert.textgroup.entity.Text;
import com.riffert.textgroup.handler.DatabaseHandler;


/*
 * This layer provides methods between user interface and the persistence layer
 */

@Service
public class DatabaseRequestService
{
		
		@Autowired
		private DatabaseHandler databaseHandler;
		
		public void removeEquivalence(Long equivalenceId)
		{
				databaseHandler.removeEquivalence(equivalenceId);	
		}
		
		public void removeGroup(Long groupId)
		{
				databaseHandler.removeGroup(groupId);
		}
		
		public List<Equivalence> getEquivalences(Domain domain)
		{
				return databaseHandler.getEquivalences(domain);
		}				
		
		public List<Long> getUserIds(Domain domain)
		{
			return databaseHandler.getUserIds(domain);
		}
		
		public void updateText(Long id,String text)
		{
				databaseHandler.updateText(id, text);
		}
		
		public void deleteText(Text text)
		{
				databaseHandler.deleteText(text);
		}
		
		public void createDomain(String domainName)
		{
				databaseHandler.createDomain(new Domain(domainName));
		}

		public void updateDomain(Domain domain)
		{
				databaseHandler.updateDomain(domain);
		}
		
		public List<Equivalence> getList(Equivalence equivalence)
		{
				List<Equivalence> listTexts = databaseHandler.getTextsEquivalence(equivalence);
				return listTexts;
		}
		
		public Page<Text> getPage(Group group,String keyword,int currentpage,int pagesize)
		{
				Page<Text> pageTexts = databaseHandler.search(keyword, group,currentpage, pagesize);
				return pageTexts;
		}
		
		public Equivalence getNewEquivalence(Group group)
		{
				return databaseHandler.createEquivalence(group.getDomain());
		}
		
		//-----------------------------------------------------------------------------//
		
		public void save(Equivalence equivalence, Domain domain)
		{
				databaseHandler.saveEquivalence(equivalence);
				databaseHandler.saveDomain(domain);
		}
		
		
		public boolean addText(Group group,Text text)
		{
				Equivalence equivalence = databaseHandler.createEquivalence(group.getDomain());
				databaseHandler.addText(text, equivalence, group);
				return true;
		}

		public boolean addText(Text text,Equivalence equivalence,Group group)
		{
				databaseHandler.addText(text, equivalence, group);
				return true;
		}

		
		public Page<Language> getLanguages(int currentpage,int pagesize)
		{
				return databaseHandler.getLanguages(currentpage,pagesize);
		}
		
		public Page<Language> getLanguagesByEnglishKeyword(String keyword,int currentpage,int pagesize)
		{
				return databaseHandler.getLanguagesByEnglishKeyword(keyword,currentpage,pagesize);
		}
		
		public void addGroup(Domain domain,String groupName, String userGroupName)
		{
				databaseHandler.addGroup(domain, new Group(groupName,userGroupName));
		}

		public List<Group> getGroups(Domain domain, Group group)
		{
				if ( domain == null) return null;
			
				List<Group> groups = databaseHandler.getDomain(domain).getGroups();

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
		
		public List<Domain> getDomains(Domain domain)
		{
				List<Domain> domains = databaseHandler.getDomains();
				
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
		
		
		
		
		
}
