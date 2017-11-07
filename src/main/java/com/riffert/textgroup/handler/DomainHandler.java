package com.riffert.textgroup.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.riffert.textgroup.dao.DomainRepository;
import com.riffert.textgroup.dao.EquivalenceRepository;
import com.riffert.textgroup.dao.GroupRepository;
import com.riffert.textgroup.dao.TextRepository;
import com.riffert.textgroup.entity.Domain;
import com.riffert.textgroup.entity.Equivalence;
import com.riffert.textgroup.entity.Group;
import com.riffert.textgroup.entity.Text;


@Component
@Transactional
public class DomainHandler
{
		@Autowired
		private DomainRepository domainRepository;
		
		@Autowired
		private GroupRepository groupRepository;
		
		@Autowired
		private EquivalenceRepository equivalenceRepository;
		
		@Autowired
		private TextRepository textRepository;

		public DomainHandler() {}
		

		public Group getGroupByName(Domain domain, String groupname)
		{
				Group group = domain.getGroupByName(groupname);
				return group;
		}
		
		public DomainHandler(AnnotationConfigApplicationContext ctx)
		{
				domainRepository = ctx.getBean(DomainRepository.class);
				groupRepository = ctx.getBean(GroupRepository.class);
				equivalenceRepository = ctx.getBean(EquivalenceRepository.class);
				textRepository = ctx.getBean(TextRepository.class);
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
		
		// TODO java 8 compliant
		public Map<String, List<Text>> getMap(long n)
		{
				Map<String, List<Text>> hm = new HashMap<String, List<Text>>();
				
//				Domain domain = new Domain("default");
//				domain.setId((long) 1);
				
				Domain domain = domainRepository.findOne((long) 1);
				
				
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
