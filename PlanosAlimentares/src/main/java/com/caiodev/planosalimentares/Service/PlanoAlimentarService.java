package com.caiodev.planosalimentares.Service;

import com.caiodev.planosalimentares.Enum.TipoRefeicoes;
import com.caiodev.planosalimentares.Exception.PlanoAlimentarNotFound;
import com.caiodev.planosalimentares.Exception.RefeicaoNotFoundExeption;
import com.caiodev.planosalimentares.Model.Entity.PlanoAlimentar;
import com.caiodev.planosalimentares.Model.Entity.Refeicao;
import com.caiodev.planosalimentares.Model.Repository.PlanoAlimentarRepository;
import com.caiodev.planosalimentares.Model.Repository.RefeicaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PlanoAlimentarService {
    private final PlanoAlimentarRepository planoAlimentarRepository;
    private RefeicaoRepository refeicaoRepository;

    public PlanoAlimentarService(PlanoAlimentarRepository planoAlimentarRepository) {
        this.planoAlimentarRepository = planoAlimentarRepository;
        this.refeicaoRepository = refeicaoRepository;

    }

    @Transactional
    public PlanoAlimentar cadastrarRefeicao(TipoRefeicoes tipoRefeicoes, String nomePlano) {
        PlanoAlimentar planoAlimentar = planoAlimentarRepository.findByNome(nomePlano)
                .orElseThrow(() -> new PlanoAlimentarNotFound("Plano não encontrado"));
        Refeicao refeicao = refeicaoRepository.findByTipoRefeicoes(tipoRefeicoes)
                .orElseThrow(() -> new RefeicaoNotFoundExeption("Refeição não encontrada"));

        planoAlimentar.getRefeicao().add(refeicao);

        return planoAlimentarRepository.save(planoAlimentar);

    }

    @Transactional
    public PlanoAlimentar criarPlano(PlanoAlimentar planoAlimentar){
    return planoAlimentarRepository.save(planoAlimentar);
    }

    @Transactional
    public void deletar(String nome){
        planoAlimentarRepository.deleteByNome((nome));
    }

}
