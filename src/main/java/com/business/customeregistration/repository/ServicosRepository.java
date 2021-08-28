package com.business.customeregistration.repository;

import com.business.customeregistration.model.Servicos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicosRepository extends JpaRepository<Servicos, Long> {

}
