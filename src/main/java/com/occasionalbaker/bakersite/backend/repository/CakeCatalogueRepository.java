package com.occasionalbaker.bakersite.backend.repository;

import com.occasionalbaker.bakersite.backend.entity.CakeCatalogue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CakeCatalogueRepository extends JpaRepository<CakeCatalogue, Long> {


}
