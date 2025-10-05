package com.caiodev.planosalimentares.Controller;

import com.caiodev.planosalimentares.DTO.PessoaDTO;
import com.caiodev.planosalimentares.Model.Entity.Pessoa;
import com.caiodev.planosalimentares.Model.Repository.PessoaRepository;
import com.caiodev.planosalimentares.Service.PessoaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
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
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.cadastrar(new Pessoa(pessoaDTO)));
    }

    @GetMapping("/listar")
    public ResponseEntity <List<Pessoa>> listar(){
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.listar());
    }

    @PutMapping("/alterar/{nome}")
    public ResponseEntity<Pessoa> alterar(@RequestBody PessoaDTO pessoaDTO, @PathVariable String nome){
      return ResponseEntity.status(HttpStatus.OK).body(pessoaService.alterar(nome, pessoaDTO));
    }

    @GetMapping("/buscar/{nome}")
    public ResponseEntity <Pessoa> buscar(@PathVariable String nome){
        return ResponseEntity.status(HttpStatus.FOUND).body(pessoaService.buscar(nome));
    }

    @DeleteMapping("/deletar/{nome}")
    public ResponseEntity <Void> deletar(@PathVariable String nome){
        pessoaService.deletar(nome);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }

}