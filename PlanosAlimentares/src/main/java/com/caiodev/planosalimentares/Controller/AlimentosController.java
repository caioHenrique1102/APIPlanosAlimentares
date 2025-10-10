package com.caiodev.planosalimentares.Controller;

import com.caiodev.planosalimentares.DTO.AlimentosDTO;
import com.caiodev.planosalimentares.Model.Entity.Alimentos;
import com.caiodev.planosalimentares.Service.AlimentosService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alimento")
public class AlimentosController {
    private AlimentosService alimentosService;

    public AlimentosController(AlimentosService alimentosService) {
        this.alimentosService = alimentosService;
    }

    @PostMapping("/criar")
    public ResponseEntity<Alimentos> criar(@RequestBody AlimentosDTO alimentosDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(alimentosService.cadastrar(new Alimentos(alimentosDTO)));
    }
}
