package com.bootswana.employeejpaproject.services;

import com.bootswana.employeejpaproject.model.repositories.TitleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TitlesService {
    private final TitleRepository titleRepository;

    public TitlesService(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }

//    public int[] getSalaryRangeForAJobTitleNameInAYear(String jobTitleName, LocalDate year) {
//        // TODO
//        return new int[] {};
//    }
}
