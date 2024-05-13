package com.gabriel.apiplantoes.dtos;

import jakarta.validation.constraints.NotNull;

public record AlteracaoUnidadeAssistencial(
        @NotNull
        Long id,
        String nomeUnidadeAssistencial) {
}
