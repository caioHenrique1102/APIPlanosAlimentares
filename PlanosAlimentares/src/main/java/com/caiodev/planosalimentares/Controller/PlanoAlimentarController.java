package com.caiodev.planosalimentares.Controller;

import com.caiodev.planosalimentares.DTO.CriarPlanoDTO;
import com.caiodev.planosalimentares.Enum.TipoRefeicoes;
import com.caiodev.planosalimentares.Model.Entity.PlanoAlimentar;
import com.caiodev.planosalimentares.Service.PlanoAlimentarService;
import io.swagger.v3.oas.annotations.Operation;
import org.hibernate.annotations.Fetch;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/planoalimentar")
public class PlanoAlimentarController {
    private PlanoAlimentarService planoAlimentarService;
    public PlanoAlimentarController(PlanoAlimentarService planoAlimentarService) {
        this.planoAlimentarService = planoAlimentarService;
    }

    @Operation(summary = "Cria um plano alimentar" ,
            description = "Cadastra um plano alimentar apenas com o nome no banco de dados")
    @PostMapping("/criarPlano")
    public ResponseEntity<PlanoAlimentar> criarPlano(CriarPlanoDTO criarPlanoDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(planoAlimentarService.criarPlano(new PlanoAlimentar(criarPlanoDTO)));
    }

    @Operation(summary = "Cadastar uma refeição" ,
            description = "Cadastra uma refeição ao plano alimentar")
    @PostMapping("/cadastrarRefeicao/{nomePlano}")
    public ResponseEntity<PlanoAlimentar> cadastraRefeica(@PathVariable String nomePlano, TipoRefeicoes tipoRefeicoes){
        return ResponseEntity.status(HttpStatus.CREATED).body(planoAlimentarService.cadastrarRefeicao(tipoRefeicoes, nomePlano));
    }

    @Operation(summary = "Deleta um plano alimentar" ,
            description = "Deleta um plano alimento pelo nome")
    @DeleteMapping("/deletar/{nome}")
    public ResponseEntity<Void> deletar(@PathVariable String nome){
        planoAlimentarService.deletar(nome);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }
}
