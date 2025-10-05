package com.caiodev.planosalimentares.Service;

import com.caiodev.planosalimentares.DTO.PessoaDTO;
import com.caiodev.planosalimentares.Exception.PessoaNotFoundExeption;
import com.caiodev.planosalimentares.Model.Entity.Pessoa;
import com.caiodev.planosalimentares.Model.Repository.PessoaRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
    private PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa cadastrar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa buscar(String nome) {
        Optional<Pessoa> acharPessoa = pessoaRepository.findByNome(nome);
        if (acharPessoa.isEmpty()) {
            throw new PessoaNotFoundExeption("Pessoa não encontrada");
        }
        // vai pegar oque está dentro da "caixa" optional
        return acharPessoa.get();

    }
    //usado para proteção de dados para garantir a regra do "Tudo ou Nada".
    //Commit: Se tudo der certo, salva as mudanças.
    //Rollback: Se algo der errado, desfaz tudo.
    @Transactional
    public void deletar(String nome) {
        Optional<Pessoa> pessoaExiste = pessoaRepository.findByNome(nome);
        if(pessoaExiste.isEmpty()){
            throw new PessoaNotFoundExeption("Pessoa não encontrada");
        }
        Pessoa pessoa = pessoaExiste.get();

        pessoaRepository.delete(pessoa);
    }

    public List<Pessoa> listar() {
        Sort sort = Sort.by(Sort.Direction.ASC, "nome");
        return pessoaRepository.findAll(sort);
    }

    public Pessoa alterar(String nome, PessoaDTO pessoaDTO) {
        Optional<Pessoa> acharPessoa = pessoaRepository.findByNome(nome);
        if (acharPessoa.isEmpty()) {
            throw new PessoaNotFoundExeption("Pessoa não encontrada");
        }

      //Pessoa vai ser == a pessoa que estamos procuarando
        Pessoa pessoa = acharPessoa.get();
        pessoa.setAltura(pessoaDTO.altura());
        pessoa.setIdade(pessoaDTO.idade());
        pessoa.setNome(pessoaDTO.nome());
        return pessoaRepository.save(pessoa);


    }
}
