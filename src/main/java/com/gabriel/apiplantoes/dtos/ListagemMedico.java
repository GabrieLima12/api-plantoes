package com.gabriel.apiplantoes.dtos;

import com.gabriel.apiplantoes.medico.Status;

import java.util.List;

public record ListagemMedico(
        Long id,
        String nomeMedico,
        String crm,
        Long idEspecialidadePrimaria,
        Long idEspecialidadeSecundaria,
        Status status,
        List<Long> idsUnidades) {
}
