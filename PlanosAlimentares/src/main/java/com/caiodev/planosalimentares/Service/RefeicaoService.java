package com.caiodev.planosalimentares.Service;

import com.caiodev.planosalimentares.DTO.RefeicaoDTO;
import com.caiodev.planosalimentares.Enum.TipoRefeicoes;
import com.caiodev.planosalimentares.Exception.AlimentoNotFoundExeption;
import com.caiodev.planosalimentares.Exception.RefeicaoNotFoundExeption;
import com.caiodev.planosalimentares.Model.Entity.Alimentos;
import com.caiodev.planosalimentares.Model.Entity.Refeicao;
import com.caiodev.planosalimentares.Model.Repository.AlimentosRepository;
import com.caiodev.planosalimentares.Model.Repository.RefeicaoRepository;
import org.springframework.stereotype.Service;

import java.sql.Ref;
import java.util.List;
import java.util.Optional;

@Service
public class RefeicaoService {
    private RefeicaoRepository refeicaoRepository;
    private AlimentosRepository alimentosRepository;
    public RefeicaoService(RefeicaoRepository refeicaoRepository, AlimentosRepository alimentosRepository) {
        this.alimentosRepository = alimentosRepository;
        this.refeicaoRepository = refeicaoRepository;
    }

    public Refeicao cadastrarAlimento(String nomeAlimento, TipoRefeicoes tipoRefeicao){
        Refeicao refeicao = refeicaoRepository.findByTipoRefeicoes(tipoRefeicao).orElseThrow( () -> new RefeicaoNotFoundExeption("Refeição não encontrada"));
        Alimentos alimentos = alimentosRepository.findByNome(nomeAlimento).orElseThrow(() -> new AlimentoNotFoundExeption("Alimento não encontrado"));
        refeicao.getAlimentos().add(alimentos);
        return refeicaoRepository.save(refeicao);
    }

    public Refeicao criar(RefeicaoDTO refeicaoDTO){
        Refeicao refeicao = new Refeicao(refeicaoDTO);
        refeicao.setTipoRefeicoes(refeicaoDTO.tipoRefeicoes());
        refeicao.setAlimentos(refeicaoDTO.alimentos());
        return refeicaoRepository.save(refeicao);
    }

    public void deletar(TipoRefeicoes tipoRefeicoes){
        refeicaoRepository.deleteByTipoRefeicoes(tipoRefeicoes);
    }

    public Refeicao alterar(TipoRefeicoes tipoRefeicoes,RefeicaoDTO refeicaoDTO){
        Optional<Refeicao> buscar = refeicaoRepository.findByTipoRefeicoes(tipoRefeicoes);
        if(buscar.isEmpty()){
            throw new RefeicaoNotFoundExeption("Refeição não encontrada");
        }

        Refeicao refeicao = buscar.get();
        refeicao.setAlimentos(refeicaoDTO.alimentos());
        refeicao.setTipoRefeicoes(refeicaoDTO.tipoRefeicoes());
        return refeicaoRepository.save(refeicao);
    }

    public List<Refeicao> listar(){
        return refeicaoRepository.findAll();
    }
}
