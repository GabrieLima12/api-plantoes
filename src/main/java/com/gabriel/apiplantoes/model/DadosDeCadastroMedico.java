package com.gabriel.apiplantoes.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosDeCadastroMedico(
        @NotBlank
        String nomeMedico,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotBlank
        String nomeUnidadeAssistencial
) {
}
