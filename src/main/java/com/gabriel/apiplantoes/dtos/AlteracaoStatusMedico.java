package com.gabriel.apiplantoes.dtos;

import com.gabriel.apiplantoes.medico.Status;
import jakarta.validation.constraints.NotNull;

public record AlteracaoStatusMedico(
        @NotNull
        Long id,
        Status status) {
}
