package com.gabriel.apiplantoes.dtos;

import com.gabriel.apiplantoes.medico.Status;

import java.util.List;

public record ListagemMedico(
        Long id,
        String nomeMedico,
        String crm,
        List<EspecialidadeDTO> especialidades,
        Status status,
        List<Long> idsUnidades) {
}
