package com.caiodev.planosalimentares.Controller;
import com.caiodev.planosalimentares.DTO.Request.AlimentosDTORequest;
import com.caiodev.planosalimentares.Model.Entity.Alimentos;
import com.caiodev.planosalimentares.Service.AlimentosService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.owasp.html.PolicyFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@SuppressWarnings("JvmTaintAnalysis")
@CrossOrigin
@RestController
@RequestMapping("/alimento")
public class AlimentosController {
    private final PolicyFactory policyFactory;
    private final AlimentosService alimentosService;

    public AlimentosController(PolicyFactory policyFactory, AlimentosService alimentosService) {
        this.policyFactory = policyFactory;
        this.alimentosService = alimentosService;
    }
    @Operation(summary = "Cria um alimento" ,
            description = "Salva alimentos no banco de dados, com informações como nome e quantidade")
    @PostMapping("/criar")
    public ResponseEntity<Alimentos> criar( @Valid @RequestBody AlimentosDTORequest alimentosDTO){

        String nomeLimpo = policyFactory.sanitize(alimentosDTO.nome());
        String quantidadeLimpa = policyFactory.sanitize(alimentosDTO.quantidade());
        Alimentos alimentos = new Alimentos();
        alimentos.setNome(nomeLimpo);
        alimentos.setQuantidade(quantidadeLimpa);

        return ResponseEntity.status(HttpStatus.CREATED).body(alimentosService.cadastrar(alimentos));
    }
@Operation(summary = "Altera os aliementos" ,
    description = "Altera todas as informações dos alimentos e salva no banco de dados")
    @PutMapping("/alterar/{nome}")
    public ResponseEntity<Alimentos> alterar(@PathVariable String nome, @Valid @RequestBody AlimentosDTORequest alimentosDTO){
        String nomePesquisaLimpo = policyFactory.sanitize(nome);
        String nomeLimpo = policyFactory.sanitize(alimentosDTO.nome());
        String quantidadeLimpa = policyFactory.sanitize(alimentosDTO.quantidade());

        Alimentos alimentos = new Alimentos();
        alimentos.setNome(nomeLimpo);
        alimentos.setQuantidade(quantidadeLimpa);

        return ResponseEntity.status(HttpStatus.OK).body(alimentosService.alterar(nomePesquisaLimpo, alimentos));
    }
@Operation(summary = "Deleta alimentos",
    description = "Deleta alimentos com base no nome do alimento e atualiza o banco de dados")
    @DeleteMapping("/deletar/{nome}")
    public ResponseEntity<Void> deletar(@PathVariable String nome){
        alimentosService.deletar(nome);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }
}
