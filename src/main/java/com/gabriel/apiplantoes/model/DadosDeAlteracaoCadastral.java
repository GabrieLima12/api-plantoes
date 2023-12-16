package com.gabriel.apiplantoes.model;

import jakarta.validation.constraints.NotNull;

public record DadosDeAlteracaoCadastral(
        @NotNull
        Long id,
        String nomeUnidadeAssistencial
) {
}
