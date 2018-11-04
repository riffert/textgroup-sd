package com.riffert.textgroup.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.riffert.textgroup.entity.Domain;
import com.riffert.textgroup.entity.Group;
import com.riffert.textgroup.entity.Text;
import com.riffert.textgroup.handler.DatabaseHandler;


/*
 * This class provides methods between user interface and the persistence layer
 */

@Service
public class DatabaseRequestService extends DatabaseHandler
{

		public Page<Text> getPage(Group group,String keyword,int currentpage,int pagesize)
		{
				Page<Text> pageTexts = search(group, keyword,currentpage, pagesize);
				return pageTexts;
		}
		
		public List<Group> getGroups(Domain domain, Group group)
		{
				if ( domain == null) return null;
			
				List<Group> groups = getDomain(domain).getGroups();

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
				List<Domain> domains = getDomains();
				
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
