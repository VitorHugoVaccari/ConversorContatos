package com.vitorvaccari.conversorcontatos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

public class LeitorPlanilhaServiceTest {

    private LeitorPlanilhaService leitor;

    @BeforeEach
    void setUp() {
        this.leitor = new LeitorPlanilhaService();
    }

    private InputStream paraStream(Workbook wb) throws IOException {
        ByteArrayOutputStream saida = new ByteArrayOutputStream();
        wb.write(saida);
        return new ByteArrayInputStream(saida.toByteArray());
    }

    @Test
    void devePularOCabecalho() throws Exception {

        try (Workbook wb = new XSSFWorkbook()) {

            Sheet aba = wb.createSheet("Contatos");

            Row cabecalho = aba.createRow(0);
            cabecalho.createCell(0).setCellValue("Nome");
            cabecalho.createCell(1).setCellValue("Telefone");

            Row linha1 = aba.createRow(1);
            linha1.createCell(0).setCellValue("João");
            linha1.createCell(1).setCellValue("+55 (31) 99999-9999");

            List<Contato> lista = leitor.ler(paraStream(wb));

            assertEquals(1, lista.size());
            assertEquals("João", lista.get(0).nome());
        }
    }

    @Test
    void devePularLinhaVazia() throws Exception {

        try (Workbook wb = new XSSFWorkbook()) {

            Sheet aba = wb.createSheet("Contatos");

            Row cabecalho = aba.createRow(0);
            cabecalho.createCell(0).setCellValue("Nome");
            cabecalho.createCell(1).setCellValue("Telefone");

            aba.createRow(1).createCell(0).setCellValue("João");
            aba.createRow(2);
            aba.createRow(3).createCell(0).setCellValue("Maria");
            

            List<Contato> lista = leitor.ler(paraStream(wb));

            assertEquals(2, lista.size());
        }
    }

    @Test
    void deveAceitarTelefoneVazio() throws Exception {

        try (Workbook wb = new XSSFWorkbook()) {

            Sheet aba = wb.createSheet("Contatos");

            Row cabecalho = aba.createRow(0);
            cabecalho.createCell(0).setCellValue("Nome");
            cabecalho.createCell(1).setCellValue("Telefone");

            aba.createRow(1).createCell(0).setCellValue("João");

            List<Contato> lista = leitor.ler(paraStream(wb));

            assertEquals("", lista.get(0).telefone());
        }
    }

    @Test
    void deveVoltarTextoQuandoNumeroGrande() throws Exception {

        try (Workbook wb = new XSSFWorkbook()) {

            Sheet aba = wb.createSheet("Contatos");

            Row cabecalho = aba.createRow(0);
            cabecalho.createCell(0).setCellValue("Nome");
            cabecalho.createCell(1).setCellValue("Telefone");

            Row linha1 = aba.createRow(1);
            linha1.createCell(0).setCellValue("João");
            linha1.createCell(1).setCellValue(5531955555555.0);

            List<Contato> lista = leitor.ler(paraStream(wb));

            assertEquals("5531955555555.0", lista.get(0).telefone());
        }
    }
}
