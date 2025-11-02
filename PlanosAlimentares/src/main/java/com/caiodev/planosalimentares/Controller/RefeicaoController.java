package com.caiodev.planosalimentares.Controller;

import com.caiodev.planosalimentares.DTO.Request.CriaRefeicaoDTORequest;
import com.caiodev.planosalimentares.Enum.TipoRefeicoes;
import com.caiodev.planosalimentares.Model.Entity.Refeicao;
import com.caiodev.planosalimentares.Service.RefeicaoService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Cria refeições",
    description = "Cria uma refeição baseada em uma lista de alimentos e no tipo da refeição")
    @PostMapping("/cadastrarRefeicao")
    public ResponseEntity<Refeicao> cadastrarRefeicao(@RequestBody CriaRefeicaoDTORequest criaRefeicaoDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(refeicaoService.cadastrarRefeicao(criaRefeicaoDTO));
    }

    @Operation(summary = "Deleta refeição",
            description = "Deleta a refeição usando o tipo da refeição")
    @DeleteMapping("/deletarRefeicao/{tipoRefeicoes}")
    public ResponseEntity<Void> deletarRefeicao(@PathVariable TipoRefeicoes tipoRefeicoes){
        refeicaoService.deletar(tipoRefeicoes);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }
    @Operation(summary = "Remover alimento",
            description = "Remove alimento de alguma refeição especifica")
    @PutMapping("/removerAlimento/{tipoRefeicoes}")
    public ResponseEntity<Void> removerAliemento(@PathVariable TipoRefeicoes tipoRefeicoes, String nomeAlimento){
       refeicaoService.deletarAlimento(tipoRefeicoes, nomeAlimento);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }
    @Operation(summary = "Altera a refeição",
            description = "Altera a refeição por completo, tanto os alimentos quanto o tipo")
    @PutMapping("/alterar/{tipoRefeicoes}")
    public ResponseEntity<Refeicao> alterar(@PathVariable TipoRefeicoes tipoRefeicoes, @RequestBody CriaRefeicaoDTORequest criaRefeicaoDTO){
        return ResponseEntity.status(200).body(refeicaoService.alterar(tipoRefeicoes, criaRefeicaoDTO));
    }
    @Operation(summary = "Lista as refeições",
            description = "Listas todas as refeições criadas")
    @GetMapping("/listar")
    public ResponseEntity<List<Refeicao>> listar(){
        return ResponseEntity.status(200).body(refeicaoService.listar());
    }
}
