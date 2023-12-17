package com.gabriel.apiplantoes.model;

import jakarta.validation.constraints.NotNull;

public record AlteracaoStatusMedico(
        @NotNull
        Long id,
        Status status) {
}
