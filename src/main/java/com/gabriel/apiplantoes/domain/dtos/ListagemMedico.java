package com.gabriel.apiplantoes.domain.dtos;

import com.gabriel.apiplantoes.domain.endereco.Endereco;
import com.gabriel.apiplantoes.domain.medico.Status;

import java.util.List;

public record ListagemMedico(
        Long id,
        String nomeMedico,
        String crm,
        Long idEspecialidadePrimaria,
        Long idEspecialidadeSecundaria,
        Status status,
        List<Long> idsUnidades,
        Endereco endereco) {
}
