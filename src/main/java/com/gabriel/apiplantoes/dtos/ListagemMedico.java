package com.gabriel.apiplantoes.dtos;

import com.gabriel.apiplantoes.medico.Especialidade;
import com.gabriel.apiplantoes.medico.Status;

import java.util.List;

public record ListagemMedico(
        Long id,
        String nomeMedico,
        String crm,
        Especialidade especialidade,
        Status status,
        List<Long> idsUnidades) {
}
