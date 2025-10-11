package com.caiodev.planosalimentares.Controller;
import com.caiodev.planosalimentares.DTO.AlimentosDTO;
import com.caiodev.planosalimentares.Model.Entity.Alimentos;
import com.caiodev.planosalimentares.Service.AlimentosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alimento")
public class AlimentosController {
    private AlimentosService alimentosService;

    public AlimentosController(AlimentosService alimentosService) {
        this.alimentosService = alimentosService;
    }

    @PostMapping("/criar")
    public ResponseEntity<Alimentos> criar(@RequestBody AlimentosDTO alimentosDTO){
        Alimentos alimentos = new Alimentos(alimentosDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(alimentosService.cadastrar(alimentos));
    }

    @PutMapping("/alterar/{nome}")
    public ResponseEntity<Alimentos> alterar(@PathVariable String nome, @RequestBody AlimentosDTO alimentosDTO){
        return ResponseEntity.status(HttpStatus.OK).body(alimentosService.alterar(nome, alimentosDTO));
    }

    @DeleteMapping("/deletar/{nome}")
    public ResponseEntity<Void> deletar(@PathVariable String nome){
        alimentosService.deletar(nome);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }
}
