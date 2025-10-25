package com.caiodev.planosalimentares.Service;


import com.caiodev.planosalimentares.Exception.PessoaNotFoundExeption;
import com.caiodev.planosalimentares.Exception.PlanoAlimentarNotFound;
import com.caiodev.planosalimentares.Model.Entity.Pessoa;
import com.caiodev.planosalimentares.Model.Entity.PlanoAlimentar;
import com.caiodev.planosalimentares.Model.Repository.PessoaRepository;
import com.caiodev.planosalimentares.Model.Repository.PlanoAlimentarRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final PlanoAlimentarRepository planoAlimentarRepository;

    public PessoaService(PessoaRepository pessoaRepository, PlanoAlimentarRepository planoAlimentarRepository) {
        this.pessoaRepository = pessoaRepository;
        this.planoAlimentarRepository = planoAlimentarRepository;
    }

    @Transactional
    public Pessoa cadastrar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @Transactional
    public void cadastrarPlano(String nomePlano, String nomePessoa) {
        Pessoa pessoa = buscar(nomePessoa);
        PlanoAlimentar planoAlimentar = planoAlimentarRepository.findByNome(nomePlano)
                .orElseThrow(() -> new PlanoAlimentarNotFound("Plano não encontrado"));
        pessoa.setPlanoAlimentar(planoAlimentar);
        pessoaRepository.save(pessoa);
    }

    public Pessoa buscar(String nome) {
        Optional<Pessoa> acharPessoa = pessoaRepository.findByNome(nome);
        if (acharPessoa.isEmpty()) throw new PessoaNotFoundExeption("Pessoa não encontrada");
        return acharPessoa.get();
    }

    //usado para proteção de dados para garantir a regra do "Tudo ou Nada".
    //Commit: Se tudo der certo, salva as mudanças.
    //Rollback: Se algo der errado, desfaz tudo.
    @Transactional
    public void deletar(String nome) {
        Optional<Pessoa> pessoaExiste = pessoaRepository.findByNome(nome);
        if (pessoaExiste.isEmpty()) throw new PessoaNotFoundExeption("Pessoa não encontrada");
        Pessoa pessoa = pessoaExiste.get();
        pessoaRepository.delete(pessoa);
    }

    public List<Pessoa> listar() {
        Sort sort = Sort.by(Sort.Direction.ASC, "nome");
        return pessoaRepository.findAll(sort);
    }

    @Transactional
    public Pessoa alterar(String nome, Pessoa pessoa) {
        Pessoa acharPessoa = buscar(nome);

        acharPessoa.setNome(pessoa.getNome() != null ? pessoa.getNome() : acharPessoa.getNome());
        acharPessoa.setIdade(pessoa.getIdade() != null ? pessoa.getIdade() : acharPessoa.getIdade());
        acharPessoa.setAltura(pessoa.getAltura() != null ? pessoa.getAltura() : acharPessoa.getAltura());

        return pessoaRepository.save(acharPessoa);
    }
}
