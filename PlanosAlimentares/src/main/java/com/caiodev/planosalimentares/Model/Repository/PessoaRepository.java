package com.caiodev.planosalimentares.Model.Repository;

import com.caiodev.planosalimentares.Model.Entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
    Long findIdByNome(String nome);

    void deleteByNome(String nome);
}
