package com.bootswana.employeejpaproject.model.repositories;

import com.bootswana.employeejpaproject.model.dtos.TitleDTO;
import com.bootswana.employeejpaproject.model.dtos.TitleDTOId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleRepository extends JpaRepository<TitleDTO, TitleDTOId> {
}