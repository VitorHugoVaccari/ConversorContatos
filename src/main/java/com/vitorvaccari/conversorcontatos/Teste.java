package com.vitorvaccari.conversorcontatos;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import com.vitorvaccari.conversorcontatos.model.Contato;
import com.vitorvaccari.conversorcontatos.service.LeitorPlanilhaService;

public class Teste {
    public static void main(String[] args) throws Exception {

        try (InputStream in = new FileInputStream(
                "");) {

            LeitorPlanilhaService contatos = new LeitorPlanilhaService();

            List<Contato> lista = contatos.ler(in);

            System.out.println(lista);

        }
    }

}
