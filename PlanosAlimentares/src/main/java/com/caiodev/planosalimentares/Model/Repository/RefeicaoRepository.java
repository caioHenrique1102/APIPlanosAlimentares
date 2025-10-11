package com.caiodev.planosalimentares.Model.Repository;

import com.caiodev.planosalimentares.Enum.TipoRefeicoes;
import com.caiodev.planosalimentares.Model.Entity.Refeicao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefeicaoRepository extends JpaRepository<Refeicao, Long> {
    void deleteByTipoRefeicoes(TipoRefeicoes tipoRefeicoes);

    Optional<Refeicao> findByTipoRefeicoes(TipoRefeicoes tipoRefeicoes);
}
