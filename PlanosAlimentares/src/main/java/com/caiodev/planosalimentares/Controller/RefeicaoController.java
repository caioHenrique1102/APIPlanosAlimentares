package com.caiodev.planosalimentares.Controller;

import com.caiodev.planosalimentares.DTO.RefeicaoDTO;
import com.caiodev.planosalimentares.Model.Entity.Alimentos;
import com.caiodev.planosalimentares.Model.Entity.Refeicao;
import com.caiodev.planosalimentares.Service.RefeicaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;

@RestController
@RequestMapping("/refeicao")
public class RefeicaoController {
    private RefeicaoService refeicaoService;

    public RefeicaoController(RefeicaoService refeicaoService) {
        this.refeicaoService = refeicaoService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Refeicao> cadastrar(@RequestBody RefeicaoDTO refeicaoDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(refeicaoService.criar(refeicaoDTO));
    }
}
