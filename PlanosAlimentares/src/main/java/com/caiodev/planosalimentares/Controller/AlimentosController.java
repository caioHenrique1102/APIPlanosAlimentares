package com.caiodev.planosalimentares.Controller;
import com.caiodev.planosalimentares.DTO.Request.AlimentosDTORequest;
import com.caiodev.planosalimentares.Model.Entity.Alimentos;
import com.caiodev.planosalimentares.Service.AlimentosService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/alimento")
public class AlimentosController {
    private AlimentosService alimentosService;

    public AlimentosController(AlimentosService alimentosService) {
        this.alimentosService = alimentosService;
    }
    @Operation(summary = "Cria um alimento" ,
            description = "Salva alimentos no banco de dados, com informações como nome e quantidade")
    @PostMapping("/criar")
    public ResponseEntity<Alimentos> criar(@RequestBody AlimentosDTORequest alimentosDTO){
        Alimentos alimentos = new Alimentos(alimentosDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(alimentosService.cadastrar(alimentos));
    }
@Operation(summary = "Altera os aliementos" ,
    description = "Altera todas as informações dos alimentos e salva no banco de dados")
    @PutMapping("/alterar/{nome}")
    public ResponseEntity<Alimentos> alterar(@PathVariable String nome, @RequestBody AlimentosDTORequest alimentosDTO){
        return ResponseEntity.status(HttpStatus.OK).body(alimentosService.alterar(nome, alimentosDTO));
    }
@Operation(summary = "Deleta alimentos",
    description = "Deleta alimentos com base no nome do alimento e atualiza o banco de dados")
    @DeleteMapping("/deletar/{nome}")
    public ResponseEntity<Void> deletar(@PathVariable String nome){
        alimentosService.deletar(nome);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }
}
