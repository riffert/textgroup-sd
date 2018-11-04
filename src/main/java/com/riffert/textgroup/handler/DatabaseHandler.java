package com.riffert.textgroup.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

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
import com.riffert.textgroup.dao.SomethingRepository;
import com.riffert.textgroup.dao.TextRepository;
import com.riffert.textgroup.entity.Domain;
import com.riffert.textgroup.entity.Equivalence;
import com.riffert.textgroup.entity.Group;
import com.riffert.textgroup.entity.Language;
import com.riffert.textgroup.entity.Text;


/*
 * Persistence layer
 */

@Transactional
public class DatabaseHandler
{
	
		public DatabaseHandler() {}
	
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
		
		@Autowired
		private SomethingRepository somethingRepository;
		
		@Autowired
		private EntityManager entityManager;
		
		/*_____________________________________________________________________________*/
		
		
		public Page<Language> getLanguages(int currentpage,int pagesize)
		{
				return languageRepository.findAll(new PageRequest(currentpage, pagesize));
		}
		
		public Page<Language> getLanguagesByEnglishKeyword(String keyword,int currentpage,int pagesize)
		{
				return languageRepository.searchByEnglishKeyword("%"+keyword+"%",new PageRequest(currentpage, pagesize));
		}

		public void deleteText(Text text)
		{
				textRepository.delete(text);
		}
		
		public Page<Text> search(Group group,String keyword,int currentpage,int pagesize)
		{
				return textRepository.search("%"+keyword+"%", group,new PageRequest(currentpage, pagesize));
		}
		
		public List<Equivalence> getEquivalences(Domain domain)
		{
				return equivalenceRepository.getEquivalences(domain);
		}				
		
		public List<Long> getUserIds(Domain domain)
		{
				return equivalenceRepository.getUserIds(domain);
		}

		public DatabaseHandler(AnnotationConfigApplicationContext ctx)
		{
				domainRepository = ctx.getBean(DomainRepository.class);
				groupRepository = ctx.getBean(GroupRepository.class);
				equivalenceRepository = ctx.getBean(EquivalenceRepository.class);
				textRepository = ctx.getBean(TextRepository.class);
		}
		
		public void save(Equivalence equivalence, Domain domain)
		{
				saveEquivalence(equivalence);
				saveDomain(domain);
		}
		
		public void saveEquivalence(Equivalence equivalence)
		{
				equivalenceRepository.saveAndFlush(equivalence);
		}

		public void saveDomain(Domain domain)
		{
				domainRepository.saveAndFlush(domain);
		}
		
		public void updateText(Long id,String text)
		{
				textRepository.updateText(id, text);
		}
		
		
		public void removeEquivalence(Long equivalenceId)
		{
				textRepository.removeEquivalence(equivalenceId);
				equivalenceRepository.remove(equivalenceId);
		}
		
		public void removeGroup(Domain domain, Group group) throws InterruptedException
		{
				if ( group != null )
				{
						textRepository.removeGroup(group.getId());
						groupRepository.remove(group.getId());
						
						Domain dom = somethingRepository.findOne(domain.getId());
						somethingRepository.refresh(dom);
						
						System.out.println("dom.getId() : "+dom.getId());
						System.out.println("dom.getGroups().size() :"+dom.getGroups().size());
						
						if (dom.getGroups().size() == 0)
						{
								equivalenceRepository.removeDomain(domain.getId());
								domain.setNextEquivalenceId((long) 1);
								updateDomain(domain);
							
								System.out.println("dom.getGroups().size() == 0 : ok");
						}
				}
		}
		
		public Domain updateDomain(Domain domain)
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
		
		/*_____________________________________________________________________________*/
		
		
		public Equivalence getNewEquivalence(Group group)
		{
				return createEquivalence(group.getDomain());
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
		
		/*_____________________________________________________________________________*/
		
		
		public boolean addText(Group group,Text text)
		{
				Equivalence equivalence = createEquivalence(group.getDomain());
				addText(text, equivalence, group);
				return true;
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
		
		/*_____________________________________________________________________________*/
		
		
		public void addGroup(Domain domain,String groupName, String userGroupName)
		{
				addGroup(domain, new Group(groupName,userGroupName));
		}
		
		
		public Group addGroup(Domain domain,Group group)
		{
				groupRepository.save(group);
				
				domain.add(group);
				domainRepository.saveAndFlush(domain);

				groupRepository.saveAndFlush(group);
				
				return group;
		}
		
		/*_____________________________________________________________________________*/
		
		public void createDomain(String domainName)
		{
				createDomain(new Domain(domainName));
		}		
		
		public Domain createDomain(Domain domain)
		{
				domainRepository.saveAndFlush(domain);
				return domain;
		}
		
		/*_____________________________________________________________________________*/
		

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
