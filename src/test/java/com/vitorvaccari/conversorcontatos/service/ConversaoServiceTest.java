package com.vitorvaccari.conversorcontatos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.vitorvaccari.conversorcontatos.model.Contato;
import com.vitorvaccari.conversorcontatos.model.ResultadoConversao;

public class ConversaoServiceTest {
    private ConversaoService servico;

    @BeforeEach
    void setUp() {
        this.servico = new ConversaoService(new LeitorPlanilhaService(), new NomeNormalizer(), new TelefoneNormalizer(),
                new VcfGeradorService());
    }

    private InputStream planilhaContatos(List<Contato> contatos) throws IOException {
        try (Workbook wb = new XSSFWorkbook()) {
            Sheet aba = wb.createSheet("Contatos");

            Row cabecalho = aba.createRow(0);
            cabecalho.createCell(0).setCellValue("Nome");
            cabecalho.createCell(0).setCellValue("Telefone");

            int numeroDaLinha = 1;
            for (Contato contato : contatos) {
                Row linha = aba.createRow(numeroDaLinha);
                linha.createCell(0).setCellValue(contato.nome());
                linha.createCell(1).setCellValue(contato.telefone());
                numeroDaLinha++;
            }

            ByteArrayOutputStream saida = new ByteArrayOutputStream();
            wb.write(saida);
            return new ByteArrayInputStream(saida.toByteArray());

        }
    }

    @Test 
    void deveAceitarDoisContatos() throws Exception {
        List<Contato> contatos = List.of(new Contato("João", "(31) 99999-0001"),
                                         new Contato("Maria", "(31) 99999-0002"));

        ResultadoConversao resultado = servico.converter(planilhaContatos(contatos));

        assertEquals(2, resultado.gerados());

        assertEquals(0, resultado.duplicados());
        assertEquals(0, resultado.semNome());
        assertEquals(0, resultado.telefoneInvalido());

        assertTrue(resultado.vcf().contains("FN:João"));
        assertTrue(resultado.vcf().contains("FN:Maria"));
    }

}
