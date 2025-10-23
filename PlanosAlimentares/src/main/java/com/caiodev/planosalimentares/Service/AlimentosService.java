package com.caiodev.planosalimentares.Service;

import com.caiodev.planosalimentares.DTO.AlimentosDTO;
import com.caiodev.planosalimentares.Exception.AlimentoNotFoundExeption;
import com.caiodev.planosalimentares.Model.Entity.Alimentos;
import com.caiodev.planosalimentares.Model.Repository.AlimentosRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlimentosService {
    private AlimentosRepository alimentosRepository;

    public AlimentosService(AlimentosRepository alimentosRepository) {
        this.alimentosRepository = alimentosRepository;
    }
    @Transactional
    public Alimentos cadastrar(Alimentos alimentos){
        return alimentosRepository.save(alimentos);
    }

    @Transactional
    public Alimentos alterar(String nome,AlimentosDTO alimentosDTO){
        Optional <Alimentos> buscar = alimentosRepository.findByNomeIgnoreCase(nome);

        if(buscar.isEmpty()){
            throw new AlimentoNotFoundExeption("Alimento não encontrado");
        }
        
        Alimentos alimentos = buscar.get();
        alimentos.setNome(alimentosDTO.nome());
        alimentos.setQuantidade(alimentosDTO.quantidade());
        return alimentosRepository.save(alimentos);

    }
    @Transactional
    public void deletar(String nome){
        Optional<Alimentos> buscar =  alimentosRepository.findByNomeIgnoreCase(nome);

        if(buscar.isEmpty()) throw new AlimentoNotFoundExeption("Alimento não encontrado");

        Alimentos alimentos = buscar.get();
        alimentosRepository.delete(alimentos);

    }

}
