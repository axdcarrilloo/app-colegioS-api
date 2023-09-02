package com.casa.repositories;

import com.casa.domain.entities.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<RolEntity, Long> {

    RolEntity findByCodigo(String codigo);

}
