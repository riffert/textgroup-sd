package com.riffert.textgroup.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

		@Modifying
	    @Transactional
	    @Query("delete from Text t where t.equivalence = :equivalenceId")
	    void remove(@Param("equivalenceId") Equivalence equivalenceId);
	
		@Modifying
	    @Query("UPDATE Text t SET t.value = :text WHERE t.id = :id")
	    int updateText(@Param("id") Long id, @Param("text") String text);
		
		Page<Text> findByValueLike(String v,Pageable p);
	
		// searching in all languages of all domains, equivalent as previous
		@Query("select t from Text t where t.value like :v")
		Page<Text> search(@Param(value = "v") String searchValue,Pageable p);

		// searching in all languages of same domain
		@Query("select t from Text t where t.value like :v and t.domain = :d")
		Page<Text> search(@Param(value = "v") String searchValue,@Param(value = "d") Domain domain,Pageable p);

		// searching in same group
		// @Query("select t from Text t where t.value like :v and t.group = :g")
		@Query("select t from Text t where t.value like :v and t.group = :g order by t.equivalence.userId")
		Page<Text> search(@Param(value = "v") String searchValue,@Param(value = "g") Group group,Pageable p);
		
		@Query("select t from Text t where t.equivalence = :e ")
		List<Text> search(@Param(value = "e") Equivalence equivalence);

}
