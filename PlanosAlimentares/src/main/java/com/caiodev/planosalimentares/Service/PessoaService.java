package com.caiodev.planosalimentares.Service;

import com.caiodev.planosalimentares.Model.Entity.Pessoa;
import com.caiodev.planosalimentares.Model.Repository.PessoaRepository;
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

    public Pessoa cadastrar(Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }

    public Optional <Pessoa> buscar(String nome){

        Long id  = pessoaRepository.findIdByNome(nome);
        if(id == null) {
            throw new RuntimeException("Erro, id não pode ser null");
        }

        Optional <Pessoa> pessoa = pessoaRepository.findById(id);
        return pessoa;
    }

    public void deletar(String nome){
        buscar(nome);
        pessoaRepository.deleteByNome(nome);
    }

    public List<Pessoa> listar(){
        Sort sort = Sort.by("Nome").ascending();
        return pessoaRepository.findAll(sort);
    }

    public Pessoa alterar(String nome,Pessoa pessoa){
        buscar(nome);
        return pessoaRepository.save(pessoa);
    }
}
