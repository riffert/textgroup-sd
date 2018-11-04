package com.riffert.textgroup.dao;


import org.springframework.stereotype.Repository;
import com.riffert.textgroup.entity.Domain;

@Repository
public interface SomethingRepository extends CustomRepository<Domain, Long> {}
