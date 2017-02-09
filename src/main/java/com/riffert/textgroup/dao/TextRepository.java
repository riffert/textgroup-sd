package com.riffert.textgroup.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.riffert.textgroup.entity.Domain;
import com.riffert.textgroup.entity.Equivalence;
import com.riffert.textgroup.entity.Group;
import com.riffert.textgroup.entity.Text;

@Component
@Transactional
public interface TextRepository extends JpaRepository<Text, Long>
{
		Page<Text> findByValueLike(String v,Pageable p);
	
		// searching in all languages of all domains, equivalent as previous
		@Query("select t from Text t where t.value like :v")
		Page<Text> search(@Param(value = "v") String searchValue,Pageable p);

		// searching in all languages of same domain
		@Query("select t from Text t where t.value like :v and t.domain = :d")
		Page<Text> search(@Param(value = "v") String searchValue,@Param(value = "d") Domain domain,Pageable p);

		// searching in same language of same domain
		@Query("select t from Text t where t.value like :v and t.group = :g")
		Page<Text> search(@Param(value = "v") String searchValue,@Param(value = "g") Group group,Pageable p);
		
		@Query("select t from Text t where t.equivalence = :e ")
		List<Text> search(@Param(value = "e") Equivalence equivalence);
}
