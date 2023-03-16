package com.example.air_quality_check.repository;




import org.springframework.data.jpa.repository.JpaRepository;

import com.example.air_quality_check.model.DataModel.entity;


/* JPArepository which manages data in this application, with respect to ID */
public interface DataRepository extends JpaRepository<entity, Long> {
    
}
