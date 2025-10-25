package com.caiodev.planosalimentares.Controller;

import com.caiodev.planosalimentares.DTO.CriarPlanoDTO;
import com.caiodev.planosalimentares.Enum.TipoRefeicoes;
import com.caiodev.planosalimentares.Model.Entity.PlanoAlimentar;
import com.caiodev.planosalimentares.Service.PlanoAlimentarService;
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


    @PostMapping("/criarPlano")
    public ResponseEntity<PlanoAlimentar> criarPlano(CriarPlanoDTO criarPlanoDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(planoAlimentarService.criarPlano(new PlanoAlimentar(criarPlanoDTO)));
    }

    @PostMapping("/cadastrarRefeicao/{nomePlano}")
    public ResponseEntity<PlanoAlimentar> cadastraRefeica(@PathVariable String nomePlano, TipoRefeicoes tipoRefeicoes){
        return ResponseEntity.status(HttpStatus.CREATED).body(planoAlimentarService.cadastrarRefeicao(tipoRefeicoes, nomePlano));
    }

    @DeleteMapping("/deletar/{nome}")
    public ResponseEntity<Void> deletar(@PathVariable String nome){
        planoAlimentarService.deletar(nome);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }
}
