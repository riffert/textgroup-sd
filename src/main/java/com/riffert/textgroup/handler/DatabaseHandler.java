package com.riffert.textgroup.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.riffert.textgroup.dao.DomainRepository;
import com.riffert.textgroup.dao.EquivalenceRepository;
import com.riffert.textgroup.dao.GroupRepository;
import com.riffert.textgroup.dao.LanguageRepository;
import com.riffert.textgroup.dao.TextRepository;
import com.riffert.textgroup.entity.Domain;
import com.riffert.textgroup.entity.Equivalence;
import com.riffert.textgroup.entity.Group;
import com.riffert.textgroup.entity.Language;
import com.riffert.textgroup.entity.Text;


@Component
@Transactional
public class DatabaseHandler
{
		@Autowired
		private DomainRepository domainRepository;
		
		@Autowired
		private GroupRepository groupRepository;
		
		@Autowired
		private EquivalenceRepository equivalenceRepository;
		
		@Autowired
		private TextRepository textRepository;
		
		@Autowired
		private LanguageRepository languageRepository;

		
		
		public DatabaseHandler() {}
		
		public Page<Language> getLanguages(int currentpage,int pagesize)
		{
				return languageRepository.findAll(new PageRequest(currentpage, pagesize));
		}
		
		public Page<Language> getLanguagesByEnglishKeyword(String keyword,int currentpage,int pagesize)
		{
				return languageRepository.searchByEnglishKeyword("%"+keyword+"%",new PageRequest(currentpage, pagesize));
		}

		
		public List<Equivalence> getTextsEquivalence(Equivalence equivalence)
		{
				ArrayList<Equivalence> al = new ArrayList<Equivalence>();
				al.add(new Equivalence());
			
				return al;
		}
		
		
		public void deleteText(Text text)
		{
				textRepository.delete(text);
		}
		
		public Page<Text> search(String keyword,Group group,int currentpage,int pagesize)
		{
				return textRepository.search("%"+keyword+"%", group,new PageRequest(currentpage, pagesize));
		}
		
		public List<Equivalence> getHoles(Domain domain)
		{
			return equivalenceRepository.getHoles(domain);
		}				
		
		public List<Long> getUserIdHoles(Domain domain)
		{
			return equivalenceRepository.getUserIdHoles(domain);
		}

		public Group getGroupByName(Domain domain, String groupname)
		{
				Group group = domain.getGroupByName(groupname);
				return group;
		}
		
		public DatabaseHandler(AnnotationConfigApplicationContext ctx)
		{
				domainRepository = ctx.getBean(DomainRepository.class);
				groupRepository = ctx.getBean(GroupRepository.class);
				equivalenceRepository = ctx.getBean(EquivalenceRepository.class);
				textRepository = ctx.getBean(TextRepository.class);
		}
		
		public void saveDomain(Domain domain)
		{
				domainRepository.saveAndFlush(domain);
		}
		
		public void saveEquivalence(Equivalence equivalence)
		{
				equivalenceRepository.saveAndFlush(equivalence);
		}
		
		public Equivalence createEquivalence(Domain domain)
		{
				Equivalence equivalence = new Equivalence();
				equivalenceRepository.save(equivalence);
				
				domain.add(equivalence);
				domainRepository.saveAndFlush(domain);
				equivalenceRepository.saveAndFlush(equivalence);
			
				return equivalence;
		}
		
		public Text addText(Text text,Equivalence equivalence,Group group)
		{
				textRepository.save(text);

				equivalence.add(text);
				group.add(text);
				
				equivalenceRepository.saveAndFlush(equivalence);
				groupRepository.saveAndFlush(group);
				textRepository.saveAndFlush(text);
				
				return text;
		}
		
		public void updateText(Long id,String text)
		{
				textRepository.updateText(id, text);
		}
		
		public void removeEquivalence(Long equivalenceId)
		{
				Equivalence equivalence = new Equivalence();
				equivalence.setId(equivalenceId);
			
				textRepository.remove(equivalence);
				equivalenceRepository.remove(equivalenceId);
		}


		public Group addGroup(Domain domain,Group group)
		{
				groupRepository.save(group);
				
				domain.add(group);
				domainRepository.saveAndFlush(domain);

				groupRepository.saveAndFlush(group);
				
				return group;
		}
		
		public Domain createDomain(Domain domain)
		{
				domainRepository.saveAndFlush(domain);
				return domain;
		}

		public List<Domain> getDomains()
		{
				List<Domain> found = domainRepository.findAll();
				
				return found;
				
		}
		
		public Domain getDomain(Domain domain)
		{
				return domainRepository.findOne((long)domain.getId());
		}
		

		public Map<String, List<Text>> getMap(long n)
		{
				Map<String, List<Text>> hm = new HashMap<String, List<Text>>();
				
				Domain domain = domainRepository.findOne((long) n);
				
				List<Group> groups = domain.getGroups();
				int qty = groups.size();
				
				System.out.println("Domain "+domain.getName()+" has "+qty+" languages");
				
				for (int i=0;i<qty;i++) 
				{
						Group group = groups.get(i);
						List<Text> texts = group.getTextsFrom1();
						hm.put(group.getName(), texts); System.out.println("["+group.getName()+"] loaded,"+(texts.size()-1)+" items");
				}
				
				return hm;
		}

}
