package com.gabriel.apiplantoes.model;

import jakarta.validation.constraints.NotNull;

public record AlteracaoUnidadeAssistencial(
        @NotNull
        Long id,
        String nomeUnidadeAssistencial) {
}
