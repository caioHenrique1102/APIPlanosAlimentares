package com.caiodev.planosalimentares.Controller;

import com.caiodev.planosalimentares.DTO.PessoaDTO;
import com.caiodev.planosalimentares.Model.Entity.Pessoa;
import com.caiodev.planosalimentares.Model.Repository.PessoaRepository;
import com.caiodev.planosalimentares.Service.PessoaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    private final PessoaRepository pessoaRepository;
    private PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService, PessoaRepository pessoaRepository) {
        this.pessoaService = pessoaService;
        this.pessoaRepository = pessoaRepository;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Pessoa> cadastrar(@RequestBody PessoaDTO pessoaDTO){
        return ResponseEntity.ok(pessoaService.cadastrar(new Pessoa(pessoaDTO)));
    }

    @GetMapping("/listar")
    public ResponseEntity <List<Pessoa>> listar(){
        return ResponseEntity.ok(pessoaService.listar());
    }

    @PutMapping("/alterar")
    public ResponseEntity<Pessoa> alterar(@RequestBody PessoaDTO pessoaDTO, String nome){
        return ResponseEntity.ok(pessoaService.alterar(nome, new Pessoa(pessoaDTO)));

    }
}
