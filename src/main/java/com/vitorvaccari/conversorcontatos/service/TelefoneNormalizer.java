package com.vitorvaccari.conversorcontatos.service;

import java.util.Optional;

public class TelefoneNormalizer {
    public Optional<String> normalizar(String telefoneBruto) {
        // Tratando valores vazios
        if (telefoneBruto == null) {
            return Optional.empty();
        }

        // Limpando qualquer valor que não seja digito/numero
        String telefoneLimpo = telefoneBruto.replaceAll("\\D", "");

        // Tratando DDI e DDD
        if (telefoneLimpo.length() >= 10 && telefoneLimpo.length() < 12) {
            telefoneLimpo = "+55" + telefoneLimpo;
            return Optional.of(telefoneLimpo);

        } else if (telefoneLimpo.length() >= 12 && telefoneLimpo.length() <= 13 && telefoneLimpo.startsWith("55")) {
            telefoneLimpo = "+" + telefoneLimpo;
            return Optional.of(telefoneLimpo);

        } else {
            return Optional.empty();

        }
    }
}
