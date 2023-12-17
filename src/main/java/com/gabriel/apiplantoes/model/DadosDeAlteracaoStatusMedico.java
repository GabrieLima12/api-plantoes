package com.gabriel.apiplantoes.model;

import jakarta.validation.constraints.NotNull;

public record DadosDeAlteracaoStatusMedico(
        @NotNull
        Long id,
        Status status
) {
}
