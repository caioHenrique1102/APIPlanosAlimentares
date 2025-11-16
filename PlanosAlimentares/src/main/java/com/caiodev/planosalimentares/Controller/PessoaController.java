package com.caiodev.planosalimentares.Controller;

import com.caiodev.planosalimentares.DTO.Request.PessoaDTORequest;
import com.caiodev.planosalimentares.Model.Entity.Pessoa;
import com.caiodev.planosalimentares.Service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.owasp.html.PolicyFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    private final PessoaService pessoaService;
    private final PolicyFactory policyFactory;
    public PessoaController(PessoaService pessoaService, PolicyFactory policyFactory) {
        this.pessoaService = pessoaService;
        this.policyFactory = policyFactory;
    }

    @Operation(summary = "Cria uma nova pessoa",
            description = "Usado para cadastrar uma pessoa que tem um plano alimentar")
    @PostMapping("/cadastro")
    public ResponseEntity<Pessoa> cadastrar(@Valid @RequestBody PessoaDTORequest pessoaDTO) {
        String nomeLimpo = policyFactory.sanitize(pessoaDTO.nome());
        String alturaLimpa = policyFactory.sanitize(pessoaDTO.altura());
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nomeLimpo);
        pessoa.setAltura(alturaLimpa);

        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.cadastrar(pessoa));
    }

    @Operation(summary = "Casdastra um plano alimentar",
            description = "Usado para associar um plano alimentar a uma pessoa")
    @PostMapping("/cadastrarPlano/{nomePessoa}")
    public ResponseEntity<Void> cadastarPlano(@PathVariable String nomePessoa, String nomePlano) {
        String nomePessoaLimpo = policyFactory.sanitize(nomePessoa);
        String nomePlanoLimpo = policyFactory.sanitize(nomePlano);
        pessoaService.cadastrarPlano(nomePessoaLimpo, nomePlanoLimpo);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Listar todas as pessoas",
            description = " Usado para listar todas as pessoas que estão associadas a plano alimentar")
    @GetMapping("/listar")
    public ResponseEntity<List<Pessoa>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.listar());
    }

    @Operation(summary = "Altera os dados da pessoa",
            description = "Usado para alterar todos os dados da pessoa pelo nome")
    @PutMapping("/alterar/{nome}")
    public ResponseEntity<Pessoa> alterar(@RequestBody Pessoa pessoa, @PathVariable String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.alterar(nome, pessoa));
    }

    @Operation(summary = "Busca a pessoa",
            description = "Usado para buscar a pessoa pelo nome")
    @GetMapping("/buscar/{nome}")
    public ResponseEntity<Pessoa> buscar(@PathVariable String nome) {
        String nomeLimpo = policyFactory.sanitize(nome);
        return ResponseEntity.status(HttpStatus.FOUND).body(pessoaService.buscar(nomeLimpo));
    }

    @Operation(summary = "Deleta pessoa",
            description = "Usado para deleta a pessoa pelo nome")
    @DeleteMapping("/deletar/{nome}")
    public ResponseEntity<Void> deletar(@PathVariable String nome) {
        pessoaService.deletar(nome);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }

}