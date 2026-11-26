package com.vitorvaccari.conversorcontatos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.vitorvaccari.conversorcontatos.model.Contato;

public class VcfGeradorServiceTest {

    private VcfGeradorService gerador;

    @BeforeEach 
    void setUp() {
        this.gerador = new VcfGeradorService();
    }

    @Test 
    void deveGerarTextoVazioParaListaVazia() {
        assertEquals("", gerador.gerar(List.of()));
    }

    @Test 
    void deveGerarUmVcard() {
        List<Contato> contatos = List.of(new Contato("João", "+5531999999999"));

        String esperado = "BEGIN:VCARD\r\n"
                        + "VERSION:3.0\r\n"
                        + "N:;João;;;\r\n"
                        + "FN:João\r\n"
                        + "TEL;TYPE=CELL:+5531999999999\r\n"
                        + "END:VCARD\r\n";

        assertEquals(esperado, gerador.gerar(contatos));
    }

    @Test 
    void deveGerarUmVcardDoisContatos() {
        List<Contato> contatos = List.of(new Contato("João", "+5531888888888"),
                                         new Contato("Maria", "+5531777777777"));

        String esperado = "BEGIN:VCARD\r\n"
                        + "VERSION:3.0\r\n"
                        + "N:;João;;;\r\n"
                        + "FN:João\r\n"
                        + "TEL;TYPE=CELL:+5531888888888\r\n"
                        + "END:VCARD\r\n"
                        + "BEGIN:VCARD\r\n"
                        + "VERSION:3.0\r\n"
                        + "N:;Maria;;;\r\n"
                        + "FN:Maria\r\n"
                        + "TEL;TYPE=CELL:+5531777777777\r\n"
                        + "END:VCARD\r\n";

        assertEquals(esperado, gerador.gerar(contatos));
    }

    @Test 
    void deveAceitarCaracteresEspeciaisNoNome() {
        List<Contato> contatos = List.of(new Contato("Silva; João, Jr.", "+5531999990000"));

        String esperado = "BEGIN:VCARD\r\n"
                        + "VERSION:3.0\r\n"
                        + "N:;Silva\\; João\\, Jr.;;;\r\n"
                        + "FN:Silva\\; João\\, Jr.\r\n"
                        + "TEL;TYPE=CELL:+5531999990000\r\n"
                        + "END:VCARD\r\n";

        assertEquals(esperado, gerador.gerar(contatos));

    }
}
