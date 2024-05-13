package com.gabriel.apiplantoes.dtos;

import com.gabriel.apiplantoes.medico.Especialidade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record CadastroMedico(
        @NotBlank
        String nomeMedico,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotEmpty
        List<Long> idsUnidades) {
}
