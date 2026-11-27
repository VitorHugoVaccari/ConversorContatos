package com.vitorvaccari.conversorcontatos.service;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;

import com.vitorvaccari.conversorcontatos.model.ResultadoConversao;

@Service 
public class ConversaoService {
    private final LeitorPlanilhaService leitorPlanilha;
    private final NomeNormalizer nomeNormalizer;
    private final TelefoneNormalizer telefoneNormalizer;
    private final VcfGeradorService vcfGerador;

    public ConversaoService(LeitorPlanilhaService leitorPlanilha, NomeNormalizer nomeNormalizer,
            TelefoneNormalizer telefoneNormalizer, VcfGeradorService vcfgerador) {

        this.leitorPlanilha = leitorPlanilha;
        this.nomeNormalizer = nomeNormalizer;
        this.telefoneNormalizer = telefoneNormalizer;
        this.vcfGerador = vcfgerador;

    }

    public ResultadoConversao converter(InputStream arquivo) throws IOException {
        return null;
    }

}
