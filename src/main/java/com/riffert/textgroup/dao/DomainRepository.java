package com.riffert.textgroup.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.riffert.textgroup.entity.Domain;


@Component
@Transactional
//@Repository
public interface DomainRepository extends JpaRepository<Domain, Long> {}
