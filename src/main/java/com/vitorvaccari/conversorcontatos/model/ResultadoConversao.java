package com.vitorvaccari.conversorcontatos.model;

public record ResultadoConversao(String vcf, int gerados, int duplicados, int semNome, int telefoneInvalido) {

}
