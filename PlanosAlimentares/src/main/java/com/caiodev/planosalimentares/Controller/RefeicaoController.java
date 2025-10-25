package com.caiodev.planosalimentares.Controller;

import com.caiodev.planosalimentares.DTO.CriaRefeicaoDTO;
import com.caiodev.planosalimentares.Enum.TipoRefeicoes;
import com.caiodev.planosalimentares.Model.Entity.Alimentos;
import com.caiodev.planosalimentares.Model.Entity.Refeicao;
import com.caiodev.planosalimentares.Service.RefeicaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/refeicao")
public class RefeicaoController {
    private RefeicaoService refeicaoService;

    public RefeicaoController(RefeicaoService refeicaoService) {
        this.refeicaoService = refeicaoService;
    }

    @PostMapping("/cadastrarRefeicao")
    public ResponseEntity<Refeicao> cadastrarRefeicao(@RequestBody CriaRefeicaoDTO criaRefeicaoDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(refeicaoService.cadastrarRefeicao(criaRefeicaoDTO));
    }


    @DeleteMapping("/deletarRefeicao/{tipoRefeicoes}")
    public ResponseEntity<Void> deletarRefeicao(@PathVariable TipoRefeicoes tipoRefeicoes){
        refeicaoService.deletar(tipoRefeicoes);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }

    @PutMapping("/deletarAlimento/{tipoRefeicoes}")
    public ResponseEntity<Void> deletarAliemento(@PathVariable TipoRefeicoes tipoRefeicoes, String nomeAlimento){
       refeicaoService.deletarAlimento(tipoRefeicoes, nomeAlimento);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }

    @PutMapping("/alterar/{tipoRefeicoes}")
    public ResponseEntity<Refeicao> alterar(@PathVariable TipoRefeicoes tipoRefeicoes, @RequestBody CriaRefeicaoDTO criaRefeicaoDTO){
        return ResponseEntity.status(200).body(refeicaoService.alterar(tipoRefeicoes, criaRefeicaoDTO));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Refeicao>> listar(){
        return ResponseEntity.status(200).body(refeicaoService.listar());
    }
}
