package com.occasionalbaker.bakersite.backend.service;

import com.occasionalbaker.bakersite.backend.entity.CakeCatalogue;
import com.occasionalbaker.bakersite.backend.repository.CakeCatalogueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CakeCatalogueService {


    CakeCatalogueRepository cakeCatalogueRepository;

    @Autowired
    public CakeCatalogueService(CakeCatalogueRepository cakeCatalogueRepository) {
        this.cakeCatalogueRepository = cakeCatalogueRepository;
    }

    public List<CakeCatalogue> catalogueRepositoryList(){
        return cakeCatalogueRepository.findAll();
    }
}
