package com.vitorvaccari.conversorcontatos.service;

import java.util.Optional;

public class NomeNormalizer {
    public Optional<String> normalizar(String nomeBruto) {

        if (nomeBruto == null || nomeBruto == "" || nomeBruto == "''") {
            // Tratando valores vazios
            return Optional.empty();
        }

        String nomeLimpo = nomeBruto.replaceAll("\\s+"," ")
                                    .replaceAll("^[\\s'\"]+|[\\s'\"]+$", "");

        return Optional.of(nomeLimpo);

    }

}
