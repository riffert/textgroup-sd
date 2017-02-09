package com.riffert.textgroup.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.riffert.textgroup.entity.Group;


@Component
@Transactional
public interface GroupRepository extends JpaRepository<Group, Long>
{

}
