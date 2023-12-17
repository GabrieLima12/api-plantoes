package com.gabriel.apiplantoes.model;

import jakarta.validation.constraints.NotNull;

public record DadosDeAlteracaoUnidadeAssistencial(
        @NotNull
        Long id,
        String nomeUnidadeAssistencial
) {
}
