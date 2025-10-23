package com.caiodev.planosalimentares.Service;

import com.caiodev.planosalimentares.DTO.CriaRefeicaoDTO;
import com.caiodev.planosalimentares.Enum.TipoRefeicoes;
import com.caiodev.planosalimentares.Exception.AlimentoNotFoundExeption;
import com.caiodev.planosalimentares.Exception.RefeicaoNotFoundExeption;
import com.caiodev.planosalimentares.Model.Entity.Alimentos;
import com.caiodev.planosalimentares.Model.Entity.Refeicao;
import com.caiodev.planosalimentares.Model.Repository.AlimentosRepository;
import com.caiodev.planosalimentares.Model.Repository.RefeicaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RefeicaoService {
    private final RefeicaoRepository refeicaoRepository;
    private final AlimentosRepository alimentosRepository;

    public RefeicaoService(RefeicaoRepository refeicaoRepository, AlimentosRepository alimentosRepository) {
        this.alimentosRepository = alimentosRepository;
        this.refeicaoRepository = refeicaoRepository;
    }

    @Transactional
    public Refeicao cadastrarRefeicao(CriaRefeicaoDTO criaRefeicaoDTO) {
        System.out.println("PROCURANDO POR ALIMENTO " + "[" + criaRefeicaoDTO.nomeAlimento() + "]");
        Refeicao refeicao = new Refeicao();
        Alimentos alimentos = alimentosRepository.findByNomeIgnoreCase(criaRefeicaoDTO.nomeAlimento())
                .orElseThrow(() -> new AlimentoNotFoundExeption("Alimento não encontrado"));
        refeicao.getAlimentos().add(alimentos);
        refeicao.setTipoRefeicoes(criaRefeicaoDTO.tipoRefeicoes());
        return refeicaoRepository.save(refeicao);
    }

    @Transactional
    public void deletar(TipoRefeicoes tipoRefeicoes) {
        refeicaoRepository.deleteByTipoRefeicoes(tipoRefeicoes);
    }

    @Transactional
    public Refeicao alterar(TipoRefeicoes tipoRefeicoes, CriaRefeicaoDTO criaRefeicaoDTO) {

        Optional<Refeicao> buscar = refeicaoRepository.findByTipoRefeicoes(tipoRefeicoes);
        if (buscar.isEmpty()) throw new RefeicaoNotFoundExeption("Refeição não encontrada");

        Refeicao refeicao = buscar.get();
        Optional<Alimentos> alimentos = alimentosRepository.findByNomeIgnoreCase(criaRefeicaoDTO.nomeAlimento());
        if (alimentos.isEmpty()) throw new AlimentoNotFoundExeption("Alimento não encontrado");

        //.stream() -> cria um fluxo com 1 item (se achou) ou 0 itens (se estava vazio)
        //.toList() -> transforma esse fluxo em uma List
        refeicao.setAlimentos(alimentos.stream().toList());
        refeicao.setTipoRefeicoes(criaRefeicaoDTO.tipoRefeicoes());
        return refeicaoRepository.save(refeicao);
    }

    @Transactional
    public void deletarAlimento(TipoRefeicoes tipoRefeicoes, String nomeAlimento) {
        Refeicao refeicao = refeicaoRepository.findByTipoRefeicoes(tipoRefeicoes)
                .orElseThrow(() -> new RefeicaoNotFoundExeption("Refeição não encontrada"));
        Alimentos alimentos = alimentosRepository.findByNomeIgnoreCase(nomeAlimento)
                .orElseThrow(() -> new AlimentoNotFoundExeption("Alimento não econtrado"));
        if (refeicao.getAlimentos().contains(alimentos)) {
            refeicao.getAlimentos().remove(alimentos);
        } else {
            throw new RuntimeException("O alimento não está nessa refeição");
        }
        refeicaoRepository.save(refeicao);
    }

    public List<Refeicao> listar() {
        return refeicaoRepository.findAll();
    }
}
