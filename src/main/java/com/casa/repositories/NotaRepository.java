package com.casa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casa.domain.entities.NotaEntity;

@Repository
public interface NotaRepository extends JpaRepository<NotaEntity, Long> {

}
