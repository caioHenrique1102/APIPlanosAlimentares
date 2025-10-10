package com.caiodev.planosalimentares.Model.Repository;

import com.caiodev.planosalimentares.Model.Entity.Alimentos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlimentosRepository extends JpaRepository<Alimentos, Long> {
     Optional<Alimentos> findByNome(String nome)
}
