package com.caiodev.planosalimentares.Model.Repository;

import com.caiodev.planosalimentares.Model.Entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{


    void delete(Pessoa pessoa);

    Optional<Pessoa> findByNome(String nome);


}
