package com.riffert.textgroup.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.riffert.textgroup.entity.Equivalence;
import com.riffert.textgroup.entity.Text;


@Component
@Transactional
public interface EquivalenceRepository extends JpaRepository<Equivalence, Long>
{
	// TODO
	//@Query("select e from Equivalence e where e.equivalence = :e")
	//List<Equivalence> search(@Param(value = "e") Equivalence equivalence);
	
	
}
