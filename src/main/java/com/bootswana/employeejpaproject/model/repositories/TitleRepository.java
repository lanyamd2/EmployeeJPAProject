package com.bootswana.employeejpaproject.model.repositories;

import com.bootswana.employeejpaproject.model.dtos.TitleDTO;
import com.bootswana.employeejpaproject.model.dtos.TitleDTOId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleRepository extends JpaRepository<TitleDTO, TitleDTOId> {
}