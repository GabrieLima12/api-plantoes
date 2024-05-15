package com.gabriel.apiplantoes.domain.dtos;

import com.gabriel.apiplantoes.domain.medico.Status;
import jakarta.validation.constraints.NotNull;

public record AlteracaoStatusMedico(
        @NotNull
        Long id,
        Status status) {
}
