package com.riffert.textgroup.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.riffert.textgroup.dao.EquivalenceRepository;
import com.riffert.textgroup.dao.TextRepository;

import com.riffert.textgroup.entity.Equivalence;
import com.riffert.textgroup.entity.Group;
import com.riffert.textgroup.entity.Text;


@Component
@Transactional
public class TextHandler
{
		@Autowired
		private TextRepository textRepository;

		@Autowired
		private EquivalenceRepository equivalenceRepository;
		
		public void deleteText(Text text)
		{
				textRepository.delete(text);
		}
		
		public Page<Text> search(String keyword,Group group,int currentpage,int pagesize)
		{
				return textRepository.search("%"+keyword+"%", group,new PageRequest(currentpage, pagesize));
		}
		
		

		//TODO
//		public List<Equivalence> getTextsEquivalence(Equivalence equivalence)
//		{
//				return equivalenceRepository.getOne(equivalence.getId());
//		}
		
		public List<Equivalence> getTextsEquivalence(Equivalence equivalence)
		{
				ArrayList<Equivalence> al = new ArrayList<Equivalence>();
				al.add(new Equivalence());
			
				return al;
		}	
		
	
		
}
