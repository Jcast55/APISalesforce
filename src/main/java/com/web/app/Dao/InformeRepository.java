package com.web.app.Dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.app.Model.Informe;

@Repository
public interface InformeRepository extends JpaRepository<Informe, Long> {
    Informe findByIdInforme1(String idInforme1);
}