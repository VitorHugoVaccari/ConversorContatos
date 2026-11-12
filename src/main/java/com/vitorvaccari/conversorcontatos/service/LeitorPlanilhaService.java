package com.vitorvaccari.conversorcontatos.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import com.vitorvaccari.conversorcontatos.model.Contato;

@Service
public class LeitorPlanilhaService {

    public List<Contato> ler(InputStream arquivo) throws IOException {
        try (Workbook wb = WorkbookFactory.create(arquivo)) {

            Sheet aba = wb.getSheetAt(0);
            DataFormatter formatador = new DataFormatter();
            List<Contato> contatos = new ArrayList<>();

            for (Row linha : aba) {
                if (linha.getRowNum() == 0) {
                    continue;
                }

                String nome = formatador.formatCellValue(linha.getCell(0));
                String telefone = formatador.formatCellValue(linha.getCell(1));

                if (nome.isBlank() && telefone.isBlank()) {
                    continue;
                }

                contatos.add(new Contato(nome, telefone));

            }
            return contatos;
        }
    }
}
