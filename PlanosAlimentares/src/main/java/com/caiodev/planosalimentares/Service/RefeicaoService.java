package com.caiodev.planosalimentares.Service;

import com.caiodev.planosalimentares.Model.Repository.RefeicaoRepository;
import org.springframework.stereotype.Service;

@Service
public class RefeicaoService {
    private RefeicaoRepository refeicaoRepository;

    public RefeicaoService(RefeicaoRepository refeicaoRepository) {
        this.refeicaoRepository = refeicaoRepository;
    }

}
