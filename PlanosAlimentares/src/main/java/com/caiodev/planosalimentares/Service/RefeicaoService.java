package com.caiodev.planosalimentares.Service;

import com.caiodev.planosalimentares.DTO.RefeicaoDTO;
import com.caiodev.planosalimentares.Enum.TipoRefeicoes;
import com.caiodev.planosalimentares.Exception.RefeicaoNotFoundExeption;
import com.caiodev.planosalimentares.Model.Entity.Refeicao;
import com.caiodev.planosalimentares.Model.Repository.RefeicaoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RefeicaoService {
    private RefeicaoRepository refeicaoRepository;

    public RefeicaoService(RefeicaoRepository refeicaoRepository) {
        this.refeicaoRepository = refeicaoRepository;
    }


    public Refeicao criar(Refeicao refeicao){
     return refeicaoRepository.save(refeicao);
    }

    public void deletar(TipoRefeicoes tipoRefeicoes){
        refeicaoRepository.deleteByTipoRefeicoes(tipoRefeicoes);
    }

    public Refeicao alterar(TipoRefeicoes tipoRefeicoes,RefeicaoDTO refeicaoDTO){
        Optional<Refeicao> buscar = refeicaoRepository.findByTipoRefeicoes(tipoRefeicoes);
        if(buscar.isEmpty){
            throw new RefeicaoNotFoundExeption("Refeição não encontrada");
        }

        Refeicao refeicao = buscar.get();
        refeicao.setAlimentos(refeicaoDTO.alimentos());
        refeicao.setTipoRefeicoes(refeicaoDTO.tipoRefeicoes());
        return refeicaoRepository.save(refeicao);
    }
}
