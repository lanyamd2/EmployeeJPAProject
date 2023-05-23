package com.bootswana.employeejpaproject.model.repository;

import com.bootswana.employeejpaproject.model.TitleDTO;
import com.bootswana.employeejpaproject.model.TitleDTOId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleRepository extends JpaRepository<TitleDTO, TitleDTOId> {
}