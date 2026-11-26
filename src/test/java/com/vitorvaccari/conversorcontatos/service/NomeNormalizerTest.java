package com.vitorvaccari.conversorcontatos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NomeNormalizerTest {
    private NomeNormalizer normalizer;

    @BeforeEach 
    void setUp() {
        this.normalizer = new NomeNormalizer();
    }

    @Test 
    void deveNormalizarAspasSimplesDosDoisLados() {
        assertEquals(Optional.of("Carlos Teste"), normalizer.normalizar("'Carlos Teste'"));
    }

    @Test 
    void deveNormalizarAspasDuplasDosDoisLados() {
        assertEquals(Optional.of("Pedro Teste"), normalizer.normalizar("\"Pedro Teste\""));
    }

    @Test 
    void deveNormalizarAspasComEspacosMisuradosNasPontas() {
        assertEquals(Optional.of("Ana Teste"), normalizer.normalizar("'  Ana Teste  '"));
    }

    @Test 
    void deveNormalizarAspaSimplesDeUmLado() {
        assertEquals(Optional.of("Paulo"), normalizer.normalizar("'Paulo"));
    }

    @Test 
    void deveNormalizarEspacosExtrasNoMeio() {
        assertEquals(Optional.of("Maria Souza"), normalizer.normalizar("Maria  Souza"));
    }

    @Test 
    void deveAceitarNomeComApostrofo() {
        assertEquals(Optional.of("João D'Avila"), normalizer.normalizar("João D'Avila"));
    }

    @Test 
    void deveRetornarVazioQuandoSoAspas() {
        assertTrue(normalizer.normalizar("''").isEmpty());
    }

    @Test 
    void deveRetornarVazioQuandoNomeNull() {
        assertTrue(normalizer.normalizar(null).isEmpty());
    }

}
