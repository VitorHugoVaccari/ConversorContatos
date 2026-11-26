package com.vitorvaccari.conversorcontatos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vitorvaccari.conversorcontatos.model.Contato;

@Service
public class VcfGeradorService {

    private String escapar(String nome) {
        return nome.replace("\\", "\\\\")
                   .replace(";", "\\;")
                   .replace(",", "\\,");
                   
    }

    /**
     * Gera o texto vCard 3.0.
     * Espera contatos já normalizados e válidos: quem chama garante isso.
     */
    public String gerar(List<Contato> contatos) {
        StringBuilder sb = new StringBuilder();

        for (Contato contato : contatos) {

            sb.append("BEGIN:VCARD").append("\r\n");
            sb.append("VERSION:3.0").append("\r\n");
            sb.append("N:;").append(escapar(contato.nome())).append(";;;").append("\r\n");
            sb.append("FN:").append(escapar(contato.nome())).append("\r\n");
            sb.append("TEL;TYPE=CELL:").append(contato.telefone()).append("\r\n");
            sb.append("END:VCARD").append("\r\n");

        }
        String texto = sb.toString();

        return texto;
    }
}
