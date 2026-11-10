package com.vitorvaccari.conversorcontatos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TelefoneNormalizerTest {

    private TelefoneNormalizer normalizer;

    @BeforeEach
    void setUp() {
        this.normalizer = new TelefoneNormalizer();
    }

    @Test
    void deveRetornarVazioQuandoNumeroAleatorio() {
        assertTrue(normalizer.normalizar("123456789012").isEmpty());
    }

    @Test
    void deveNormalizarTelefoneComParentesesETraco() {
        assertEquals(Optional.of("+5531999999999"), normalizer.normalizar("(31) 99999-9999"));
    }

    @Test
    void deveNormalizarTelefoneComParentesesTracoESemONonoDigito() {
        assertEquals(Optional.of("+5531999999999"), normalizer.normalizar("(31) 99999-9999"));
    }

    @Test
    void deveNormalizarTelefoneComDDIETraco() {
        assertEquals(Optional.of("+5531999999999"), normalizer.normalizar("+55 31 99999-9999"));
    }

    @Test
    void deveNormalizarTelefoneSemNonoDigito() {
        assertEquals(Optional.of("+553199999999"), normalizer.normalizar("3199999999"));
    }

    @Test
    void deveNormalizarTelefoneComDDD55() {
        assertEquals(Optional.of("+555599999999"), normalizer.normalizar("(55) 9999-9999"));
    }

    @Test
    void deveRetornarVazioQuandoTelefoneSemDDD() {
        assertTrue(normalizer.normalizar("99999-9999").isEmpty());
    }

    @Test
    void deveRetornarVazioQuandoTelefoneTiverApenasLetras() {
        assertTrue(normalizer.normalizar("abc").isEmpty());
    }

    @Test
    void deveRetornarVazioQuandoTelefoneTiverApenasEspacos() {
        assertTrue(normalizer.normalizar("   ").isEmpty());
    }

    @Test
    void deveRetornarVazioQuandoTelefoneVazio() {
        assertTrue(normalizer.normalizar("").isEmpty());
    }

    @Test
    void deveRetornarVazioQuandoTelefoneNull() {
        assertTrue(normalizer.normalizar(null).isEmpty());
    }
}
