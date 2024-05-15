package com.gabriel.apiplantoes.domain.dtos;

import jakarta.validation.constraints.NotNull;

public record AlteracaoUnidadeAssistencial(
        @NotNull
        Long id,
        String nomeUnidadeAssistencial) {
}
