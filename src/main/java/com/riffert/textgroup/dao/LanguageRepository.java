package com.riffert.textgroup.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.riffert.textgroup.entity.Domain;
import com.riffert.textgroup.entity.Language;
import com.riffert.textgroup.entity.Text;


@Component
@Transactional
public interface LanguageRepository extends JpaRepository<Language, Long>
{
		@Query("select l from Language l where l.english like :v")
		Page<Language> searchByEnglishKeyword(@Param(value = "v") String searchValue,Pageable p);
}
