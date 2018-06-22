package com.riffert.textgroup.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.riffert.textgroup.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long>
{
	
	@Modifying
    @Query("delete from Group g where g.id = :groupId")
    void remove(@Param("groupId") Long groupId);	

}
