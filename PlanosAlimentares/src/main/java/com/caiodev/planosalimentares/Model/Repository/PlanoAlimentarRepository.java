package com.caiodev.planosalimentares.Model.Repository;

import com.caiodev.planosalimentares.Model.Entity.PlanoAlimentar;
import jakarta.persistence.OneToMany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanoAlimentarRepository extends JpaRepository<PlanoAlimentar, Long> {
    Optional <PlanoAlimentar> findByNome(String nomePlano);
}
