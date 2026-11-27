package com.vitorvaccari.conversorcontatos.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service 
public class NomeNormalizer {
    public Optional<String> normalizar(String nomeBruto) {

        if (nomeBruto == null) {
            // Tratando valores vazios
            return Optional.empty();
        }

        String nomeLimpo = nomeBruto.replaceAll("\\s+"," ")
                                    .replaceAll("^[\\s'\"]+|[\\s'\"]+$", "");

        if (nomeLimpo.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(nomeLimpo);

    }

}
